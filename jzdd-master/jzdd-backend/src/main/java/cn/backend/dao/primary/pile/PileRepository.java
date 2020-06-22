package cn.backend.dao.primary.pile;

import cn.backend.model.primary.pile.PileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Repository("pileRepository")
public interface PileRepository extends JpaRepository<PileEntity, String>, JpaSpecificationExecutor<PileEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @Query("select e from PileEntity e where e.isDel=:deleteStatus")
    List<PileEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update PileEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from PileEntity e where e.isDel=:deleteStatus and e.id=:id")
    PileEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from PileEntity e where e.isDel=:deleteStatus and e.pileNo=:no")
    PileEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据名称查找
     *
     * @param deleteStatus
     * @param pileName
     * @param corpNo
     * @return
     */
    @Query("select e from PileEntity e where e.isDel=:deleteStatus and e.pileName=:pileName and e.corpNo=:corpNo")
    PileEntity findByNameAndCorp(@Param("deleteStatus") String deleteStatus, @Param("pileName") String pileName, @Param("corpNo") String corpNo);

}

