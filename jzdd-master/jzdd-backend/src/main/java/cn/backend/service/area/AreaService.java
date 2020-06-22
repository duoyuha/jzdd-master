package cn.backend.service.area;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.area.AreaRepository;
import cn.backend.model.primary.area.AreaEntity;
import cn.backend.model.primary.area.AreaQuery;
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

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "areaService")
public class AreaService extends BaseService implements IAreaService {

    @Resource
    private AreaRepository areaRepository;

    /**
     * 分页查询
     *
     * @param areaQuery
     * @return
     */
    @Override
    public Page<AreaEntity> findList(AreaQuery areaQuery) {
        Sort sort = buildSort(areaQuery.getSort());
        Pageable pageable = new PageRequest(areaQuery.getPageNum(), areaQuery.getPageSize(), sort);
        Page<AreaEntity> entityPage = areaRepository.findAll(createSpecification(areaQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param areaQuery
     * @return
     */
    @Override
    public List<AreaEntity> findAll(AreaQuery areaQuery) {
        Sort sort = buildSort(areaQuery.getSort());
        List<AreaEntity> entityList = areaRepository.findAll(createSpecification(areaQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param areaQuery
     * @return
     */
    private Specification createSpecification(AreaQuery areaQuery) {
        return new Specification<AreaEntity>() {

            @Override
            public Predicate toPredicate(Root<AreaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

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
    public AreaEntity add(AreaEntity entity) {
        areaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public AreaEntity edit(AreaEntity entity) {
        areaRepository.saveAndFlush(entity);
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
        AreaEntity entity = areaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        areaRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public AreaEntity findById(String id) {
        AreaEntity entity = areaRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public AreaEntity checkById(String id) {
        AreaEntity entity = areaRepository.findById(SysConstant.IS_DEL_N, id);
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
    public AreaEntity findByNo(String no) {
        AreaEntity entity = areaRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }


}

