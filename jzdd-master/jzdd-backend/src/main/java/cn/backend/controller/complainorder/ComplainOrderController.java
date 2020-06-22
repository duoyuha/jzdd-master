package cn.backend.controller.complainorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewcomplainorder.ViewComplainEntity;
import cn.backend.service.complainorder.IComplainOrderService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.viewcomplainorder.IViewComplainService;
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
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/complainOrder")
@Api(description = "订单投诉")
public class ComplainOrderController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IComplainOrderService _complainOrderService;

    @Autowired
    private IViewComplainService viewComplainService;

    @Autowired
    private IScrmInfoService scrmInfoService;

    @Autowired
    private ComplainOrderMasterExcelExportConfig complainOrderMasterExcelExportConfig;

    /**
     * 列表，分页查询功能
     *
     * @param complainOrderQuery
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
    public Page<ViewComplainEntity> getList(@Validated({PageGroup.class}) ComplainOrderQuery complainOrderQuery){
        UserEntity userEntity = getUserEntity();
        complainOrderQuery.setUserType(userEntity.getUserType());
        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {
            complainOrderQuery.setSolutionSupplierNo(userEntity.getSupplierNo());
        }
        Page<ViewComplainEntity> viewComplainEntityPage = viewComplainService.findList(complainOrderQuery);
        for(ViewComplainEntity item : viewComplainEntityPage){
            if (!StringUtils.isEmpty(item.getScrmNo())) {
                ScrmInfoEntity scrmInfoEntity = scrmInfoService.findByNo(item.getScrmNo());
                item.setScrmInfoEntity(scrmInfoEntity);
            }
        }
        return viewComplainEntityPage;
    }

    /**
     * 保存
     *
     * @param complainOrder
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "complainOrderEntity", value = "实体complainOrderEntity", required = true, dataType = "ComplainOrderEntity")
    @CustomResult
    @PostMapping()
    public ComplainOrderEntity saveComplainOrder(@Validated({CreateGroup.class}) @RequestBody ComplainOrderEntity complainOrder) {

        UserEntity userEntity = getUserEntity();
        complainOrder.setCorpNo(userEntity.getCorpNo());
        return _complainOrderService.add(complainOrder);
    }



    /**
     * 修改
     *
     * @param complainOrder
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "complainOrderEntity", value = "实体complainOrderEntity", required = true, dataType = "ComplainOrderEntity")
    @CustomResult
    @PutMapping()
    public ComplainOrderEntity updateComplainOrder(@Validated({UpdateGroup.class}) @RequestBody ComplainOrderEntity complainOrder) {
        return _complainOrderService.edit(complainOrder);
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
    public Boolean deleteComplainOrder(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _complainOrderService.delete(id);
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
    public ViewComplainEntity findComplainOrderById(@PathVariable String id) {
        return viewComplainService.findById(id);
    }

    /**
     * 投诉订单-直接关闭
     *
     * @param complain
     * @return
     */
    @ApiOperation(value = "投诉订单-直接关闭", notes = "投诉订单-直接关闭")
    @ApiImplicitParam(name = "complainOrderEntity", value = "实体complainOrderEntity", required = true, dataType = "ComplainOrderEntity")
    @CustomResult
    @PutMapping("/close")
    public ComplainOrderEntity complainClose(@RequestBody ComplainOrderEntity complain) {
        return _complainOrderService.closeComplain(complain);
    }

    /**
     * 导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @CustomResult
    @GetMapping(value = "export")
    public ComplainOrderController.UI_ExcelImportResponse export(ComplainOrderQuery query) {
        UserEntity userEntity = getUserEntity();
        query.setUserType(userEntity.getUserType());
        List<ViewComplainEntity> entities = viewComplainService.findAll(query);
        MasterExcelExporter<ViewComplainEntity> exporter = generateMasterExcelExporter();
        XSSFWorkbook workbook = exporter.exportWorkbook(entities);
        String encoded = MasterExcelExporter.toBase64(workbook);
        ComplainOrderController.UI_ExcelImportResponse responseBody = new ComplainOrderController.UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("complain.xlsx");
        // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        return responseBody;
    }

    /**
     * 导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewComplainEntity> generateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewComplainEntity>(complainOrderMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(ViewComplainEntity data, XSSFRow newRow) {
                int i = 0;
                //工单号
                setStringValue(newRow, ++i, parseString(data.getOrderNo()));
                setStringValue(newRow, ++i, parseString(data.getComplainNo()));
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
                setStringValue(newRow, ++i, parseString(data.getComplainTime()==null ?"":dateTimeFormat.format(data.getComplainTime())));
                //投诉内容
                setStringValue(newRow, ++i, parseString(data.getComplainDescName() == null ? "" : data.getComplainDescName()));
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

