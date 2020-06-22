package cn.backend.dao.primary.corp;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.corp.CorpEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Repository("corpRepository")
public interface CorpRepository extends IBaseDao<CorpEntity, String> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from CorpEntity e where e.isDel=:deleteStatus")
    List<CorpEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 根据公司名查找
     *
     * @param deleteStatus
     * @param corpName
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from CorpEntity e where e.isDel =:deleteStatus and e.corpName=:corpName ")
    CorpEntity findByName(@Param("deleteStatus") String deleteStatus, @Param("corpName") String corpName);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param corpId
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update CorpEntity e set e.isDel=:deleteStatus where e.id=:corpId")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("corpId") String corpId);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param corpId
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from CorpEntity e where e.isDel=:deleteStatus and e.id=:corpId")
    CorpEntity findById(@Param("deleteStatus") String deleteStatus, @Param("corpId") String corpId);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param corpNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from CorpEntity e where e.isDel=:deleteStatus and e.corpNo=:corpNo")
    CorpEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("corpNo") String corpNo);

}

