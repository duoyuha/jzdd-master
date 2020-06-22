package cn.gateway.security.auth;

import cn.gateway.model.SysUser;
import cn.gateway.security.jwt.JwtTools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 登录拦截器
 * Created by sunkw on 2017/11/22.
 */
public class CustomLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    public CustomLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            //解析为对象
            SysUser user = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
//            if(StringUtils.isEmpty(user.getCorpNo())){
//                throw new BadCredentialsException("公司不能为空");
//            }
//            if(StringUtils.isEmpty(user.getPassword())){
//                throw new BadCredentialsException("密码不能为空");
//            }
//            if(StringUtils.isEmpty(user.getUserAcc())){
//                throw new BadCredentialsException("账号不能为空");
//            }

      //      user.setUserType(cn.gateway.config.constant.GatewayConstant.USER_TYPE_WEB);
            //调用自定义的CustomAuthenticationProvider
            return authenticationManager.authenticate(new CustomAuthenticationToken(user, new ArrayList<>()));
        } catch (Exception e) {
            //发现非继承AuthenticationException的异常不会处理
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }
            //如果调用服务异常，则抛出已知的异常消息
            if (e instanceof RuntimeException || e instanceof IOException) {
                JwtTools.catchException(res, e, HttpStatus.BAD_REQUEST);
            }

        }
        return null;
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        JwtTools.addAuthentication(res, auth);
    }

    /**
     * 异常处理
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        JwtTools.catchException(response, failed, HttpStatus.UNAUTHORIZED);
    }

}