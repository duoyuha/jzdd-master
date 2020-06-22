package cn.backend.service.vehicledetail;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.vehicledetail.VehicleDetailRepository;
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/08/17
 */
@Service(value = "vehicleDetailService")
public class VehicleDetailService extends BaseService implements IVehicleDetailService {

    @Resource
    private VehicleDetailRepository vehicleDetailRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param vehicleDetailQuery
     * @return
     */
    @Override
    public Page<VehicleDetailEntity> findList(VehicleDetailQuery vehicleDetailQuery) {
        Sort sort = buildSort(vehicleDetailQuery.getSort());
        Pageable pageable = new PageRequest(vehicleDetailQuery.getPageNum(), vehicleDetailQuery.getPageSize(), sort);
        Page<VehicleDetailEntity> entityPage =
                vehicleDetailRepository.findAll(createSpecification(vehicleDetailQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param vehicleDetailQuery
     * @return
     */
    @Override
    public List<VehicleDetailEntity> findAll(VehicleDetailQuery vehicleDetailQuery) {
        Sort sort = buildSort(vehicleDetailQuery.getSort());
        List<VehicleDetailEntity> entityList =
                vehicleDetailRepository.findAll(createSpecification(vehicleDetailQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param vehicleDetailQuery
     * @return
     */
    private Specification createSpecification(VehicleDetailQuery vehicleDetailQuery) {
        return new Specification<VehicleDetailEntity>() {

            @Override
            public Predicate toPredicate(Root<VehicleDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();


                if (!isEmpty(vehicleDetailQuery.getVehicleNo())) {
                    predicate.add(cb.equal(root.get("vehicleNo").as(String.class), vehicleDetailQuery.getVehicleNo()));
                }
                if (!isEmpty(vehicleDetailQuery.getDetailName())) {
                    predicate.add(cb.equal(root.get("detailName").as(String.class),
                            vehicleDetailQuery.getDetailName()));
                }

                predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
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
    public VehicleDetailEntity add(VehicleDetailEntity entity) {
        VehicleDetailEntity checkEntity = findByVehicleNoAndName(entity.getDetailName(),entity.getVehicleNo(), entity.getCorpNo());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        entity.setDetailNo(SysConstant.VehicleDetail.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        vehicleDetailRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public VehicleDetailEntity edit(VehicleDetailEntity entity) {
        VehicleDetailEntity dbEntity = findById(entity.getId());
        if (!dbEntity.getDetailName().equals(entity.getDetailName())) {
            VehicleDetailEntity checkEntity = findByDetailNameAndCorp(entity.getDetailName(), entity.getCorpNo());
            if (checkEntity != null && !entity.getId().equals(checkEntity.getId())) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        configService.convert(entity);
        vehicleDetailRepository.saveAndFlush(entity);
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
        VehicleDetailEntity entity = vehicleDetailRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        vehicleDetailRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public VehicleDetailEntity findById(String id) {
        VehicleDetailEntity entity = vehicleDetailRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public VehicleDetailEntity checkById(String id) {
        VehicleDetailEntity entity = vehicleDetailRepository.findById(SysConstant.IS_DEL_N, id);
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
    public VehicleDetailEntity findByNo(String no) {
        VehicleDetailEntity entity = vehicleDetailRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据车型编号查找
     *
     * @param no
     * @return
     */
    @Override
    public List<VehicleDetailEntity> findByVehicleNo(String no) {
        return vehicleDetailRepository.findByVehicleNo(SysConstant.IS_DEL_N, no);
    }

    /**
     * 根据车型详细名称和车型编号查找
     *
     * @param name
     * @return
     */
    @Override
    public VehicleDetailEntity findByDetailNameAndCorp(String name, String corpNo) {
        return vehicleDetailRepository.findByDetailNameAndCorp(SysConstant.IS_DEL_N, name,corpNo);
    }


    @Override
    public VehicleDetailEntity findByVehicleNoAndName(String name,String no, String corpNo){
        return vehicleDetailRepository.findByVehicleNoAndName(SysConstant.IS_DEL_N,name,no,corpNo);
    }
}

