package cn.backend.service.supplier;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.supplier.SupplierRepository;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.supplier.SupplierQuery;
import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.supplierarea.ISupplierAreaService;
import cn.backend.service.user.IUserService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.DateUtils;
import cn.zdwl.common.util.IdUtils;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Service(value = "supplierService")
public class SupplierService extends BaseService implements ISupplierService {

    @Resource
    private SupplierRepository supplierRepository;

    @Autowired
    private ISupplierAreaService supplierAreaService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    /**
     * 分页查询
     *修改排序按照订单生成时间正序
     * @param supplierQuery
     * @return
     */
    @Override
    public Page<SupplierEntity> findList(SupplierQuery supplierQuery) {
        Sort sort = buildSort("+createdWhen");
        Pageable pageable = new PageRequest(supplierQuery.getPageNum(), supplierQuery.getPageSize(), sort);
        Page<SupplierEntity> entityPage = supplierRepository.findAll(createSpecification(supplierQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param supplierQuery
     * @return
     */
    @Override
    public List<SupplierEntity> findAll(SupplierQuery supplierQuery) {
        Sort sort = buildSort(supplierQuery.getSort());
        List<SupplierEntity> entityList = supplierRepository.findAll(createSpecification(supplierQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param supplierQuery
     * @return
     */
    private Specification createSpecification(SupplierQuery supplierQuery) {
        return new Specification<SupplierEntity>() {

            @Override
            public Predicate toPredicate(Root<SupplierEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //服务商名称
                if (!isEmpty(supplierQuery.getSupplierName())) {
                    predicate.add(cb.equal(root.get("supplierName").as(String.class), supplierQuery.getSupplierName()));
                }

                if (!isEmpty(supplierQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), supplierQuery.getSupplierNo()));
                }

                //公司
                if (!isEmpty(supplierQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), supplierQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                UserEntity user = userService.findById(BaseContextHandler.getUserId());

                if (!isEmpty(user.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), user.getSupplierNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 新建
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public SupplierEntity add(SupplierEntity entity) {
        //服务商名称不能重复
        SupplierEntity checkEntity = findByNameAndCorp(entity.getSupplierName(), entity.getCorpNo());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        //生成编号
        entity.setSupplierNo(SysConstant.Supplier.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        supplierRepository.saveAndFlush(entity);
        //区域
        List<SupplierAreaEntity> areaList = JSONArray.parseArray(entity.getArea(), SupplierAreaEntity.class);
        // Set<SupplierAreaEntity> supplierAreaEntitySet = new HashSet<>(areaList);
        for (SupplierAreaEntity item : areaList) {
            item.setCorpNo(entity.getCorpNo());
            item.setSupplierNo(entity.getSupplierNo());
        }
        supplierAreaService.saveBatch(areaList);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public SupplierEntity edit(SupplierEntity entity) {
        SupplierEntity dbEntity = findById(entity.getId());
        //服务商名称不能重复
        if (!dbEntity.getSupplierName().equals(entity.getSupplierName())) {
            SupplierEntity checkEntity = findByNameAndCorp(entity.getSupplierName(), entity.getCorpNo());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        supplierRepository.saveAndFlush(entity);
        //区域信息
        //关系是否变化，变化才处理
        // List<SupplierAreaEntity> areaList = JSONArray.parseArray(entity.getArea(), SupplierAreaEntity.class);
        // Set<SupplierAreaEntity> areas = new HashSet<>(areaList);
        // if (!CollectionUtils.isEmpty(areas)) {
        //     //原绑定的
        //     Set<SupplierAreaEntity> originalAreas = supplierAreaService.findBySupplierNo(entity.getSupplierNo());
        //     //不一样才做处理
        //     if (!originalAreas.equals(areas)) {
        //         Set<SupplierAreaEntity> addSet = (Set<SupplierAreaEntity>) CustomCollectionUtils.subtract(areas, originalAreas);
        //         Set<SupplierAreaEntity> deleteSet = (Set<SupplierAreaEntity>) CustomCollectionUtils.subtract(originalAreas, areas);
        //         //删除操作
        //         if (deleteSet.size() > 0) {
        //             supplierAreaService.deleteBySet(deleteSet);
        //         }
        //         //新增操作
        //         if (addSet.size() > 0) {
        //             for (SupplierAreaEntity item : addSet) {
        //                 item.setCorpNo(entity.getCorpNo());
        //                 item.setSupplierNo(entity.getSupplierNo());
        //             }
        //             supplierAreaService.saveBatch(addSet);
        //         }
        //     }
        // }
        //先把旧的删除
        SupplierAreaQuery supplierAreaQuery = new SupplierAreaQuery();
        supplierAreaQuery.setSupplierNo(entity.getSupplierNo());
        List<SupplierAreaEntity> areaList = supplierAreaService.findAll(supplierAreaQuery);
//        Set<String> ids = areaList.stream().map(e -> e.getId()).collect(Collectors.toSet());
//        supplierAreaService.deleteBySet(ids);
        for (SupplierAreaEntity item : areaList) {
            item.setIsDel(SysConstant.IS_DEL_Y);
        }
        supplierAreaService.saveBatch(areaList);
        //新增
        List<SupplierAreaEntity> addAreaList = JSONArray.parseArray(entity.getArea(), SupplierAreaEntity.class);
        // Set<SupplierAreaEntity> supplierAreaEntitySet = new HashSet<>(addAreaList);
        for (SupplierAreaEntity item : addAreaList) {
            item.setCorpNo(entity.getCorpNo());
            item.setSupplierNo(entity.getSupplierNo());
        }
        supplierAreaService.saveBatch(addAreaList);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        SupplierEntity entity = supplierRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        supplierRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public SupplierEntity findById(String id) {
        SupplierEntity entity = supplierRepository.findById(SysConstant.IS_DEL_N, id);
        Set<SupplierAreaEntity> supplierAreaEntities = supplierAreaService.findBySupplierNo(entity.getSupplierNo());
        entity.setSupplierAreaEntities(supplierAreaEntities);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public SupplierEntity checkById(String id) {
        SupplierEntity entity = supplierRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    @Override
    public SupplierEntity findByNo(String no) {
        SupplierEntity entity = supplierRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据名称查找
     *
     * @param name
     * @param corpNo
     * @return
     */
    @Override
    public SupplierEntity findByNameAndCorp(String name, String corpNo) {
        SupplierEntity entity = supplierRepository.findByNameAndCorp(SysConstant.IS_DEL_N, name, corpNo);
        return entity;
    }

    /**
     * 根据订单查询服务商详细
     *
     * @param province
     * @param city
     * @return
     */
    @Override
    public List<SupplierEntity> findListByArea(String province, String city, String corpNo) {
        return supplierRepository.findListByArea(SysConstant.IS_DEL_N, province, city, corpNo);
    }

    /**
     * 根据地区查询服务商详细
     *
     * @param orderNo
     * @return
     */
    @Override
    public List<SupplierEntity> findListByOrder(String orderType, String orderNo) {
        //获取当前月份第一天0点0分0秒 当前月份最后一天23点59分59秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date sdate = calendar.getTime();
        //将当前月加1；
        calendar.add(Calendar.MONTH, 1);
        //在当前月的下一月基础上减去1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天
        Date edate = calendar.getTime();
        Date startTime=DateUtils.parseDate(sdf.format(sdate),"yyyy-MM-dd HH:mm:ss");
        Date endTime=DateUtils.parseDate(sdf.format(edate),"yyyy-MM-dd HH:mm:ss");
        String province = "";
        String city = "";
        String corpNo = "";
        switch (orderType) {
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL:
                InstallOrderEntity installOrderEntity = installOrderService.findByNo(orderNo);
                if (installOrderEntity == null) {
                    throw new AppException(CustomMessage.NO_DATA_FIND);
                }
                province = installOrderEntity.getInstallProvince();
                city = installOrderEntity.getInstallCity();
                corpNo = installOrderEntity.getCorpNo();
                break;
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY:
                DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByNo(orderNo);
                if (deliveryOrderEntity == null) {
                    throw new AppException(CustomMessage.NO_DATA_FIND);
                }
                province = deliveryOrderEntity.getInstallProvince();
                city = deliveryOrderEntity.getInstallCity();
                corpNo = deliveryOrderEntity.getCorpNo();
                break;
        }
        List<SupplierEntity> supplierEntityList = supplierRepository.findListByArea(SysConstant.IS_DEL_N, province, city, corpNo);
        String filterCity = city;
        int cityNum = 0;
        int allNum = 0;
        int cityNotFianlNum = 0;
        int allNotFinalNum = 0;
        int monthNum = 0;
        switch (orderType) {
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY:
                DeliveryOrderQuery deliveryOrderQuery = new DeliveryOrderQuery();
                List<DeliveryOrderEntity> deliveryOrderEntityList = deliveryOrderService.findAll(deliveryOrderQuery);
                // List<DeliveryOrderEntity> deliverAllComplete = deliveryOrderEntityList.stream()
                //         .filter(e -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(e.getFinishFlg()))
                //         .collect(Collectors.toList());
                List<DeliveryOrderEntity> deliverAllSign = deliveryOrderEntityList.stream()
                        .filter(e -> SysConstant.InstallOrder.RECEIVE_FLG_SIGN.equals(e.getReceiveStatus())
                                && SysConstant.InstallOrder.CONFIG_FLG_N.equals(e.getFinishFlg()))
                        .collect(Collectors.toList());
                for (SupplierEntity item : supplierEntityList) {
                    //全国数量
                    allNum = deliveryOrderEntityList.stream().filter(x -> item.getSupplierNo().equals(x.getSupplierNo())).collect(Collectors.toList()).size();
                    //城市数量
                    cityNum = deliveryOrderEntityList.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();
                    //当月
                    monthNum = deliveryOrderEntityList.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).filter(x->x.getOrderTime().after(startTime)).filter(x->x.getOrderTime().before(endTime)).collect(Collectors.toList()).size();
                    //全国已签收未完成数量
                    allNotFinalNum = deliverAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo())).collect(Collectors.toList()).size();
                    //城市已签收未完成数量
                    cityNotFianlNum = deliverAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();
                    Map<String, Integer> numMap = new HashMap<>();
                    numMap.put("cityNum", cityNum);
                    numMap.put("allNum", allNum);
                    numMap.put("cityNotFianlNum", cityNotFianlNum);
                    numMap.put("allNotFinalNum", allNotFinalNum);
                    numMap.put("monthNum", monthNum);
                    item.setNumMap(numMap);
                    item.setCity(filterCity);
                }
                break;
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL:
                InstallOrderQuery installOrderQuery = new InstallOrderQuery();
                List<InstallOrderEntity> installOrderEntityList = installOrderService.findAll(installOrderQuery);
                // List<InstallOrderEntity> installAllComplete = installOrderEntityList.stream()
                //         .filter(e -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(e.getFinishFlg()))
                //         .collect(Collectors.toList());
                List<InstallOrderEntity> installAllSign = installOrderEntityList.stream()
                        .filter(e -> SysConstant.InstallOrder.RECEIVE_FLG_SIGN.equals(e.getReceiveStatus())
                                && SysConstant.InstallOrder.CONFIG_FLG_N.equals(e.getFinishFlg()))
                        .collect(Collectors.toList());
                for (SupplierEntity item : supplierEntityList) {
                    // //全国数量
                    // List<InstallOrderEntity> supplierInstallList = installOrderEntityList.stream().filter(x -> item.getSupplierNo().equals(x.getSupplierNo())).collect(Collectors.toList());
                    // if (supplierInstallList != null && supplierInstallList.size() > 0) {
                    //     allNum = supplierInstallList.size();
                    // }
                    // //城市数量
                    // cityNum = installOrderEntityList.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();
                    // //全国已签收未完成数量
                    // allNotFinalNum = installAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();
                    // //城市已签收未完成数量
                    // cityNotFianlNum = installAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();

                    //全国数量
                    allNum = installOrderEntityList.stream().filter(x -> item.getSupplierNo().equals(x.getSupplierNo())).collect(Collectors.toList()).size();
                    //城市数量
                    cityNum = installOrderEntityList.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();
                    //当月
                    monthNum = installOrderEntityList.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).filter(x->x.getOrderTime().after(startTime)).filter(x->x.getOrderTime().before(endTime)).collect(Collectors.toList()).size();
                    //全国已签收未完成数量
                    allNotFinalNum = installAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo())).collect(Collectors.toList()).size();
                    //城市已签收未完成数量
                    cityNotFianlNum = installAllSign.stream().filter(e -> item.getSupplierNo().equals(e.getSupplierNo()) && filterCity.equals(e.getInstallCity())).collect(Collectors.toList()).size();

                    Map<String, Integer> numMap = new HashMap<>();
                    numMap.put("cityNum", cityNum);
                    numMap.put("allNum", allNum);
                    numMap.put("cityNotFianlNum", cityNotFianlNum);
                    numMap.put("allNotFinalNum", allNotFinalNum);
                    numMap.put("monthNum", monthNum);
                    item.setNumMap(numMap);
                    item.setCity(filterCity);
                }
                break;
        }
        return supplierEntityList;
    }

    @Override
    public List<SupplierEntity> countMonth(String corpNo) {
        //获取当前月份第一天0点0分0秒 当前月份最后一天23点59分59秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date sdate = calendar.getTime();
        //将当前月加1；
        calendar.add(Calendar.MONTH, 1);
        //在当前月的下一月基础上减去1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天
        Date edate = calendar.getTime();
        Date startTime=DateUtils.parseDate(sdf.format(sdate),"yyyy-MM-dd HH:mm:ss");
        Date endTime=DateUtils.parseDate(sdf.format(edate),"yyyy-MM-dd HH:mm:ss");
        SupplierQuery supplierQuery=new SupplierQuery();
        supplierQuery.setCorpNo(corpNo);
        List<SupplierEntity> supplierEntityList = this.findAll(supplierQuery);
        ViewDeliveryOrderQuery deliveryOrderQuery = new ViewDeliveryOrderQuery();
        deliveryOrderQuery.setCorpNo(corpNo);
        //修改查询未完成订单为全部订单
        deliveryOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);
        List<ViewDeliveryOrderEntity> deliveryOrderEntityList = viewDeliveryOrderService.findAll(deliveryOrderQuery);
        InstallOrderQuery installOrderQuery = new InstallOrderQuery();
        installOrderQuery.setCorpNo(corpNo);
        //修改查询未完成订单为全部订单
        installOrderQuery.setAllOrderFlg(SysConstant.Common.LIST_ALL_FLG_YES);
        List<ViewInstallOrderEntity> installOrderEntityList = installOrderViewService.findAll(installOrderQuery);
        for (SupplierEntity item : supplierEntityList) {
           int monthDelNum=deliveryOrderEntityList.stream().filter(x->SysConstant.IS_DEL_N.equals(x.getBeginCacle())).filter(x-> item.getSupplierNo().equals(x.getSupplierNo())).filter(x->x.getOrderTime().after(startTime)).filter(x->x.getOrderTime().before(endTime)).collect(Collectors.toList()).size();
           int monthInsNum=installOrderEntityList.stream().filter(x->SysConstant.IS_DEL_N.equals(x.getBeginCacle())).filter(x-> item.getSupplierNo().equals(x.getSupplierNo())).filter(x->x.getOrderTime().after(startTime)).filter(x->x.getOrderTime().before(endTime)).collect(Collectors.toList()).size();
           int all=monthDelNum+monthInsNum;
           item.setAllMonth(all);
        }

        return supplierEntityList;
    }

    /**
     * 自动派单
     *
     * @param supplierQuery
     * @return
     */
    @Override
    @Transactional
    public boolean automaticOrder(SupplierQuery supplierQuery) {
        //校验自动派单状态
        ConfigEntity configEntity = configService.findByTypeAndCode(SysConstant.Supplier.CONFIG_TYPE_AUTO_ORDER
                , SysConstant.Supplier.CONFIG_VAL_AUTO_ORDER, supplierQuery.getCorpNo());
        if (configEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        String automaticType = configEntity.getConfigCont1();
        if (automaticType.equals(supplierQuery.getAutomaticCommand())) {
            throw new AppException(CustomMessage.DATA_ERROR);
        }
        //设置派单状态
        configEntity.setConfigCont1(supplierQuery.getAutomaticCommand());
        configService.edit(configEntity);
        //百分比
        // Map<String, Double> automaticMap = supplierQuery.getAutomaticMap();
        List<SupplierEntity> supplierEntityList = findAll(supplierQuery);
        // for (SupplierEntity item : supplierEntityList) {
        //     if(automaticMap.containsKey(item.getSupplierNo())){
        //         item.setOrderScale(automaticMap.get(item.getSupplierNo()));
        //     }
        // }
        //保存
        supplierRepository.save(supplierEntityList);
        return true;
    }

    /**
     * 设置自动派单比例
     *
     * @param supplierEntityList
     * @return
     */
    @Override
    @Transactional
    public boolean setAutoOrderProp(List<SupplierEntity> supplierEntityList) {
        //校验是否超过100
        int sumProp = supplierEntityList.stream().mapToInt(SupplierEntity::getAutoOrderProp).sum();
        if (sumProp != 100) {
            throw new AppException(CustomMessage.AUTO_ORDER_PROP_CANNOT_OVER_HUNDRED);
        }
        for (SupplierEntity item : supplierEntityList) {
            supplierRepository.setAutoOrderProp(item.getAutoOrderProp(), item.getId());
        }
        return true;
    }


}

