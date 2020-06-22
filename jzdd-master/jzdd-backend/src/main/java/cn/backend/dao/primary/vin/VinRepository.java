package cn.backend.dao.primary.vin;

import cn.backend.model.primary.vin.VinEntity;
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
@Repository("vinRepository")
public interface VinRepository extends JpaRepository<VinEntity, String>, JpaSpecificationExecutor<VinEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from VinEntity e where e.isDel=:deleteStatus")
    List<VinEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update VinEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from VinEntity e where e.isDel=:deleteStatus and e.id=:id")
    VinEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param no
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from VinEntity e where e.isDel=:deleteStatus and e.vinNo=:no")
    VinEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据code查找
     *
     * @param deleteStatus
     * @param vinCode
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from VinEntity e where e.isDel=:deleteStatus and e.vinCode=:vinCode")
    VinEntity findByCode(@Param("deleteStatus") String deleteStatus, @Param("vinCode") String vinCode);

    /**
     * 查询不送桩原因列表
     * @return
     */
    @Query("select e.remark from VinEntity e GROUP BY e.remark")
    List<String> getRemark();
}

