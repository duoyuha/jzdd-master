package cn.backend.service.scrminfo;

import cn.backend.access.webchat.Result;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.scrminfo.ScrmCarInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
public interface IScrmInfoService extends IBaseService<ScrmInfoEntity, ScrmInfoQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    ScrmInfoEntity findByNo(String no);


    /**
     * 新增或更新
     * @param entity
     * @return
     */
    ScrmInfoEntity saveOrUpdateCase(ScrmInfoEntity entity);


    /**
     * 咨询、投诉处理结果
     * @param entity
     * @return
     */
    boolean caseResult(AdviceEntity entity);
    boolean caseResult(ComplainOrderEntity entity);

    /**
     * 查询车主、车辆及经销商信息
     * @param token
     * @param vin
     * @return
     */
    ScrmCarInfoEntity carInfo(String token, String vin, Integer brand);

    /**
     * 校验token是否合法
     * @param token
     * @param brand
     * @return
     */
    Boolean checkToken(String token,Integer brand);

    /**
     * 提交充电桩订单
     * @param token
     * @param brand
     * @return
     */
    Result submitPileOrder(String token,String vin,Integer brand, Integer state);
}

