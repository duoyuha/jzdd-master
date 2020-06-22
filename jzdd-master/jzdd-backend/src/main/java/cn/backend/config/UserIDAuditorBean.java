package cn.backend.config;

import cn.zdwl.common.context.BaseContextHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

/**
 * 当前用户
 * 设置当前用户jpa @CreatedBy 和 @LastModifiedBy
 * Created by sunkuiwei on 2017/11/2.
 */
@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {


    @Override
    public String getCurrentAuditor() {
       // return BaseContextHandler.getUserName();
        if(StringUtils.isEmpty( BaseContextHandler.getUserName())){
            return BaseContextHandler.getUserId();
        }else {
            return BaseContextHandler.getUserName();
        }
    }


}
