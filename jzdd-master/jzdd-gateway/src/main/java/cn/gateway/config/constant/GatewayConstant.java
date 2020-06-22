package cn.gateway.config.constant;

public class GatewayConstant {

    /**
     * 时间戳有效范围
     */
    public static final int TIME_TICKET_RANGE = 60;
    /**
     * 偏移量
     */
    public static final String ENCRYPT_IV = "20180101Abcdefg!";
    /**
     * 加密的秘钥
     */
    public static final String ENCRYPT_KEY = "WXZhidaCX_V1.0.0";
    /**
     * 密码不能被解析
     */
    public static final String UNDECRYPT_PWD = "************";
    /**
     * token过期时间
     */
    public static final long TOKEN_EXP_TIME = 60 * 60 * 24 * 7 * 1000;

    /**
     * redis前缀
     */
    public static final String REDIS_PREFIX="jzdd_";


    public static final String USER_TYPE_WEB="01";

    //crm 提报画面
    public static final String USER_TYPE_WEBCHAT="02";
}
