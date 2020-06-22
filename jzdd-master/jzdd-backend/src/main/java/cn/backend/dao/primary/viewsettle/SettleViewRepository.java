package cn.backend.dao.primary.viewsettle;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.viewsettle.SettleViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("settleViewRepository")
public interface SettleViewRepository extends IBaseDao<SettleViewEntity,String> {

    @Query("select e from SettleViewEntity e where e.isDel=:deleteStatus and e.id=:id")
    SettleViewEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    @Query("select e from SettleViewEntity e where e.isDel=:deleteStatus and e.settleNo=:settleNo")
    SettleViewEntity findBySettleNo(@Param("deleteStatus") String deleteStatus, @Param("settleNo") String settleNo);
}
