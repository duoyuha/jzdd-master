package cn.backend.service.workfollowdetail;

import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.IBaseService;

import java.util.Set;

/**
 * @author sunkw
 * @date 2019/07/16
 */
public interface IWorkFollowDetailService extends IBaseService<WorkFollowDetailEntity, WorkFollowDetailQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    WorkFollowDetailEntity findByNo(String no);

    /**
     * 根据编号查找
     *
     * @param followNo
     * @param followSeq
     * @return
     */
    WorkFollowDetailEntity findByFollowNoAndFollowSeq(String followNo, Integer followSeq);

    /**
     * 获取对应类型的最小步骤
     *
     * @param followNo
     * @return
     */
    WorkFollowDetailEntity getMinStep(String followNo);

    /**
     * 获取对应类型的最大步骤
     *
     * @param followNo
     * @return
     */
    WorkFollowDetailEntity getMaxStep(String followNo);

    /**
     * 获取对应类型的下一步骤
     *
     * @param followNo
     * @return
     */
    WorkFollowDetailEntity getNextStep(String followNo, Integer followSeq);

    /**
     * 获取对应类型的下一步骤
     *
     * @param followNo
     * @return
     */
    Set<String> getStepSet(String followNo);

    /**
     * 获取对应类型的待确认步骤
     *
     * @param followNo
     * @return
     */
    WorkFollowDetailEntity getConfirmStep(String followNo);

}

