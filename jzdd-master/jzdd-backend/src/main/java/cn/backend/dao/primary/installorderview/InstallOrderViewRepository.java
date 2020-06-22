package cn.backend.dao.primary.installorderview;

import cn.backend.model.primary.inspection.InspectionQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Repository("installOrderViewRepository")
public interface InstallOrderViewRepository extends JpaRepository<ViewInstallOrderEntity, String>, JpaSpecificationExecutor<ViewInstallOrderEntity> {

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ViewInstallOrderEntity e where e.isDel=:deleteStatus and e.id=:id")
    ViewInstallOrderEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    @Query("select e from ViewInstallOrderEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo and e.corpNo=:corpNo")
    ViewInstallOrderEntity findByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo, @Param("corpNo") String corpNo);


}

