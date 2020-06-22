package cn.gateway.security.auth;

import cn.gateway.model.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final SysUser sysUser;

    public CustomAuthenticationToken(SysUser sysUser) {
        super(sysUser.getUserAcc(), sysUser.getPassword());
        this.sysUser = sysUser;
    }

    public CustomAuthenticationToken(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUserId(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
