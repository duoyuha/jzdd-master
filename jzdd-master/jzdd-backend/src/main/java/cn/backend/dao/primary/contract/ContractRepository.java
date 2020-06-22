package cn.backend.dao.primary.contract;

import cn.backend.model.primary.contract.ContractEntity;
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
@Repository("contractRepository")
public interface ContractRepository extends JpaRepository<ContractEntity, String>, JpaSpecificationExecutor<ContractEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractEntity e where e.isDel=:deleteStatus")
    List<ContractEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ContractEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete( @Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractEntity e where e.isDel=:deleteStatus and e.id=:id")
    ContractEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param contractNo
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractEntity e where e.isDel=:deleteStatus and e.contractNo=:contractNo")
    ContractEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("contractNo") String contractNo);

    /**
     * 根据合同编号查询
     * @param deleteStatus
     * @param corpNo
     * @param contractCode
     * @return
     */
    // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from ContractEntity e where e.isDel=:deleteStatus and e.corpNo=:corpNo and e.contractCode=:contractCode")
    ContractEntity findByCode(@Param("deleteStatus") String deleteStatus, @Param("corpNo") String corpNo, @Param("contractCode") String contractCode);



}

