package cn.backend.access.webchat;

import cn.backend.model.primary.user.UserEntity;
import cn.zdwl.common.util.HttpUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public class OpenIdUtil {


    /**
     * 商户Id
     */
    @Value("${WeixinConfig.url}")
    private  String url;

    /**
     * 商户Id
     */
    @Value("${WeixinConfig.appId}")
    private  String appId;

    /**
     * 商户Id
     */
    @Value("${WeixinConfig.appSecret}")
    private  String appSecret;

    private  Gson gson = new Gson();

    public   String getOpenid(String code) {


        //发送请求
        ResponseEntity<String> responseEntity = HttpUtils.doGet(String.format(url,appId
                ,appSecret , code), null, String.class);
        UserEntity user = gson.fromJson(responseEntity.getBody(), UserEntity.class);
       return  null;
        // return user.getOpenId();
    }
}
