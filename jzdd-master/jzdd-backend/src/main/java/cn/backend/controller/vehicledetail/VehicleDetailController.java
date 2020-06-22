package cn.backend.controller.vehicledetail;

import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailQuery;
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
 *
 * @author sunkw
 * @date 2019/08/19
 */
@Controller
@RequestMapping(value = "/vehicleDetail")
@Api(description = "vehicleDetail")
public class VehicleDetailController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IVehicleDetailService _vehicleDetailService;

    /**
     * 列表，分页查询功能
     *
     * @param vehicleDetailQuery
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
                        dataType = DataType.INT)
        })
    public Page<VehicleDetailEntity> getList(@Validated({PageGroup.class}) VehicleDetailQuery vehicleDetailQuery){
        return _vehicleDetailService.findList(vehicleDetailQuery);
    }

    /**
     * 保存
     *
     * @param vehicleDetail
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "vehicleDetailEntity", value = "实体vehicleDetailEntity", required = true, dataType = "VehicleDetailEntity")
    @CustomResult
    @PostMapping()
    public VehicleDetailEntity saveVehicleDetail(@Validated({CreateGroup.class}) @RequestBody VehicleDetailEntity vehicleDetail) {
        return _vehicleDetailService.add(vehicleDetail);
    }

    /**
     * 修改
     *
     * @param vehicleDetail
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "vehicleDetailEntity", value = "实体vehicleDetailEntity", required = true, dataType = "VehicleDetailEntity")
    @CustomResult
    @PutMapping()
    public VehicleDetailEntity updateVehicleDetail(@Validated({UpdateGroup.class}) @RequestBody VehicleDetailEntity vehicleDetail) {
        return _vehicleDetailService.edit(vehicleDetail);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @DeleteMapping(value = "/{id}")
    public Boolean deleteVehicleDetail(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _vehicleDetailService.delete(id);
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
    public VehicleDetailEntity findVehicleDetailById(@PathVariable String id) {
        return _vehicleDetailService.findById(id);
    }



}

