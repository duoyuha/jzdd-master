package cn.backend.controller.report;

import cn.backend.access.utils.MonthUtils;
import cn.backend.config.constant.SysConstant;
import cn.backend.controller.BaseController;
import cn.backend.controller.report.export.SupplierMasterExcelExportConfig;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.config.ConfigQuery;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.model.primary.report.SupplierReport;
import cn.backend.model.primary.report.SupplierReportQuery;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.supplier.SupplierQuery;
import cn.backend.service.complainorder.IComplainOrderService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.ordergrade.IOrderGradeService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.supplier.ISupplierService;
import cn.zdwl.common.jsonfilter.CustomResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author james
 * @date 2019/8/5
 **/
@Slf4j
@Controller
@RequestMapping(value = "/report/supplier")
@Api(description = "服务商月度评价")
public class SupplierMonthReportController extends BaseController {

    @Autowired
    private SupplierMasterExcelExportConfig masterExcelExportConfig;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IOrderGradeService orderGradeService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private IComplainOrderService complainOrderService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IOrderStepService orderStepService;

    private DecimalFormat df = new DecimalFormat("0.000");


    @CustomResult
    @GetMapping("/month")
    public List<SupplierReport> supplierReport(SupplierReportQuery supplierReportQuery) {
        if(supplierReportQuery.getQueryTimeStart() != null && "".equals(supplierReportQuery.getQueryTimeStart())){
            Date start = MonthUtils.monthFirstDay(supplierReportQuery.getQueryTimeStart());
            supplierReportQuery.setQueryTimeStart(start);
        }
        if(supplierReportQuery.getQueryTimeEnd() != null && "".equals(supplierReportQuery.getQueryTimeEnd())) {
            Date end = MonthUtils.monthLastDay(supplierReportQuery.getQueryTimeEnd());
            supplierReportQuery.setQueryTimeEnd(end);
        }
        List<SupplierReport> reports = new ArrayList<>();
        //配送
        DeliveryOrderQuery deliveryOrderQuery = new DeliveryOrderQuery();
        deliveryOrderQuery.setOrderStartTime(supplierReportQuery.getQueryTimeStart());
        deliveryOrderQuery.setOrderOverTime(supplierReportQuery.getQueryTimeEnd());
        deliveryOrderQuery.setBeginCacle(SysConstant.InstallOrder.CONFIG_FLG_N);
        deliveryOrderQuery.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        List<DeliveryOrderEntity> deliveryOrderEntityList = deliveryOrderService.findAll(deliveryOrderQuery);

        //安装
        InstallOrderQuery installOrderQuery = new InstallOrderQuery();
        installOrderQuery.setOrderTimeStart(supplierReportQuery.getQueryTimeStart());
        installOrderQuery.setOrderTimeEnd(supplierReportQuery.getQueryTimeEnd());
        installOrderQuery.setBeginCacle(SysConstant.InstallOrder.CONFIG_FLG_N);
        installOrderQuery.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        List<InstallOrderEntity> installOrderEntityList = installOrderService.findAll(installOrderQuery);

        //投诉订单
        ComplainOrderQuery complainOrderQuery = new ComplainOrderQuery();
        complainOrderQuery.setComplainTimeStart(supplierReportQuery.getQueryTimeStart());
        complainOrderQuery.setComplainTimeEnd(supplierReportQuery.getQueryTimeEnd());
        List<ComplainOrderEntity> complainOrderEntityList = complainOrderService.findAll(complainOrderQuery);

        //服务商
        SupplierQuery supplierQuery = new SupplierQuery();
        supplierQuery.setSupplierNo(supplierReportQuery.getSupplierNo());
        List<SupplierEntity> supplierEntities = supplierService.findAll(supplierQuery);
        for (SupplierEntity supplierEntity : supplierEntities) {
            String supplierNo = supplierEntity.getSupplierNo();

            SupplierReport report = new SupplierReport();
            report.setSupplierNo(supplierNo);
            report.setSupplierName(supplierEntity.getSupplierName());

            //完成订单数量
            int finishOrderNum = 0;
            finishOrderNum += deliveryOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(e.getFinishFlg())).count();
            finishOrderNum += installOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(e.getFinishFlg())).count();
            report.setFinishOrderNum(finishOrderNum);
            //总订单数量
            int allOrderNum = 0;
            allOrderNum += deliveryOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo())).count();
            allOrderNum += installOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo())).count();
            report.setAllOrderNum(allOrderNum);

            //投诉订单数量
            int complainOrderNum = 0;
            List<ComplainOrderEntity> complainOrderEntitySupplierList = complainOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo())).collect(Collectors.toList());
            complainOrderNum = complainOrderEntitySupplierList.size();
            report.setComplainOrderNum(complainOrderNum);
            //总投诉问题数量
            int descNum = 0;
            //投诉问题分类
            ConfigQuery configQuery = new ConfigQuery();
            configQuery.setConfigType(SysConstant.Complain.CONFIG_TYPE_COMPLAINDESC);
            List<ConfigEntity> configEntityList = configService.findAll(configQuery);
            Map<String, Double> descMap = new HashMap<>(configEntityList.size());
            for (ConfigEntity item : configEntityList) {
                descMap.put(item.getConfigName(), 0D);
            }
            //计算各投诉问题数量
            for (ComplainOrderEntity item : complainOrderEntitySupplierList) {
                String complainDescNames = item.getComplainDescName();
                String[] descName = complainDescNames.split(",");
                for (String str : descName) {
                    if (descMap.containsKey(str)) {
                        descMap.replace(str, descMap.get(str) + 1);
                        descNum += 1;
                    }
                }
            }
            //投诉问题比例
            for (Map.Entry<String, Double> entry : descMap.entrySet()) {
                double value = entry.getValue();
                BigDecimal per = new BigDecimal(0);
                if (descNum > 0) {
                    per = new BigDecimal(String.valueOf(value))
                            .divide(new BigDecimal(String.valueOf(descNum)), 3, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(100));
                }
                entry.setValue(per.doubleValue());
            }
            report.setComplainDesc(descMap);
            //投诉率
            BigDecimal complainPer = new BigDecimal(0);
            if (allOrderNum > 0) {
                complainPer = new BigDecimal(String.valueOf(complainOrderNum))
                        .divide(new BigDecimal(String.valueOf(allOrderNum)), 3, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(100));
            }
            report.setComplainPer(complainPer.doubleValue());


            //分数
            //超时
            double outTimeScore = 40D;
            Set<String> orderNoSet = new HashSet<>();
            List<DeliveryOrderEntity> deliveryOrderEntitySupplierList = deliveryOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo())).collect(Collectors.toList());
            List<InstallOrderEntity> installOrderEntitySupplierList = installOrderEntityList.stream()
                    .filter(e -> supplierNo.equals(e.getSupplierNo())).collect(Collectors.toList());
            //得到订单号
            for (DeliveryOrderEntity item : deliveryOrderEntitySupplierList) {
                orderNoSet.add(item.getOrderNo());
            }
            for (InstallOrderEntity item : installOrderEntitySupplierList) {
                orderNoSet.add(item.getOrderNo());
            }
            //查询订单号下的所有步骤
            OrderStepQuery orderStepQuery = new OrderStepQuery();
            orderStepQuery.setOrderNos(orderNoSet);
            orderStepQuery.setTimeoutStatus(SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT_Y);
            List<OrderStepEntity> orderStepEntityList = orderStepService.findAll(orderStepQuery);
            int outTimeMin = orderStepEntityList.stream().mapToInt(OrderStepEntity::getOutTime).sum();
            double outTimeDay = new BigDecimal(outTimeMin).divide(new BigDecimal(1440), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            int subScore = (int) outTimeDay;
//            if (outTimeDay - (int) outTimeDay > 0) {
//                subScore += 1;
//            }
//            outTimeScore -= subScore;
            //平均超期时长
            if(0 < allOrderNum) {
                BigDecimal outTimeAvg = new BigDecimal(String.valueOf(outTimeMin))
                        .divide(new BigDecimal(String.valueOf(allOrderNum)), 2, BigDecimal.ROUND_HALF_UP);
                report.setOutTimeAvg(outTimeAvg.doubleValue());
            }

            // 总分 40 - 平均超时时长的进一法 _wk 0926
            double outtimeAvg = report.getOutTimeAvg()/60/24;
            int subScore = (int) outtimeAvg;
            if (outtimeAvg - (int) outtimeAvg > 0) {
                subScore += 1;
            }
            outTimeScore -= subScore;

            //投诉
            double complainScore = 40 - complainPer.doubleValue();
            //满意度
            int gradeNum = 0;
            double gradeScore = 0D;
            for (DeliveryOrderEntity item : deliveryOrderEntitySupplierList) {
                OrderGradeEntity orderGradeEntity = orderGradeService.findByOrderNo(item.getOrderNo());
                if (orderGradeEntity != null) {
                    gradeScore += orderGradeEntity.getSupplierGrade();
                    gradeNum += 1;
                }
            }
            int installTime = 0;
            for (InstallOrderEntity item : installOrderEntitySupplierList) {
                OrderGradeEntity orderGradeEntity = orderGradeService.findByOrderNo(item.getOrderNo());
                if (orderGradeEntity != null) {
                    gradeScore += orderGradeEntity.getSupplierGrade();
                    gradeNum += 1;
                }
                //安装总时长(分)
                if (item.getInstallTime() != null && item.getInstallFinishTime() != null) {
                    installTime += (item.getInstallFinishTime().getTime() - item.getInstallTime().getTime()) / 60000;
                }
            }
            //平均安装时长
            if(allOrderNum > 0){
                BigDecimal installTimeAvg = new BigDecimal(String.valueOf(installTime))
                        .divide(new BigDecimal(String.valueOf(allOrderNum)), 2, BigDecimal.ROUND_HALF_UP);
                report.setInstallTimeAvg(installTimeAvg.doubleValue());
            }

            double scoreAvg = 0D;
            if (gradeNum > 0) {
                scoreAvg = new BigDecimal(String.valueOf(gradeScore))
                        .divide(new BigDecimal(String.valueOf(gradeNum)), 2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
            } else {
                scoreAvg = 0;
            }
            //平均满意度/5*20
            gradeScore = scoreAvg * 4;
            report.setGradeScore(scoreAvg);
            report.setScore(outTimeScore + complainScore + gradeScore);
            reports.add(report);
        }
        return reports;
    }

//     @CustomResult
//     @GetMapping("/month/export")
//     public UI_ExcelImportResponse supplierReportExport(InstallOrderQuery orderQuery) {
//         MasterExcelExporter<AnalyseReport> exporter = generateMasterExcelExporter();
//         List<AnalyseReport> supplierReports = supplierReport(orderQuery);
//         XSSFWorkbook workbook = exporter.exportWorkbook(supplierReports);
//         String encoded = MasterExcelExporter.toBase64(workbook);
//         UI_ExcelImportResponse responseBody = new UI_ExcelImportResponse();
//         responseBody.setBase64(encoded);
//         responseBody.setFileName("month_report.xlsx");
//         return responseBody;
//     }
//
//     private MasterExcelExporter<AnalyseReport> generateMasterExcelExporter() {
//         return new MasterExcelExporter<AnalyseReport>(masterExcelExportConfig, translateService) {
//             @Override
//             public void setupExportRow(AnalyseReport data, XSSFRow newRow) {
//                 int i = 1;
//                 //服务商名称
//                 setStringValue(newRow, i++, parseString(data.getSupplierName() == null ? "" : data.getSupplierName
//                 ()));
//                 //已完成
//
//                 //评分
//                 setStringValue(newRow, i++, parseString(data.getGrade()));
//             }
//         };
//     }
//
//     @Data
//     private static class UI_ExcelImportResponse {
//         private String base64;
//         private String fileName;
//         private List<MasterExcelMessage.TranslatedMessage> messageList;
//     }
//
//     private long calcRate(long n1, long n2) {
//         if (n2 == 0) {
//             return 0;
//         }
//         return n1 / n2;
//     }
//
//     private BigDecimal calcRate(BigDecimal n1, BigDecimal n2) {
//         if (BigDecimal.ZERO.compareTo(n2) == 0) {
//             return BigDecimal.ZERO;
//         }
//         return n1.divide(n2, 2, BigDecimal.ROUND_HALF_UP);
//     }

}
