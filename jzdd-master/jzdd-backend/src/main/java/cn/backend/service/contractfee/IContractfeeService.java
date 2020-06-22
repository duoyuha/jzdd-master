package cn.backend.service.contractfee;

import cn.backend.model.primary.contractfee.ContractfeeEntity;
import cn.backend.model.primary.contractfee.ContractfeeQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
public interface IContractfeeService extends IBaseService<ContractfeeEntity, ContractfeeQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    List<ContractfeeEntity> findByNo(String no);

    /**
     * 新增还是编辑
     * @param entity
     * @return
     */
    ContractfeeEntity editOrSave(ContractfeeEntity entity);

    boolean deleteBatch(Set<String> ids);

    List<ContractfeeEntity> addBatch(List<ContractfeeEntity> contractfeeEntities);

}

