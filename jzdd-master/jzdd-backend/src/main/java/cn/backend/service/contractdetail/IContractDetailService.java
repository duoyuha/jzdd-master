package cn.backend.service.contractdetail;

import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.backend.model.primary.contractdetail.ContractDetailQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
public interface IContractDetailService extends IBaseService<ContractDetailEntity, ContractDetailQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    List<ContractDetailEntity> findByNo(String no);

}

