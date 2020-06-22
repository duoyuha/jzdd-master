package cn.backend.controller.dashbord;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.dashbord.*;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdashboardcalendar.QViewDashbordCalendarEntity;
import cn.backend.model.primary.viewdashboardtodo.DashbordTodoNumEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoQuery;
import cn.backend.model.primary.viewdashbordrank.QViewDashbordRankEntity;
import cn.backend.service.dashbord.IDashbordService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.viewdashboardtodo.IViewDashbordTodoService;
import cn.backend.service.viewdashbordrank.IViewDashbordRankService;
import cn.zdwl.common.jsonfilter.CustomResult;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/dashbord")
@Api(tags = "仪表盘对应的接口")
public class DashbordController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IDashbordService _dashbordService;

    @Autowired
    private IViewDashbordRankService viewDashbordRankService;

    @Autowired
    private IViewDashbordTodoService viewDashbordTodoService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private EntityManager entityManager;

    //查询工厂实体
    private JPAQueryFactory queryFactory;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {

        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 代办事项
     *
     * @param dashbordQuery
     * @return
     */
    @CustomResult
    @GetMapping("/todolist")
    @ApiOperation(value = "代办事项", notes = "代办事项")
    @ApiImplicitParam(name = "dashbordQuery", value = "实体dashbordQuery", required = true, dataType = "DashbordQuery")
    public Page<ViewDashbordTodoEntity> todoList(DashbordQuery dashbordQuery) {
        UserEntity userEntity = getUserEntity();
        dashbordQuery.setCorpNo(userEntity.getCorpNo());
        dashbordQuery.setPositionCode(userEntity.getUserType());
        dashbordQuery.setSupplierNo(userEntity.getSupplierNo());
        // if(!StringUtils.isEmpty(userEntity.getSupplierNo())){
        //     dashbordQuery.setSupplierNo(userEntity.getSupplierNo());
        // }
        return _dashbordService.todoList(dashbordQuery);
    }

    /**
     * 代办事项-数量
     *
     * @param dashbordQuery
     * @return
     */
    @CustomResult
    @GetMapping("/todolistnum")
    @ApiOperation(value = "代办事项", notes = "代办事项")
    @ApiImplicitParam(name = "dashbordQuery", value = "实体dashbordQuery", required = true, dataType = "DashbordQuery")
    public List<DashbordTodoNumEntity> todoListNum(DashbordQuery dashbordQuery) {
        UserEntity userEntity = getUserEntity();
        ViewDashbordTodoQuery viewDashbordTodoQuery = new ViewDashbordTodoQuery();
        viewDashbordTodoQuery.setSupplierNo(userEntity.getSupplierNo());
        viewDashbordTodoQuery.setUserType(userEntity.getUserType());
        viewDashbordTodoQuery.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        List<ViewDashbordTodoEntity> viewDashbordTodoEntityList =
                viewDashbordTodoService.findAll(viewDashbordTodoQuery);
        //安装分类
        List<ViewDashbordTodoEntity> viewDashbordTodoEntityInstallList = viewDashbordTodoEntityList.stream()
                .filter(e -> SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL.equals(e.getFollowCode()))
                .collect(Collectors.toList());
        //配送分类
        List<ViewDashbordTodoEntity> viewDashbordTodoEntityDeliveryList = viewDashbordTodoEntityList.stream()
                .filter(e -> SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY.equals(e.getFollowCode()))
                .collect(Collectors.toList());
        //作废分类
        List<ViewDashbordTodoEntity> viewDashbordTodoEntityCancelList = viewDashbordTodoEntityList.stream()
                .filter(e -> SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID.equals(e.getFollowCode()))
                .collect(Collectors.toList());
        //结算分类
        List<ViewDashbordTodoEntity> viewDashbordTodoEntitySettleList = viewDashbordTodoEntityList.stream()
                .filter(e -> SysConstant.WorkFollow.CONFIG_WORKFOLLOW_SETTLE.equals(e.getFollowCode()))
                .collect(Collectors.toList());
        //根据每个步骤分类出来
        List<DashbordTodoNumEntity> installList = todoClassify(viewDashbordTodoEntityInstallList,
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        List<DashbordTodoNumEntity> deliveryList = todoClassify(viewDashbordTodoEntityDeliveryList,
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        //结果相加
        installList.addAll(deliveryList);

        List<DashbordTodoNumEntity> cancelList = todoClassify(viewDashbordTodoEntityCancelList,
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID);
        installList.addAll(cancelList);

        List<DashbordTodoNumEntity> settleList = todoClassify(viewDashbordTodoEntitySettleList,
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_SETTLE);
        installList.addAll(settleList);
        return installList;
    }

    /**
     * 根据每个步骤分类出来
     *
     * @param viewDashbordTodoEntityList
     * @param followCode
     * @return
     */
    private List<DashbordTodoNumEntity> todoClassify(List<ViewDashbordTodoEntity> viewDashbordTodoEntityList,
                                                     String followCode) {
        List<DashbordTodoNumEntity> result = new ArrayList<>();
        Map<String, List<ViewDashbordTodoEntity>> classifyMap = viewDashbordTodoEntityList.stream()
                .collect(Collectors.groupingBy(e -> e.getStepName(), Collectors.toList()));
        for (Map.Entry<String, List<ViewDashbordTodoEntity>> item : classifyMap.entrySet()) {
            DashbordTodoNumEntity dashbordTodoNumEntity = new DashbordTodoNumEntity();
            dashbordTodoNumEntity.setFollowCode(followCode);
            dashbordTodoNumEntity.setStepName(item.getKey());
            dashbordTodoNumEntity.setOrderNum(item.getValue().size());
            result.add(dashbordTodoNumEntity);
        }
        result.add(addSum(viewDashbordTodoEntityList,followCode));
        return result;
    }

    /**
     * 任务日历
     * <p>
     * // * @param dashbordQuery
     *
     * @return
     */
    @CustomResult
    @GetMapping("/taskcalendar")
    @ApiOperation(value = "工作日历", notes = "工作日历")

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "startDate",
                    value = "开始日期",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "endDate",
                    value = "结束日期",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),


    })

    public List<RankDto> taskCalendar(
            @RequestParam(value = "startDate", required = true)
                    Date startDate,
            @RequestParam(value = "endDate", required = true)
                    Date endDate) {

        QViewDashbordCalendarEntity qViewDashbordCalendarEntity =
                QViewDashbordCalendarEntity.viewDashbordCalendarEntity;

        UserEntity userEntity = getUserEntity();

        Predicate predicate = qViewDashbordCalendarEntity.corpNo.eq(userEntity.getCorpNo());


        predicate = ExpressionUtils.and(predicate, qViewDashbordCalendarEntity.appointmentDate.between(startDate,
                endDate));


        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {

            // predicate = qViewDashbordCalendarEntity.supplierNo.eq(userEntity.getSupplierNo());
            predicate = ExpressionUtils.and(predicate,
                    qViewDashbordCalendarEntity.supplierNo.eq(userEntity.getSupplierNo()));

        }

        List<RankDto> list = queryFactory
                .select(
                        Projections.bean(
                                RankDto.class,//返回自定义实体的类型
                                (qViewDashbordCalendarEntity.id.count()).as("orderNumLong"),
                                qViewDashbordCalendarEntity.followCode,
                                qViewDashbordCalendarEntity.stepName,
                                qViewDashbordCalendarEntity.appointmentDate
                        )

                )

                //查询积分总数
                .from(qViewDashbordCalendarEntity)
                .where(predicate)
                .groupBy(qViewDashbordCalendarEntity.followCode, qViewDashbordCalendarEntity.stepName,
                        qViewDashbordCalendarEntity.appointmentDate)
                .fetch();


        //    System.out.println(list);

        return list;

    }

    /**
     * 车型分布
     *
     * @param
     * @return
     */
    @CustomResult
    @GetMapping("/rank")
    @ApiOperation(value = "省份和车型排名", notes = "省份和车型排名")

    public DashbordModelParameter rank() {

        DashbordModelParameter result = new DashbordModelParameter();

       /* List<MapDto> mapTtlVehicle = new ArrayList<>();

        List<MapDto> mapFinishVehicle = new ArrayList<>();

        List<MapDto> mapUnFinishVehicle = new ArrayList<>();*/

        //   List<MapDto> mapTtlProvince = new ArrayList<>();

        List<MapDto> mapFinishProvince = new ArrayList<>();

        List<MapDto> mapUnFinishProvince = new ArrayList<>();

        QViewDashbordRankEntity qViewDashbordRankEntity = QViewDashbordRankEntity.viewDashbordRankEntity;
        //商品类型

        // Predicate predicate = qViewDashbordRankEntity.isNotNull().or(qViewDashbordRankEntity.isNull());

        UserEntity userEntity = getUserEntity();

        Predicate predicate = qViewDashbordRankEntity.corpNo.eq(userEntity.getCorpNo());

        if (!StringUtils.isEmpty(userEntity.getSupplierNo())) {


            predicate = ExpressionUtils.and(predicate,
                    qViewDashbordRankEntity.supplierNo.eq(userEntity.getSupplierNo()));

        }

        List<RankDto> provinceList = queryFactory
                .select(
                        Projections.bean(
                                RankDto.class,//返回自定义实体的类型
                                (qViewDashbordRankEntity.orderNum.sum()).as("orderNum"),
                                qViewDashbordRankEntity.finishFlg,
                                qViewDashbordRankEntity.installProvince
                        )
                )

                //查询积分总数
                .from(qViewDashbordRankEntity)
                .where(predicate)
                .groupBy(qViewDashbordRankEntity.finishFlg, qViewDashbordRankEntity.installProvince)
                .fetch();

        if (null != provinceList && !provinceList.isEmpty()) {

            for (RankDto rankDto : provinceList) {

                if (StringUtils.isEmpty(rankDto.getInstallProvince())) {
                    rankDto.setInstallProvince("-");
                }

                if (SysConstant.InstallOrder.FINISH_FLG_Y.equals(rankDto.getFinishFlg())) {
                    MapDto mapDtoFinish = new MapDto();
                    mapDtoFinish.setKey(rankDto.getInstallProvince());
                    mapDtoFinish.setValue(rankDto.getOrderNum() + "");
                    mapFinishProvince.add(mapDtoFinish);
                } else {
                    MapDto mapDtoUnfinish = new MapDto();
                    mapDtoUnfinish.setKey(rankDto.getInstallProvince());
                    mapDtoUnfinish.setValue(rankDto.getOrderNum() + "");
                    mapUnFinishProvince.add(mapDtoUnfinish);
                }
            }
        }

        result.setFinishProvince(mapFinishProvince);

        result.setUnfinishProvince(mapUnFinishProvince);

        // result.setTtlProvince(mapTtlProvince);

        List<RankDto> vehicleList = queryFactory
                .select(
                        Projections.bean(
                                RankDto.class,//返回自定义实体的类型
                                (qViewDashbordRankEntity.orderNum.sum()).as("orderNum"),
                                qViewDashbordRankEntity.finishFlg,
                                qViewDashbordRankEntity.supplierNo,
                                qViewDashbordRankEntity.carVehicle
                        )
                )

                //查询积分总数
                .from(qViewDashbordRankEntity)
                .where(predicate)
                .groupBy(qViewDashbordRankEntity.supplierNo, qViewDashbordRankEntity.carVehicle, qViewDashbordRankEntity.finishFlg)
                .fetch();


        SupplieDto supplieDto = new SupplieDto();
        Map supplieMap = new HashMap();

        for (RankDto rankDto : vehicleList) {
            SupplierEntity supplier = supplierService.findByNo(rankDto.getSupplierNo());
            String supplierName = supplier == null ? "" : supplier.getSupplierName();
            if (!StringUtils.isEmpty(supplierName)) {

                Map vehicleDto = (Map) supplieMap.get(supplierName);
                if (vehicleDto == null) {
                    vehicleDto = new HashMap();
                }
                MapDto mapDto = (MapDto) vehicleDto.get(rankDto.getCarVehicle());
                if (mapDto == null) {
                    mapDto = new MapDto();
                }

                int tt = mapDto.getTotalNum() == null ? 0 : mapDto.getTotalNum();
                int finish = mapDto.getFinishNum() == null ? 0 : mapDto.getFinishNum();
                int unfinish = mapDto.getUnfinishNum() == null ? 0 : mapDto.getUnfinishNum();

                if (rankDto.getFinishFlg().equals(SysConstant.InstallOrder.FINISH_FLG_N)) {
                    finish = finish + rankDto.getOrderNum();
                } else {
                    unfinish = unfinish + rankDto.getOrderNum();
                }
                tt = tt + rankDto.getOrderNum();

                mapDto.setFinishNum(finish);
                mapDto.setUnfinishNum(unfinish);
                mapDto.setTotalNum(tt);
                mapDto.setCarVehicle(rankDto.getCarVehicle());

                vehicleDto.put(rankDto.getCarVehicle(), mapDto);

                vehicleDto.put("supplierName",supplierName);
                supplieMap.put(supplierName, vehicleDto);
            }
            supplieDto.setSupplieMap(supplieMap);
            result.setSupplieDto(supplieDto);
        }
//
//        if (null != vehicleList && !vehicleList.isEmpty()) {
//
//
//            for (RankDto rankDto : vehicleList) {
//                SupplierEntity supplier = supplierService.findByNo(rankDto.getSupplierNo());
//
//                if (StringUtils.isEmpty(rankDto.getCarVehicle())) {
//                    rankDto.setCarVehicle("-");
//                }
//
//                if (SysConstant.InstallOrder.FINISH_FLG_Y.equals(rankDto.getFinishFlg())) {
//                    MapDto mapDtofinish = new MapDto();
//                    mapDtofinish.setSupplierName(supplier == null ? "" : supplier.getSupplierName());
//                    mapDtofinish.setKey(rankDto.getCarVehicle());
//                    mapDtofinish.setValue(rankDto.getOrderNum() + "");
//                    mapFinishVehicle.add(mapDtofinish);
//                } else {
//                    MapDto mapDtoUnfinish = new MapDto();
//                    mapDtoUnfinish.setSupplierName(supplier == null ? "" : supplier.getSupplierName());
//                    mapDtoUnfinish.setKey(rankDto.getCarVehicle());
//                    mapDtoUnfinish.setValue(rankDto.getOrderNum() + "");
//                    mapUnFinishVehicle.add(mapDtoUnfinish);
//                }
//            }
//
//            //计算车型合计
//
//            for(RankDto rankDto : vehicleList ){
//                SupplierEntity supplier = supplierService.findByNo(rankDto.getSupplierNo());
//                MapDto mapDtoTtl = new MapDto();
//                mapDtoTtl.setSupplierName(supplier == null ? "" : supplier.getSupplierName());
//                mapDtoTtl.setKey(rankDto.getCarVehicle());
//                mapDtoTtl.setValue(rankDto.getOrderNum()+"");
//                mapTtlVehicle.add(mapDtoTtl);
//            }
//
//            Set<String> vehicleSets = vehicleList.stream().map(RankDto::getCarVehicle).collect(Collectors.toSet());
//            Set<String> supplierSets = vehicleList.stream().map(RankDto::getSupplierNo).collect(Collectors.toSet());
//
//            for (String vehicle : vehicleSets) {
//                List<RankDto> rankDtoListVehilce =
//                        vehicleList.stream().filter(x -> vehicle.equals(x.getCarVehicle())).collect(Collectors.toList());
//                int sum = rankDtoListVehilce.stream().mapToInt(RankDto::getOrderNum).sum();
//                MapDto mapDtoTtl = new MapDto();
////                mapDtoTtl.setSupplierName();
//                mapDtoTtl.setKey(vehicle);
//                mapDtoTtl.setValue(sum + "");
//                mapTtlVehicle.add(mapDtoTtl);
//
//
//            }

//        }

//        result.setFinishVehicle(mapFinishVehicle);
//
//        result.setUnfinishVehicle(mapUnFinishVehicle);
//
//        result.setTtlVehicle(mapTtlVehicle);

        return result;
    }

    private DashbordTodoNumEntity addSum(List<ViewDashbordTodoEntity> viewDashbordTodoEntityList,String followCode){

        DashbordTodoNumEntity dashbordTodoNumEntity = new DashbordTodoNumEntity();
        dashbordTodoNumEntity.setFollowCode(followCode);
        dashbordTodoNumEntity.setStepName("总量");
        dashbordTodoNumEntity.setOrderNum(viewDashbordTodoEntityList.size());
        return dashbordTodoNumEntity;
    }

}

