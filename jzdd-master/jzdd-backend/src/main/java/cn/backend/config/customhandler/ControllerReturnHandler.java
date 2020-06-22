package cn.backend.config.customhandler;

import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.jsonfilter.JSONFilter;
import cn.zdwl.common.jsonfilter.JSONFilters;
import cn.zdwl.common.jsonfilter.JSONSerializer;
import cn.zdwl.common.message.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 自定义过滤，处理多余字段
 */
@Configuration
public class ControllerReturnHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 如果有我们自定义的 JSON 注解 就用我们这个Handler 来处理
        boolean hasJsonAnno = returnType.getMethodAnnotation(JSONFilter.class) != null ||
                returnType.getMethodAnnotation(JSONFilters.class) != null ||
                returnType.getMethodAnnotation(CustomResult.class) != null;
        return hasJsonAnno;
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled(true);

        // 获得注解并执行filter方法 最后返回
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //获取注解
        Annotation[] annos = returnType.getMethodAnnotations();
        JSONSerializer jsonSerializer = new JSONSerializer();
        final Boolean[] useResult = {true};
        Arrays.asList(annos).forEach(a -> {
            // 解析注解，设置过滤条件
            if (a instanceof JSONFilter) {
                jsonSerializer.filter((JSONFilter) a);
            }
            if (a instanceof JSONFilters) {
                JSONFilters jsonValues = (JSONFilters) a;
                Arrays.asList(jsonValues.value()).forEach(jsonSerializer::filter);
            }
            //使用过滤条件
            if (a instanceof CustomResult) {
                useResult[0] = ((CustomResult) a).value();
            }
        });
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        //判断是否使用自定义对象
        if (useResult[0]) {
            Result result = new Result();
            result.setData(returnValue);
            returnValue = result;
        }

        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
    }
}
