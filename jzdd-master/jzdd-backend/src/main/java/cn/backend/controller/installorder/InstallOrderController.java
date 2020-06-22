package cn.backend.controller.installorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.orderattach.IOrderAttachService;
import cn.backend.service.ordercancel.IOrderCancelService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
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

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/installOrder")
@Api(description = "安装订单对应的接口")
public class InstallOrderController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IInstallOrderService _installOrderService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private InstallMasterExcelExportConfig installMasterExcelExportConfig;

    @Autowired
    private IOrderCancelService orderCancelService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    /**
     * 列表，分页查询功能
     *
     * @param installOrderQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "Web及App获取订单分页列表的接口")
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
                    name = "installProvince",
                    value = "省",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "installCity",
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
                    name = "orderTimeStart",
                    value = "订单创建日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "orderTimeEnd",
                    value = "订单创建日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "appointmentTimeStart",
                    value = "预约勘察日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "appointmentTimeEnd",
                    value = "预约勘察日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "checkTimeStart",
                    value = "勘察完成日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "checkTimeEnd",
                    value = "勘察完成日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "appointmentInstallTimeStart",
                    value = "预约安装日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "appointmentInstallTimeEnd",
                    value = "预约安装日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "installFinishTimeStart",
                    value = "安装完成日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "installFinishTimeEnd",
                    value = "安装完成日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "orderFinishTimeStart",
                    value = "订单完成日期开始",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "orderFinishTimeEnd",
                    value = "订单完成日期结束",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "multipleQuery",
                    value = "多条件查询",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "corpNo",
                    value = "公司",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "noUseSteps",
                    value = "不需要的步骤",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "需要的步骤",
                    value = "useSteps",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "beginCacle",
                    value = "是否开始做废动作 Y 是 N 否",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "settleFlg",
                    value = "是否结算 Y 是 N 否",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "finishFlg",
                    value = "是否完成 Y 是 N 否",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "inspectionTourFlg",
                    value = "是否巡检 Y 是 N 否",
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
    })
    public Page<ViewInstallOrderEntity> getList(@Validated({PageGroup.class}) InstallOrderQuery installOrderQuery) {
        // UserEntity userEntity = getUserEntity();
        // installOrderQuery.setUserPosition(userEntity.getUserType());
        UserEntity userEntity = getUserEntity();
        installOrderQuery.setUserType(userEntity.getUserType());
        // if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {
        //     installOrderQuery.setSupplierNo(userEntity.getSupplierNo());
        // }
        return installOrderViewService.findList(installOrderQuery);
    }

    /**
     * 保存
     *
     * @param installOrder
     * @return
     */
    @ApiOperation(value = "新增", notes = "提单调用接口，传入参数：页面参数 + VIN")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping()
    public InstallOrderEntity saveInstallOrder(@RequestParam(value = "file", required = false) MultipartFile file, InstallOrderEntity installOrder) {
        // return _installOrderService.submitOrder(installOrder);
        // UserEntity userEntity = getUserEntity();
        // installOrder.setCorpNo(userEntity.getCorpNo());
        InstallOrderEntity installOrderEntity = _installOrderService.addInstall(installOrder);
        //删除已有附件（CRM退回涉及）
        List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findByOrderNo(installOrderEntity.getOrderNo());
        if (orderAttachEntityList != null && orderAttachEntityList.size() > 0) {
            List<String> nos = orderAttachEntityList.stream().map(e -> e.getAttachNo()).collect(Collectors.toList());
            orderAttachService.delete(nos);
        }
        //保存附件
        int index = 1;
        if (file != null) {
            UploadFile uploadFile = getFile(file, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_SUBMIT);
            OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
            orderAttachEntity.setAttachPath(uploadFile.getViewPath());
            orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_9);
            orderAttachEntity.setFileName(uploadFile.getOriginalFileName());
            orderAttachEntity.setOrderNo(installOrderEntity.getOrderNo());
            orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
            orderAttachEntity.setAttachDisp(index++);
            orderAttachService.add(orderAttachEntity);
        }
        return installOrderEntity;
    }

    /**
     * 安装订单-流程
     *
     * @param installOrder
     * @return
     */
    @ApiOperation(value = "安装订单-无图片流程", notes = "安装订单流程都调用此接口，需要在调用findbyid的情况下将得到的参数修改，并将完整的对象返送回来")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping("/workfollow")
    public InstallOrderEntity installOrderWorkFollow(@RequestBody InstallOrderEntity installOrder) {
        // return _installOrderService.orderWorkFollow(installOrder);
        //充电桩编号不能重复
        if (!StringUtils.isEmpty(installOrder.getPileCode())) {
            InstallOrderEntity installCheckEntity = _installOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
            DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
            if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals(installOrder.getId()))) {
                throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
            }
        }
        return _installOrderService.editInstall(installOrder);

    }

    /**
     * 安装订单-附件
     *
     * @param file
     * @param orderNo
     * @param attachType
     * @return
     */
    @ApiOperation(value = "安装订单-附件", notes = "安装订单-附件上传（与流程接口分离）")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping("/file")
    public OrderAttachEntity installOrderWorkFollow(@RequestParam(value = "file", required = false) MultipartFile file,
                                                    String orderNo, String attachType) {
        String folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME;
        switch (attachType) {
            case "01":
                folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME_PLAN;
                break;
            case "02":
                folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME_CHECKOTHER;
                break;
            case "03":
                folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME_GROUP;
                break;
            case "04":
                folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME_CONFIRM;
                break;
            case "05":
                folderName = SysConstant.InstallOrder.CONFIG_FOLDER_NAME_INSTALLOTHER;
                break;
        }
        UploadFile uploadFile = null;
        if (file != null) {
            uploadFile = getFile(file, folderName);
        }
        OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
        orderAttachEntity.setAttachPath(uploadFile.getViewPath());
        orderAttachEntity.setAttachType(attachType);
        orderAttachEntity.setFileName(uploadFile.getOriginalFileName());
        orderAttachEntity.setOrderNo(orderNo);
        orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        return orderAttachService.add(orderAttachEntity);
    }

    /**
     * 安装订单-勘察 安装方案 其它附件
     *
     * @param installOrder
     * @return
     */
    @ApiOperation(value = "安装订单-勘察", notes = "安装订单-勘察流程专用，对象与文件一起传输")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping("/check")
    @Transactional
    public InstallOrderEntity installOrderCheck(@RequestParam(value = "plan", required = false) List<MultipartFile> planList,
                                                @RequestParam(value = "other", required = false) List<MultipartFile> otherList,
                                                InstallOrderEntity installOrder) {
        int index = 1;
        //安装方案
        if (planList != null && planList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(planList, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_PLAN);
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_1);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(installOrder.getOrderNo());
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
        index = 1;
        //其它附件
        if (otherList != null && otherList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(otherList, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_CHECKOTHER);
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_2);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(installOrder.getOrderNo());
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
        //删除图片
        if (!StringUtils.isEmpty(installOrder.getDelAttachNos())) {
            String[] nos = installOrder.getDelAttachNos().split(SysConstant.COMMA);
            List<String> noList = new ArrayList<>();
            Collections.addAll(noList, nos);
            orderAttachService.delete(noList);
        }
        //保存还是提交 01保存 02提交
        if (!StringUtils.isEmpty(installOrder.getSaveOrSubmit()) && SysConstant.InstallOrder.CONFIG_VERIFY_N.equals(installOrder.getSaveOrSubmit())) {
            //保存
            return _installOrderService.saveOrderAndStep(installOrder);
        } else if (!StringUtils.isEmpty(installOrder.getSaveOrSubmit()) && SysConstant.InstallOrder.CONFIG_VERIFY_Y.equals(installOrder.getSaveOrSubmit())) {
            //提交
            // return _installOrderService.orderWorkFollow(installOrder);
            return _installOrderService.editInstall(installOrder);
        } else {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }

    /**
     * 安装订单-安装 人桩合影 安装确认书 其它附件
     *
     * @param installOrder
     * @return
     */
    @ApiOperation(value = "安装订单-安装", notes = "安装订单-安装流程专用，对象与文件一起传输")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping("/install")
    @Transactional
    public InstallOrderEntity installOrderInstall(@RequestParam(value = "group", required = false) List<MultipartFile> groupList,
                                                  @RequestParam(value = "confirm", required = false) List<MultipartFile> confirmList,
                                                  @RequestParam(value = "other", required = false) List<MultipartFile> otherList,
                                                  InstallOrderEntity installOrder) {
        ViewInstallOrderEntity viewInstallOrderEntity=installOrderViewService.findByOrderNo(installOrder.getOrderNo(),installOrder.getCorpNo());
        String secondInstall = SysConstant.InstallOrder.CONFIG_FLG_N;
        if (viewInstallOrderEntity!=null){
            logger.info("viewInstallOrderEntity不为空");
            OrderCarEntity orderCarEntity = orderCarService.findByVinNoAndCorp(viewInstallOrderEntity.getVinNo(), installOrder.getCorpNo());
            if (orderCarEntity != null) {
                logger.info("orderCarEntity不为空");
                //20190903 增加查询二次安装
                VehicleDetailEntity vehicleDetailEntity =
                        vehicleDetailService.findByDetailNameAndCorp(orderCarEntity.getVehicleDetail(),
                                orderCarEntity.getCorpNo());
                if (vehicleDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(vehicleDetailEntity.getSecondInstall())) {
                    logger.info("vehicleDetailEntity不为空");
                    // secondInstall = vehicleDetailEntity.getSecondInstall();
                    DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService
                            .findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
                    InstallOrderEntity installCheckEntity = _installOrderService
                            .findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
                    if(deliveryCheckEntity !=null && installCheckEntity == null){
                        secondInstall = SysConstant.InstallOrder.CONFIG_FLG_Y;
                    }
                }
            }
        }
        if (SysConstant.InstallOrder.CONFIG_FLG_N.equals(secondInstall)){
            //充电桩编号不能重复
            logger.info("检查充电桩是否重复");
            if (!StringUtils.isEmpty(installOrder.getPileCode())) {
                logger.info("充电桩码不为空");
                InstallOrderEntity installCheckEntity = _installOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
                DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
                logger.info("installCheckEntity: "+installCheckEntity);
                logger.info("deliveryCheckEntity: "+deliveryCheckEntity);
                if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals(installOrder.getId()))) {
                    throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
                }
            }
        }
        int index = 1;
        //人桩合影
        if (groupList != null && groupList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(groupList, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_GROUP);
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_3);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(installOrder.getOrderNo());
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
        //安装确认书
        index = 1;
        if (confirmList != null && confirmList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(confirmList, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_CONFIRM);
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_4);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(installOrder.getOrderNo());
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
        //其它附件
        index = 1;
        if (otherList != null && otherList.size() > 0) {
            List<UploadFile> uploadFileList = getFile(otherList, SysConstant.InstallOrder.CONFIG_FOLDER_NAME_INSTALLOTHER);
            for (UploadFile item : uploadFileList) {
                OrderAttachEntity orderAttachEntity = new OrderAttachEntity();
                orderAttachEntity.setAttachPath(item.getViewPath());
                orderAttachEntity.setAttachType(SysConstant.orderAttach.CONFIG_ATTACH_TYPE_5);
                orderAttachEntity.setFileName(item.getOriginalFileName());
                orderAttachEntity.setOrderNo(installOrder.getOrderNo());
                orderAttachEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                orderAttachEntity.setAttachDisp(index++);
                orderAttachService.add(orderAttachEntity);
            }
        }
        //删除图片
        if (!StringUtils.isEmpty(installOrder.getDelAttachNos())) {
            String[] nos = installOrder.getDelAttachNos().split(SysConstant.COMMA);
            List<String> noList = new ArrayList<>();
            Collections.addAll(noList, nos);
            orderAttachService.delete(noList);
        }
        //installOrder.setOrderAttachEntityList(null);
        //保存还是提交 01保存 02提交
        if (!StringUtils.isEmpty(installOrder.getSaveOrSubmit()) && SysConstant.InstallOrder.CONFIG_VERIFY_N.equals(installOrder.getSaveOrSubmit())) {
            //保存
            return _installOrderService.saveOrderAndStep(installOrder);
        } else if (!StringUtils.isEmpty(installOrder.getSaveOrSubmit()) && SysConstant.InstallOrder.CONFIG_VERIFY_Y.equals(installOrder.getSaveOrSubmit())) {
            //提交
            // return _installOrderService.orderWorkFollow(installOrder);
            return _installOrderService.editInstall(installOrder);
        } else {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }

    /**
     * 修改
     *
     * @param installOrder
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PutMapping()
    public InstallOrderEntity updateInstallOrder(@Validated({UpdateGroup.class}) @RequestBody InstallOrderEntity installOrder) {
        //充电桩编号不能重复
        if (!StringUtils.isEmpty(installOrder.getPileCode())) {
            InstallOrderEntity installCheckEntity = _installOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
            DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(installOrder.getPileCode(), BaseContextHandler.getCorpNo());
            if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals(installOrder.getId()))) {
                throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
            }
        }
        return _installOrderService.saveOrderAndStep(installOrder);
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
    public Boolean deleteInstallOrder(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _installOrderService.delete(id);
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
    public ViewInstallOrderEntity findInstallOrderById(@PathVariable String id) {
        return installOrderViewService.findById(id);
    }

    /**
     * 根据id获取详情
     *
     * @param no
     * @return
     */
    @ApiOperation(value = "根据no获取", notes = "根据no获取详情")
    @ApiImplicitParam(name = "no", value = "no", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "findbyno/{no}")
    public ViewInstallOrderEntity findInstallOrderByNo(@PathVariable String no) {
        UserEntity userEntity = getUserEntity();
        return installOrderViewService.findByOrderNo(no, userEntity.getCorpNo());
    }

    /**
     * 撤回操作
     *
     * @param installOrderEntity
     * @return
     */
    @ApiOperation(value = "撤回操作", notes = "撤回操作")
    @ApiImplicitParam(name = "installOrderEntity", value = "实体installOrderEntity", required = true, dataType = "InstallOrderEntity")
    @CustomResult
    @PostMapping("/return")
    public InstallOrderEntity applyReturn(@RequestBody InstallOrderEntity installOrderEntity) {
        return _installOrderService.installReturn(installOrderEntity.getOrderNo(), installOrderEntity.getRemark());
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
    public InstallOrderEntity applyReturn(@PathVariable String vin) {
        UserEntity userEntity = getUserEntity();
        InstallOrderEntity installOrderEntity = _installOrderService.findByVin(vin, userEntity.getCorpNo());
        if (installOrderEntity != null) {
            OrderAttachQuery orderAttachQuery = new OrderAttachQuery();
            orderAttachQuery.setOrderNo(installOrderEntity.getOrderNo());
            orderAttachQuery.setSort("+createdWhen");
            List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findAll(orderAttachQuery);
            if (orderAttachEntityList == null) {
                orderAttachEntityList = new ArrayList<>();
            } else {
                for (OrderAttachEntity item : orderAttachEntityList) {
                    item.setAttachPath(SysConstant.ATTACH_VISIT_PERF + item.getAttachPath());
                }
            }
            installOrderEntity.setOrderAttachEntityList(orderAttachEntityList);
        }
        return installOrderEntity;
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
        return orderCancelService.editInsatll(orderCancelEntity);
    }

    /**
     * 申请作废
     *
     * @param orderCancelEntity
     * @return
     */
    @ApiOperation(value = "申请作废", notes = "申请")

    @CustomResult
    @PostMapping("/apply")
    public OrderCancelEntity applyCancel(@RequestBody OrderCancelEntity orderCancelEntity) {
        // installOrderQuery.setCorpNo(getCorpNo());
        return orderCancelService.addInstall(orderCancelEntity);
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
    public List<InstallOrderEntity> findRollBackOrder(InstallOrderQuery query) {
        List<InstallOrderEntity> installOrderEntityList = _installOrderService.findRollBackOrder(SysConstant.InstallOrder.CONFIG_FLG_Y, query.getVinNo(), getCorpNo());
        for(InstallOrderEntity item : installOrderEntityList){
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
        return installOrderEntityList;
    }

    /**
     * 导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @CustomResult
    @GetMapping(value = "export")
    public InstallOrderController.UI_ExcelImportResponse export(InstallOrderQuery query) {
        //UserEntity userEntity = getUserEntity();
        //query.setUserType(userEntity.getUserType());
        List<ViewInstallOrderEntity> entities = installOrderViewService.findAll(query);
        MasterExcelExporter<ViewInstallOrderEntity> exporter = generateMasterExcelExporter();
        XSSFWorkbook workbook = exporter.exportWorkbook(entities);
        String encoded = MasterExcelExporter.toBase64(workbook);
        InstallOrderController.UI_ExcelImportResponse responseBody = new InstallOrderController.UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("install.xlsx");
        // crateFile(encoded,"C:\\Users\\user02\\Desktop\\settleExport.xlsx");
        return responseBody;
    }

    /**
     * 导出操作
     *
     * @return
     */
    private MasterExcelExporter<ViewInstallOrderEntity> generateMasterExcelExporter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<ViewInstallOrderEntity>(installMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(ViewInstallOrderEntity data, XSSFRow newRow) {
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
                //派单时间
                setStringValue(newRow, ++i, parseString(data.getDispatchTime()==null ?"":dateTimeFormat.format(data.getDispatchTime())));
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

    @Data
    private static class UI_ExcelImportResponse {
        private String base64;
        private String fileName;
        private List<MasterExcelMessage.TranslatedMessage> messageList;
    }


}