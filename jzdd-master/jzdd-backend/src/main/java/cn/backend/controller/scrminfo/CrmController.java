package cn.backend.controller.scrminfo;

import cn.backend.access.webchat.Result;
import cn.backend.config.constant.SysConstant;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.scrminfo.ScrmCarInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.service.config.IConfigService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.util.IdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Slf4j
@Controller
@RequestMapping(value = "/crm")
@Api(description = "CRM接口")
public class
CrmController extends BaseController{

    @Autowired
    private IScrmInfoService _scrmInfoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    /**
     * 获取车主信息
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "获取车主信息", notes = "获取车主信息")
    @ApiImplicitParam(name = "query", value = "实体query", required = true, dataType = "ScrmInfoQuery")
    @CustomResult
    @PostMapping("getcarinfo")
    public ScrmCarInfoEntity carInfo(@RequestBody ScrmInfoQuery query) {
        ScrmCarInfoEntity scrmCarInfo =_scrmInfoService.carInfo(query.getToken(),query.getVin(),query.getBrand());
        return scrmCarInfo;
    }

    /**
     * 校验token是否合法
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "校验token是否合法", notes = "校验token是否合法")
    @ApiImplicitParam(name = "query", value = "实体query", required = true, dataType = "ScrmInfoQuery")
    @CustomResult
    @PostMapping("checktoken")
    public Boolean checkToken(@RequestBody ScrmInfoQuery query) {
        Boolean isToken = _scrmInfoService.checkToken(query.getToken(),query.getBrand());
        return isToken;
    }


    /**
     * 获取用户信息
     *
     * @param token brand vin
     * @return
     */
    @PostMapping(value = "/getDealerUser")
    @CustomResult(false)
    public synchronized UserEntity getDealerUser(@RequestParam("token") String token, @RequestParam("brand") String brand, @RequestParam("vin") String vin) {

      if(! _scrmInfoService.checkToken(token,Integer.valueOf(brand))){
          return  null;
      }

        ScrmCarInfoEntity scrmCarInfo =_scrmInfoService.carInfo(token,vin,Integer.valueOf(brand));

      if(scrmCarInfo==null){

          return  null;

      }
      String corp="";
      if(SysConstant.Corp.BRAND_WEY.equals(brand)){
          corp=SysConstant.Delivery.CORP_WEIPA;
          log.error("==========WEY-token-{};brand-{};vin-{}==========",token,brand,vin);
      }else if(SysConstant.Corp.BRAND_ORA.equals(brand)){
          log.error("==========ORA-token-{};brand-{};vin-{}==========",token,brand,vin);
          corp=SysConstant.Delivery.CORP_ORA;
      }else{
          log.error("==========brand-token-{};brand-{};vin-{}==========",token,brand,vin);
      }


      UserEntity userEntity =userService.findByUserNameAndCorp(scrmCarInfo.getWholeName(),corp);

      if(userEntity ==null){
          userEntity = new UserEntity();
          userEntity.setCorpNo(corp);
          userEntity.setUserAcc(IdUtils.getGenerateNumber());
          userEntity.setUserName(scrmCarInfo.getWholeName());
          userEntity.setUserPassword(IdUtils.getGenerateNumber());
          userEntity.setUserPosition(SysConstant.User.DEALER_USER);
          userEntity.setUserType(SysConstant.User.DEALER_USER);
          ConfigEntity configEntity = configService.findByTypeAndCode(SysConstant.User.CONFIG_USERPOSITION,userEntity.getUserType(),corp);
          userEntity.setUserPositionName(configEntity.getConfigName());
          userEntity.setUserTypeName(configEntity.getConfigName());
          userEntity.setUserPhone(StringUtils.isEmpty(scrmCarInfo.getEmployeePhone()) ? SysConstant.User.FIX_PHONE:scrmCarInfo.getEmployeePhone());
          userEntity=userService.add(userEntity);

      }

        userEntity.setDealerID(userEntity.getUserId());
        userEntity.setUserId(IdUtils.getGenerateNumber());
        return  userEntity;
    }




    /**
     * 提交充电桩订单
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "提交充电桩订单", notes = "提交充电桩订单")
    @ApiImplicitParam(name = "query", value = "实体query", required = true, dataType = "ScrmInfoQuery")
    @CustomResult
    @PostMapping("submitpileorder")
    public Result submitPileOrder(@RequestBody ScrmInfoQuery query) {
        Result result = _scrmInfoService.submitPileOrder(query.getToken(),query.getVin(),query.getBrand(),query.getState());
        return result;
    }

}

