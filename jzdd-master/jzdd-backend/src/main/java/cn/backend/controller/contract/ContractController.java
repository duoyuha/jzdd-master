package cn.backend.controller.contract;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.contract.ContractEntity;
import cn.backend.model.primary.contract.ContractQuery;
import cn.backend.service.contract.IContractService;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author sunkw
 * @date 2019/07/09
 */
@Controller
@RequestMapping(value = "/contract")
@Api(description = "合同")
public class ContractController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IContractService _contractService;

    /**
     * 列表，分页查询功能
     *
     * @param contractQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    //@ApiImplicitParam(name = "contractQuery", value = "查询实体contractQuery", required = true, dataType = "ContractQuery")
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
                    name = "contractCode",
                    value = "合同编号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
    })
    public Page<ContractEntity> getList(@Validated({PageGroup.class}) ContractQuery contractQuery) {
        return _contractService.findList(contractQuery);
    }

    /**
     * 保存
     *
     * @param contract
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "contractEntity", value = "实体contractEntity", required = true, dataType = "ContractEntity")
    @CustomResult
    @PostMapping("/add")
    public ContractEntity saveContract(@Validated({CreateGroup.class})ContractEntity contract,
    @RequestParam(value = "con", required = false) MultipartFile con) {
        UploadFile condetail = null;
        if (con != null ) {
            condetail = getFile(con, SysConstant.Contract.CONFIG_FOLDER_NAME);
        }
        contract.setContract(condetail);
        return _contractService.add(contract);
    }

    /**
     * 修改
     *
     * @param contract
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "contractEntity", value = "实体contractEntity", required = true, dataType = "ContractEntity")
    @CustomResult
    @PostMapping("/edit")
    public ContractEntity updateContract(@Validated({UpdateGroup.class})ContractEntity contract,
                                         @RequestParam(value = "con", required = false) MultipartFile con) {
        UploadFile condetail = null;
        if (con != null ) {
            condetail = getFile(con, SysConstant.Contract.CONFIG_FOLDER_NAME);
        }
        contract.setContract(condetail);
        return _contractService.edit(contract);
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
    public Boolean deleteContract(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _contractService.delete(id);
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
    public ContractEntity findContractById(@PathVariable String id) {
        return _contractService.findById(id);
    }


}

