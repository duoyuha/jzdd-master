package cn.backend.controller.supplier;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.pile.PileEntity;
import cn.backend.model.primary.pile.PileQuery;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.supplier.SupplierQuery;
import cn.backend.service.pile.IPileService;
import cn.backend.service.supplier.ISupplierService;
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
 * @date 2019/07/08
 */
@Controller
@RequestMapping(value = "/supplier")
@Api(description = "服务商对应的接口")
public class SupplierController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ISupplierService _supplierService;

    @Autowired
    private IPileService pileService;

    /**
     * 列表，分页查询功能
     *
     * @param supplierQuery
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
                    name = "supplierName",
                    value = "服务商名称",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
    })
    public Page<SupplierEntity> getList(@Validated({PageGroup.class}) SupplierQuery supplierQuery) {
        return _supplierService.findList(supplierQuery);
    }

    /**
     * 保存
     *
     * @param supplier
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片附件", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "supplier", value = "实体supplier", required = true, dataType = "SupplierEntity")
    })
    @CustomResult
    @PostMapping("/add")
    public SupplierEntity saveSupplier(@RequestParam(value = "file", required = false) MultipartFile file,
                                       @Validated({CreateGroup.class}) SupplierEntity supplier) {
        //上传附件
        if (file != null) {
            UploadFile uploadFile = getFile(file, SysConstant.Supplier.CONFIG_FOLDER_NAME);
            supplier.setCertificateFile(uploadFile.getViewPath());
        }
        return _supplierService.add(supplier);
    }

    /**
     * 修改
     *实体类添加了图片名称字段
     * @param supplier
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片附件", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "supplier", value = "实体supplier", required = true, dataType = "SupplierEntity")
    })
    @CustomResult
    @PostMapping("/edit")
    public SupplierEntity updateSupplier(@RequestParam(value = "file", required = false) MultipartFile file,
                                         @Validated({UpdateGroup.class}) SupplierEntity supplier) {
        //上传附件
        if (file != null) {
            UploadFile uploadFile = getFile(file, SysConstant.Supplier.CONFIG_FOLDER_NAME);
            supplier.setCertificateFile(uploadFile.getViewPath());
            supplier.setEleFileName(uploadFile.getOriginalFileName());
        }
        return _supplierService.edit(supplier);
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
    public Boolean deleteSupplier(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _supplierService.delete(id);
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
    public SupplierEntity findSupplierById(@PathVariable String id) {
        return _supplierService.findById(id);
    }

    /**
     * 根据订单获取
     * @param supplierQuery
     * @return
     */
    @ApiOperation(value = "根据订单获取", notes = "根据订单获取")
    @ApiImplicitParam(name = "supplierQuery", value = "实体supplierQuery", required = true, dataType = "SupplierQuery")
    @CustomResult
    @GetMapping(value = "/order")
    public List<SupplierEntity> findSupplierByOrderNo(SupplierQuery supplierQuery) {
        return _supplierService.findListByOrder(supplierQuery.getOrderType(),supplierQuery.getOrderNo());
    }

    /**
     * 根据订单获取
     * @param
     * @return
     */
    @ApiOperation(value = "根据订单获取", notes = "根据订单获取")
    @ApiImplicitParam(name = "supplierQuery", value = "实体supplierQuery", required = true, dataType = "SupplierQuery")
    @CustomResult
    @GetMapping(value = "/month")
    public List<SupplierEntity> countMonth() {
        return _supplierService.countMonth(getCorpNo());
    }




    /**
     * 自动派单
     * @param supplierno
     * @return
     */
    @ApiOperation(value = "根据供应商查询桩", notes = "根据供应商查询桩")
    @ApiImplicitParam(name = "supplierno", value = "supplierno", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/findpilebysupplier/{supplierno}")
    public List<PileEntity> automaticOrder(@PathVariable String supplierno) {
        SupplierEntity supplierEntity= _supplierService.findByNo(supplierno);
        PileQuery pileQuery =new PileQuery();
        pileQuery.setPileNos(supplierEntity.getPileNos());
        return   pileService.findAll(pileQuery);

    }

    @ApiOperation(value = "根据供应商查询桩", notes = "根据供应商查询桩")
    @ApiImplicitParam(name = "supplierno", value = "supplierno", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @PutMapping(value = "/setautoprop")
    public boolean setAutomaticProp(@RequestBody List<SupplierEntity> supplierEntityList) {
        _supplierService.setAutoOrderProp(supplierEntityList);
        return true;
    }


}

