package cn.backend.controller.report;

import cn.backend.config.constant.SysConstant;
import cn.backend.controller.BaseController;
import cn.backend.controller.report.export.SettleReportMasterExcelExportConfig;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.report.SettleReport;
import cn.backend.model.primary.report.SettleReportQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.message.MasterExcelMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/settlereport")
@Api(description = "结算报表对应的api接口" ,tags="结算报表对应的api接口")
public class SettleReportController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private SettleReportMasterExcelExportConfig settleReportMasterExcelExportConfig;





    /**
     * 列表，不分页查询功能
     *
     * @param
     * @return
     */
    //实例化控制器完成后执行该方法实例化JPAQueryFactory
//    @PostConstruct
//    public void initFactory()
//    {
//        System.out.println("开始实例化JPAQueryFactory");
//        queryFactory = new JPAQueryFactory(entityManager);
//    }
    @CustomResult
    @GetMapping("/listall")
    @ApiOperation(value = "全部列表", notes = "按照安装服务商进行报表统计" +
            "<ul>" +
            "<li>车型作为查询条件：carVehicle</li>" +
            "<li>服务商作为查询条件：supplierNo</li>" +
            "<li>提报时间为查询条件：orderTimeStart和orderTimeEnd</li>" +

            "</ul>")

    public List<SettleReport> listAll(SettleReportQuery settleReportQuery) {

        List<SettleReport> list = new ArrayList<>();

        list=   settleOrderList(settleReportQuery);


        return list;

    }



    /**
     * @Author: 孙魁伟
     * @Description:用于获取安装信息
     * @Date:Create：in 2019/08/05
     * @Modified By：
     */
    private List<SettleReport> settleOrderList(SettleReportQuery settleReportQuery) {

        List<SettleReport> list = new ArrayList<>();
        //安装订单列表
        List<ViewInstallOrderEntity> installorderList = new ArrayList<>();

        InstallOrderQuery installOrderQuery = new InstallOrderQuery();

        installOrderQuery.setCarVehicle(settleReportQuery.getCarVehicle());

        installOrderQuery.setSupplierNo(settleReportQuery.getSupplierNo());

        installOrderQuery.setOrderTimeStart(settleReportQuery.getOrderTimeStart());

        installOrderQuery.setOrderTimeEnd(settleReportQuery.getOrderTimeEnd());

        installOrderQuery.setAllOrderFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);

        if(!StringUtils.isEmpty(settleReportQuery.getType())){
            if(settleReportQuery.getType().equals(SysConstant.Delivery.PILE_MANGER)){
                installOrderQuery.setType(SysConstant.Delivery.PILE_MANGER);
            }else {
                installOrderQuery.setType(SysConstant.Delivery.DELIVERY);
                installOrderQuery.setSupplierNo(settleReportQuery.getSupplierNo());
            }
        }

        //获取订单数据
        installorderList = installOrderViewService.findAll(installOrderQuery);


        if (installorderList != null && installorderList.size() > 0) {
            List<String> suppliserList = installorderList.stream().map(a -> a.getSupplierName()).collect(Collectors.toList());

            List<String> distinctSupplier = suppliserList.stream().distinct().collect(Collectors.toList());

            for (String strSupplier : distinctSupplier) {
                if(StringUtils.isEmpty(strSupplier)){
                    continue;
                }

                SettleReport report = new SettleReport();
                report.setSupplierName(strSupplier);

                List<ViewInstallOrderEntity> submitList = installorderList.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName()) && SysConstant.InstallOrder.SETTLE_FLG_Y.equals(x.getSettleFlg()))
                        .collect(Collectors.toList());

                Double sumSubmit = submitList.stream()
                        .mapToDouble(p -> p.getSettleAmt())
                        .sum();

                report.setSubmitOrderAmt(sumSubmit);

                report.setSubmitOrderNum(submitList.size());


                List<ViewInstallOrderEntity> unSubmitList = installorderList.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName()) && SysConstant.InstallOrder.SETTLE_FLG_N.equals(x.getSettleFlg()))
                        .collect(Collectors.toList());


                Double sumUnSubmit = unSubmitList.stream()
                        .mapToDouble(p -> p.getSettleAmt())
                        .sum();

                report.setUnSubmitOrderAmt(sumUnSubmit);

                report.setUnSubmitOrderNum(unSubmitList.size());


                list.add(report);



            }


        }

        return list;
    }

    @CustomResult
    @GetMapping("/export")
    public UI_ExcelImportResponse orderReportExport(SettleReportQuery settleReportQuery) {
        MasterExcelExporter<SettleReport> exporter = generateMasterExcelExporter(settleReportMasterExcelExportConfig);
        List<SettleReport> reportList = listAll(settleReportQuery);
        XSSFWorkbook workbook = exporter.exportWorkbook(reportList);
        String encoded = MasterExcelExporter.toBase64(workbook);
        UI_ExcelImportResponse responseBody = new UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("settle_report.xlsx");
        return responseBody;
    }


    private MasterExcelExporter<SettleReport> generateMasterExcelExporter(AbsMasterExcelExportConfig masterExcelExportConfig) {
        return new MasterExcelExporter<SettleReport>(masterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(SettleReport data, XSSFRow newRow) {
                int i = 1;
                //服务商名称
                setStringValue(newRow, i++, parseString(data.getSupplierName()==null?"":data.getSupplierName()));
                //已提交订单数
                setStringValue(newRow, i++, parseString(data.getSubmitOrderNum()));
                //已提交结算金额
                setStringValue(newRow, i++, parseString(data.getSubmitOrderAmt()));
                //未提交订单数
                setStringValue(newRow, i++, parseString(data.getUnSubmitOrderNum()));
                //未提交结算金额
                setStringValue(newRow, i++, parseString(data.getUnSubmitOrderAmt()));
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

