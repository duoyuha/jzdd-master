package cn.backend.service.complainorder;

import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
public interface IComplainOrderService extends IBaseService<ComplainOrderEntity, ComplainOrderQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    ComplainOrderEntity findByNo(String no);


    ComplainOrderEntity saveOrUpdate(ScrmInfoEntity scrmInfoEntity);

    /**
     * 提交订单
     * @param entity
     * @return
     */
    ComplainOrderEntity submitOrder(ComplainOrderEntity entity);

    /**
     * 保存
     * @param entity
     * @return
     */
    ComplainOrderEntity saveOrderAndStep(ComplainOrderEntity entity);

    /**
     * 订单工作流
     * @param entity
     * @return
     */
    ComplainOrderEntity orderWorkFollow(ComplainOrderEntity entity);

    /**
     * 报修订单关闭
     *
     * @param entity
     * @return
     */
    ComplainOrderEntity closeComplain(ComplainOrderEntity entity);

}

