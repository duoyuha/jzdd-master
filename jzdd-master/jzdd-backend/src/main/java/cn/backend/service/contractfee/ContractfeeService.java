package cn.backend.service.contractfee;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.contractfee.ContractfeeRepository;
import cn.backend.model.primary.contractfee.ContractfeeEntity;
import cn.backend.model.primary.contractfee.ContractfeeQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
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
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/07/09
 */
@Service(value = "contractfeeService")
public class ContractfeeService extends BaseService implements IContractfeeService {

    @Resource
    private ContractfeeRepository contractfeeRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param contractfeeQuery
     * @return
     */
    @Override
    public Page<ContractfeeEntity> findList(ContractfeeQuery contractfeeQuery) {
        Sort sort = buildSort(contractfeeQuery.getSort());
        Pageable pageable = new PageRequest(contractfeeQuery.getPageNum(), contractfeeQuery.getPageSize(), sort);
        Page<ContractfeeEntity> entityPage = contractfeeRepository.findAll(createSpecification(contractfeeQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param contractfeeQuery
     * @return
     */
    @Override
    public List<ContractfeeEntity> findAll(ContractfeeQuery contractfeeQuery) {
        Sort sort = buildSort(contractfeeQuery.getSort());
        List<ContractfeeEntity> entityList = contractfeeRepository.findAll(createSpecification(contractfeeQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param contractfeeQuery
     * @return
     */
    private Specification createSpecification(ContractfeeQuery contractfeeQuery) {
        return new Specification<ContractfeeEntity>() {

            @Override
            public Predicate toPredicate(Root<ContractfeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public ContractfeeEntity add(ContractfeeEntity entity) {
        configService.convert(entity);
        contractfeeRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public List<ContractfeeEntity> addBatch(List<ContractfeeEntity> contractfeeEntities) {
        for(ContractfeeEntity item : contractfeeEntities){
            configService.convert(item);
        }
        contractfeeRepository.save(contractfeeEntities);
        return contractfeeEntities;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public ContractfeeEntity edit(ContractfeeEntity entity) {
        configService.convert(entity);
        contractfeeRepository.saveAndFlush(entity);
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
        ContractfeeEntity entity = contractfeeRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        contractfeeRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    @Override
    public boolean deleteBatch(Set<String> ids) {
        if (ids != null && ids.size() > 0) {
            contractfeeRepository.deleteBatch(SysConstant.IS_DEL_Y, ids);
        }
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ContractfeeEntity findById(String id) {
        ContractfeeEntity entity = contractfeeRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 判断新增还是编辑
     *
     * @param entity
     * @return
     */
    @Override
    public ContractfeeEntity editOrSave(ContractfeeEntity entity) {
        if (entity.getId() == null || entity.getId().equals("")) {
            this.add(entity);
        } else {
            this.edit(entity);
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
    public ContractfeeEntity checkById(String id) {
        ContractfeeEntity entity = contractfeeRepository.findById(SysConstant.IS_DEL_N, id);
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
    public List<ContractfeeEntity> findByNo(String no) {
        List<ContractfeeEntity> list = contractfeeRepository.findByNo(SysConstant.IS_DEL_N, no);
        return list;
    }


}

