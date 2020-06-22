package cn.backend.dao.primary.settledetail;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author James
 * @date 2019/07/29
 */
@Repository("settleDetailRepository")
public interface SettleDetailRepository extends IBaseDao<SettleDetailEntity, String> {


    /**
     * 删除
     *
     * @param deleteStatus 逻辑删除标志
     * @param id           结算单明细Id
     * @return 删除的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Query(value = "update SettleDetailEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据结算单明细Id查找
     *
     * @param deleteStatus 逻辑删除标志
     * @param id           结算单明细Id
     * @return 结算单明细
     */
    @Query("select e from SettleDetailEntity e where e.isDel=?1 and e.id=?2")
    SettleDetailEntity findById(String deleteStatus, String id);

    @Query("select e from SettleDetailEntity e where e.isDel=:deleteStatus and e.settleNo=:no")
    List<SettleDetailEntity> findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

}

