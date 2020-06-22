package cn.backend.service.contractarea;

import cn.backend.model.primary.contractarea.ContractareaEntity;
import cn.backend.model.primary.contractarea.ContractareaQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
public interface IContractareaService extends IBaseService<ContractareaEntity, ContractareaQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    List<ContractareaEntity> findByNo(String no);

    /**
     * 批量保存
     * @param contractareaEntityList
     * @return
     */
    List<ContractareaEntity> saveBatch(List<ContractareaEntity> contractareaEntityList);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(Set<String> ids);

}

