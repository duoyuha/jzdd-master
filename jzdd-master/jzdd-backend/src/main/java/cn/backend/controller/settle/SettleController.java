package cn.backend.controller.settle;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.settle.SettleEntity;
import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.model.primary.viewsettle.SettleViewEntity;
import cn.backend.service.corp.ICorpService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.settle.ISettleService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.backend.service.viewsettle.ISettleViewService;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.message.MasterExcelMessage;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author James
 * @date 2019/07/29
 */
@Api(description = "结算管理对应的接口")
@Slf4j
@Controller
@RequestMapping(value = "/settle")
public class SettleController extends BaseController {


    @Autowired
    private SettleMasterExcelExportConfig exportConfig;

    @Autowired
    private ISettleService settleService;

    @Autowired
    private ISettleViewService settleViewService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private ICorpService corpService;

    @ApiOperation(value = "获取未结算数据", notes = "获取未结算的数据，以下为查询条件" +
            "<ul>" +
            "<li>1. 省份 installProvince</li>" +
            "<li>2. 城市 installCity</li>" +
            "<li>3. 服务商 supplierNo</li>" +
            "<li>4. 订单创建日期 orderTimeStart orderTimeEnd</li>" +
            "<li>5. 是否超时  timeoutFlg</li>" +
            "<li>6. 勘查时间  appointmentTimeStart appointmentTimeEnd</li>" +
            "<li>7. 勘查时间  checkTimeStart checkTimeEnd</li>" +
            "<li>8. 预约安装时间  appointmentInstallTimeStart appointmentInstallTimeEnd</li>" +
            "<li>9. 安装完成日期  installFinishTimeStart installFinishTimeEnd</li>" +

            "</ul>")

    @CustomResult
    @GetMapping("/unsettle")
    public Page<ViewInstallOrderEntity> findUnsettleList(@Validated({PageGroup.class}) InstallOrderQuery installOrderQuery) {


        installOrderQuery.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
        installOrderQuery.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
        installOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);
        UserEntity userEntity = getUserEntity();
        if (StringUtils.isEmpty(userEntity.getSupplierNo())) {
            installOrderQuery.setSupplierNo("-");
        }
        // installOrderQuery.setUserTypeWeb(getUserEntity().getUserType());
        return installOrderViewService.findList(installOrderQuery);
    }

    @ApiOperation(value = "获取未结算数据", notes = "获取未结算的数据，以下为查询条件" +
            "<ul>" +
            "<li>1. 省份 installProvince</li>" +
            "<li>2. 城市 installCity</li>" +
            "<li>3. 服务商 supplierNo</li>" +
            "<li>4. 订单创建日期 orderTimeStart orderTimeEnd</li>" +
            "<li>5. 是否超时  timeoutFlg</li>" +
            "<li>6. 勘查时间  appointmentTimeStart appointmentTimeEnd</li>" +
            "<li>7. 勘查时间  checkTimeStart checkTimeEnd</li>" +
            "<li>8. 预约安装时间  appointmentInstallTimeStart appointmentInstallTimeEnd</li>" +
            "<li>9. 安装完成日期  installFinishTimeStart installFinishTimeEnd</li>" +

            "</ul>")

    @CustomResult
    @GetMapping("/delunsettle")
    public Page<ViewDeliveryOrderEntity> findDeliveryUnsettleList(@Validated({PageGroup.class}) ViewDeliveryOrderQuery deliveryOrderQuery) {


        deliveryOrderQuery.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
        deliveryOrderQuery.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
        deliveryOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);
        UserEntity userEntity = getUserEntity();
        if (StringUtils.isEmpty(userEntity.getSupplierNo())) {
            deliveryOrderQuery.setSupplierNo("-");
        }
        return viewDeliveryOrderService.findList(deliveryOrderQuery);
    }

    @ApiOperation(value = "获取已结算分页列表", notes = "获取已结算分页列表")
    @CustomResult
    @GetMapping()
    public Page<SettleViewEntity> findList(@Validated({PageGroup.class}) SettleQuery settleQuery) {
        UserEntity userEntity = getUserEntity();
        settleQuery.setUserType(userEntity.getUserType());
        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {
            settleQuery.setSupplierNo(userEntity.getSupplierNo());
        }
        settleQuery.setSettleStatus(SysConstant.Settle.TRIAL_PASS);
        return settleViewService.findList(settleQuery);
    }


    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "结算单Id", required = true, paramType = ParamType.PATH, dataType = DataType.STRING)
    @CustomResult
    @GetMapping(value = "/{id}")
    public SettleEntity findById(@PathVariable String id) {
        return settleService.findById(id);
    }

    @ApiOperation(value = "创建结算单", notes = "创建结算单")
    @ApiImplicitParam(name = "settleEntity", value = "实体settleEntity", required = true, dataType = "SettleEntity")
    @CustomResult
    @PostMapping()
    public SettleEntity add(@RequestBody SettleEntity settleEntity) {
        UserEntity userEntity = getUserEntity();
        settleEntity.setUserType(userEntity.getUserType());
        settleEntity.setCorpNo(userEntity.getCorpNo());
        return settleService.add(settleEntity);
    }

    @ApiOperation(value = "回退修改明细", notes = "回退修改明细")
    @ApiImplicitParam(name = "settleEntity", value = "实体settleEntity", required = true, dataType = "SettleEntity")
    @CustomResult
    @PostMapping("/back")
    public boolean back(@RequestBody SettleQuery query) {
        return settleService.back(query);
    }

    /**
     * 上传附件
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "上传附件", notes = "上传附件")
    @ApiImplicitParam(name = "settleEntity", value = "实体settleEntity", required = true, dataType = "SettleEntity")
    @CustomResult
    @PostMapping("/submitfile")
    public SettleEntity submitFile(@RequestParam(value = "id", required = false) String id,
                                                 @RequestParam(value = "settleFile", required = false) MultipartFile settleFile) {


        // UploadFileAttach(deliveryOrderEntity.getOrderNo(), explainFileList, SysConstant.orderAttach.CONFIG_ATTACH_TYPE_8);
        //删除已有附件（CRM退回涉及）
//        List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findByOrderNo(deliveryOrderEntity.getOrderNo());
//        if (orderAttachEntityList != null && orderAttachEntityList.size() > 0) {
//            List<String> nos = orderAttachEntityList.stream().map(e -> e.getAttachNo()).collect(Collectors.toList());
//            orderAttachService.delete(nos);
//        }
        SettleEntity settleEntity=settleService.findById(id);
        //保存附件
        if (settleFile != null) {
            UploadFile uploadFile = getFile(settleFile, SysConstant.Settle.CONFIG_FOLDER_NAME_SIGN);
            settleEntity.setSettleFile(uploadFile.getViewPath());
            settleService.submit(settleEntity);
        }
        return settleEntity;
    }


    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "settleEntity", value = "实体settleEntity", required = true, dataType = "SettleEntity")
    @CustomResult
    @PutMapping()
    public SettleEntity edit(@Validated({UpdateGroup.class}) @RequestBody SettleEntity settle) {
        return settleService.edit(settle);
    }

    @ApiOperation(value = "批量审核", notes = "批量审核")
    @ApiImplicitParam(name = "settleEntity", value = "实体settleEntity", required = true, dataType = "SettleEntity")
    @CustomResult
    @PutMapping("/verifybatch")
    @Transactional
    public boolean editBatch(@RequestBody SettleQuery query) {
        if (!StringUtils.isEmpty(query.getIds())) {
            String ids = query.getIds();
            String[] idArray = ids.split(",");
            if (idArray.length > 0) {
                for(String id : idArray){
                    SettleEntity settle = settleService.findById(id);
                    settle.setVerifyStatus(SysConstant.OrderStep.VERIFY_RESULT_PASS);
                    settle.setVerifyRemark("批量审核");
                    settle.setType(query.getType());
                    settleService.edit(settle);
                }
            }
        }
        return true;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @DeleteMapping(value = "/{id}")
    public Boolean delete(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return settleService.delete(id);
    }

    /**
     * 导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @CustomResult
    @GetMapping(value = "export")
    public SettleController.UI_ExcelImportResponse export(SettleQuery query) {
        UserEntity userEntity = getUserEntity();
        query.setUserType(userEntity.getUserType());
        SettleController.UI_ExcelImportResponse responseBody = new SettleController.UI_ExcelImportResponse();
        if (query.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL)) {
            List<ViewInstallOrderEntity> installOrderEntities = settleService.export(query);

            MasterExcelExporter<ViewInstallOrderEntity> exporter = generateMasterExcelExporter();
            XSSFWorkbook workbook = exporter.exportWorkbook(installOrderEntities);
            String encoded = MasterExcelExporter.toBase64(workbook);

            responseBody.setBase64(encoded);
            responseBody.setFileName("settle_result.xlsx");
            // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        } else if (query.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY)) {
            List<ViewDeliveryOrderEntity> deliveryOrderEntities = settleService.delExport(query);

            MasterExcelExporter<ViewDeliveryOrderEntity> exporter = delGenerateMasterExcelExporter();
            XSSFWorkbook workbook = exporter.exportWorkbook(deliveryOrderEntities);
            String encoded = MasterExcelExporter.toBase64(workbook);
            responseBody.setBase64(encoded);
            responseBody.setFileName("settle_result.xlsx");
            // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        }
        return responseBody;
    }

    /**
     * 安装导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewInstallOrderEntity> generateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewInstallOrderEntity>(exportConfig, translateService) {
            @Override
            public void setupExportRow(ViewInstallOrderEntity data, XSSFRow newRow) {
                int i = 0;
                //进度
                if (SysConstant.IS_DEL_Y.equals(data.getFinishFlg())) {
                    setStringValue(newRow, ++i, parseString("已完成"));
                } else {
                    setStringValue(newRow, ++i, parseString(data.getStepName() == null ? "" : data.getStepName()));
                }
                //开票状态
                if ("是".equals(data.getInvoiceStutas())){
                    setStringValue(newRow, ++i, "已开票");
                }else {
                    setStringValue(newRow, ++i, "未开票");
                }
                //经销商
                setStringValue(newRow, ++i, data.getDealerName() == null ? "" : data.getDealerName());
                //车型
                setStringValue(newRow, ++i, parseString(data.getCarVehicle() == null ? "" : data.getCarVehicle()));
                //配置
                setStringValue(newRow, ++i, parseString(data.getVehicleDetail() == null ? "" : data.getVehicleDetail()));
                //VIN
                setStringValue(newRow, ++i, parseString(data.getVinNo()));
                //发动机号
                setStringValue(newRow, ++i, parseString(data.getEngineNo() == null ? "" : data.getEngineNo()));
                //车主
                setStringValue(newRow, ++i, parseString(data.getCarOwner() == null ? "" : data.getCarOwner()));
                //车主电话
                setStringValue(newRow, ++i, parseString(data.getCarOwnerPhone() == null ? "" : data.getCarOwnerPhone()));
                //销售时间
                setStringValue(newRow, ++i, parseString(data.getSaleDate() == null ? "" : dateTimeFormat.format(data.getSaleDate())));
                //经销商接口人
                setStringValue(newRow, ++i, parseString(data.getDealerContact() == null ? "" : data.getDealerContact()));
                //经销商联系方式
                setStringValue(newRow, ++i, parseString(data.getDealerTel() == null ? "" : data.getDealerTel()));
                //订单申请时间
                setStringValue(newRow, ++i, parseString(data.getOrderTime() == null ? "" : dateTimeFormat.format(data.getOrderTime())));
                //充电桩样式
                setStringValue(newRow, ++i, parseString(data.getPileTypeName() == null ? "" : data.getPileTypeName()));
                //安装地区
                setStringValue(newRow, ++i, parseString(data.getInstallProvince() + data.getInstallCity() == null ? "" : data.getInstallProvince() + data.getInstallCity()));
                //安装地址
                setStringValue(newRow, ++i, parseString(data.getInstallAddress() == null ? "" : data.getInstallAddress()));
                //安装联系人
                setStringValue(newRow, ++i, parseString(data.getInstallContact() == null ? "" : data.getInstallContact()));
                //联系人电话
                setStringValue(newRow, ++i, parseString(data.getInstallContactPhone() == null ? "" : data.getInstallContactPhone()));
                //订单号
                setStringValue(newRow, ++i, parseString(data.getOrderNo() == null ? "" : data.getOrderNo()));
                //服务商
                setStringValue(newRow, ++i, parseString(data.getSupplierName() == null ? "" : data.getSupplierName()));
                //服务商签收时间
                setStringValue(newRow, ++i, data.getReceiveTime() == null ? "" : dateTimeFormat.format(data.getReceiveTime()));
                //安装完成时间
                setStringValue(newRow, ++i, data.getInstallFinishTime() == null ? "" : dateTimeFormat.format(data.getInstallFinishTime()));
                //充电桩编码
                setStringValue(newRow, ++i, data.getPileCode() == null ? "" : data.getPileCode());
                //结束状态
                setStringValue(newRow, ++i, data.getSettleFlg() == null ? "" : data.getSettleFlg());
                //结束金额
                setStringValue(newRow, ++i, parseString(data.getSettleAmt() == 0 ? 0 : data.getSettleAmt()));
            }
        };
    }

    /**
     * 配送导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewDeliveryOrderEntity> delGenerateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewDeliveryOrderEntity>(exportConfig, translateService) {
            @Override
            public void setupExportRow(ViewDeliveryOrderEntity data, XSSFRow newRow) {
                int i = 0;
                //进度
                if (SysConstant.IS_DEL_Y.equals(data.getFinishFlg())) {
                    setStringValue(newRow, ++i, parseString("已完成"));
                } else {
                    setStringValue(newRow, ++i, parseString(data.getStepName() == null ? "" : data.getStepName()));
                }
                //开票状态
                if ("是".equals(data.getInvoiceStutas())){
                    setStringValue(newRow, ++i, "已开票");
                }else {
                    setStringValue(newRow, ++i, "未开票");
                }
                //经销商
                setStringValue(newRow, ++i, data.getDealerName() == null ? "" : data.getDealerName());
                //车型
                setStringValue(newRow, ++i, parseString(data.getCarVehicle() == null ? "" : data.getCarVehicle()));
                //配置
                setStringValue(newRow, ++i, parseString(data.getVehicleDetail() == null ? "" : data.getVehicleDetail()));
                //VIN
                setStringValue(newRow, ++i, parseString(data.getVinNo()));
                //发动机号
                setStringValue(newRow, ++i, parseString(data.getEngineNo() == null ? "" : data.getEngineNo()));
                //车主
                setStringValue(newRow, ++i, parseString(data.getCarOwner() == null ? "" : data.getCarOwner()));
                //车主电话
                setStringValue(newRow, ++i, parseString(data.getCarOwnerPhone() == null ? "" : data.getCarOwnerPhone()));
                //销售时间
                setStringValue(newRow, ++i, parseString(data.getSaleDate() == null ? "" : dateTimeFormat.format(data.getSaleDate())));
                //经销商接口人
                setStringValue(newRow, ++i, parseString(data.getDealerContact() == null ? "" : data.getDealerContact()));
                //经销商联系方式
                setStringValue(newRow, ++i, parseString(data.getDealerTel() == null ? "" : data.getDealerTel()));
                //订单申请时间
                setStringValue(newRow, ++i, parseString(data.getOrderTime() == null ? "" : dateTimeFormat.format(data.getOrderTime())));
                //充电桩样式
                setStringValue(newRow, ++i, parseString(data.getPileTypeName() == null ? "" : data.getPileTypeName()));
                //安装地区
                setStringValue(newRow, ++i, parseString(data.getInstallProvince() + data.getInstallCity() == null ? "" : data.getInstallProvince() + data.getInstallCity()));
                //安装地址
                setStringValue(newRow, ++i, parseString(data.getInstallAddress() == null ? "" : data.getInstallAddress()));
                //安装联系人
                setStringValue(newRow, ++i, parseString(data.getInstallContact() == null ? "" : data.getInstallContact()));
                //联系人电话
                setStringValue(newRow, ++i, parseString(data.getInstallContactPhone() == null ? "" : data.getInstallContactPhone()));
                //订单号
                setStringValue(newRow, ++i, parseString(data.getOrderNo() == null ? "" : data.getOrderNo()));
                //服务商
                setStringValue(newRow, ++i, parseString(data.getSupplierName() == null ? "" : data.getSupplierName()));
                //服务商签收时间
                setStringValue(newRow, ++i, data.getReceiveTime() == null ? "" : dateTimeFormat.format(data.getReceiveTime()));
                //安装完成时间
                setStringValue(newRow, ++i, data.getDeliveryFinishTime() == null ? "" : dateTimeFormat.format(data.getDeliveryFinishTime()));
                //充电桩编码
                setStringValue(newRow, ++i, data.getPileCode() == null ? "" : data.getPileCode());
                //结束状态
                setStringValue(newRow, ++i, data.getSettleFlg() == null ? "" : data.getSettleFlg());
                //结束金额
                setStringValue(newRow, ++i, parseString(data.getSettleAmt() == 0 ? 0 : data.getSettleAmt()));
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

