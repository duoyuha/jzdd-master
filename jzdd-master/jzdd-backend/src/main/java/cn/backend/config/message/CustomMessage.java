package cn.backend.config.message;

import cn.zdwl.common.message.AppHttpMessage;
import cn.zdwl.common.message.AppMessageLevel;
import cn.zdwl.common.translate.TranslateService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.http.HttpStatus;

/**
 * Created by sunkw on 2017/11/24.
 */
public enum CustomMessage implements AppHttpMessage.QuirkEnum {


    //数据不存在
    NO_DATA_FIND(
            HttpStatus.BAD_REQUEST
            , "MST0001"
            , AppMessageLevel.ERROR),
    //数据不存在
    DATA_ALREADY_EXIST(
            HttpStatus.BAD_REQUEST
            , "MST0002"
            , AppMessageLevel.ERROR),
    //菜单设置错误
    MENU_SET_ERROR(
            HttpStatus.BAD_REQUEST
            , "MST0003"
            , AppMessageLevel.ERROR),
    //推送消息失败
    PUSH_MESSAGE_FAIL(
            HttpStatus.BAD_REQUEST
            , "MST0004"
            , AppMessageLevel.ERROR),
    //合同编号已存在
    CONTRACT_CODE_EXIST(
    HttpStatus.BAD_REQUEST
            , "MST0005"
            , AppMessageLevel.ERROR),
    //不能创建订单
    CAN_NOT_CREATE_ORDER(
            HttpStatus.BAD_REQUEST
            , "MST0006"
            , AppMessageLevel.ERROR),
    //无法操作
    CAN_NOT_OPERATE(
            HttpStatus.BAD_REQUEST
            , "MST0007"
            , AppMessageLevel.ERROR),
    // 结算单未找到
    SETTLE_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST0008"
            , AppMessageLevel.ERROR),

    // 安装工单编号不能为空
    INSTALL_ORDER_NO_NOT_EMPTY(
            HttpStatus.BAD_REQUEST
            , "MST0009"
            , AppMessageLevel.ERROR),

    // 安装工单未找到
    INSTALL_ORDER_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST0009"
            , AppMessageLevel.ERROR),

    // 结算单明细未找到
    SETTLE_DETAIL_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST00010"
            , AppMessageLevel.ERROR),

    // 服务商未找到
    SUPPLIER_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST00011"
            , AppMessageLevel.ERROR),
    //作废权限
    CANCEL_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST00012"
            , AppMessageLevel.ERROR),

    // 安装工单已提交结算
    INSTALL_ORDER_SETTLED(
            HttpStatus.BAD_REQUEST
            , "MST00012"
            , AppMessageLevel.ERROR),

    // 不能审核
    CAN_NOT_CHECK(
            HttpStatus.BAD_REQUEST
            , "MST00013"
            , AppMessageLevel.ERROR),
    // 不能回退
    CAN_NOT_ROLLBACK(
            HttpStatus.BAD_REQUEST
            , "MST00013"
            , AppMessageLevel.ERROR),
    // 时间校验
    TIME_ERROR(
            HttpStatus.BAD_REQUEST
            , "MST00014"
            , AppMessageLevel.ERROR),
    //数据有误
    DATA_ERROR(
            HttpStatus.BAD_REQUEST
            , "MST00015"
            , AppMessageLevel.ERROR),
    //签名错误
    SIGN_ERROR(
            HttpStatus.BAD_REQUEST
            , "MST00016"
            , AppMessageLevel.ERROR),
    //充电桩编号不能重复
    PILE_CODE_CANNOT_RSD(
            HttpStatus.BAD_REQUEST
            , "MST00017"
            , AppMessageLevel.ERROR),
    //不满足送桩政策
    DO_NOT_MEET_PILE_POLICY(
            HttpStatus.BAD_REQUEST
            , "MST00018"
            , AppMessageLevel.ERROR),
    RECEIVE_STATUS(
            HttpStatus.BAD_REQUEST
            , "MST00019"
            , AppMessageLevel.ERROR),
    CANCEL_CANNOT_AGAIN(
            HttpStatus.BAD_REQUEST
            , "MST00020"
            , AppMessageLevel.ERROR),
    //自动派单比例综合不能超过100
    AUTO_ORDER_PROP_CANNOT_OVER_HUNDRED(
            HttpStatus.BAD_REQUEST
            , "MST00021"
            , AppMessageLevel.ERROR),
    //车型配置未找到
    VEHICLE_DETAIL_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST00022"
            , AppMessageLevel.ERROR),
    //合同资费未找到
    CONTRACT_FEE_NOT_FOUND(
            HttpStatus.BAD_REQUEST
            , "MST00023"
            , AppMessageLevel.ERROR),
    //创建时间空
    FROM_TIME_NULL(
            HttpStatus.BAD_REQUEST
            , "MST00024"
            , AppMessageLevel.ERROR),
    /*//结束时间空
    TO_TIME_NULL(
            HttpStatus.BAD_REQUEST
            , "MST00025"
            , AppMessageLevel.ERROR),*/
    ;

    CustomMessage(
            HttpStatus status,
            String messageId,
            AppMessageLevel messageLevel) {
        this.status = status;
        this.messageId = messageId;
        this.messageLevel = messageLevel;
    }

    /**
     * HTTP状态
     */
    @Getter(onMethod = @__({@Override}))
    private HttpStatus status;

    /**
     * 消息id
     */
    @Getter(onMethod = @__({@Override}))
    private String messageId;

    /**
     * 消息登记
     */
    @Getter(onMethod = @__({@Override}))
    private AppMessageLevel messageLevel;

    /**
     * 国际化翻译
     *
     * @param ts
     * @return 国际化翻译
     */
    public TranslatedMessage toTranslatedMessage(TranslateService ts) {
        return new TranslatedMessage(this, ts);
    }

    public static class TranslatedMessage {

        @Delegate(types = Parent.class)
        private final CustomMessage customMessage;
        private final TranslateService ts;

        private interface Parent {
            HttpStatus getStatus();

            String getMessageId();

            AppMessageLevel getMessageLevel();
        }

        public String getMessageSubject() {
            return ts.translate(customMessage.getMessageSubject());
        }

        public String getMessageBodyTemplate() {
            return ts.translate(customMessage.getMessageBodyTemplate());
        }

        @JsonProperty
        public String getMessageBody() {
            return getMessageBodyTemplate();
        }

        /* package private */
        TranslatedMessage(CustomMessage customMessage, TranslateService ts) {
            this.ts = ts;
            this.customMessage = customMessage;
        }
    }
}
