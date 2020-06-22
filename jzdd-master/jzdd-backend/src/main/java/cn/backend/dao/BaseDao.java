package cn.backend.dao;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * jpa扩展
 */
@NoRepositoryBean
public class BaseDao<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements IBaseDao<T,ID> {

    private final EntityManager em;

    public BaseDao(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    /**
     * 逻辑删除
     * @param entity
     */
    @Transactional
    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        entity.setIsDel(SysConstant.IS_DEL_Y);
        this.saveAndFlush(entity);
    }

}
