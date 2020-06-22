package cn.backend.controller.vin;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.vin.VinEntity;
import cn.backend.model.primary.vin.VinQuery;
import cn.backend.service.vin.IVinService;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.excel.MasterExcelImporter;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author sunkw
 * @date 2019/07/08
 */
@Controller
@RequestMapping(value = "/vin")
@Api(description = "VIN对应的接口")
public class VinController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IVinService _vinService;

    @Autowired
    private VinMasterExcelExportConfig vinMasterExcelExportConfig;

    @Autowired
    private Validator validator;

    /**
     * 列表，分页查询功能
     *
     * @param vinQuery
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
                    name = "vinCode",
                    value = "vin编码",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "fromTime",
                    value = "创建时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "toTime",
                    value = "创建时间",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
    })

    /**
     * 查询
     *
     * @param vin
     * @return
     */
    public Page<VinEntity> getList(@Validated({PageGroup.class}) VinQuery vinQuery) {
        return _vinService.findList(vinQuery);
    }

    /**
     * 根据创建时间批量删除
     *
     * @param  vinQuery
     * @return
     */
    @ApiOperation(value = "根据根据创建时间获取", notes = "根据创建时间获取详情")
    @ApiImplicitParam(name = "vinQuery", value = "vinQuery", required = true, dataType = "VinQuery", paramType = "path")
    @CustomResult
    @PostMapping(value = "/delall")
    public Boolean deleteGetList(@RequestBody VinQuery vinQuery) {
        return _vinService.delete(vinQuery);
    }

    /**
     * 新增
     *
     * @param vin
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "vinEntity", value = "实体vinEntity", required = true, dataType = "VinEntity")
    @CustomResult
    @PostMapping()
    public VinEntity saveVin(@Validated({CreateGroup.class}) @RequestBody VinEntity vin) {
        return _vinService.add(vin);
    }

    /**
     * 修改
     *
     * @param vin
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "vinEntity", value = "实体vinEntity", required = true, dataType = "VinEntity")
    @CustomResult
    @PutMapping()
    public VinEntity updateVin(@Validated({UpdateGroup.class}) @RequestBody VinEntity vin) {
        return _vinService.edit(vin);
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
    public Boolean deleteVin(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _vinService.delete(id);
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
    public VinEntity findVinById(@PathVariable String id) {
        return _vinService.findById(id);
    }

    @Data
    private static class ExcelExoprtEntity{
        private String base64;
        private String fileName;
        private List<MasterExcelMessage.TranslatedMessage> messageList;
    }

    /**
     * 模板导出功能
     * @param query
     * @return
     */
    @ApiOperation(value = "导出", notes = "根据查询条件导出")
    @CustomResult
    @GetMapping(value = "/export")
    @ApiImplicitParam(name = "corpQuery", value = "查询实体corpQuery", required = true, dataType = "CorpQuery")
    public ExcelExoprtEntity export(VinQuery query) throws IOException {

        query.setCorpNo(SysConstant.IS_DEL_N);
        List<VinEntity> result = _vinService.findAll(query);
        MasterExcelExporter<VinEntity> exporter = generateMasterExcelExporter();
        XSSFWorkbook workbook = exporter.exportWorkbook(result);
        String encoded = exporter.toBase64(workbook);
        ExcelExoprtEntity excelExoprtEntity = new ExcelExoprtEntity();
        excelExoprtEntity.setBase64(encoded);
        excelExoprtEntity.setFileName("vin.xlsx");
        return excelExoprtEntity;
    }
     /**
     *模板导出操作
     * @return
            */
    private MasterExcelExporter<VinEntity> generateMasterExcelExporter() {
        return new MasterExcelExporter<VinEntity>(vinMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(VinEntity data, XSSFRow newRow) {
                int i = 1;
                setStringValue(newRow, i++, parseString(data.getVinCode()));
                setStringValue(newRow, i++, parseString(data.getRemark()==null?"":data.getRemark()));
            }
        };
    }

    @Data
    private static class ExcelExoprtEntityt{
        private String base64;
        private String fileName;
        private List<MasterExcelMessage.TranslatedMessage> messageList;
    }

    /**
     * 导出功能
     * @param query
     * @return
     */
    //@ApiOperation(value = "导出", notes = "根据查询条件导出")
    @CustomResult
    @GetMapping(value = "/exportall")
    @ApiImplicitParam(name = "corpQuery", value = "查询实体corpQuery", required = true, dataType = "CorpQuery")
    public ExcelExoprtEntityt exportt(VinQuery query) throws IOException {

        query.setCorpNo(getUserEntity().getCorpNo());
        List<VinEntity> result = _vinService.findAll(query);
        MasterExcelExporter<VinEntity> exporter = generateMasterExcelExportert();
        XSSFWorkbook workbook = exporter.exportWorkbook(result);
        String encoded = exporter.toBase64(workbook);
        VinController.ExcelExoprtEntityt excelExoprtEntity = new ExcelExoprtEntityt();
        excelExoprtEntity.setBase64(encoded);
        excelExoprtEntity.setFileName("vin.xlsx");
        return excelExoprtEntity;
    }
    /**
     * 导出操作
     * @return
     */
    private MasterExcelExporter<VinEntity> generateMasterExcelExportert() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new MasterExcelExporter<VinEntity>(vinMasterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(VinEntity data, XSSFRow newRow) {
                int i = 0;
                setStringValue(newRow, ++i, parseString(data.getVinCode()));
                setStringValue(newRow, ++i, parseString(data.getRemark()==null?"":data.getRemark()));
            }
        };
    }


    /**
     * 导入功能
     *
     * @return
     */
    @CustomResult
    //@ApiOperation(value = "导入", notes = "从excel导入")
    @RequestMapping(value = "excel", method = {RequestMethod.PATCH, RequestMethod.POST})
    public UI_ExcelImportResponse excel(@RequestParam Part file,VinQuery query) throws IOException {
        MasterExcelImporter<VinController> importer = new MasterExcelImporter<VinController>(vinMasterExcelExportConfig, translateService) {
            @Override
            public void importRow(XSSFRow dataRow, VinController supportClass) {
                VinEntity rec = new VinEntity();
                SetValueResult setValueResult = setValue(rec, dataRow, columnConfigList);
                if (setValueResult.getErrorCount() > 0) {
                    importFail(dataRow);
                    return;
                }
                if (setValueResult.getNotNullCount() == 0) {
                    return;
                }
                Set<ConstraintViolation<VinEntity>> validated = supportClass.validate(rec);
                //根据名称获取代码
                rec.setCorpNo(query.getCorpNo());
                rec = setDbValue(rec);
                //做逻辑判断和校验
                //RT_NAME存在则不导入
                if (rec == null) {
                    setErrorMessage(dataRow, columnConfigList, validated);
                    importFail(dataRow);
                    return;
                }
                //必填字段校验
                // if (rec.getRtStatus() == null) {
                //     setErrorMessage(dataRow, columnConfigList, validated);
                //     importFail(dataRow);
                //     return;
                // }
//                if (rec.getSerMode() == null) {
//                    setErrorMessage(dataRow, columnConfigList, validated);
//                    importFail(dataRow);
//                    return;
//                }

                if (validated.size() == 0) {
                    //写入数据库
                    supportClass.excelImportSub(rec);
                    importSuccess(dataRow);
                } else {
                    setErrorMessage(dataRow, columnConfigList, validated);
                    importFail(dataRow);
                }

            }

            ColumnConfig[] columnConfigList = new ColumnConfig[]{
                    new ColumnConfig("vinCode", 2, String.class),
                    new ColumnConfig("remark", 3, String.class),

            };
        };

        String encoded = importer.importByRow(file, this);
        MasterExcelMessage r = null;
        UI_ExcelImportResponse responseBody = new UI_ExcelImportResponse();
        if (encoded == null) {
            r = MasterExcelMessage.EXCEL_IMPORTED;
        } else {
            r = MasterExcelMessage.EXCEL_IMPORT_HAS_ERROR;
            responseBody.setBase64(encoded);
            System.out.println(encoded);
            responseBody.setFileName("vin_result.xlsx");
        }
        List<MasterExcelMessage.TranslatedMessage> l = new ArrayList<>();
        l.add(r.toTranslatedMessage(translateService));
        responseBody.setMessageList(l);
        return responseBody;
    }

    public Set<ConstraintViolation<VinEntity>> validate(VinEntity arg) {
        Set<ConstraintViolation<VinEntity>> result = validator.validate(arg);
        return result;
    }

    public VinEntity setDbValue(VinEntity arg) {
        //vinCode不能重复
        VinEntity checkEntity = _vinService.findByCode(arg.getVinCode());
        if (checkEntity != null) {
            return null;
        }
        //查找字典值
        // arg.setRtStatus(sysConfigService.getSysconfingCodeByName(SysConstant.SYSTEM_CONFIG_RENTAL_STATUS, arg.getRtStatusName(), arg.getCorpNo()));
//        arg.setSerMode(sysConfigService.getSysconfingCodeByName(SysConstant.SYSTEM_CONFIG_SERVICE_RANGE, arg.getSerModeName(),arg.getCorpNo()));
        return arg;
    }
    public void excelImportSub(VinEntity arg) {

        //BigDecimal转化为Integer
        //  if (arg.getParkNumExcel() != null) {
        //      arg.setParkNum(arg.getParkNumExcel().intValue());
        //  }
//        arg.setRemainPileNum(arg.getRemainPileNumExcel().intValue());
//        arg.setRtLon(arg.getRtLonExcle().doubleValue());
//        arg.setRtLat(arg.getRtLatExcle().doubleValue());

        //把省、市、区再重新进行拆分
        // String rtArea = arg.getRtArea();
        // arg.setRtProvince(rtArea.substring(0, rtArea.indexOf("省") + 1));
        // arg.setRtCity(rtArea.substring(rtArea.indexOf("省") + 1, rtArea.indexOf("市") + 1));
        // arg.setRtCounty(rtArea.substring(rtArea.indexOf("市") + 1, rtArea.indexOf("区") + 1));

        //增加到数据库
        _vinService.add(arg);
    }

    @Data
    public static class UI_ExcelImportResponse {
        private String base64;
        private String fileName;
        private List<MasterExcelMessage.TranslatedMessage> messageList;
    }

    /**
     * 获取不送桩原因
     * @return
     */
    @ApiOperation(value = "获取不送桩原因", notes = "获取不送桩原因")
    @ApiImplicitParam()
    @CustomResult
    @GetMapping(value = "/getremark")
    public List<String> getRemark() {
        return _vinService.getRemark();
    }

}

