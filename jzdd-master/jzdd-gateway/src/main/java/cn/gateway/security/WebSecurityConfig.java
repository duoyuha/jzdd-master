package cn.gateway.security;


import cn.gateway.security.auth.*;
import cn.gateway.security.handler.CustomAccessDeniedHandler;
import cn.gateway.security.service.ICustomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created by sunkw on 2017/11/22.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private ICustomDetailService userDetailsService;

    @Autowired
    CustomAccessDeniedHandler authenticationAccessDeniedHandler;

    private boolean swaggerEnable = true;

    public WebSecurityConfig(ICustomDetailService detailService) {
        this.userDetailsService = detailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.cors().and().csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                //配置角色权限管理
                .withObjectPostProcessor(getObjectPostProcessor())
                // 因为使用了自定义的登录，所以把这个过滤掉,这个加了也没用,web.ignoring()优先级高
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/corp/info/user/**").permitAll()
                .antMatchers("/api/v1/api/case/afterorder","/api/v1/case/afterorder").permitAll()
                .antMatchers("/api/v1/api/crm/**").permitAll()
                .antMatchers("/api/v1/honda/**").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //在之前校验登录
                .addFilterBefore(new CustomLoginFilter("/api/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)

                //token校验
                .addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                //403处理
                .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }

    /**
     * 静态资源过滤取消
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        if(swaggerEnable){
            web.ignoring().antMatchers("/api/swagger-ui.html");
            web.ignoring().antMatchers("/api/webjars/**");
            web.ignoring().antMatchers("/api/swagger-resources/**");
            web.ignoring().antMatchers("/api/v2/**");
        }
        web.ignoring().antMatchers("/api/v1/files/**");
        web.ignoring().antMatchers("/api/druid/**");
        web.ignoring().antMatchers("/api/static/**");

        //service过滤接口
        web.ignoring().antMatchers(HttpMethod.GET,"/api/corp/info/user/**");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/repair/h5/**");
        web.ignoring().antMatchers(HttpMethod.PUT,"/api/repair/h5");
        web.ignoring().antMatchers("/api/v1/api/case/afterorder","/api/v1/case/afterorder");
        web.ignoring().antMatchers("/api/v1/api/crm/**","/api/v1/crm/**");
        web.ignoring().antMatchers("/api/v1/honda/**");

    }

    /**
     * 注册自定义身份认证验证组件
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService));
    }

    /**
     * 添加自定义角色路由管理
     * @return
     */
    private ObjectPostProcessor<FilterSecurityInterceptor> getObjectPostProcessor(){
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(new CustomAccessDecisionManager());
                return o;
            }
        };
    }

}