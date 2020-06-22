package cn.backend.dao.primary.orderfee;

import cn.backend.model.primary.orderfee.OrderFeeEntity;
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
 * @date 2019/08/06
 */
@Repository("orderFeeRepository")
public interface OrderFeeRepository extends JpaRepository<OrderFeeEntity, String>, JpaSpecificationExecutor<OrderFeeEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderFeeEntity e where e.isDel=:deleteStatus")
    List<OrderFeeEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderFeeEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 删除
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderFeeEntity e set e.isDel=:deleteStatus where e.id in (:ids)")
    int deleteBatch(@Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderFeeEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderFeeEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据订单no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderFeeEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    OrderFeeEntity findByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

}

