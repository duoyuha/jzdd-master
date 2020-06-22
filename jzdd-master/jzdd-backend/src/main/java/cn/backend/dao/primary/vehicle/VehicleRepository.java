package cn.backend.dao.primary.vehicle;

import cn.backend.model.primary.vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<VehicleEntity, String>, JpaSpecificationExecutor<VehicleEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from VehicleEntity e where e.isDel=:deleteStatus")
    List<VehicleEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update VehicleEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from VehicleEntity e where e.isDel=:deleteStatus and e.id=:id")
    VehicleEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param no
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from VehicleEntity e where e.isDel=:deleteStatus and e.vehicleNo=:no")
    VehicleEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据名称查找
     *
     * @param deleteStatus
     * @param vehicleName
     * @param corpNo
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from VehicleEntity e where e.isDel=:deleteStatus and e.vehicleName=:vehicleName and e.corpNo=:corpNo")
    VehicleEntity findByNameAndCorp(@Param("deleteStatus") String deleteStatus, @Param("vehicleName") String vehicleName, @Param("corpNo") String corpNo);

}

