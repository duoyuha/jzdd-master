package cn.backend.controller.vehicle;

import cn.backend.model.primary.vehicle.VehicleEntity;
import cn.backend.model.primary.vehicle.VehicleQuery;
import cn.backend.service.vehicle.IVehicleService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import cn.backend.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.zdwl.common.jsonfilter.CustomResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;


/**
 * @author baimin
 * @date 2019/07/08
 */
@Controller
@RequestMapping(value = "/vehicle")
@Api(description = "车型对应的接口")
public class VehicleController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IVehicleService _vehicleService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    /**
     * 列表，分页查询功能
     *
     * @param vehicleQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageSize",
                    value = "查询当前分页条数",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "pageNum",
                    value = "查询分页索引从0开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "vehicleName",
                    value = "车型名称",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })
    public Page<VehicleEntity> getList(@Validated({PageGroup.class}) VehicleQuery vehicleQuery) {
        Page<VehicleEntity> vehicleEntityPage = _vehicleService.findList(vehicleQuery);
        for (VehicleEntity item : vehicleEntityPage) {
            item.setVehicleDetailEntityList(vehicleDetailService.findByVehicleNo(item.getVehicleNo()));
        }
        return vehicleEntityPage;
    }

    /**
     * 保存
     *
     * @param vehicle
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "vehicleEntity", value = "实体vehicleEntity", required = true, dataType = "VehicleEntity")
    @CustomResult
    @PostMapping()
    public VehicleEntity saveVehicle(@Validated({CreateGroup.class}) @RequestBody VehicleEntity vehicle) {
        return _vehicleService.add(vehicle);
    }

    /**
     * 修改
     *
     * @param vehicle
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "vehicleEntity", value = "实体vehicleEntity", required = true, dataType = "VehicleEntity")
    @CustomResult
    @PutMapping()
    public VehicleEntity updateVehicle(@Validated({UpdateGroup.class}) @RequestBody VehicleEntity vehicle) {
        return _vehicleService.edit(vehicle);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @DeleteMapping(value = "/{id}")
    public Boolean deleteVehicle(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _vehicleService.delete(id);
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/{id}")
    public VehicleEntity findVehicleById(@PathVariable String id) {
        return _vehicleService.findById(id);
    }

}

