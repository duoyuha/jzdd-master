package cn.backend.service.workfollowdetail;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.workfollowdetail.WorkFollowDetailRepository;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.BeanCopyUtil;
import org.apache.commons.lang.StringUtils;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/07/16
 */
@Service(value = "workFollowDetailService")
public class WorkFollowDetailService extends BaseService implements IWorkFollowDetailService {

    @Resource
    private WorkFollowDetailRepository workFollowDetailRepository;

    /**
     * 分页查询
     *
     * @param workFollowDetailQuery
     * @return
     */
    @Override
    public Page<WorkFollowDetailEntity> findList(WorkFollowDetailQuery workFollowDetailQuery) {
       Sort sort = buildSort(workFollowDetailQuery.getSort());

        Pageable pageable = new PageRequest(workFollowDetailQuery.getPageNum(), workFollowDetailQuery.getPageSize(), sort);
        Page<WorkFollowDetailEntity> entityPage = workFollowDetailRepository.findAll(createSpecification(workFollowDetailQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param workFollowDetailQuery
     * @return
     */
    @Override
    public List<WorkFollowDetailEntity> findAll(WorkFollowDetailQuery workFollowDetailQuery) {
        Sort sort = buildSort(workFollowDetailQuery.getSort());
        List<WorkFollowDetailEntity> entityList = workFollowDetailRepository.findAll(createSpecification(workFollowDetailQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param workFollowDetailQuery
     * @return
     */
    private Specification createSpecification(WorkFollowDetailQuery workFollowDetailQuery) {
        return new Specification<WorkFollowDetailEntity>() {

            @Override
            public Predicate toPredicate(Root<WorkFollowDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //编号
                if (!StringUtils.isEmpty(workFollowDetailQuery.getFollowNo())) {
                    predicate.add(cb.equal(root.get("followNo").as(String.class), workFollowDetailQuery.getFollowNo()));
                }

                //步骤
                if (workFollowDetailQuery.getFollowSeq() != null) {
                    predicate.add(cb.equal(root.get("followSeq").as(Integer.class), workFollowDetailQuery.getFollowSeq()));
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
    public WorkFollowDetailEntity add(WorkFollowDetailEntity entity) {
        workFollowDetailRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public WorkFollowDetailEntity edit(WorkFollowDetailEntity entity) {
        WorkFollowDetailEntity oldEntity = checkById(entity.getId());
        BeanCopyUtil.beanCopy(entity, oldEntity);
        workFollowDetailRepository.saveAndFlush(oldEntity);
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
        WorkFollowDetailEntity entity = workFollowDetailRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        workFollowDetailRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public WorkFollowDetailEntity findById(String id) {
        WorkFollowDetailEntity entity = workFollowDetailRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public WorkFollowDetailEntity checkById(String id) {
        WorkFollowDetailEntity entity = workFollowDetailRepository.findById(SysConstant.IS_DEL_N, id);
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
    public WorkFollowDetailEntity findByNo(String no) {
        WorkFollowDetailEntity entity = workFollowDetailRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 获取对应类型的下一步骤
     *
     * @param followNo
     * @return
     */
    @Override
    public Set<String> getStepSet(String followNo){
        Set<Integer> setInteger = workFollowDetailRepository.findStepsByFollowNo(SysConstant.IS_DEL_N, followNo);
        Set<String> set = new HashSet<>();
        for(Integer item : setInteger){
            set.add(item.toString());
        }
        return set;
    }

    /**
     * 根据编号查找
     *
     * @param followNo
     * @param followSeq
     * @return
     */
    @Override
    public WorkFollowDetailEntity findByFollowNoAndFollowSeq(String followNo, Integer followSeq) {
        WorkFollowDetailEntity entity = workFollowDetailRepository.findByFollowNoAndFollowSeq(SysConstant.IS_DEL_N, followNo, followSeq);
        return entity;
    }

    /**
     * 获取对应类型的最小步骤
     *
     * @param followNo
     * @return
     */
    @Override
    public WorkFollowDetailEntity getMinStep(String followNo) {
        WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailRepository
                .findMinFollowSeq(SysConstant.IS_DEL_N, followNo);
        if (workFollowDetailEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return workFollowDetailEntity;
    }

    /**
     * 获取对应类型的最大步骤
     *
     * @param followNo
     * @return
     */
    @Override
    public WorkFollowDetailEntity getMaxStep(String followNo){
        WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailRepository
                .findMaxFollowSeq(SysConstant.IS_DEL_N, followNo);
        if (workFollowDetailEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return workFollowDetailEntity;
    }

    /**
     * 获取对应类型的待确认步骤
     *
     * @param followNo
     * @return
     */
    @Override
    public WorkFollowDetailEntity getConfirmStep(String followNo){
        WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailRepository
                .findConfirmSeq(SysConstant.IS_DEL_N, followNo);
        return workFollowDetailEntity;
    }

    /**
     * 获取对应类型的下一步骤
     *
     * @param followNo
     * @return
     */
    @Override
    public WorkFollowDetailEntity getNextStep(String followNo, Integer followSeq) {
        WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailRepository
                .findNextFollowSeq(SysConstant.IS_DEL_N, followNo, followSeq);

        return workFollowDetailEntity;
    }


}

