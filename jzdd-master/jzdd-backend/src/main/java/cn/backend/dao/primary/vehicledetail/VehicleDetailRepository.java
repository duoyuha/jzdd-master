package cn.backend.dao.primary.vehicledetail;

import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/08/17
 */
@Repository("vehicleDetailRepository")
public interface VehicleDetailRepository extends JpaRepository<VehicleDetailEntity, String>, JpaSpecificationExecutor<VehicleDetailEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus")
    List<VehicleDetailEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update VehicleDetailEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus and e.id=:id")
    VehicleDetailEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus and e.detailNo=:no")
    VehicleDetailEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param vehicleNo
     * @return
     */
    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus and e.vehicleNo=:vehicleNo")
    List<VehicleDetailEntity> findByVehicleNo(@Param("deleteStatus") String deleteStatus, @Param("vehicleNo") String vehicleNo);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param detailName
     * @return
     */
    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus and e.detailName=:detailName and e.corpNo=:corpNo")
    VehicleDetailEntity findByDetailNameAndCorp(@Param("deleteStatus") String deleteStatus, @Param("detailName") String detailName, @Param("corpNo") String corpNo);

    @Query("select e from VehicleDetailEntity e where e.isDel=:deleteStatus and e.detailName=:detailName and e.vehicleNo=:vehicleNo and e.corpNo=:corpNo")
    VehicleDetailEntity findByVehicleNoAndName(@Param("deleteStatus") String deleteStatus, @Param("detailName") String detailName,@Param("vehicleNo") String vehicleNo, @Param("corpNo") String corpNo);
}

