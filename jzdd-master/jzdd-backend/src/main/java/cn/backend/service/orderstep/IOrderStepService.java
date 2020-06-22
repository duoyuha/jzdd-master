package cn.backend.service.orderstep;

import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.service.IBaseService;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/16
 */
public interface IOrderStepService extends IBaseService<OrderStepEntity, OrderStepQuery> {

    /**
     * 仪表盘查找已完成订单的步骤
     *
     * @param orderStepQuery
     * @return
     */
    Page<OrderStepEntity> findFinishList(OrderStepQuery orderStepQuery);

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    List<OrderStepEntity> findByNo(String no);

    /**
     * 根据订单编号查找最后一步
     *
     * @param orderNo
     * @return
     */
    OrderStepEntity findLastByOrderNo(String orderNo, String followCode);

    /**
     * 根据订单编号和步骤号查找
     *
     * @param orderNo
     * @param followSeq
     * @return
     */
    List<OrderStepEntity> findByOrderNoAndFollowSeq(String orderNo, int followSeq);

    // /**
    //  * 根据工作流编号和业务号查询
    //  *
    //  * @param followNo
    //  * @param stepSeq
    //  * @return
    //  */
    // OrderStepEntity findByFollowNoAndStepSeq(String followNo, int stepSeq);

    /**
     * 工作流处理
     *
     * @param followCode
     * @param followStep
     * @return
     */
    OrderStepEntity workFollow(String followCode, int followStep);

    // /**
    //  * 工作流(重构)
    //  *
    //  * @param followNo
    //  * @param currentFollowSeq
    //  * @return
    //  */
    // OrderStepEntity workFollowStep(String orderNo, String followNo, int currentFollowSeq);
    //
    // /**
    //  * 撤回(重构)
    //  *
    //  * @param followNo
    //  * @param currentFollowSeq
    //  * @return
    //  */
    // OrderStepEntity withDrawStep(String orderNo, String followNo, int currentFollowSeq, String remark);
    //
    // /**
    //  * 审核(重构)
    //  *
    //  * @param followNo
    //  * @param currentFollowSeq
    //  * @return
    //  */
    // OrderStepEntity checkStep(String orderNo, String followNo, int currentFollowSeq, String remark);
    //
    // /**
    //  * 超时(重构)
    //  *
    //  * @param followNo
    //  * @param currentFollowSeq
    //  * @return
    //  */
    // OrderStepEntity timeOutStep(String orderNo, String followNo, int currentFollowSeq);

    // /**
    //  * 初始化OrderStep
    //  *
    //  * @param workFollowDetailEntity
    //  * @return
    //  */
    // OrderStepEntity initNextOrderStep(WorkFollowDetailEntity workFollowDetailEntity);
    //
    // /**
    //  * 编辑当前OrderStep
    //  *
    //  * @param orderStepEntity
    //  * @return
    //  */
    // OrderStepEntity editCurrentOrderStep(OrderStepEntity orderStepEntity);

    /**
     * 初始化OrderStep
     *
     * @param workFollowEntity
     * @param workFollowDetailEntity
     * @param userEntity
     * @return
     */
    OrderStepEntity initOrderStep(WorkFollowEntity workFollowEntity
            , WorkFollowDetailEntity workFollowDetailEntity, UserEntity userEntity);

    /**
     * 判断是否超时
     *
     * @param days      限期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    int judgeTimeoutDays(int days, Date startDate, Date endDate);

    /**
     * 判断是否超时
     *
     * @param hours     限期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    int judgeTimeoutHours(int hours, Date startDate, Date endDate);

    /**
     * 判断是否超时-指定每天工作时间
     *
     * @param hours     限期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    int judgeTimeoutHoursWithWorking(int hours, Date startDate, Date endDate, String dayWorkStart, String dayWorkEnd);

    /**
     * 用于创建审批流程 顺次执行
     *
     * @param parameter     业务编号 每种业务对应的 唯一号
     * @return
     */
    OrderStepEntity orderStepAdd(OrderStepParameter parameter);

    /**
     * 用于创建审批流程 审批拒绝或回退
     *
     * @param parameter     业务编号 每种业务对应的 唯一号
     * @return
     */
    OrderStepEntity orderStepRejct(OrderStepParameter parameter);

    /**
     * 用于编辑当前流程，不产生下一流程 对应安装、配送中的保存
     *
     * @param parameter
     * @return
     */
    OrderStepEntity orderStepEdit(OrderStepParameter parameter);

    /**
     * 用于直接关闭订单 2019 0809
     *
     * @param parameter 业务订单号
     * @return
     */
    OrderStepEntity orderStepFinish(OrderStepParameter parameter);

    /**
     * 结算审核不通过 20190909
     *
     * @param parameter 业务订单号
     * @return
     */
    OrderStepEntity orderStepSettleFail(OrderStepParameter parameter);
}

