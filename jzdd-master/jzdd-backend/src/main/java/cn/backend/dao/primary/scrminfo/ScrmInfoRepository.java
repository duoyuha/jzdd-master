package cn.backend.dao.primary.scrminfo;

import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
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
@Repository("scrmInfoRepository")
public interface ScrmInfoRepository extends JpaRepository<ScrmInfoEntity, String>, JpaSpecificationExecutor<ScrmInfoEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from ScrmInfoEntity e where e.isDel=:deleteStatus")
    List<ScrmInfoEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ScrmInfoEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ScrmInfoEntity e where e.isDel=:deleteStatus and e.id=:id")
    ScrmInfoEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param scrmNo
     * @return
     */
    @Query("select e from ScrmInfoEntity e where e.isDel=:deleteStatus and e.scrmNo=:scrmNo")
    ScrmInfoEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("scrmNo") String scrmNo);

    /**
     * 根据caseId查找
     * @param deleteStatus
     * @param caseId
     * @return
     */
    @Query("select e from ScrmInfoEntity e where e.isDel=:deleteStatus and e.caseId=:caseId")
    ScrmInfoEntity findByCaseId(@Param("deleteStatus") String deleteStatus, @Param("caseId") String caseId);


}

