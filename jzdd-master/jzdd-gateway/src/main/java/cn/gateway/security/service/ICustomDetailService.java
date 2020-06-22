package cn.gateway.security.service;

import cn.gateway.model.SysUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ICustomDetailService {

    SysUser loadUserByUsername( String userName, String corpNo) throws UsernameNotFoundException;


    SysUser loadDealerByToken( String token,String brand, String vin) throws UsernameNotFoundException;



}
