package cn.backend.dao.primary.adviceorder;

import cn.backend.model.primary.adviceorder.AdviceEntity;
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
@Repository("adviceRepository")
public interface AdviceRepository extends JpaRepository<AdviceEntity, String>, JpaSpecificationExecutor<AdviceEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from AdviceEntity e where e.isDel=:deleteStatus")
    List<AdviceEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update AdviceEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from AdviceEntity e where e.isDel=:deleteStatus and e.id=:id")
    AdviceEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param adviceNo
     * @return
     */
    @Query("select e from AdviceEntity e where e.isDel=:deleteStatus and e.adviceNo=:adviceNo")
    AdviceEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("adviceNo") String adviceNo);


    /**
     * 根据scrmNo查找
     * @param deleteStatus
     * @param scrmNo
     * @return
     */
    @Query("select e from AdviceEntity e where e.isDel=:deleteStatus and e.scrmNo=:scrmNo")
    AdviceEntity findByScrmNo(@Param("deleteStatus") String deleteStatus, @Param("scrmNo") String scrmNo);

}

