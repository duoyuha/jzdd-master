package cn.backend.service.corp;



import cn.backend.config.message.CustomMessage;

import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.CompareObj;

import cn.zdwl.common.util.IdUtils;
import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.corp.CorpRepository;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.corp.CorpQuery;
import cn.backend.service.BaseService;
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
import java.util.ArrayList;
import java.util.List;


/**
 * @author xsj
 * @date 2019/03/13
 */
@Service(value = "corpService")
public class CorpService extends BaseService implements ICorpService {

    @Resource
    private CorpRepository corpRepository;


    /**
     * 分页查询
     *
     * @param corpQuery
     * @return
     */
    @Override
    public Page<CorpEntity> findList(CorpQuery corpQuery) {
        Sort sort = buildSort(corpQuery.getSort());
        Pageable pageable = new PageRequest(corpQuery.getPageNum(), corpQuery.getPageSize(), sort);
        Page<CorpEntity> entityPage = corpRepository.findAll(createSpecification(corpQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param corpQuery
     * @return
     */
    @Override
    public List<CorpEntity> findAll(CorpQuery corpQuery) {
        Sort sort = buildSort(corpQuery.getSort());
        List<CorpEntity> entityList = corpRepository.findAll(createSpecification(corpQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param corpQuery
     * @return
     */
    private Specification createSpecification(CorpQuery corpQuery) {
        return new Specification<CorpEntity>() {

            @Override
            public Predicate toPredicate(Root<CorpEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //公司名查询
                if (!StringUtils.isEmpty(corpQuery.getCorpName())) {
                    predicate.add(cb.like(root.get("corpName").as(String.class), "%" + corpQuery.getCorpName() + "%"));
                }

                //公司
                if (!StringUtils.isEmpty(corpQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), corpQuery.getCorpNo()));
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
    public CorpEntity add(CorpEntity entity) {
        //公司名重复校验
        CorpEntity checkEntity = corpRepository.findByName(SysConstant.IS_DEL_N, entity.getCorpName());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        //公司编码
        entity.setCorpNo(SysConstant.Corp.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        corpRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public CorpEntity edit(CorpEntity entity) {

        CorpEntity oldEntity = corpRepository.findById(SysConstant.IS_DEL_N, entity.getId());
        if (oldEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }


        //公司名重复校验
        if (!oldEntity.getCorpName().equals(entity.getCorpName())) {
            CorpEntity checkEntity = corpRepository.findByName(SysConstant.IS_DEL_N, entity.getCorpName());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }


        corpRepository.saveAndFlush(entity);
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
        CorpEntity entity = corpRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        corpRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public CorpEntity findById(String id) {
        CorpEntity entity = corpRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public CorpEntity checkById(String id) {
        CorpEntity entity = corpRepository.findById(SysConstant.IS_DEL_N, id);
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
    public CorpEntity findByNo(String no) {
        CorpEntity entity = corpRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }


}

