package cn.gateway.feign;

import cn.gateway.model.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用
 */
@FeignClient(name="jzdd-backend")
public interface IUserService {

    @PostMapping(value="/user/getUserByAcc")
    SysUser findByUserAcc(@RequestParam("userAcc") String userAcc,@RequestParam("corpNo") String corpNo);


    @PostMapping(value="/crm/getDealerUser")
    SysUser findByUserCrm(@RequestParam("token") String token,@RequestParam("brand") String brand,@RequestParam("vin") String vin);



}
