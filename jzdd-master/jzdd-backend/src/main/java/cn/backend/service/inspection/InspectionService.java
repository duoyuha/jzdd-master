package cn.backend.service.inspection;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.inspection.InspectionRepository;
import cn.backend.model.primary.inspection.InspectionEntity;
import cn.backend.model.primary.inspection.InspectionQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.service.BaseService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.ordercar.OrderCarService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/07/29
 */
@Service(value = "inspectionService")
public class InspectionService extends BaseService implements IInspectionService {

    @Resource
    private InspectionRepository inspectionRepository;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private OrderCarService orderCarService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISupplierService supplierService;

    /**
     * 分页查询
     *
     * @param inspectionQuery
     * @return
     */
    @Override
    public Page<InspectionEntity> findList(InspectionQuery inspectionQuery) {
        Sort sort = buildSort(inspectionQuery.getSort());
        Pageable pageable = new PageRequest(inspectionQuery.getPageNum(), inspectionQuery.getPageSize(), sort);
        //  Page<InspectionEntity> entityPage = inspectionRepository.findPage(createSpecification(inspectionQuery),pageable);
        Page<InspectionEntity> entityPage = inspectionRepository.findAll(createSpecification(inspectionQuery), pageable);
        for (InspectionEntity inspection : entityPage.getContent()) {
            inspection.setInstallOrderEntity(installOrderService.findByNo(inspection.getOrderNo()));
            OrderCarEntity orderCarEntity = orderCarService.findByNo(inspection.getOrderNo());
            if(orderCarEntity != null){
                inspection.setVin(orderCarEntity.getVinNo());
            }
        }
        return entityPage;
    }


    /**
     * 列表查询
     *
     * @param inspectionQuery
     * @return
     */
    @Override
    public List<InspectionEntity> findAll(InspectionQuery inspectionQuery) {
        Sort sort = buildSort(inspectionQuery.getSort());
        List<InspectionEntity> entityList = inspectionRepository.findAll(createSpecification(inspectionQuery), sort);
        for (InspectionEntity inspection : entityList) {
            inspection.setInstallOrderEntity(installOrderService.findByNo(inspection.getOrderNo()));
            OrderCarEntity orderCarEntity = orderCarService.findByNo(inspection.getOrderNo());
            if(orderCarEntity != null){
                inspection.setVin(orderCarEntity.getVinNo());
            }
        }
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param inspectionQuery
     * @return
     */
    private Specification createSpecification(InspectionQuery inspectionQuery) {
        return new Specification<InspectionEntity>() {

            @Override
            public Predicate toPredicate(Root<InspectionEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //是否完成
                if (!StringUtils.isEmpty(inspectionQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), inspectionQuery.getFinishFlg()));
                }
                //订单编号
                if (!StringUtils.isEmpty(inspectionQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), inspectionQuery.getOrderNo()));
                }
                //巡检状态
                if (!StringUtils.isEmpty(inspectionQuery.getInspectionStatus())) {
                    predicate.add(cb.equal(root.get("inspectionStatus").as(String.class), inspectionQuery.getInspectionStatus()));
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
    public List<InspectionEntity> addList(InspectionEntity entity) {
        List<InspectionEntity> result = new ArrayList<>();

        //根据nos分别创建巡检单
        String[] nos = entity.getOrderNos().split(",");
        for (String no : nos) {
            InspectionEntity inspectionEntity = new InspectionEntity();

            InstallOrderEntity installOrderEntity = installOrderService.findByNo(no);
            installOrderEntity.setInspectionTourFlg(SysConstant.Inspetcion.CHECK_FLG_YES);
            installOrderService.edit(installOrderEntity);
            inspectionEntity.setInspectionNo(SysConstant.Inspection.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            inspectionEntity.setOrderNo(installOrderEntity.getOrderNo());
            inspectionEntity.setFinishFlg(SysConstant.IS_DEL_N);
            inspectionEntity.setSupplierNo(installOrderEntity.getSupplierNo());
            inspectionEntity.setSupplierName(installOrderEntity.getSupplierName());
            inspectionRepository.saveAndFlush(inspectionEntity);
            result.add(inspectionEntity);
        }
        return result;
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public List<InspectionEntity> addList2(InspectionEntity entity) {
//       List<InspectionEntity> result = new ArrayList<>();
//
//        //根据nos分别创建巡检单
//       for (String no : entity.getOrderNoList()) {
//
//            InspectionEntity inspectionEntityList = findByNo(no);
//            if(inspectionEntityList != null){
//                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
//            }
//
//            InstallOrderEntity installOrderEntity = installOrderService.findByNo(no);
//            entity.setInspectionStatus(SysConstant.InstallOrder.CONFIG_FLG_N);
//            installOrderEntity.setInspectionTourFlg(SysConstant.Inspetcion.CHECK_FLG_YES);
//            installOrderService.edit(installOrderEntity);
//
//           //UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
//          // entity.setInspectionPerson(userEntity.getUserName());
//            InspectionEntity inspectionEntity = new InspectionEntity();
//            inspectionEntity.setInspectionNo(SysConstant.Inspection.PREFIX_NUMBER + IdUtils.getGenerateNumber());
//            inspectionEntity.setOrderNo(installOrderEntity.getOrderNo());
//            inspectionEntity.setFinishFlg(SysConstant.IS_DEL_N);
//            inspectionEntity.setSupplierNo(installOrderEntity.getSupplierNo());
//            inspectionEntity.setSupplierName(installOrderEntity.getSupplierName());
//
//            //installOrderEntity.getOrderNo(); orderentity vin set
//            OrderCarEntity orderCarEntity = orderCarService.findByNo(no);
//            inspectionEntity.setVin(orderCarEntity.getVinNo());
//          // add(inspectionEntity);
//            result.add(inspectionEntity);
//            if (!installOrderEntity.getOrderNo().equals(entity.getOrderNo())) {
//
//                inspectionRepository.saveAndFlush(inspectionEntity);
//                result.add(inspectionEntity);
//            }
//       }
       List<InspectionEntity> result = new ArrayList<>();
        List<String> installOrderNoList = entity.getOrderNoList();

        for (String item : installOrderNoList) {
            InspectionEntity inspectionEntity = new InspectionEntity();
            InspectionEntity inspectionEntityList = findByNo(item);
            if(inspectionEntityList != null){
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
            inspectionEntity.setInspectionStatus(SysConstant.InstallOrder.CONFIG_FLG_N);
            inspectionEntity.setOrderNo(item);
            UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
            inspectionEntity.setInspectionPerson(userEntity.getUserName());
            inspectionEntity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
            inspectionEntity.setSupplierNo(userEntity.getSupplierNo());
            SupplierEntity supplierEntity = supplierService.findByNo(userEntity.getSupplierNo());
            inspectionEntity.setSupplierName(supplierEntity == null ? null : supplierEntity.getSupplierName());
            add(inspectionEntity);
            InstallOrderEntity installOrderEntity = installOrderService.findByNo(item);
            if (!installOrderEntity.getOrderNo().equals(entity.getOrderNo())) {
                result.add(inspectionEntity);
            }

        }
        return result;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public InspectionEntity edit(InspectionEntity entity) {


        inspectionRepository.saveAndFlush(entity);
//        InstallOrderEntity installOrderEntity= installOrderService.findByNo(entity.getOrderNo());
//        installOrderEntity.setInspectionTourFlg(SysConstant.Inspetcion.CHECK_FLG_YES);
//        installOrderService.edit(installOrderEntity);
//        entity.setInspectionNo(SysConstant.Inspection.PREFIX_NUMBER+ IdUtils.getGenerateNumber());
//        entity.setOrderNo(installOrderEntity.getOrderNo());
        return entity;
    }

    /**
     * 废弃
     *
     * @param entity
     * @return
     */
    @Override
    public InspectionEntity add(InspectionEntity entity) {
        inspectionRepository.saveAndFlush(entity);
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
        InspectionEntity entity = inspectionRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        inspectionRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public InspectionEntity findById(String id) {
        InspectionEntity entity = inspectionRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public InspectionEntity checkById(String id) {
        InspectionEntity entity = inspectionRepository.findById(SysConstant.IS_DEL_N, id);
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
    public InspectionEntity findByNo(String no) {
        InspectionEntity entity = inspectionRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }
    @Override
    @Transactional
    public void timer() {
        InspectionQuery query = new InspectionQuery();
        Date data = new Date();
        List<String> nos = new ArrayList<>();
        List<InspectionEntity> list = findAll(query);
        List<InspectionEntity> listAll = new ArrayList<>();
        Map<String, List<InspectionEntity>> map = list.stream().collect(Collectors.groupingBy(e -> e.getOrderNo(), Collectors.toList()));
        //Map<String, List<InspectionEntity>> mapResult = new TreeMap<String, List<InspectionEntity>>();
        for (Map.Entry<String, List<InspectionEntity>> entry : map.entrySet()) {
            entry.setValue(entry.getValue().stream().sorted((e1, e2) -> (e2.getInspectionTime().compareTo(e1.getInspectionTime()))).collect(Collectors.toList()));
            listAll.add(entry.getValue().stream().findFirst().orElse(null));
        }
        for (InspectionEntity inspectionEntity : listAll) {
            LocalDateTime startTime = dateToLocalDateTime(inspectionEntity.getInspectionTime());
            LocalDateTime endTime = dateToLocalDateTime(data);
            Duration duration = Duration.between(startTime, endTime);
            int minutes = (int) duration.toMinutes();
            if (minutes > SysConstant.Inspetcion.THREE_MOUTHS_MINS) {
                nos.add(inspectionEntity.getOrderNo());
            }
        }
        InstallOrderQuery installOrderQuery = new InstallOrderQuery();
        List<InstallOrderEntity> installOrderEntityList = installOrderService.findAll(installOrderQuery);
        installOrderEntityList = installOrderEntityList.stream().filter(e -> nos.contains(e.getOrderNo())).collect(Collectors.toList());
        for (InstallOrderEntity installOrderEntity : installOrderEntityList) {
            installOrderEntity.setInspectionTourFlg(SysConstant.IS_DEL_N);
        }
        //批量修改
        installOrderService.addList(installOrderEntityList);
    }
    /**
     * date转localDateTime
     *
     * @param date
     * @return
     */
    private LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}

