package cn.backend.dao.primary.ordercar;

import cn.backend.model.primary.ordercar.OrderCarEntity;
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
@Repository("orderCarRepository")
public interface OrderCarRepository extends JpaRepository<OrderCarEntity, String>, JpaSpecificationExecutor<OrderCarEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus")
    List<OrderCarEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderCarEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderCarEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    OrderCarEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

    // /**
    //  * 根据no查找
    //  * @param deleteStatus
    //  * @param vinNo
    //  * @return
    //  */
    // @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus and e.vinNo=:vinNo")
    // OrderCarEntity findByVinNo(@Param("deleteStatus") String deleteStatus, @Param("vinNo") String vinNo);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param vinNo
     * @return
     */
    @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus and e.vinNo=:vinNo and e.corpNo=:corpNo")
    OrderCarEntity findByVinNoAndCorpNo(@Param("deleteStatus") String deleteStatus, @Param("vinNo") String vinNo, @Param("corpNo") String corpNo);

    @Query("select e from OrderCarEntity e where e.isDel=:deleteStatus and e.vinNo=:vinNo order by e.createdWhen desc ")
    List<OrderCarEntity> findListByVinNo(@Param("deleteStatus") String deleteStatus, @Param("vinNo") String vinNo);

}

