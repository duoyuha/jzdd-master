package cn.backend.controller.delivery;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.orderattach.IOrderAttachService;
import cn.backend.service.ordercancel.IOrderCancelService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.message.MasterExcelMessage;
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
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author sunkw
 * @date 2019/07/16
 */
@Controller
@RequestMapping(value = "/deliveryOrder")
@Api(description = "配送订单")
public class DeliveryOrderController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IDeliveryOrderService _deliveryOrderService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private DeliveryMasterExcelExportConfig deliveryMasterExcelExportConfig;

    @Autowired
    private IOrderCancelService orderCancelService;

    @Autowired
    private IInstallOrderService installOrderService;

    /**
     * 列表，分页查询功能
     *
     * @param viewDeliveryOrderQuery
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
                    name = "province",
                    value = "省",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "city",
                    value = "市",
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
                    name = "orderStartTime",
                    value = "订单创建开始时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "orderOverTime",
                    value = "订单创建结束时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "deliveryStartTime",
                    value = "配送开始时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "deliveryOverTime",
                    value = "配送结束时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "finishStartTime",
                    value = "订单完成开始时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "finishOverTime",
                    value = "订单完成结束时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "multipleQuery",
                    value = "多条件查询（VIN,订单号）",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "corpNo",
                    value = "公司",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "orderNo",
                    value = "订单编号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "carVehicle",
                    value = "车型",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "dispatchStartTime",
                    value = "派单开始时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "dispatchOverTime",
                    value = "派单结束时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            /*@ApiImplicitParam(
                    name = "finishFlg",
                    value = "是否完成",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),*/


    })

    public Page<ViewDeliveryOrderEntity> getList(@Validated({PageGroup.class}) ViewDeliveryOrderQuery viewDeliveryOrderQuery) {
       UserEntity userEntity = getUserEntity();
        viewDeliveryOrderQuery.setUserType(userEntity.getUserType());
        return viewDeliveryOrderService.findList(viewDeliveryOrderQuery);
    }

    /**
     * 保存
     *
     * @param deliveryOrder
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PostMapping()
    public DeliveryOrderEntity saveDeliveryOrder(DeliveryOrderEntity deliveryOrder,
                                                 @RequestParam(value = "explainFile", required = false) MultipartFile explainFile) {


        DeliveryOrderEntity deliveryOrderEntity = _deliveryOrderService.add(deliveryOrder);
        // UploadFileAttach(deliveryOrderEntity.getOrderNo(), explainFileList, SysConstant.orderAttach.CONFIG_ATTACH_TYPE_8);
        //删除已有附件（CRM退回涉及）
        List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findByOrderNo(deliveryOrderEntity.getOrderNo());
        if (orderAttachEntityList != null && orderAttachEntityList.size() > 0) {
            List<String> nos = orderAttachEntityList.stream().map(e -> e.getAttachNo()).collect(Collectors.toList());
            orderAttachService.delete(nos);
        }
        //保存附件
        if (explainFile != null) {
            UploadFile uploadFile = getFile(explainFile, SysConstant.Delivery.CONFIG_FOLDER_NAME_EXPLAIN);
            OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
            orderAttachEntity.setAttachPath(uploadFile.getViewPath());
            orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_8);
            orderAttachEntity.setFileName(uploadFile.getOriginalFileName());
            orderAttachEntity.setOrderNo(deliveryOrderEntity.getOrderNo());
            orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
            orderAttachService.add(orderAttachEntity);
        }
        return deliveryOrder;
    }

    private void UploadFileAttach(String orderNo, List<MultipartFile> explainFileList, String fileType) {

        if (explainFileList != null && explainFileList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(explainFileList, SysConstant.Delivery.CONFIG_FOLDER_NAME_EXPLAIN);
            int index = 1;
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(fileType);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(orderNo);
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
    }

    /**
     * 修改
     *
     * @param deliveryOrderEntity
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PostMapping("/edit")
    public DeliveryOrderEntity updateDelivery(@Validated({UpdateGroup.class}) DeliveryOrderEntity deliveryOrderEntity,
                                              @RequestParam(value = "wuliuFileList", required = false) List<MultipartFile> wuliuFileList,
                                              @RequestParam(value = "signFileList", required = false) List<MultipartFile> signFileList
    ) {
        //充电桩编号不能重复
        if (!StringUtils.isEmpty(deliveryOrderEntity.getPileCode())) {
            InstallOrderEntity installCheckEntity = installOrderService.findByPileCodeAndCorp(deliveryOrderEntity.getPileCode(), BaseContextHandler.getCorpNo());
            DeliveryOrderEntity deliveryCheckEntity = _deliveryOrderService.findByPileCodeAndCorp(deliveryOrderEntity.getPileCode(), BaseContextHandler.getCorpNo());
            if (installCheckEntity != null || (deliveryCheckEntity != null && !deliveryCheckEntity.getId().equals(deliveryOrderEntity.getId()))) {
                throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
            }
        }
        DeliveryOrderEntity entity;
        if (!StringUtils.isEmpty(deliveryOrderEntity.getSaveOrSubmit()) && SysConstant.InstallOrder.CONFIG_VERIFY_N.equals(deliveryOrderEntity.getSaveOrSubmit())) {
            entity = _deliveryOrderService.saveOrderAndStep(deliveryOrderEntity);
        } else {
            entity = _deliveryOrderService.edit(deliveryOrderEntity);
        }

        if (wuliuFileList != null && wuliuFileList.size() > 0) {
            UploadFileAttach(deliveryOrderEntity.getOrderNo(), wuliuFileList, SysConstant.orderAttach.CONFIG_ATTACH_TYPE_6);
        }
        if (signFileList != null && signFileList.size() > 0) {
            UploadFileAttach(deliveryOrderEntity.getOrderNo(), signFileList, SysConstant.orderAttach.CONFIG_ATTACH_TYPE_7);
        }
        //删除图片
        if (!StringUtils.isEmpty(deliveryOrderEntity.getDelAttachNos())) {
            String[] nos = deliveryOrderEntity.getDelAttachNos().split(SysConstant.COMMA);
            List<String> noList = new ArrayList<>();
            Collections.addAll(noList, nos);
            orderAttachService.delete(noList);
        }
        return entity;
    }

    /**
     * 修改
     *
     * @param deliveryOrderEntity
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PostMapping("/edit/grade")
    public DeliveryOrderEntity gradeDelivery(@Validated({UpdateGroup.class}) @RequestBody DeliveryOrderEntity deliveryOrderEntity){
        return _deliveryOrderService.edit(deliveryOrderEntity);
    }


    /**
     * 修改
     *
     * @param deliveryOrderEntity
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PostMapping("/test")
    public DeliveryOrderEntity updateDelivery( @RequestBody DeliveryOrderEntity deliveryOrderEntity
    ) {
        DeliveryOrderEntity entity;
        entity = _deliveryOrderService.edit(deliveryOrderEntity);


        return entity;
    }




    /**
     * 保存
     *
     * @param deliveryOrderEntity
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PutMapping()
    public DeliveryOrderEntity updateInstallOrder(@Validated({UpdateGroup.class}) @RequestBody DeliveryOrderEntity deliveryOrderEntity) {
        //充电桩编号不能重复
        if (!StringUtils.isEmpty(deliveryOrderEntity.getPileCode())) {
            InstallOrderEntity installCheckEntity = installOrderService.findByPileCodeAndCorp(deliveryOrderEntity.getPileCode(), BaseContextHandler.getCorpNo());
            DeliveryOrderEntity deliveryCheckEntity = _deliveryOrderService.findByPileCodeAndCorp(deliveryOrderEntity.getPileCode(), BaseContextHandler.getCorpNo());
            if (installCheckEntity != null || (deliveryCheckEntity != null && !deliveryCheckEntity.getId().equals(deliveryOrderEntity.getId()))) {
                throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
            }
        }
        return _deliveryOrderService.saveOrderAndStep(deliveryOrderEntity);
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
    public Boolean deleteDeliveryOrder(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _deliveryOrderService.delete(id);
    }

    /**
     * 根据no获取详情
     *
     * @param no
     * @return
     */
    @ApiOperation(value = "根据no获取", notes = "根据no获取视图分页详情")
    @ApiImplicitParam(name = "no", value = "no", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/{no}")
    public ViewDeliveryOrderEntity findViewDelByOrderNo(@PathVariable String no) {
        return viewDeliveryOrderService.findByNo(no);
    }

    /**
     * 根据no获取详情
     *
     * @param orderno
     * @return
     */
    @ApiOperation(value = "根据no获取", notes = "根据no获取详情")
    @ApiImplicitParam(name = "orderno", value = "orderno", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "find/{orderno}")
    public DeliveryOrderEntity findDeliveryOrderByOrderNo(@PathVariable String orderno) {
        DeliveryOrderEntity entity = _deliveryOrderService.findByNo(orderno);
        if (entity != null) {
            List<OrderAttachEntity> list = orderAttachService.findByOrderNo(entity.getOrderNo());
            for (OrderAttachEntity orderAttachEntity : list) {
                orderAttachEntity.setAttachPath(SysConstant.ATTACH_VISIT_PERF + orderAttachEntity.getAttachPath());
            }
            if (list != null && list.size() > 0) {
                entity.setOrderAttachEntityList(list);
            }
        }
        return entity;
    }

    /**
     * 撤回操作
     *
     * @param deliveryOrderEntity
     * @return
     */
    @ApiOperation(value = "撤回操作", notes = "撤回操作")
    @ApiImplicitParam(name = "deliveryOrderEntity", value = "实体deliveryOrderEntity", required = true, dataType = "DeliveryOrderEntity")
    @CustomResult
    @PostMapping("/return")
    public DeliveryOrderEntity applyReturn(@RequestBody DeliveryOrderEntity deliveryOrderEntity) {
        return _deliveryOrderService.deliveryReturn(deliveryOrderEntity.getOrderNo(), deliveryOrderEntity.getRemark());
    }

    /**
     * 根据vin查询
     *
     * @param vin
     * @return
     */
    @ApiOperation(value = "根据vin查询", notes = "根据vin查询")
    @ApiImplicitParam(name = "vin", value = "vin", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping("/findbyvin/{vin}")
    public DeliveryOrderEntity applyReturn(@PathVariable String vin) {
        UserEntity userEntity = getUserEntity();
        return _deliveryOrderService.findByVin(vin, userEntity.getCorpNo());

    }

    /**
     * 作废
     *
     * @param orderCancelEntity
     * @return
     */
    @ApiOperation(value = "作废", notes = "作废接口:Query中传三个参数：orderNo,cancelVerify(初审01通过，02没通过),cancelVerifySecond(复审01通过，02没通过)")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "orderNo",
                    value = "订单编码",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "cancelVerify",
                    value = "作废：初审 01通过，02没通过",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "cancelVerifySecond",
                    value = "作废：复审 01通过，02没通过",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),

    })
    @CustomResult
    @PostMapping("/cancel")
    public OrderCancelEntity cancel(@RequestBody OrderCancelEntity orderCancelEntity) {
        return orderCancelService.editDelivery(orderCancelEntity);
    }

    /**
     * 申请作废
     *
     * @param orderCancelEntity
     * @return
     */
    @ApiOperation(value = "申请作废", notes = "申请")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "orderNo",
                    value = "订单编码",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),

    })
    @CustomResult
    @PostMapping("/apply")
    public OrderCancelEntity applyCancel(@RequestBody OrderCancelEntity orderCancelEntity) {
        // installOrderQuery.setCorpNo(getCorpNo());
        return orderCancelService.addDelivery(orderCancelEntity);
    }

    /**
     * 获得已撤回订单
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获得已撤回订单", notes = "获得已撤回订单")
    @CustomResult
    @GetMapping("/rollback")
    public List<DeliveryOrderEntity> findRollBackOrder(DeliveryOrderQuery query) {
        List<DeliveryOrderEntity> deliveryOrderEntityList = _deliveryOrderService.findRollBackOrder(SysConstant.InstallOrder.CONFIG_FLG_Y, query.getVin(), getCorpNo());
        for(DeliveryOrderEntity item : deliveryOrderEntityList){
            OrderAttachQuery orderAttachQuery = new OrderAttachQuery();
            orderAttachQuery.setOrderNo(item.getOrderNo());
            orderAttachQuery.setSort("+createdWhen");
            List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findAll(orderAttachQuery);
            if (orderAttachEntityList == null) {
                orderAttachEntityList = new ArrayList<>();
            } else {
                for (OrderAttachEntity attach : orderAttachEntityList) {
                    attach.setAttachPath(SysConstant.ATTACH_VISIT_PERF + attach.getAttachPath());
                }
            }
            item.setOrderAttachEntityList(orderAttachEntityList);

        }
        return deliveryOrderEntityList;
    }

    /**
     * 导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @CustomResult
    @GetMapping(value = "export")
    public DeliveryOrderController.UI_ExcelImportResponse export(ViewDeliveryOrderQuery query) {
        UserEntity userEntity = getUserEntity();
        query.setUserType(userEntity.getUserType());
        List<ViewDeliveryOrderEntity> entities = viewDeliveryOrderService.findAll(query);
        MasterExcelExporter<ViewDeliveryOrderEntity> exporter = generateMasterExcelExporter();
        XSSFWorkbook workbook = exporter.exportWorkbook(entities);
        String encoded = MasterExcelExporter.toBase64(workbook);
        DeliveryOrderController.UI_ExcelImportResponse responseBody = new DeliveryOrderController.UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("delivery.xlsx");
        // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        return responseBody;
    }

    /**
     * 导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewDeliveryOrderEntity> generateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewDeliveryOrderEntity>(deliveryMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(ViewDeliveryOrderEntity data, XSSFRow newRow) {
                int i = 0;
                //进度
                if (SysConstant.IS_DEL_Y.equals(data.getFinishFlg())){
                    setStringValue(newRow, ++i, parseString("已完成"));
                }else {
                    setStringValue(newRow, ++i, parseString(data.getStepName() == null ? "" : data.getStepName()));
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
                setStringValue(newRow, ++i, parseString(data.getSaleDate()==null ?"":dateTimeFormat.format(data.getSaleDate())));
                //经销商接口人
                setStringValue(newRow, ++i, parseString(data.getDealerContact() == null ? "" : data.getDealerContact()));
                //经销商联系方式
                setStringValue(newRow, ++i, parseString(data.getDealerTel() == null ? "" : data.getDealerTel()));
                //订单申请时间
                setStringValue(newRow, ++i, parseString(data.getOrderTime() == null ? "" : dateTimeFormat.format(data.getOrderTime())));
                //充电桩样式
                setStringValue(newRow, ++i, parseString(data.getPileTypeName() == null ? "" : data.getPileTypeName()));
                //安装地区
                setStringValue(newRow, ++i, parseString(data.getInstallProvince()+data.getInstallCity()==null?"":data.getInstallProvince()+data.getInstallCity()));
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
                //派单时间
                setStringValue(newRow, ++i, parseString(data.getDispatchTime()==null ?"":dateTimeFormat.format(data.getDispatchTime())));
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

