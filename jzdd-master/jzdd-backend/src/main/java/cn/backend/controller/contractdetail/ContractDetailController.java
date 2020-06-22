package cn.backend.controller.contractdetail;

import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.backend.model.primary.contractdetail.ContractDetailQuery;
import cn.backend.service.contractdetail.IContractDetailService;
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
 * @date 2019/07/18
 */
@Controller
@RequestMapping(value = "/contractDetail")
@Api(description = "contractDetail")
public class ContractDetailController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IContractDetailService _contractDetailService;

    /**
     * 列表，分页查询功能
     *
     * @param contractDetailQuery
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
    public Page<ContractDetailEntity> getList(@Validated({PageGroup.class}) ContractDetailQuery contractDetailQuery){
        return _contractDetailService.findList(contractDetailQuery);
    }

    /**
     * 保存
     *
     * @param contractDetail
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "contractDetailEntity", value = "实体contractDetailEntity", required = true, dataType = "ContractDetailEntity")
    @CustomResult
    @PostMapping()
    public ContractDetailEntity saveContractDetail(@Validated({CreateGroup.class}) @RequestBody ContractDetailEntity contractDetail) {
        return _contractDetailService.add(contractDetail);
    }

    /**
     * 修改
     *
     * @param contractDetail
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "contractDetailEntity", value = "实体contractDetailEntity", required = true, dataType = "ContractDetailEntity")
    @CustomResult
    @PutMapping()
    public ContractDetailEntity updateContractDetail(@Validated({UpdateGroup.class}) @RequestBody ContractDetailEntity contractDetail) {
        return _contractDetailService.edit(contractDetail);
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
    public Boolean deleteContractDetail(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _contractDetailService.delete(id);
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
    public ContractDetailEntity findContractDetailById(@PathVariable String id) {
        return _contractDetailService.findById(id);
    }



}

