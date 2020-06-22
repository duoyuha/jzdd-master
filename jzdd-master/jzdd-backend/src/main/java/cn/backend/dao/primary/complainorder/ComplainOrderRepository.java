package cn.backend.dao.primary.complainorder;

import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
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
 * @date 2019/07/17
 */
@Repository("complainOrderRepository")
public interface ComplainOrderRepository extends JpaRepository<ComplainOrderEntity, String>, JpaSpecificationExecutor<ComplainOrderEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from ComplainOrderEntity e where e.isDel=:deleteStatus")
    List<ComplainOrderEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ComplainOrderEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ComplainOrderEntity e where e.isDel=:deleteStatus and e.id=:id")
    ComplainOrderEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param complainNo
     * @return
     */
    @Query("select e from ComplainOrderEntity e where e.isDel=:deleteStatus and e.complainNo=:complainNo")
    ComplainOrderEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("complainNo") String complainNo);


    /**
     * 根据scrmNo查找
     * @param deleteStatus
     * @param scrmNo
     * @return
     */
    @Query("select e from ComplainOrderEntity e where e.isDel=:deleteStatus and e.scrmNo=:scrmNo")
    ComplainOrderEntity findByScrmNo(@Param("deleteStatus") String deleteStatus, @Param("scrmNo") String scrmNo);

}

