package cn.backend.config.customhandler;

import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.exception.ControllerErrors;
import cn.zdwl.common.exception.MessageResponseList;
import cn.zdwl.common.message.AppHttpMessage;
import cn.zdwl.common.message.AppMessage;
import cn.zdwl.common.message.ErrorResult;
import cn.zdwl.common.message.MessageResponse;
import cn.zdwl.common.translate.Translatable;
import cn.zdwl.common.translate.TranslateService;
import cn.zdwl.common.util.IdUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;

/**
 * 全局异常消息处理
 * 并返回统一格式的消息体
 * AppException
 * RuntimeException
 * ConstraintViolationException
 * MethodArgumentNotValidException
 * BindException
 * Created by sunkw on 2017/11/24.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 日志
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 转换
     */
    @Autowired
    private TranslateService ts;

    /**
     * spring内部异常处理重写
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        if (status == HttpStatus.BAD_REQUEST) {
            return handleBadRequest(ex, headers, status, request);
        }

        MessageResponseList msgList = new MessageResponseList(ex, status, request);
        logger.error(msgList.toString(), ex);
        return handleResponseEntity(msgList, headers, status);
    }

    /**
     * AppException处理。
     *
     * @param request
     * @param ex      AppException
     * @return 异常响应
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAppException(HttpServletRequest request, AppException ex) {
        return handleError(request, ex.getError(), ex.getStatus(), ex, ex.getArgs());
    }


    /**
     * RuntimeException的异常处理。
     *
     * @param request
     * @param ex      RuntimeException
     * @return 异常响应
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, RuntimeException ex) {
        if (ex instanceof ConstraintViolationException) {
            return handleMethodArgumentNotValidException((ConstraintViolationException) ex, request);
        }
        AppHttpMessage error = ControllerErrors.UNEXPECTED;
        return handleError(request, error, error.getStatus(), ex, IdUtils.getUniqueId());
    }

    /**
     * App内部异常处理
     *
     * @param request
     * @param error
     * @param status  HTTP
     * @param ex
     * @param args
     * @return
     */
    private ResponseEntity<Object> handleError(HttpServletRequest request,
                                               AppMessage error,
                                               HttpStatus status,
                                               Exception ex,
                                               Object... args) {

        String messageBody = MessageFormat.format(ts.translate(error.getMessageBodyTemplate()), args);
        MessageResponse msgResponse = buildError(request.getRequestURI(), error, ex, messageBody);

        String message = msgResponse.getMessageBody();
        if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error(message, ex);
        } else {
            log(message, ex);
        }

        if (status == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity<>(status);
        }

        //列表
        MessageResponseList msgList = new MessageResponseList();
        msgList.add(msgResponse);

        return handleResponseEntity(msgList, new LinkedMultiValueMap<>(), status);
    }

    /**
     * badRequest处理
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    public ResponseEntity<Object> handleBadRequest(Exception ex,
                                                   HttpHeaders headers,
                                                   HttpStatus status,
                                                   WebRequest request) {

        if (ex instanceof MethodArgumentNotValidException) {
            return handleBindingResult(ex,((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors(),headers,status,request);
        }

        if (ex instanceof BindException) {
            return handleBindingResult(ex,((BindException) ex).getAllErrors(),headers,status,request);
        }

        AppHttpMessage error = ControllerErrors.BAD_REQUEST;
        MessageResponseList msgList = new MessageResponseList();

        MessageResponse msgResponse = buildError(request.getContextPath(),error,ex,error.getMessageBodyTemplate());
        msgList.add(msgResponse);
        log(msgList, ex);
        return handleResponseEntity(msgList, headers, status);

    }

    /**
     * bing异常处理
     * @param ex
     * @param objErrs
     * @param headers
     * @param status
     * @param request
     * @return
     */
    public ResponseEntity<Object> handleBindingResult(Exception ex,
                                                      List<ObjectError> objErrs,
                                                      HttpHeaders headers,
                                                      HttpStatus status,
                                                      WebRequest request) {

        AppHttpMessage error = ControllerErrors.VALIDATION_ERROR;
        MessageResponseList msgList = new MessageResponseList();
        for (ObjectError objErr : objErrs) {
            msgList.add(buildError(request.getContextPath(),error,ex,objErr.getDefaultMessage()));
        }
        log(msgList, ex);
        return handleResponseEntity(msgList, headers, status);
    }

    /**
     * 唯一约束异常
     * @param ex
     * @param request
     * @return
     */
    public ResponseEntity<Object> handleMethodArgumentNotValidException(ConstraintViolationException ex,
                                                                        HttpServletRequest request) {
        AppHttpMessage error = ControllerErrors.VALIDATION_ERROR;
        MessageResponseList msgList = new MessageResponseList();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            msgList.add(buildError(request.getContextPath(),error,ex,constraintViolation.getMessage()));
        }
        log(msgList, ex);
        return handleResponseEntity(msgList, new LinkedMultiValueMap<>(), error.getStatus());
    }


    /**
     * 生成 MessageResponse
     *
     * @param requestPath
     * @param error
     * @param ex
     * @param messageBody
     * @return
     */
    private MessageResponse buildError(String requestPath, AppMessage error, Exception ex, Object messageBody) {

        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setPath(requestPath);
        msgResponse.setMessageId(error.getMessageId());
        msgResponse.setMessageLevel(error.getMessageLevel());
        //翻译（国际化）内容
        msgResponse.setMessageSubject(ts.translate(error.getMessageSubject()));

        if(messageBody instanceof String){
            messageBody=((String) messageBody).replaceAll("[{}]", "");
            msgResponse.setMessageBody(ts.translate((String)messageBody));
        }

        if(messageBody instanceof Translatable){
            msgResponse.setMessageBody(ts.translate((Translatable)messageBody));
        }

        msgResponse.setMessageForDeveloper(ex.getMessage());
        msgResponse.setExceptionClass(ex.getClass().getName());

        return msgResponse;
    }


    private void log(MessageResponseList msgList, Throwable ex) {
        if (logger.isDebugEnabled()) {
            logger.info(msgList, ex);
        } else {
            logger.info(msgList);
        }
    }

    private void log(String msg, Throwable ex) {
        if (logger.isDebugEnabled()) {
            logger.info(msg, ex);
        } else {
            logger.info(msg);
        }
    }

    /**
     * 封装处理消息返回
     *
     * @param msgList
     * @param headers
     * @param status
     * @return
     */
    private ResponseEntity<Object> handleResponseEntity(MessageResponseList msgList,
                                                        MultiValueMap<String, String> headers,
                                                        HttpStatus status) {
        ErrorResult result = new ErrorResult();
        result.setStatus(status.value());
        result.setMsg(msgList.getMessageList().get(0).getMessageBody());
        result.setErrList(msgList.getMessageList());
        //为了每个请求都返回200
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, status);
    }

}
