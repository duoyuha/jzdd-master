package cn.backend.service.dept;

import cn.backend.model.primary.dept.DeptEntity;
import cn.backend.model.primary.dept.DeptQuery;
import cn.backend.service.IBaseService;

/**
 * @author xsj
 * @date 2019/03/14
 */
public interface IDeptService extends IBaseService<DeptEntity, DeptQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    DeptEntity findByNo(String no);

    DeptEntity findByName(String name);

}

