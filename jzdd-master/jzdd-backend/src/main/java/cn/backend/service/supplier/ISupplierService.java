package cn.backend.service.supplier;

import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.supplier.SupplierQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
public interface ISupplierService extends IBaseService<SupplierEntity, SupplierQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    SupplierEntity findByNo(String no);

    /**
     * 根据名称查找
     *
     * @param name
     * @param corpNo
     * @return
     */
    SupplierEntity findByNameAndCorp(String name, String corpNo);

    /**
     * 根据地区查询服务商详细
     *
     * @param province
     * @param city
     * @return
     */
    List<SupplierEntity> findListByArea(String province, String city,String corpNo);

    /**
     * 根据地区查询服务商详细
     *
     * @param orderType
     * @param orderNo
     * @return
     */
    List<SupplierEntity> findListByOrder(String orderType, String orderNo);

    /**
     * 自动派单
     *
     * @param supplierQuery
     * @return
     */
    boolean automaticOrder(SupplierQuery supplierQuery);

    /**
     * 设置自动派单比例
     *
     * @param supplierEntityList
     * @return
     */
    boolean setAutoOrderProp(List<SupplierEntity> supplierEntityList);

    List<SupplierEntity> countMonth(String corpNo);

}

