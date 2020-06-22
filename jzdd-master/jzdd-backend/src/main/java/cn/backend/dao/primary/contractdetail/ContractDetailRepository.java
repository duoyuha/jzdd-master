package cn.backend.dao.primary.contractdetail;

import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Repository("contractDetailRepository")
public interface ContractDetailRepository extends JpaRepository<ContractDetailEntity, String>, JpaSpecificationExecutor<ContractDetailEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractDetailEntity e where e.isDel=:deleteStatus")
    List<ContractDetailEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractDetailEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete( @Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractDetailEntity e where e.isDel=:deleteStatus and e.id=:id")
    ContractDetailEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractDetailEntity e where e.isDel=:deleteStatus and e.contractNo=:no")
    List<ContractDetailEntity> findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

}

