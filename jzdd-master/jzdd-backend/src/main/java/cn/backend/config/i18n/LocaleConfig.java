package cn.backend.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * 设置按Cookie解析区域，并配置默认区域
 * Created by sunkw on 2017/11/24.
 */
public class LocaleConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver r = new CookieLocaleResolver();
        r.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        r.setCookieName("lang");
        return r;
    }

}
