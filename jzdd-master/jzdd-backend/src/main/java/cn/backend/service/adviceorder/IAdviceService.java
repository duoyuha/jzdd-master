package cn.backend.service.adviceorder;

import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.adviceorder.AdviceQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
public interface IAdviceService extends IBaseService<AdviceEntity, AdviceQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    AdviceEntity findByNo(String no);


    AdviceEntity saveOrUpdate(ScrmInfoEntity scrmInfoEntity);

    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    AdviceEntity submitOrder(AdviceEntity entity);

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    AdviceEntity saveOrderAndStep(AdviceEntity entity);

    /**
     * 订单工作流
     *
     * @param entity
     * @return
     */
    AdviceEntity orderWorkFollow(AdviceEntity entity);

    /**
     * 新建报修订单
     *
     * @param entity
     * @return
     */
    AdviceEntity addAdvice(AdviceEntity entity);

    /**
     * 报修订单工作流
     *
     * @param entity
     * @return
     */
    AdviceEntity editAdvice(AdviceEntity entity);

    /**
     * 报修订单关闭
     *
     * @param entity
     * @return
     */
    AdviceEntity closeAdvice(AdviceEntity entity);
}

