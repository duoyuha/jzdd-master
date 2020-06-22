package cn.backend.dao.primary.contractarea;

import cn.backend.model.primary.contractarea.ContractareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Repository("contractareaRepository")
public interface ContractareaRepository extends JpaRepository<ContractareaEntity, String>, JpaSpecificationExecutor<ContractareaEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractareaEntity e where e.isDel=:deleteStatus")
    List<ContractareaEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractareaEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete( @Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractareaEntity e where e.isDel=:deleteStatus and e.id=:id")
    ContractareaEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractareaEntity e where e.isDel=:deleteStatus and e.detailNo=:no")
    List<ContractareaEntity> findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 批量删除
     *
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractareaEntity e set e.isDel =:deleteStatus where e.id in (:ids)")
    int deleteBySet(@Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids);

}

