package cn.backend.service.vin;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.vin.VinRepository;
import cn.backend.model.primary.vin.VinEntity;
import cn.backend.model.primary.vin.VinQuery;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Service(value = "vinService")
public class VinService extends BaseService implements IVinService {

    @Resource
    private VinRepository vinRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param vinQuery
     * @return
     */
    @Override
    public Page<VinEntity> findList(VinQuery vinQuery) {
        Sort sort = buildSort(vinQuery.getSort());
        Pageable pageable = new PageRequest(vinQuery.getPageNum(), vinQuery.getPageSize(), sort);
        Page<VinEntity> entityPage = vinRepository.findAll(createSpecification(vinQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param vinQuery
     * @return
     */
    @Override
    public List<VinEntity> findAll(VinQuery vinQuery) {
        Sort sort = buildSort(vinQuery.getSort());
        List<VinEntity> entityList = vinRepository.findAll(createSpecification(vinQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param vinQuery
     * @return
     */
    private Specification createSpecification(VinQuery vinQuery) {
        return new Specification<VinEntity>() {

            @Override
            public Predicate toPredicate(Root<VinEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //vin编码
                if (!isEmpty(vinQuery.getVinCode())) {
                    predicate.add(cb.like(root.get("vinCode").as(String.class), "%" + vinQuery.getVinCode() + "%"));
                }

                //创建时间
                if (!isEmpty(vinQuery.getFromTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("createdWhen").as(Date.class), vinQuery.getFromTime()));
                }
                if (!isEmpty(vinQuery.getToTime())) {
                    //小于或等于创建时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("createdWhen").as(Date.class), vinQuery.getToTime()));
                }

                //公司
                if (!isEmpty(vinQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), vinQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }
                //不送桩原因
                if(!isEmpty(vinQuery.getRemark())){
                    predicate.add(cb.equal(root.get("remark").as(String.class), vinQuery.getRemark()));
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
    public VinEntity add(VinEntity entity) {
        //vinCode不能重复
        VinEntity checkEntity = findByCode(entity.getVinCode());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        entity.setVinNo(SysConstant.Vin.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        vinRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public VinEntity edit(VinEntity entity) {
        VinEntity dbEntity = findById(entity.getId());
        //vinCode不能重复
        if (!dbEntity.getVinCode().equals(entity.getVinCode())) {
            VinEntity checkEntity = findByCode(entity.getVinCode());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        vinRepository.saveAndFlush(entity);
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
        VinEntity entity = vinRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        vinRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }


    /**
     * 删除根据创建时间查询出来的东西
     *
     * @param vinQuery
     * @return
     */
    @Override
    @Transactional
    public boolean delete(VinQuery vinQuery) {
//        if(isEmpty(vinQuery.getFromTime()) && isEmpty(vinQuery.getToTime())){
//            throw new AppException(CustomMessage.FROM_TIME_NULL);
//        }
        List<VinEntity> vinEntities = findAll(vinQuery);
            for (VinEntity item : vinEntities) {
                    item.setIsDel(SysConstant.IS_DEL_Y);
            }
            vinRepository.save(vinEntities);
           return true;

    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public VinEntity findById(String id) {
        VinEntity entity = vinRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public VinEntity checkById(String id) {
        VinEntity entity = vinRepository.findById(SysConstant.IS_DEL_N, id);
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
    public VinEntity findByNo(String no) {
        VinEntity entity = vinRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据编码查找
     *
     * @param code
     * @return
     */
    @Override
    public VinEntity findByCode(String code) {
        VinEntity entity = vinRepository.findByCode(SysConstant.IS_DEL_N, code);
        return entity;
    }

    /**
     * 查询不送桩原因列表
     * @return
     */
    @Override
    public List<String> getRemark(){

        return vinRepository.getRemark();

    }
}

