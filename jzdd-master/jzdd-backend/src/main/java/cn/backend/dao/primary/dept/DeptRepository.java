package cn.backend.dao.primary.dept;

import cn.backend.model.primary.dept.DeptEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author xsj
 * @date 2019/03/14
 */
@Repository("deptRepository")
public interface DeptRepository extends JpaRepository<DeptEntity, String>, JpaSpecificationExecutor<DeptEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from DeptEntity e where e.isDel=:deleteStatus")
    List<DeptEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update DeptEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from DeptEntity e where e.isDel=:deleteStatus and e.id=:id")
    DeptEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param deptNo
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from DeptEntity e where e.isDel=:deleteStatus and e.deptNo=:deptNo")
    DeptEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("deptNo") String deptNo);

    /**
     * 根据name查询
     * @param deleteStatus
     * @param deptName
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from DeptEntity e where e.isDel=:deleteStatus and e.deptName=:deptName")
    DeptEntity findByName(@Param("deleteStatus") String deleteStatus, @Param("deptName") String deptName);

}

