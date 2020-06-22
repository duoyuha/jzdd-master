package cn.backend.service.contract;

import cn.backend.model.primary.contract.ContractEntity;
import cn.backend.model.primary.contract.ContractQuery;
import cn.backend.service.IBaseService;
import cn.zdwl.common.file.UploadFile;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
public interface IContractService extends IBaseService<ContractEntity, ContractQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    ContractEntity findByNo(String no);

    /**
     * 根据车型和充电桩查找
     *
     * @param vehicleNo
     * @param pileNo
     * @return
     */
    ContractEntity findByVehicleAndPile(String vehicleNo, String pileNo);


    //ContractEntity addDetail(ContractEntity entity, UploadFile condetail);

}

