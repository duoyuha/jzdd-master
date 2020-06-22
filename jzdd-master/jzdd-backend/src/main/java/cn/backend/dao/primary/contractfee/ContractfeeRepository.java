package cn.backend.dao.primary.contractfee;

import cn.backend.model.primary.contractfee.ContractfeeEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Repository("contractfeeRepository")
public interface ContractfeeRepository extends JpaRepository<ContractfeeEntity, String>, JpaSpecificationExecutor<ContractfeeEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractfeeEntity e where e.isDel=:deleteStatus")
    List<ContractfeeEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractfeeEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete( @Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 删除
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractfeeEntity e set e.isDel=:deleteStatus where e.id in (:ids)")
    int deleteBatch( @Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractfeeEntity e where e.isDel=:deleteStatus and e.id=:id")
    ContractfeeEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractfeeEntity e where e.isDel=:deleteStatus and e.detailNo=:no")
    List<ContractfeeEntity> findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

}

