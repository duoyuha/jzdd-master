package cn.backend.controller.report;

import cn.backend.config.constant.SysConstant;
import cn.backend.controller.BaseController;
import cn.backend.controller.report.export.SupplierCommonMasterExcelExportConfig;
import cn.backend.model.primary.complainorder.QComplainOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.ordergrade.QOrderGradeEntity;
import cn.backend.model.primary.report.AnalyseReport;
import cn.backend.model.primary.report.OrderReportQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.QViewDeliveryOrderEntity;
import cn.backend.model.primary.viewinstallorder.QViewInstallOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import cn.zdwl.common.excel.MasterExcelExporter;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.message.MasterExcelMessage;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author james
 * @date 2019/8/5
 **/
@Slf4j
@Controller
@RequestMapping(value = "/report/")
@Api(tags = "综合分析报表")
public class AnalyseReportController extends BaseController {


    @Autowired
    private SupplierCommonMasterExcelExportConfig commonMasterExcelExportConfig;


    @Autowired
    private IInstallOrderViewService installOrderViewService;


    @Autowired
    private EntityManager entityManager;

    //查询工厂实体
    private JPAQueryFactory queryFactory;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {

        queryFactory = new JPAQueryFactory(entityManager);
    }

    @CustomResult
    @GetMapping("/analyse")
    public List<AnalyseReport> analyseReport(OrderReportQuery orderQuery) {
        List<AnalyseReport> results = new ArrayList<>();

        if (SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL.equals(orderQuery.getOrderType())) {

            results = getInstallData(orderQuery);
        } else {
            results = getDeliveryData(orderQuery);
        }


        return results;
    }

    private List<AnalyseReport> getDeliveryData(OrderReportQuery orderQuery) {


        QViewDeliveryOrderEntity qViewDeliveryOrderEntity = QViewDeliveryOrderEntity.viewDeliveryOrderEntity;

        QComplainOrderEntity qComplainOrderEntity = QComplainOrderEntity.complainOrderEntity;

        QOrderGradeEntity qOrderGradeEntity = QOrderGradeEntity.orderGradeEntity;

        UserEntity userEntity = getUserEntity();

        Predicate predicate = qViewDeliveryOrderEntity.corpNo.eq(userEntity.getCorpNo());

        predicate = ExpressionUtils.and(predicate, qViewDeliveryOrderEntity.isDel.eq(SysConstant.IS_DEL_N));

        predicate = ExpressionUtils.and(predicate,qViewDeliveryOrderEntity.supplierNo.isNotEmpty());

        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {
            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.supplierNo.eq(userEntity.getSupplierNo()));

        }

        if (!StringUtils.isEmpty(orderQuery.getSupplierNo())) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.supplierNo.eq(orderQuery.getSupplierNo()));

        }

        if (!StringUtils.isEmpty(orderQuery.getCarVehicle())) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.carVehicle.eq(orderQuery.getCarVehicle()));

        }

        if (orderQuery.getOrderTimeStart() != null) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.orderTime.before(orderQuery.getOrderTimeStart()));

        }

        if (orderQuery.getOrderTimeEnd() != null) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.orderTime.after(orderQuery.getOrderTimeEnd()));

        }
        if (!StringUtils.isEmpty(orderQuery.getInstallProvince())) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.installProvince.eq(orderQuery.getInstallProvince()));

        }

        if (!StringUtils.isEmpty(orderQuery.getInstallCity())) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDeliveryOrderEntity.installCity.eq(orderQuery.getInstallCity()));

        }


//获取配送订单数量

        Long ttlNum = 0L;
        List<AnalyseReport> list = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                (qViewDeliveryOrderEntity.id.count()).as("orderNum"),
                                (qViewDeliveryOrderEntity.installCity).as("city"),
                                (qViewDeliveryOrderEntity.deliverySpan.sum()).as("installSpan"),
                                qViewDeliveryOrderEntity.supplierNo,
                                qViewDeliveryOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qViewDeliveryOrderEntity)

                .where(predicate)
                .groupBy(qViewDeliveryOrderEntity.installCity, qViewDeliveryOrderEntity.supplierNo,
                        qViewDeliveryOrderEntity.supplierName)
                .fetch();

        if (list == null || list.size() == 0) {


            return null;
        } else {
            for (AnalyseReport analyseReport : list) {

                if (StringUtils.isEmpty(analyseReport.getSupplierNo())) {
                    analyseReport.setSupplierNo("-");
                }

                ttlNum += analyseReport.getOrderNum();

            }
        }

        List<AnalyseReport> listSupplier =
                list.stream().filter(x -> !x.getSupplierNo().equals("-")).collect(Collectors.toList());

        //获取投诉订单数量
        List<AnalyseReport> listComplain = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                (qComplainOrderEntity.id.count()).as("complainOrderNum"),
                                (qViewDeliveryOrderEntity.installCity).as("city"),
                                qViewDeliveryOrderEntity.supplierNo,
                                qViewDeliveryOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qViewDeliveryOrderEntity, qComplainOrderEntity)

                .where(qViewDeliveryOrderEntity.orderNo.eq(qComplainOrderEntity.orderNo), predicate)
                .groupBy(qViewDeliveryOrderEntity.installCity, qViewDeliveryOrderEntity.supplierNo,
                        qViewDeliveryOrderEntity.supplierName)
                .fetch();


        //获取订单评价分数


        List<AnalyseReport> listGrade = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                // ( qOrderGradeEntity.id.count()).as("complainOrderNum"),
                                (qOrderGradeEntity.supplierGrade.sum()).as("gradeTtl"),
                                (qViewDeliveryOrderEntity.installCity).as("city"),
                                qViewDeliveryOrderEntity.supplierNo,
                                qViewDeliveryOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qViewDeliveryOrderEntity, qOrderGradeEntity)

                .where(qViewDeliveryOrderEntity.orderNo.eq(qOrderGradeEntity.orderNo), predicate)
                .groupBy(qViewDeliveryOrderEntity.installCity, qViewDeliveryOrderEntity.supplierNo,
                        qViewDeliveryOrderEntity.supplierName)
                .fetch();


        for (AnalyseReport analyseReport : listSupplier) {

            if (listComplain != null && listComplain.size() > 0) {
                List<AnalyseReport> tempList =
                        listComplain.stream().filter(x -> x.getSupplierNo().equals(analyseReport.getSupplierNo()) && x.getCity().equals(analyseReport.getCity())).collect(Collectors.toList());
                if (tempList != null && tempList.size() > 0) {

                    AnalyseReport temp = tempList.stream().findFirst().orElse(null);

                    //计算投诉量
                    analyseReport.setComplainOrderNum(temp.getComplainOrderNum());
                }
            }
            //计算接单量

            analyseReport.setOrderNumPercent(analyseReport.getOrderNum() / (double) ttlNum);

            //安装时效

            analyseReport.setAverageInstallTime(analyseReport.getInstallSpan() / analyseReport.getOrderNum() / (60 * 60 * 24));


            //计算服务商分数

            if (listGrade != null && listGrade.size() > 0) {
                List<AnalyseReport> tempGradeList =
                        listGrade.stream().filter(x -> x.getSupplierNo().equals(analyseReport.getSupplierNo()) && x.getCity().equals(analyseReport.getCity())).collect(Collectors.toList());
                if (tempGradeList != null && tempGradeList.size() > 0) {

                    AnalyseReport tempGrade = tempGradeList.stream().findFirst().orElse(null);

                    //
                    analyseReport.setGrade(tempGrade.getGradeTtl() / (double) analyseReport.getOrderNum());
                }
            }


            InstallOrderQuery installOrderQuery = new InstallOrderQuery();

            if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {

                installOrderQuery.setSupplierNo(userEntity.getSupplierNo());
            }

            installOrderQuery.setCorpNo(userEntity.getCorpNo());


            if (!StringUtils.isEmpty(orderQuery.getSupplierNo())) {


                installOrderQuery.setSupplierNo(userEntity.getSupplierNo());

            }

            installOrderQuery.setCarVehicle(orderQuery.getCarVehicle());

            installOrderQuery.setOrderTimeStart(orderQuery.getOrderTimeStart());

            installOrderQuery.setOrderTimeEnd(orderQuery.getOrderTimeEnd());
            installOrderQuery.setPageNum(0);
            installOrderQuery.setPageSize(Integer.MAX_VALUE);
            Page<ViewInstallOrderEntity> viewInstallOrderEntities = installOrderViewService.findList(installOrderQuery);

            List<ViewInstallOrderEntity> viewInstallOrderEntityList = viewInstallOrderEntities.getContent();
            if (viewInstallOrderEntityList != null && viewInstallOrderEntityList.size() > 0) {

                viewInstallOrderEntityList = viewInstallOrderEntities.getContent().stream().filter(
                        x -> analyseReport.getSupplierNo().equals(x.getSupplierNo()) && analyseReport.getCity().equals(x.getInstallCity())
                ).collect(Collectors.toList());

                int overTimeOrderNum = 0;

                int overTimeMinute = 0;

                if (viewInstallOrderEntityList != null && viewInstallOrderEntityList.size() > 0) {

                    for (ViewInstallOrderEntity viewInstallOrderEntity : viewInstallOrderEntityList) {
                        if (viewInstallOrderEntity.getDispatchOverTime() > 0 || viewInstallOrderEntity.getCheckOverTime() > 0
                                || viewInstallOrderEntity.getContractOverTime() > 0 || viewInstallOrderEntity.getInstallkOverTime() > 0) {

                            overTimeMinute += viewInstallOrderEntity.getDispatchOverTime() + viewInstallOrderEntity.getCheckOverTime()
                                    + viewInstallOrderEntity.getContractOverTime() + viewInstallOrderEntity.getInstallkOverTime();

                            overTimeOrderNum += 1;

                        }
                    }
                }

                analyseReport.setOvertimeOrderNum(overTimeOrderNum);

                analyseReport.setOvertimeHours(overTimeMinute / 60 / analyseReport.getOrderNum());

            }


        }


        return list;
    }

    private List<AnalyseReport> getInstallData(OrderReportQuery orderQuery) {


        QViewInstallOrderEntity qInstallOrderEntity = QViewInstallOrderEntity.viewInstallOrderEntity;

        QComplainOrderEntity qComplainOrderEntity = QComplainOrderEntity.complainOrderEntity;

        QOrderGradeEntity qOrderGradeEntity = QOrderGradeEntity.orderGradeEntity;

        UserEntity userEntity = getUserEntity();
        Predicate predicate = qInstallOrderEntity.corpNo.eq(userEntity.getCorpNo());

        predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.isDel.eq(SysConstant.IS_DEL_N));

        predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.receiveStatus.eq(SysConstant.InstallOrder.RECEIVE_FLG_SIGN));

        // predicate = ExpressionUtils.and(predicate,qInstallOrderEntity.supplierNo.isNotNull());

        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {


            predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.supplierNo.eq(userEntity.getSupplierNo()));

        }

        if (!StringUtils.isEmpty(orderQuery.getSupplierNo())) {


            predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.supplierNo.eq(orderQuery.getSupplierNo()));

        }

        if (!StringUtils.isEmpty(orderQuery.getCarVehicle())) {


            predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.carVehicle.eq(orderQuery.getCarVehicle()));

        }

        if (orderQuery.getOrderTimeStart() != null) {


            predicate = ExpressionUtils.and(predicate,
                    qInstallOrderEntity.orderTime.before(orderQuery.getOrderTimeStart()));

        }

        if (orderQuery.getOrderTimeEnd() != null) {


            predicate = ExpressionUtils.and(predicate,
                    qInstallOrderEntity.orderTime.after(orderQuery.getOrderTimeEnd()));

        }

        if (!StringUtils.isEmpty(orderQuery.getInstallProvince())) {


            predicate = ExpressionUtils.and(predicate,
                    qInstallOrderEntity.installProvince.eq(orderQuery.getInstallProvince()));

        }

        if (!StringUtils.isEmpty(orderQuery.getInstallCity())) {


            predicate = ExpressionUtils.and(predicate, qInstallOrderEntity.installCity.eq(orderQuery.getInstallCity()));

        }


//获取安装订单数量

        Long ttlNum = 0L;
        List<AnalyseReport> list = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                (qInstallOrderEntity.id.count()).as("orderNum"),
                                (qInstallOrderEntity.installCity).as("city"),
                                (qInstallOrderEntity.installSpan.sum()).as("installSpan"),
                                qInstallOrderEntity.supplierNo,
                                qInstallOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qInstallOrderEntity)

                .where(predicate)
                .groupBy(qInstallOrderEntity.installCity, qInstallOrderEntity.supplierNo,
                        qInstallOrderEntity.supplierName)
                .fetch();

        if (list == null || list.size() == 0) {


            return null;
        } else {
            for (AnalyseReport analyseReport : list) {

                if (StringUtils.isEmpty(analyseReport.getSupplierNo())) {
                    analyseReport.setSupplierNo("-");
                }

                ttlNum += analyseReport.getOrderNum();

            }
        }

        List<AnalyseReport> listSupplier =
                list.stream().filter(x -> !x.getSupplierNo().equals("-")).collect(Collectors.toList());

        //获取投诉订单数量
        List<AnalyseReport> listComplain = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                (qComplainOrderEntity.id.count()).as("complainOrderNum"),
                                (qInstallOrderEntity.installCity).as("city"),
                                qInstallOrderEntity.supplierNo,
                                qInstallOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qInstallOrderEntity, qComplainOrderEntity)

                .where(qInstallOrderEntity.orderNo.eq(qComplainOrderEntity.orderNo), predicate)
                .groupBy(qInstallOrderEntity.installCity, qInstallOrderEntity.supplierNo,
                        qInstallOrderEntity.supplierName)
                .fetch();


        //获取订单评价分数


        List<AnalyseReport> listGrade = queryFactory
                .select(
                        Projections.bean(
                                AnalyseReport.class,//返回自定义实体的类型
                                // ( qOrderGradeEntity.id.count()).as("complainOrderNum"),
                                (qOrderGradeEntity.supplierGrade.sum()).as("gradeTtl"),
                                (qInstallOrderEntity.installCity).as("city"),
                                qInstallOrderEntity.supplierNo,
                                qInstallOrderEntity.supplierName
                        )

                )

                //查询订单总数
                .from(qInstallOrderEntity, qOrderGradeEntity)

                .where(qInstallOrderEntity.orderNo.eq(qOrderGradeEntity.orderNo), predicate)
                .groupBy(qInstallOrderEntity.installCity, qInstallOrderEntity.supplierNo,
                        qInstallOrderEntity.supplierName)
                .fetch();


        for (AnalyseReport analyseReport : listSupplier) {

            if (listComplain != null && listComplain.size() > 0) {
                List<AnalyseReport> tempList =
                        listComplain.stream().filter(x -> x.getSupplierNo().equals(analyseReport.getSupplierNo()) && x.getCity().equals(analyseReport.getCity())).collect(Collectors.toList());
                if (tempList != null && tempList.size() > 0) {

                    AnalyseReport temp = tempList.stream().findFirst().orElse(null);

                    //计算投诉量
                    analyseReport.setComplainOrderNum(temp.getComplainOrderNum());
                }
            }

            double orderNum  = analyseReport.getOrderNum();

            //计算接单量

            analyseReport.setOrderNumPercent(orderNum / (double) ttlNum);

            //安装时效

            analyseReport.setAverageInstallTime(analyseReport.getInstallSpan() / orderNum / (60 * 60 * 24));


            //计算服务商分数

            if (listGrade != null && listGrade.size() > 0) {
                List<AnalyseReport> tempGradeList =
                        listGrade.stream().filter(x -> x.getSupplierNo().equals(analyseReport.getSupplierNo()) && x.getCity().equals(analyseReport.getCity())).collect(Collectors.toList());
                if (tempGradeList != null && tempGradeList.size() > 0) {

                    AnalyseReport tempGrade = tempGradeList.stream().findFirst().orElse(null);

                    //
                    analyseReport.setGrade(tempGrade.getGradeTtl() / orderNum);
                }
            }


            InstallOrderQuery installOrderQuery = new InstallOrderQuery();

            if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {

                installOrderQuery.setSupplierNo(userEntity.getSupplierNo());
            }

            installOrderQuery.setCorpNo(userEntity.getCorpNo());


            if (!StringUtils.isEmpty(orderQuery.getSupplierNo())) {


                installOrderQuery.setSupplierNo(userEntity.getSupplierNo());

            }

            installOrderQuery.setCarVehicle(orderQuery.getCarVehicle());

            installOrderQuery.setOrderTimeStart(orderQuery.getOrderTimeStart());

            installOrderQuery.setOrderTimeEnd(orderQuery.getOrderTimeEnd());
            installOrderQuery.setPageNum(0);
            installOrderQuery.setPageSize(Integer.MAX_VALUE);
            installOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);
            Page<ViewInstallOrderEntity> viewInstallOrderEntities = installOrderViewService.findList(installOrderQuery);

            List<ViewInstallOrderEntity> viewInstallOrderEntityList = viewInstallOrderEntities.getContent();
            if (viewInstallOrderEntityList != null && viewInstallOrderEntityList.size() > 0) {

                viewInstallOrderEntityList = viewInstallOrderEntities.getContent().stream().filter(
                        x -> analyseReport.getSupplierNo().equals(x.getSupplierNo()) && analyseReport.getCity().equals(x.getInstallCity())
                ).collect(Collectors.toList());

                int overTimeOrderNum = 0;

                int overTimeMinute = 0;

                int finishedOrderSize = 0;

                if (viewInstallOrderEntityList != null && viewInstallOrderEntityList.size() > 0) {

                    for (ViewInstallOrderEntity viewInstallOrderEntity : viewInstallOrderEntityList) {
                        if(viewInstallOrderEntity.getFinishFlg().equals(SysConstant.InstallOrder.FINISH_FLG_Y)){
                            finishedOrderSize += 1;
                        }

                        if (viewInstallOrderEntity.getDispatchOverTime() > 0 || viewInstallOrderEntity.getCheckOverTime() > 0
                                || viewInstallOrderEntity.getContractOverTime() > 0 || viewInstallOrderEntity.getInstallkOverTime() > 0) {

                            overTimeMinute += viewInstallOrderEntity.getDispatchOverTime() + viewInstallOrderEntity.getCheckOverTime()
                                    + viewInstallOrderEntity.getContractOverTime() + viewInstallOrderEntity.getInstallkOverTime();

                            overTimeOrderNum += 1;

                        }
                    }
                }

                analyseReport.setOvertimeOrderNum(overTimeOrderNum);

                //计算完成量
                double finishRate = finishedOrderSize / orderNum;
                finishRate = (double) Math.round(finishRate * 100);
                analyseReport.setFinishRate(finishRate);

                double overtimeHours =new BigDecimal(String.valueOf(overTimeMinute)).divide(new BigDecimal(60),2, ROUND_HALF_DOWN).doubleValue();
                analyseReport.setOvertimeHours(overtimeHours);

                analyseReport.setFinishedOrderSize(finishedOrderSize);

            }


        }


        return list;


    }


    @CustomResult
    @GetMapping("/analyse/export")
    public UI_ExcelImportResponse supplierCommonReportExport(OrderReportQuery orderQuery) {
        MasterExcelExporter<AnalyseReport> exporter = generateMasterExcelExporter(commonMasterExcelExportConfig);
        List<AnalyseReport> supplierReports = analyseReport(orderQuery);
        XSSFWorkbook workbook = exporter.exportWorkbook(supplierReports);
        String encoded = MasterExcelExporter.toBase64(workbook);
        UI_ExcelImportResponse responseBody = new UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("common_report.xlsx");
        return responseBody;
    }


    private MasterExcelExporter<AnalyseReport> generateMasterExcelExporter(AbsMasterExcelExportConfig masterExcelExportConfig) {
        return new MasterExcelExporter<AnalyseReport>(masterExcelExportConfig, translateService) {
            DecimalFormat df = new DecimalFormat("######0.00");

            @Override
            public void setupExportRow(AnalyseReport data, XSSFRow newRow) {
                int i = 1;
                //城市
                setStringValue(newRow, i++, parseString(data.getCity() == null ? "" : data.getCity()));
                //服务商名称
                setStringValue(newRow, i++, parseString(data.getSupplierName() == null ? "" : data.getSupplierName()));
                //接单数
                setStringValue(newRow, i++, parseString(data.getOrderNumPercent() == null ? "" :
                        data.getOrderNumPercent()*100));
                //投诉量
                setStringValue(newRow, i++, parseString(data.getComplainOrderNum() == 0 ? 0 :
                        data.getComplainOrderNum()));
                //时效
                setStringValue(newRow, i++, parseString(data.getAverageInstallTime() == 0 ? 0 :
                        data.getAverageInstallTime()));
                //满意度
                setStringValue(newRow, i++, parseString(data.getGrade() == 0 ? 0 : df.format(data.getGrade())));
                //超时单数
                setStringValue(newRow, i++, parseString(data.getOvertimeOrderNum()));
                //超时时长
                setStringValue(newRow, i++, parseString(data.getOvertimeHours()));
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
