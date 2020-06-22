package cn.backend.dao.primary.supplier;


import cn.backend.model.primary.supplier.SupplierEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Repository("supplierRepository")
public interface SupplierRepository extends JpaRepository<SupplierEntity, String>, JpaSpecificationExecutor<SupplierEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from SupplierEntity e where e.isDel=:deleteStatus")
    List<SupplierEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update SupplierEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from SupplierEntity e where e.isDel=:deleteStatus and e.id=:id")
    SupplierEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param no
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from SupplierEntity e where e.isDel=:deleteStatus and e.supplierNo=:no")
    SupplierEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据名称查找
     *
     * @param deleteStatus
     * @param supplierName
     * @param corpNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from SupplierEntity e where e.isDel=:deleteStatus and e.supplierName=:supplierName and e.corpNo=:corpNo")
    SupplierEntity findByNameAndCorp(@Param("deleteStatus") String deleteStatus, @Param("supplierName") String supplierName, @Param("corpNo") String corpNo);

    /**
     * 根据地区查询
     *
     * @param deleteStatus
     * @param supplierProvince
     * @param supplierCity
     * @return
     */
    @Query(value = "SELECT T1.* FROM m_supplier T1 LEFT JOIN m_supplier_area T2 ON T1.SUPPLIER_NO = T2.SUPPLIER_NO AND T2.SUPPLIER_PROVINCE = ?2 AND T2.SUPPLIER_CITY = ?3  WHERE T1.IS_DEL = ?1  AND T2.IS_DEL = ?1 AND T1.CORP_NO=?4 ", nativeQuery = true)
    List<SupplierEntity> findListByArea(@Param("deleteStatus") String deleteStatus, @Param("supplierProvince") String supplierProvince, @Param("supplierCity") String supplierCity, @Param("corpNo") String corpNo);

    /**
     * 删除
     *
     * @param autoOrderProp
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update SupplierEntity e set e.autoOrderProp=:autoOrderProp where e.id=:id")
    int setAutoOrderProp(@Param("autoOrderProp") Integer autoOrderProp, @Param("id") String id);
}

