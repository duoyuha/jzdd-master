package cn.backend.dao.primary.viewcomplainorder;

import cn.backend.model.primary.viewcomplainorder.ViewComplainEntity;
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
@Repository("viewComplainRepository")
public interface ViewComplainRepository extends JpaRepository<ViewComplainEntity, String>, JpaSpecificationExecutor<ViewComplainEntity> {

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ViewComplainEntity e where e.isDel=:deleteStatus and e.id=:id")
    ViewComplainEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

}

