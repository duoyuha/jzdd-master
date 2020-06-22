package cn.backend.dao.primary.viewdashbordrank;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.viewdashbordrank.ViewDashbordRankEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Repository("dashbordRankRepository")
public interface ViewDashbordRankRepository extends IBaseDao<ViewDashbordRankEntity, String> {



    /**
     * 根据id查找
     * @param supplierNo
     * @param carVehicle
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ViewDashbordRankEntity e where e.supplierNo=:supplierNo and e.carVehicle=:carVehicle")
    List<ViewDashbordRankEntity> findBySupplierNoAndCarVehicle(@Param("supplierNo") String supplierNo, @Param("carVehicle") String carVehicle);

}

