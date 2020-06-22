package cn.backend.service.vehicle;

import cn.backend.model.primary.vehicle.VehicleEntity;
import cn.backend.model.primary.vehicle.VehicleQuery;
import cn.backend.service.IBaseService;

/**
 * @author sunkw
 * @date 2019/07/08
 */
public interface IVehicleService extends IBaseService<VehicleEntity, VehicleQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    VehicleEntity findByNo(String no);

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    VehicleEntity findByNameAndCorp(String name, String corpNo);

}

