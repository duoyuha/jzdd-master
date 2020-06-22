package cn.backend.service.contractarea;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.contractarea.ContractareaRepository;
import cn.backend.model.primary.contractarea.ContractareaEntity;
import cn.backend.model.primary.contractarea.ContractareaQuery;
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
@Service(value = "contractareaService")
public class ContractareaService extends BaseService implements IContractareaService {

    @Resource
    private ContractareaRepository contractareaRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param contractareaQuery
     * @return
     */
    @Override
    public Page<ContractareaEntity> findList(ContractareaQuery contractareaQuery) {
        Sort sort = buildSort(contractareaQuery.getSort());
        Pageable pageable = new PageRequest(contractareaQuery.getPageNum(), contractareaQuery.getPageSize(), sort);
        Page<ContractareaEntity> entityPage = contractareaRepository.findAll(createSpecification(contractareaQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param contractareaQuery
     * @return
     */
    @Override
    public List<ContractareaEntity> findAll(ContractareaQuery contractareaQuery) {
        Sort sort = buildSort(contractareaQuery.getSort());
        List<ContractareaEntity> entityList = contractareaRepository.findAll(createSpecification(contractareaQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param contractareaQuery
     * @return
     */
    private Specification createSpecification(ContractareaQuery contractareaQuery) {
        return new Specification<ContractareaEntity>() {

            @Override
            public Predicate toPredicate(Root<ContractareaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public ContractareaEntity add(ContractareaEntity entity) {
        contractareaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public ContractareaEntity edit(ContractareaEntity entity) {
        contractareaRepository.saveAndFlush(entity);
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
        ContractareaEntity entity = contractareaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        contractareaRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }


    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ContractareaEntity findById(String id) {
        ContractareaEntity entity = contractareaRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public ContractareaEntity checkById(String id) {
        ContractareaEntity entity = contractareaRepository.findById(SysConstant.IS_DEL_N, id);
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
//    @Override
//    public ContractareaEntity findByNo(String no) {
//        ContractareaEntity entity=contractareaRepository.findByNo(SysConstant.IS_DEL_N,no);
//        return entity;
//    }

    /**
     * 批量保存
     *
     * @param contractareaEntityList
     * @return
     */
    @Override
    public List<ContractareaEntity> saveBatch(List<ContractareaEntity> contractareaEntityList) {
        // for(ContractareaEntity item : contractareaEntityList){
        //     configService.convert(item);
        // }
        contractareaRepository.save(contractareaEntityList);
        return contractareaEntityList;
    }

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    @Override
    public List<ContractareaEntity> findByNo(String no) {
        List<ContractareaEntity> entities = contractareaRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entities;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(Set<String> ids) {
        if (ids != null && ids.size() > 0) {
            contractareaRepository.deleteBySet(SysConstant.IS_DEL_Y, ids);
        }
        return true;
    }


}

