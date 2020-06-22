package cn.backend.controller.inpection;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.inspection.InspectionEntity;
import cn.backend.model.primary.inspection.InspectionQuery;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.inspection.IInspectionService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.CreateGroup;
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
 *
 * @author sunkw
 * @date 2019/07/29
 */
@Controller
@RequestMapping(value = "/inspection")
@Api(description = "用于获取是否巡检数据")
public class InspectionController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IInspectionService _inspectionService;

    @Autowired
    private IInstallOrderService installOrderService;

//    @Autowired
    private IInstallOrderViewService installOrderViewService;



    /**
     * 列表，分页查询功能
     *
     * @param installOrderQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表",
            notes = "用于获取已经巡检数据和未巡检数据" +
                    "<ul>" +
                    "<li>通过inspectionTourFlg来区分</li>" +
                    "<li>Y表示已经做过巡检 N表示未巡检</li>" +
                    "</ul>")
    //@ApiImplicitParam(name = "inspectionQuery", value = "查询实体inspectionQuery", required = true, dataType = "InspectionQuery")
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
                    name = "inspectionTourFlg",
                    value = "是否巡检标志",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)


       })
    public Page<ViewInstallOrderEntity> getList(@Validated({PageGroup.class}) InstallOrderQuery installOrderQuery){

        installOrderQuery.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);

        Page<ViewInstallOrderEntity>  page= installOrderViewService.findList(installOrderQuery);

        for(ViewInstallOrderEntity item : page){

            InspectionEntity inspectionEntity=  _inspectionService.findByNo(item.getOrderNo());
            if(inspectionEntity!=null)
            item.setInspectionEntity(inspectionEntity);

        }

        return  page;
    }


    /**
     * 列表，分页查询功能
     *
     * @param inspectionQuery
     * @return
     */
    @CustomResult
    @GetMapping("page")
    @ApiOperation(value = "分页列表",
            notes = "用于获取已经巡检数据和未巡检数据" +
                    "<ul>" +
                    "<li>通过inspectionTourFlg来区分</li>" +
                    "<li>Y表示已经做过巡检 N表示未巡检</li>" +
                    "</ul>")
    //@ApiImplicitParam(name = "inspectionQuery", value = "查询实体inspectionQuery", required = true, dataType = "InspectionQuery")
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
                    name = "inspectionTourFlg",
                    value = "是否巡检标志",
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
                    name = "inspectionStatus",
                    value = "巡检状态",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })

    public Page<InspectionEntity> getPage(@Validated({PageGroup.class}) InspectionQuery inspectionQuery){
        Page<InspectionEntity>  page= _inspectionService.findList(inspectionQuery);

        return  page;
    }

    /**
     * 新增
     *
     * @param inspection
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "inspectionEntity", value = "实体inspectionEntity", required = true, dataType = "InspectionEntity")
    @CustomResult
    @PostMapping("addlist")
    public List<InspectionEntity> saveInspectionList(@RequestBody InspectionEntity inspection) {
        return _inspectionService.addList2(inspection);

    }


    /**
     * 修改
     *
     * @param inspection
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "inspectionEntity", value = "实体inspectionEntity", required = true, dataType = "InspectionEntity")
    @CustomResult
    @PostMapping("edit")
    public InspectionEntity updateInspection(@Validated({UpdateGroup.class})  InspectionEntity inspection, @RequestParam(value = "file", required = false) MultipartFile file) {

        InspectionEntity old =   _inspectionService.checkById(inspection.getId());
        old.setInspectionTime(inspection.getInspectionTime());
        old.setInspectionDesc(inspection.getInspectionDesc());
        old.setInspectionResult(inspection.getInspectionResult());

        old.setInspectionStatus(SysConstant.Inspetcion.CHECK_FLG_YES);
//        old.setOrderNo(inspection.getOrderNo());
        if(file!=null){
            UploadFile uploadFile = getFile(file, SysConstant.Inspetcion.CONFIG_FOLDER_NAME_INSPECTION);
            old.setFileName(uploadFile.getOriginalFileName());
            old.setAttachPath(uploadFile.getViewPath());
        }
        return _inspectionService.edit(old);
    }

    /**
     *创建新的巡检记录 同时保存文件名以及文件路径 附件
     * @param inspection
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "inspectionEntity", value = "实体inspectionEntity", required = true, dataType = "InspectionEntity")
    @CustomResult
    @PostMapping()
    public List<InspectionEntity> saveInspection(@Validated({CreateGroup.class})  @RequestBody InspectionEntity inspection) {
        inspection.setInspectionPerson(getUserEntity().getUserName());
        return _inspectionService.addList(inspection);
    }
}

