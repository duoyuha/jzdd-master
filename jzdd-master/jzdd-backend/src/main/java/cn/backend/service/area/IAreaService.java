package cn.backend.service.area;

import cn.backend.model.primary.area.AreaEntity;
import cn.backend.model.primary.area.AreaQuery;
import cn.backend.service.IBaseService;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IAreaService extends IBaseService<AreaEntity, AreaQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    AreaEntity findByNo(String no);

}

