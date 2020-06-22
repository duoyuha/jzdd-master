package cn.backend.dao.primary.orderseq;

import cn.backend.model.primary.orderseq.OrderSeqEntity;
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
 * @date 2019/07/23
 */
@Repository("orderSeqRepository")
public interface OrderSeqRepository extends JpaRepository<OrderSeqEntity, String>, JpaSpecificationExecutor<OrderSeqEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderSeqEntity e where e.isDel=:deleteStatus")
    List<OrderSeqEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderSeqEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderSeqEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderSeqEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

}

