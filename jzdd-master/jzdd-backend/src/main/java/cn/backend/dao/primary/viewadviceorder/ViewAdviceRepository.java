package cn.backend.dao.primary.viewadviceorder;

import cn.backend.model.primary.viewadviceorder.ViewAdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Repository("viewAdviceRepository")
public interface ViewAdviceRepository extends JpaRepository<ViewAdviceEntity, String>, JpaSpecificationExecutor<ViewAdviceEntity> {

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ViewAdviceEntity e where e.isDel=:deleteStatus and e.id=:id")
    ViewAdviceEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

}

