package cn.backend.service.supplierarea;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.supplierarea.SupplierAreaRepository;
import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaQuery;
import cn.backend.service.BaseService;
import cn.zdwl.common.exception.AppException;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Service(value = "supplierAreaService")
public class SupplierAreaService extends BaseService implements ISupplierAreaService {

    @Resource
    private SupplierAreaRepository supplierAreaRepository;

    /**
     * 分页查询
     *
     * @param supplierAreaQuery
     * @return
     */
    @Override
    public Page<SupplierAreaEntity> findList(SupplierAreaQuery supplierAreaQuery) {
        Sort sort = buildSort(supplierAreaQuery.getSort());
        Pageable pageable = new PageRequest(supplierAreaQuery.getPageNum(), supplierAreaQuery.getPageSize(), sort);
        Page<SupplierAreaEntity> entityPage = supplierAreaRepository.findAll(createSpecification(supplierAreaQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param supplierAreaQuery
     * @return
     */
    @Override
    public List<SupplierAreaEntity> findAll(SupplierAreaQuery supplierAreaQuery) {
        Sort sort = buildSort(supplierAreaQuery.getSort());
        List<SupplierAreaEntity> entityList = supplierAreaRepository.findAll(createSpecification(supplierAreaQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param supplierAreaQuery
     * @return
     */
    private Specification createSpecification(SupplierAreaQuery supplierAreaQuery) {
        return new Specification<SupplierAreaEntity>() {

            @Override
            public Predicate toPredicate(Root<SupplierAreaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));
                //服务商名称
                if (!isEmpty(supplierAreaQuery.getCityName())) {
                    predicate.add(cb.equal(root.get("supplierCity").as(String.class), supplierAreaQuery.getCityName()));
                }
                if (!isEmpty(supplierAreaQuery.getProvinceName())) {
                    predicate.add(cb.equal(root.get("supplierProvince").as(String.class), supplierAreaQuery.getProvinceName()));
                }

                if (!isEmpty(supplierAreaQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), supplierAreaQuery.getCorpNo()));
                }

                if(!isEmpty(supplierAreaQuery.getSupplierNo())){
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), supplierAreaQuery.getSupplierNo()));
                }

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
    public SupplierAreaEntity add(SupplierAreaEntity entity) {
        supplierAreaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public SupplierAreaEntity edit(SupplierAreaEntity entity) {
        supplierAreaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 批量保存
     *
     * @param supplierAreaEntityList
     * @return
     */
    @Override
    public List<SupplierAreaEntity> saveBatch(List<SupplierAreaEntity> supplierAreaEntityList) {
        supplierAreaRepository.save(supplierAreaEntityList);
        return supplierAreaEntityList;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        SupplierAreaEntity entity = supplierAreaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        supplierAreaRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public SupplierAreaEntity findById(String id) {
        SupplierAreaEntity entity = supplierAreaRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public SupplierAreaEntity checkById(String id) {
        SupplierAreaEntity entity = supplierAreaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param supplierNo
     * @return
     */
    @Override
    public Set<SupplierAreaEntity> findBySupplierNo(String supplierNo) {
        Set<SupplierAreaEntity> entities = supplierAreaRepository.findBySupplierNo(SysConstant.IS_DEL_N, supplierNo);
        return entities;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBySet(Set<String> ids) {
        supplierAreaRepository.deleteBySet(SysConstant.IS_DEL_Y, ids);
        return true;
    }


}

