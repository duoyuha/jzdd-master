package cn.backend.controller.temp;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.ordercar.OrderCarQuery;
import cn.backend.model.primary.ordercar.OrderCarResponseEntity;
import cn.zdwl.common.jsonfilter.CustomResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/temp")
@Api(description = "临时信息对应的接口")
public class TempController extends BaseController {

    /**
     * 列表，分页查询功能
     *
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    @ApiImplicitParam(
            name = "vin",
            value = "vin",
            required = false,
            paramType = ParamType.QUERY,
            dataType = DataType.STRING)
    public OrderCarResponseEntity getList(OrderCarQuery orderCarQuery) {
        OrderCarResponseEntity responseEntity = new OrderCarResponseEntity();
        responseEntity.setId("1");
        responseEntity.setCorpNo("CORP1560154054723");
        responseEntity.setCarOwner("wxzd");
        responseEntity.setCarOwnerPhone("13000000000");
        responseEntity.setCarVehicle("长城");
        responseEntity.setEngineNo("11111111");
        responseEntity.setVinNo(orderCarQuery.getVin());
        responseEntity.setDealerName("挚达");
        responseEntity.setDealerTel("18000000000");
        responseEntity.setDealerContact("南湖大道");
        responseEntity.setSaleDate(new Date());
        // responseEntity.setRemark();
        return responseEntity;
    }


}