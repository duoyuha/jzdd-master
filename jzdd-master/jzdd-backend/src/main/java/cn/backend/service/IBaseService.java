package cn.backend.service;

import cn.backend.model.BaseEntity;
import cn.backend.model.BaseQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * BaseService
 */
public interface IBaseService<T extends BaseEntity,E extends BaseQuery> {

    /**
     * 分页查询
     * @param query
     * @return
     */
    Page<T> findList(E query);

    /**
     * 新建
     * @param entity
     * @return
     */
    T add(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    T edit(T entity);

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 根据id校验
     * @param id
     * @return
     */
    T checkById(String id);

    /**
     * 获取全部
     * @param query
     * @return
     */
    List<T> findAll(E query);
}
