package cn.backend.service.vehicledetail;

import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/08/17
 */
public interface IVehicleDetailService extends IBaseService<VehicleDetailEntity, VehicleDetailQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    VehicleDetailEntity findByNo(String no);

    /**
     * 根据车型编号查找
     *
     * @param no
     * @return
     */
    List<VehicleDetailEntity> findByVehicleNo(String no);

    /**
     * 根据车型详细名称查找
     *
     * @param name
     * @return
     */
    VehicleDetailEntity findByDetailNameAndCorp(String name,String corpNo);

    /**
     * 根据车型详细名称和车型编号查找
     * @param name
     * @param no
     * @param corpNo
     * @return
     */
    VehicleDetailEntity findByVehicleNoAndName(String name,String no, String corpNo);
}

