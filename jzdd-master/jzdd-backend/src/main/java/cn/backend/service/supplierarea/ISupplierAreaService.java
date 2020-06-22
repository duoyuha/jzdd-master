package cn.backend.service.supplierarea;

import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
public interface ISupplierAreaService extends IBaseService<SupplierAreaEntity, SupplierAreaQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    Set<SupplierAreaEntity> findBySupplierNo(String no);

    /**
     * 批量保存
     *
     * @param supplierAreaEntityList
     * @return
     */
    List<SupplierAreaEntity> saveBatch(List<SupplierAreaEntity> supplierAreaEntityList);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBySet(Set<String> ids);


}

