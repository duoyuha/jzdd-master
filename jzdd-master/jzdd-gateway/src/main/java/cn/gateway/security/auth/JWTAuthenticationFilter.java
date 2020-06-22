package cn.gateway.security.auth;

import cn.gateway.feign.IUserService;
import cn.gateway.model.SysUser;
import cn.gateway.security.jwt.JwtTools;
import cn.gateway.security.service.ICustomDetailService;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * Created by sunkw on 2017/11/22.
 */

public class JWTAuthenticationFilter extends GenericFilterBean {



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            checkToken((HttpServletRequest)request,(HttpServletResponse)response,chain);
        }catch (Exception e){
            JwtTools.catchException((HttpServletResponse)response,e,HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 校验
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    private void checkToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain)  throws IOException, ServletException {

        SysUser sysUser=JwtTools.checkHeaders(request);

    //   SysUser user=  customDetailService.loadUserByUsername( sysUser.getUserAcc(),sysUser.getCorpNo());
        if(!StringUtils.isEmpty(sysUser.getDealerID())){
            sysUser.setUserId(sysUser.getDealerID());
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader("User-Type",sysUser.getUserType());
        requestContext.addZuulRequestHeader("User-Id",sysUser.getUserId());
        String userName="";
        if(!StringUtils.isEmpty(sysUser.getUsername()) ){
            userName = java.net.URLEncoder.encode(sysUser.getUsername(),"utf-8");
        }

        requestContext.addZuulRequestHeader("User-Name",userName);
        requestContext.addZuulRequestHeader("Corp-No",sysUser.getCorpNo());
        requestContext.addZuulRequestHeader("User-Acc",sysUser.getUserAcc());

        chain.doFilter(request, response);
    }

}


