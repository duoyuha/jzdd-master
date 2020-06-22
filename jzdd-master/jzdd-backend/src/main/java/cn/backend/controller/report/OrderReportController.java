package cn.backend.controller.report;

import cn.backend.config.constant.SysConstant;
import cn.backend.controller.BaseController;
import cn.backend.controller.report.export.OrderMasterExcelExportConfig;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.report.OrderReport;
import cn.backend.model.primary.report.OrderReportQuery;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
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
@RequestMapping(value = "/orderreport")
@Api(description = "订单报表对应的api接口")
public class OrderReportController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private IViewDeliveryOrderService deliveryOrderService;

    @Autowired
    private OrderMasterExcelExportConfig orderMasterExcelExportConfig;

//    @Autowired
//    private EntityManager entityManager;
//
//    //查询工厂实体
//    private JPAQueryFactory queryFactory;

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
            "<li>分为 01 安装订单 02 配送订单 05 投诉订单 06 咨询订单</li>" +
            "<li>车型作为查询条件：carVehicle</li>" +
            "<li>服务商作为查询条件：supplierNo</li>" +
            "<li>提报时间为查询条件：orderTimeStart和orderTimeEnd</li>" +
            "<li>省份查询条件：installProvince</li>" +
            "<li>省份查询条件：installCity</li>" +
            "</ul>")

    public List<OrderReport> listAll(OrderReportQuery orderReportQuery) {

        List<OrderReport> list = new ArrayList<>();

        if (SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL.equals(orderReportQuery.getOrderType())) {

            list=   installOrderList(orderReportQuery);
        }else if(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY.equals(orderReportQuery.getOrderType())){

            list= deliveryOrderList(orderReportQuery);

        }


        return list;

    }


    /**
     * @Author: 孙魁伟
     * @Description:用于获取安装信息
     * @Date:Create：in 2019/08/05
     * @Modified By：
     */
     
    private List<OrderReport> deliveryOrderList(OrderReportQuery orderReportQuery) {

        List<OrderReport> list = new ArrayList<>();

        List<ViewDeliveryOrderEntity> deliveryOrderEntities = new ArrayList<>();

        ViewDeliveryOrderQuery query = new ViewDeliveryOrderQuery();

        query.setCarVehicle(orderReportQuery.getCarVehicle());

        query.setCity(orderReportQuery.getInstallCity());

        query.setProvince(orderReportQuery.getInstallProvince());

        query.setSupplierNo(orderReportQuery.getSupplierNo());

        query.setOrderStartTime(orderReportQuery.getOrderTimeStart());

        query.setOrderOverTime(orderReportQuery.getOrderTimeEnd());

        query.setReceiveStatus(SysConstant.InstallOrder.RECEIVE_FLG_SIGN);

        if(orderReportQuery.getType().equals(SysConstant.Delivery.PILE_MANGER)){
            query.setType(SysConstant.Delivery.PILE_MANGER);
        }else {
            query.setType(SysConstant.Delivery.DELIVERY);
            query.setSupplierNo(orderReportQuery.getSupplierNo());
        }

        query.setPageNum(0);
        query.setPageSize(Integer.MAX_VALUE);

        //获取订单数据
        deliveryOrderEntities = deliveryOrderService.findAll(query);

        if (deliveryOrderEntities != null && deliveryOrderEntities.size() > 0) {
            List<String> suppliserCityList = deliveryOrderEntities.stream().map(a -> a.getSupplierName() + "_" + a.getInstallCity()).collect(Collectors.toList());

            List<String> distinctSupplierCity = suppliserCityList.stream().distinct().collect(Collectors.toList());

            for (String strSupplier : distinctSupplierCity) {
                OrderReport orderReport = new OrderReport();
                String[] arr = strSupplier.split("_");
                orderReport.setSupplierName(arr[0]);
                orderReport.setCity(arr[1]);
                List<ViewDeliveryOrderEntity> calcTtlList = deliveryOrderEntities.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName() + "_" + x.getInstallCity()))
                        .collect(Collectors.toList());
                //设置订单数量
                orderReport.setTtlOrderNum(calcTtlList.size());

                List<ViewDeliveryOrderEntity> finishTtlList = deliveryOrderEntities.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName() + "_" + x.getInstallCity()) && SysConstant.InstallOrder.FINISH_FLG_Y.equals(x.getFinishFlg()))
                        .collect(Collectors.toList());

                //设置订单数量
                orderReport.setFinishOrderNum(finishTtlList.size());


                //设置未完成订单数量
                orderReport.setRemainOrderNum(orderReport.getTtlOrderNum() - orderReport.getFinishOrderNum());


                //设置完成率
                if (orderReport.getTtlOrderNum() > 0) {
                    orderReport.setFinishRate(orderReport.getFinishOrderNum() / orderReport.getTtlOrderNum() * 100);
                } else {
                    orderReport.setFinishRate(0);
                }

                list.add(orderReport);


            }


        }


        return  list;
    }

    /**
     * @Author: 孙魁伟
     * @Description:用于获取安装信息
     * @Date:Create：in 2019/08/05
     * @Modified By：
     */
    private List<OrderReport> installOrderList(OrderReportQuery orderReportQuery) {

        List<OrderReport> list = new ArrayList<>();
        //安装订单列表
        List<ViewInstallOrderEntity> installorderList = new ArrayList<>();

        InstallOrderQuery installOrderQuery = new InstallOrderQuery();

        installOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);

        installOrderQuery.setCarVehicle(orderReportQuery.getCarVehicle());

        installOrderQuery.setInstallCity(orderReportQuery.getInstallCity());

        installOrderQuery.setInstallProvince(orderReportQuery.getInstallProvince());

        installOrderQuery.setOrderTimeStart(orderReportQuery.getOrderTimeStart());

        installOrderQuery.setOrderTimeEnd(orderReportQuery.getOrderTimeEnd());

        installOrderQuery.setReceiveStatus(SysConstant.InstallOrder.RECEIVE_FLG_SIGN);

        if(orderReportQuery.getType().equals(SysConstant.Delivery.PILE_MANGER)){
            installOrderQuery.setType(SysConstant.Delivery.PILE_MANGER);
        }else {
            installOrderQuery.setType(SysConstant.Delivery.DELIVERY);
            installOrderQuery.setSupplierNo(orderReportQuery.getSupplierNo());
        }

        installOrderQuery.setPageNum(0);
        installOrderQuery.setPageSize(Integer.MAX_VALUE);

        //获取订单数据
        installorderList = installOrderViewService.findAll(installOrderQuery);


        if (installorderList != null && installorderList.size() > 0) {
            List<String> suppliserCityList = installorderList.stream().map(a -> a.getSupplierName() + "_" + a.getInstallCity()).collect(Collectors.toList());

            List<String> distinctSupplierCity = suppliserCityList.stream().distinct().collect(Collectors.toList());

            for (String strSupplier : distinctSupplierCity) {
                OrderReport orderReport = new OrderReport();
                String[] arr = strSupplier.split("_");
                orderReport.setSupplierName(arr[0]);
                orderReport.setCity(arr[1]);
                List<ViewInstallOrderEntity> calcTtlList = installorderList.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName() + "_" + x.getInstallCity()))
                        .collect(Collectors.toList());
                //设置订单数量
                orderReport.setTtlOrderNum(calcTtlList.size());

                List<ViewInstallOrderEntity> finishTtlList = installorderList.stream()
                        .filter(x -> strSupplier.equals(x.getSupplierName() + "_" + x.getInstallCity()) && SysConstant.InstallOrder.FINISH_FLG_Y.equals(x.getFinishFlg()))
                        .collect(Collectors.toList());

                //设置订单数量
                orderReport.setFinishOrderNum(finishTtlList.size());


                //设置未完成订单数量
                orderReport.setRemainOrderNum(orderReport.getTtlOrderNum() - orderReport.getFinishOrderNum());


                //设置完成率
                if (orderReport.getTtlOrderNum() > 0) {
                    orderReport.setFinishRate(orderReport.getFinishOrderNum() / orderReport.getTtlOrderNum() * 100);
                } else {
                    orderReport.setFinishRate(0);
                }

                list.add(orderReport);


            }


        }

        return list;
    }

    @CustomResult
    @GetMapping("/export")
    public UI_ExcelImportResponse orderReportExport(OrderReportQuery orderReportQuery) {
        MasterExcelExporter<OrderReport> exporter = generateMasterExcelExporter(orderMasterExcelExportConfig);
        List<OrderReport> reportList = listAll(orderReportQuery);
        XSSFWorkbook workbook = exporter.exportWorkbook(reportList);
        String encoded = MasterExcelExporter.toBase64(workbook);
        UI_ExcelImportResponse responseBody = new UI_ExcelImportResponse();
        responseBody.setBase64(encoded);
        responseBody.setFileName("order_report.xlsx");
        return responseBody;
    }


    private MasterExcelExporter<OrderReport> generateMasterExcelExporter(AbsMasterExcelExportConfig masterExcelExportConfig) {
        return new MasterExcelExporter<OrderReport>(masterExcelExportConfig, translateService) {
            @Override
            public void setupExportRow(OrderReport data, XSSFRow newRow) {
                int i = 1;
                //城市
                setStringValue(newRow, i++, parseString(data.getCity()==null ?"":data.getCity()));
                //服务商名称
                setStringValue(newRow, i++, parseString(data.getSupplierName()==null?"":data.getSupplierName()));
                //工单总量
                setStringValue(newRow, i++, parseString(data.getTtlOrderNum()));
                //完成工单数
                setStringValue(newRow, i++, parseString(data.getFinishOrderNum()));
                //完成率
                setStringValue(newRow, i++, parseString(data.getFinishRate()));
                //工单存量
                setStringValue(newRow, i++, parseString(data.getRemainOrderNum()));
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

