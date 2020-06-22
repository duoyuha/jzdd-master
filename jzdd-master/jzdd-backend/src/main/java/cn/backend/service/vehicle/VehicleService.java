package cn.backend.service.vehicle;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.vehicle.VehicleRepository;
import cn.backend.model.primary.vehicle.VehicleEntity;
import cn.backend.model.primary.vehicle.VehicleQuery;
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
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

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Service(value = "vehicleService")
public class VehicleService extends BaseService implements IVehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    /**
     * 分页查询
     *
     * @param vehicleQuery
     * @return
     */
    @Override
    public Page<VehicleEntity> findList(VehicleQuery vehicleQuery) {
        Sort sort = buildSort(vehicleQuery.getSort());
        Pageable pageable = new PageRequest(vehicleQuery.getPageNum(), vehicleQuery.getPageSize(), sort);
        Page<VehicleEntity> entityPage = vehicleRepository.findAll(createSpecification(vehicleQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param vehicleQuery
     * @return
     */
    @Override
    public List<VehicleEntity> findAll(VehicleQuery vehicleQuery) {
        Sort sort = buildSort(vehicleQuery.getSort());
        List<VehicleEntity> entityList = vehicleRepository.findAll(createSpecification(vehicleQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param vehicleQuery
     * @return
     */
    private Specification createSpecification(VehicleQuery vehicleQuery) {
        return new Specification<VehicleEntity>() {

            @Override
            public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //车型名称
                if (!isEmpty(vehicleQuery.getVehicleName())) {
                    predicate.add(cb.like(root.get("vehicleName").as(String.class), "%" + vehicleQuery.getVehicleName() + "%"));
                }

                //公司
                if (!isEmpty(vehicleQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), vehicleQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
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
    public VehicleEntity add(VehicleEntity entity) {
        VehicleEntity checkEntity = findByNameAndCorp(entity.getVehicleName(), entity.getCorpNo());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        entity.setVehicleNo(SysConstant.Vehicle.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        vehicleRepository.saveAndFlush(entity);
        //车型明细
        List<VehicleDetailEntity> vehicleDetailEntityList = entity.getVehicleDetailEntityList();
        for (VehicleDetailEntity item : vehicleDetailEntityList) {
            item.setCorpNo(entity.getCorpNo());
            item.setVehicleNo(entity.getVehicleNo());
            vehicleDetailService.add(item);
        }
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public VehicleEntity edit(VehicleEntity entity) {
        VehicleEntity dbEntity = findById(entity.getId());
        if (!dbEntity.getVehicleName().equals(entity.getVehicleName())) {
            VehicleEntity checkEntity = findByNameAndCorp(entity.getVehicleName(), entity.getCorpNo());
            if (checkEntity != null && !entity.getId().equals(checkEntity.getId())) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        vehicleRepository.saveAndFlush(entity);
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
        VehicleEntity entity = vehicleRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        vehicleRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public VehicleEntity findById(String id) {
        VehicleEntity entity = vehicleRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity != null) {
            entity.setVehicleDetailEntityList(vehicleDetailService.findByVehicleNo(entity.getVehicleNo()));
        }
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public VehicleEntity checkById(String id) {
        VehicleEntity entity = vehicleRepository.findById(SysConstant.IS_DEL_N, id);
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
    public VehicleEntity findByNo(String no) {
        VehicleEntity entity = vehicleRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    @Override
    public VehicleEntity findByNameAndCorp(String name, String corpNo) {
        VehicleEntity entity = vehicleRepository.findByNameAndCorp(SysConstant.IS_DEL_N, name, corpNo);
        return entity;
    }


}

