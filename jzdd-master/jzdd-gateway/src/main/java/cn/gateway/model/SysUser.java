package cn.gateway.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用实体
 */
@Getter
@Setter
public class SysUser implements UserDetails{

    /**
     * 用户类型；01 web登录 02 微信 03 验证码登录 04 app登录
     */
    @NotEmpty(message = "用户类型不能为空")

    private String userType;

    /**
     * 用户id
     */
   // @JsonIgnore
    private String userId;

    /**
     * 微信代码
     */
    private String vin;


    private String dealerID;


    /**
     * 微信open id
     */
    //@JsonIgnore
    private String token;


    private String brand;

    /**
     * 账号
     */
  //  @NotEmpty(message = "{message.CustomMessage.UseruserAccNotEmpty.body}")
    private String userAcc;

    /**
     * 密码
     */
 //   @NotEmpty(message = "{message.CustomMessage.UseruserAccNotEmpty.body}")
    private String userPassword;

    /**
     * 单位No
     */
  //  @NotEmpty(message = "{message.CustomMessage.UseruserAccNotEmpty.body}")
    private String corpNo;

    /**
     * 用户名
     */
    // @JsonIgnore
    private String userName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }


    public String getUserAcc() {
        return this.userAcc;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toJsonString(){
        return JSON.toJSONString(this);
    }

    public static SysUser toObject(String json){
        return JSON.parseObject(json,SysUser.class);
    }
}
