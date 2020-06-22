package cn.backend.service.userarea;

import cn.backend.model.primary.userarea.UserAreaEntity;
import cn.backend.model.primary.userarea.UserAreaQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/10/08
 */
public interface IUserAreaService extends IBaseService<UserAreaEntity, UserAreaQuery> {

    /**
     * 批量保存
     *
     * @return
     */
    List<UserAreaEntity> saveBatch(List<UserAreaEntity> userAreaEntityList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Set<String> deleteBatch(Set<String> ids);

    /**
     * 根据编号查找
     *
     * @param userNo
     * @return
     */
    List<UserAreaEntity> findByUserNo(String userNo);

}

