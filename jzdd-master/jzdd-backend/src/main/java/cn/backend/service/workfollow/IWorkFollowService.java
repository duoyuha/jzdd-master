package cn.backend.service.workfollow;

import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollow.WorkFollowQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
public interface IWorkFollowService extends IBaseService<WorkFollowEntity,WorkFollowQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    WorkFollowEntity findByNo(String no);

    /**
     * 根据编号查找
     *
     * @param code
     * @return
     */
    WorkFollowEntity findByCode(String code);

}

