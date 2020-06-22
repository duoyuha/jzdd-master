package cn.backend.service.workfollow;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.workfollow.WorkFollowRepository;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollow.WorkFollowQuery;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.exception.AppException;
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
 * @date 2019/07/16
 */
@Service(value = "workFollowService")
public class WorkFollowService extends BaseService implements IWorkFollowService {

    @Resource
    private WorkFollowRepository workFollowRepository;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    /**
     * 分页查询
     *
     * @param workFollowQuery
     * @return
     */
    @Override
    public Page<WorkFollowEntity> findList(WorkFollowQuery workFollowQuery) {
        Sort sort = buildSort(workFollowQuery.getSort());
        Pageable pageable = new PageRequest(workFollowQuery.getPageNum(), workFollowQuery.getPageSize(), sort);
        Page<WorkFollowEntity> entityPage = workFollowRepository.findAll(createSpecification(workFollowQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param workFollowQuery
     * @return
     */
    @Override
    public List<WorkFollowEntity> findAll(WorkFollowQuery workFollowQuery) {
        Sort sort = buildSort(workFollowQuery.getSort());
        List<WorkFollowEntity> entityList = workFollowRepository.findAll(createSpecification(workFollowQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param workFollowQuery
     * @return
     */
    private Specification createSpecification(WorkFollowQuery workFollowQuery) {
        return new Specification<WorkFollowEntity>() {

            @Override
            public Predicate toPredicate(Root<WorkFollowEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public WorkFollowEntity add(WorkFollowEntity entity) {
        workFollowRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public WorkFollowEntity edit(WorkFollowEntity entity) {
        workFollowRepository.saveAndFlush(entity);
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
        WorkFollowEntity entity = workFollowRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        workFollowRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public WorkFollowEntity findById(String id) {
        WorkFollowEntity entity = workFollowRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public WorkFollowEntity checkById(String id) {
        WorkFollowEntity entity = workFollowRepository.findById(SysConstant.IS_DEL_N, id);
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
    public WorkFollowEntity findByNo(String no) {
        WorkFollowEntity entity = workFollowRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param code
     * @return
     */
    @Override
    public WorkFollowEntity findByCode(String code) {
        WorkFollowEntity workFollowEntity = workFollowRepository.findByCode(SysConstant.IS_DEL_N, code);
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(workFollowEntity.getFollowNo());
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);
        workFollowEntity.setWorkFollowDetailEntityList(workFollowDetailEntityList);
        return workFollowEntity;
    }


}

