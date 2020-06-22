package cn.backend.controller.adviceorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.adviceorder.AdviceQuery;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewadviceorder.ViewAdviceEntity;
import cn.backend.service.adviceorder.IAdviceService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.viewadviceorder.IViewAdviceService;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.message.MasterExcelMessage;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/advice")
@Api(description = "报修咨询")
public class AdviceController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IAdviceService _adviceService;

    @Autowired
    private IViewAdviceService viewAdviceService;

    @Autowired
    private IScrmInfoService scrmInfoService;

    @Autowired
    private AdviceOrderMasterExcelExportConfig adviceOrderMasterExcelExportConfig;

    /**
     * 列表，分页查询功能
     *
     * @param adviceQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageSize",
                    value = "查询当前分页条数",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "pageNum",
                    value = "查询分页索引从0开始",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "orderNo",
                    value = "订单号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "vinNo",
                    value = "VIN",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "supplierNo",
                    value = "服务商",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "adviceTimeStart",
                    value = "投诉日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "adviceTimeEnd",
                    value = "投诉日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "corpNo",
                    value = "公司编号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
    })
    public Page<ViewAdviceEntity> getList(@Validated({PageGroup.class}) AdviceQuery adviceQuery) {
        // UserEntity userEntity = getUserEntity();
        // adviceQuery.setSupplierNo(userEntity.getSupplierNo());
        // adviceQuery.setUserPosition(userEntity.getUserType());
        UserEntity userEntity = getUserEntity();
        adviceQuery.setUserType(userEntity.getUserType());
        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {
            adviceQuery.setSolutionSupplierNo(userEntity.getSupplierNo());
        }
        Page<ViewAdviceEntity> viewAdviceEntityPage = viewAdviceService.findList(adviceQuery);
        for (ViewAdviceEntity item : viewAdviceEntityPage) {
            if (!StringUtils.isEmpty(item.getScrmNo())) {
                ScrmInfoEntity scrmInfoEntity = scrmInfoService.findByNo(item.getScrmNo());
                item.setScrmInfoEntity(scrmInfoEntity);
            }
        }
        return viewAdviceEntityPage;
    }

    /**
     * 保存
     *
     * @param advice
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "adviceEntity", value = "实体adviceEntity", required = true, dataType = "AdviceEntity")
    @CustomResult
    @PostMapping()
    public AdviceEntity saveAdvice(@Validated({CreateGroup.class}) @RequestBody AdviceEntity advice) {
        UserEntity userEntity = getUserEntity();
        advice.setCorpNo(userEntity.getCorpNo());
        // return _adviceService.submitOrder(advice);
        return _adviceService.addAdvice(advice);
    }

    /**
     * 咨询订单-流程
     *
     * @param advice
     * @return
     */
    @ApiOperation(value = "咨询订单-无图片流程", notes = "咨询订单流程都调用此接口，需要在调用findbyid的情况下将得到的参数修改，并将完整的对象返送回来")
    @ApiImplicitParam(name = "adviceEntity", value = "实体adviceEntity", required = true, dataType = "AdviceEntity")
    @CustomResult
    @PostMapping("/workfollow")
    public AdviceEntity installOrderWorkFollow(@RequestBody AdviceEntity advice) {
        // return _adviceService.orderWorkFollow(advice);
        return _adviceService.editAdvice(advice);
    }

    /**
     * 修改
     *
     * @param advice
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "adviceEntity", value = "实体adviceEntity", required = true, dataType = "AdviceEntity")
    @CustomResult
    @PutMapping()
    public AdviceEntity updateAdvice(@Validated({UpdateGroup.class}) @RequestBody AdviceEntity advice) {

        return _adviceService.saveOrderAndStep(advice);
    }

    /**
     * 咨询订单-直接关闭
     *
     * @param advice
     * @return
     */
    @ApiOperation(value = "咨询订单-直接关闭", notes = "咨询订单-直接关闭")
    @ApiImplicitParam(name = "adviceEntity", value = "实体adviceEntity", required = true, dataType = "AdviceEntity")
    @CustomResult
    @PutMapping("/close")
    public AdviceEntity adviceClose(@RequestBody AdviceEntity advice) {
        return _adviceService.closeAdvice(advice);
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
    public Boolean deleteAdvice(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _adviceService.delete(id);
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
    public ViewAdviceEntity findAdviceById(@PathVariable String id) {
        return viewAdviceService.findById(id);
    }

    /**
     * 导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @CustomResult
    @GetMapping(value = "export")
    public AdviceController.UI_ExcelImportResponse export(AdviceQuery query) {
        UserEntity userEntity = getUserEntity();
        query.setUserType(userEntity.getUserType());
        List<ViewAdviceEntity> entities = viewAdviceService.findAll(query);
        MasterExcelExporter<ViewAdviceEntity> exporter = generateMasterExcelExporter();
        XSSFWorkbook workbook = exporter.exportWorkbook(entities);
        String encoded = MasterExcelExporter.toBase64(workbook);
        AdviceController.UI_ExcelImportResponse responseBody = new AdviceController.UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("advice.xlsx");
        // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        return responseBody;
    }

    /**
     * 导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewAdviceEntity> generateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewAdviceEntity>(adviceOrderMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(ViewAdviceEntity data, XSSFRow newRow) {
                int i = 0;
                //工单号
                setStringValue(newRow, ++i, parseString(data.getOrderNo()));
                setStringValue(newRow, ++i, parseString(data.getAdviceNo()));
                //联系客户时间
                setStringValue(newRow, ++i, parseString(data.getContactCustTime()==null ?"":dateTimeFormat.format(data.getContactCustTime())));
                //问题解决时间
                setStringValue(newRow, ++i, parseString(data.getSolutionTime()==null ?"":dateTimeFormat.format(data.getSolutionTime())));
                //问题解决方案
                setStringValue(newRow, ++i, parseString(data.getSolutionDesc() == null ? "" : data.getSolutionDesc()));
                //车主姓名
                setStringValue(newRow, ++i, parseString(data.getCarOwner() == null ? "" : data.getCarOwner()));
                //联系方式
                setStringValue(newRow, ++i, parseString(data.getCarOwnerPhone() == null ? "" : data.getCarOwnerPhone()));
                //进度
                if (SysConstant.IS_DEL_Y.equals(data.getFinishFlg())){
                    setStringValue(newRow, ++i, parseString("已完成"));
                }else {
                    setStringValue(newRow, ++i, parseString(data.getStepName() == null ? "" : data.getStepName()));
                }
                //VIN
                setStringValue(newRow, ++i, parseString(data.getVinNo()));
                //投诉时间
                setStringValue(newRow, ++i, parseString(data.getAdviceTime()==null ?"":dateTimeFormat.format(data.getAdviceTime())));
                //投诉内容
                setStringValue(newRow, ++i, parseString(data.getAdviceDesc() == null ? "" : data.getAdviceDesc()));
                //服务商
                setStringValue(newRow, ++i, parseString(data.getSupplierName() == null ? "" : data.getSupplierName()));
                //解决服务商
                setStringValue(newRow, ++i, parseString(data.getSolutionSupplierName() == null ? "" : data.getSolutionSupplierName()));
                //经销商
                setStringValue(newRow, ++i, parseString(data.getDealerName() == null ? "" : data.getDealerName()));
            }
        };
    }

    @Data
    private static class UI_ExcelImportResponse {
        private String base64;
        private String fileName;
        private List<MasterExcelMessage.TranslatedMessage> messageList;
    }

}

