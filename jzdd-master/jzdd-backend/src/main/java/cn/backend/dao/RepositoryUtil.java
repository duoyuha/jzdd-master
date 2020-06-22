package cn.backend.dao;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class RepositoryUtil {

    @PersistenceContext
    EntityManager entityManager;
    /**
     * 查询 返回集合
     * @param sql
     * @param objects   占位符参数数组
     * @return
     */
    public List<Map<String, Object>> execSql(String sql, Object[] objects) {
        Query q = this.entityManager.createNativeQuery(sql);
        if (objects != null && objects.length > 0) {
            for(int i = 0; i < objects.length; ++i) {
                q.setParameter(i + 1, objects[i]);
            }
        }

        ((SQLQuery)q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    /**
     * 查询单个结果集
     * @param sql
     * @param objects 占位符参数数组
     * @return
     * @throws Exception
     */
    public Map<String, Object> execSqlSingleResult(String sql, Object[] objects) throws Exception {
        Query q = this.entityManager.createNativeQuery(sql);
        if (objects != null && objects.length > 0) {
            for(int i = 0; i < objects.length; ++i) {
                q.setParameter(i + 1, objects[i]);
            }
        }

        ((SQLQuery)q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map)q.getSingleResult();
    }

    /**
     * 查询分页集合
     * @param pageable 分页对象  Pageable pageable = new PageRequest(0,10);
     * @param sql
     * @param objects 占位符参数数组
     * @return
     */
    public Page<Map<String, Object>> execQuerySqlPage(Pageable pageable, String sql, Object[] objects) {

        Query q = this.entityManager.createNativeQuery(sql);
        Query sizeQuery = this.entityManager.createNativeQuery("select count(*) from (" + sql + ")");
        if (objects != null && objects.length > 0) {
            for(int i = 0; i < objects.length; ++i) {
                q.setParameter(i + 1, objects[i]);
                sizeQuery.setParameter(i + 1, objects[i]);
            }
        }

        long size = 0L;
        if (pageable != null) {
            size = Long.valueOf(sizeQuery.getResultList().get(0).toString());
            q.setFirstResult(pageable.getOffset());
            q.setMaxResults(pageable.getPageSize());
        }

        ((SQLQuery)q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return pageable == null ? new PageImpl(q.getResultList()) : new PageImpl(q.getResultList(), pageable, size);
    }


}
