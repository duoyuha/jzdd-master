package cn.backend.service.corp;

import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.corp.CorpQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
public interface ICorpService extends IBaseService<CorpEntity,CorpQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    CorpEntity findByNo(String no);

}

