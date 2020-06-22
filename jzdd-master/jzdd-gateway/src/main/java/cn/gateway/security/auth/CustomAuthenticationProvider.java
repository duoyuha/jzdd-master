package cn.gateway.security.auth;

import cn.gateway.model.SysUser;
import cn.gateway.security.service.ICustomDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.DigestUtils;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private ICustomDetailService userDetailsService;

    public CustomAuthenticationProvider(ICustomDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码

        CustomAuthenticationToken authToken = (CustomAuthenticationToken) authentication;


        String corpNo = authToken.getSysUser().getCorpNo();
        String userType = authToken.getSysUser().getUserType();




        // 认证逻辑 用户名和密码验证
        SysUser sysUser= new SysUser();

        if(cn.gateway.config.constant.GatewayConstant.USER_TYPE_WEB.equals(userType)){
            String name = authToken.getSysUser().getUserAcc();
//            String password = authToken.getCredentials().toString();
            String password = authToken.getSysUser().getUserPassword();
            sysUser = userDetailsService.loadUserByUsername(name, corpNo);
            if (null != sysUser && !StringUtils.isEmpty(sysUser.getUsername())) {
                String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());
                if (!sysUser.getPassword().equals(encodePassword)) {

                    throw new BadCredentialsException("密码错误");
                }
            }

        }else{

            sysUser = userDetailsService.loadDealerByToken(authToken.getSysUser().getToken(), authToken.getSysUser().getBrand(),authToken.getSysUser().getVin());


        }


        if(sysUser==null || StringUtils.isEmpty(sysUser.getUserId()) ){
            throw new BadCredentialsException("用户不存在或者密码不正确");
        }else{
            Authentication auth = new CustomAuthenticationToken(sysUser, sysUser.getAuthorities());
               return auth;
        }

    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}