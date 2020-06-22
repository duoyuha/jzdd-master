package cn.backend.service.ordergrade;

import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import cn.backend.model.primary.ordergrade.OrderGradeQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
public interface IOrderGradeService extends IBaseService<OrderGradeEntity,OrderGradeQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderGradeEntity findByNo(String no);

    /**
     * 根据订单编号查找
     *
     * @param no
     * @return
     */
    OrderGradeEntity findByOrderNo(String no);

}

