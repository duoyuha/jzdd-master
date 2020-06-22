package cn.backend.dao.primary.settle;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.settle.SettleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author James
 * @date 2019/07/29
 */
@Repository("settleRepository")
public interface SettleRepository extends IBaseDao<SettleEntity, String> {


    /**
     * 删除
     *
     * @param deleteStatus 逻辑删除标志
     * @param id           结算单Id
     * @return 删除的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Query(value = "update SettleEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus 逻辑删除标志
     * @param id           结算单Id
     * @return 结算单
     */
    @Query("select e from SettleEntity e where e.isDel=?1 and e.id=?2")
    SettleEntity findById(String deleteStatus, String id);

    /**
     * 根据结算单编号查找
     *
     * @param deleteStatus 逻辑删除标志
     * @param no           结算单编号
     * @return 结算单
     */
    @Query("select e from SettleEntity e where e.isDel=?1 and e.settleNo=?2")
    SettleEntity findByNo(String deleteStatus, String no);

}

