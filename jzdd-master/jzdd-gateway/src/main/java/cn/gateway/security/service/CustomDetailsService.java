package cn.gateway.security.service;


import cn.gateway.feign.IUserService;
import cn.gateway.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 获取用户信息
 */
@Service
public class CustomDetailsService implements ICustomDetailService {

    @Autowired
    private IUserService userService;

    @Override
    public SysUser loadUserByUsername(String userName, String corpNo) throws UsernameNotFoundException {
        return userService.findByUserAcc(userName,corpNo);
    }

    @Override
    public SysUser loadDealerByToken(String token, String brand, String vin) throws UsernameNotFoundException {
        return userService.findByUserCrm(token,brand,vin);
    }


}

