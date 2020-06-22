package cn.backend.config;

import cn.backend.config.customhandler.ControllerReturnHandler;
import cn.zdwl.common.context.UserAuthRestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 自定义web处理方式
 * 拦截器、返回值
 * Created by sunkw on 2018/5/30.
 */
@Configuration
public class WebMvcConfigurer {

    @Value("${file.savepath}")
    private String filePath;

    @Value("${file.viewpath}")
    private String viewpath;

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {

//            @Override
//            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//                FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//                //自定义配置...
//                //FastJsonConfig config = new FastJsonConfig();
//                //config.set ...
//                //converter.setFastJsonConfig(config);
//                converters.add(0, converter);
//            }

            /**
             * 自定义返回值
             * @param returnValueHandlers
             */
            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
                returnValueHandlers.add(new ControllerReturnHandler());
            }
            /**
             * 请求拦截器
             * 根据token解析用户信息，没有登录账号都会被无视
             * 去除swagger信息
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry){
                //设置权限校验
                InterceptorRegistration addInterceptor = registry.addInterceptor(new UserAuthRestInterceptor());
                // 排除配置
                addInterceptor.excludePathPatterns("/user/getUserByAcc");

                //排除swagger
                addInterceptor.excludePathPatterns("/swagger-ui.html");
                addInterceptor.excludePathPatterns("/doc.html");
                addInterceptor.excludePathPatterns("/webjars/**");
                addInterceptor.excludePathPatterns("/swagger-resources/**");
                addInterceptor.excludePathPatterns("/v2/**");

                //文件
                addInterceptor.excludePathPatterns(viewpath+"**");

                // 拦截配置
                addInterceptor.addPathPatterns("/**");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //配置server虚拟路径，handler为访问的目录，locations为files相对应的本地路径
                registry.addResourceHandler(viewpath+"**").addResourceLocations("file:"+filePath);
            }
        };
    }

}
