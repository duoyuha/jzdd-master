package cn.mongo.service;

import cn.mongo.model.SortEntity;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseService {

    //缓存数据
    // @Autowired
    // protected RedisManager redisManager;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 条件合并
     * @param predicate
     * @param cb
     * @return
     */
    protected Predicate handlePredicate(List<Predicate> predicate, CriteriaBuilder cb){
        if(predicate.size()==0){
            return null;
        }
        Predicate[] p = new Predicate[predicate.size()];
        return cb.and(predicate.toArray(p));
    }

    /**
     * 生成排序Sort
     *
     * @param sortList
     * @return
     */
    protected Sort buildSort(List<SortEntity> sortList) {
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        if (sortList != null) {
            for (SortEntity st : sortList) {
                if (StringUtils.isEmpty(st.getSortkey()) || StringUtils.isEmpty(st.getSortvalue())) {
                    return null;
                }
                if ("ASC".equals(st.getSortvalue().toUpperCase())) {
                    orderList.add(new Sort.Order(Sort.Direction.ASC, st.getSortkey()));
                } else {
                    orderList.add(new Sort.Order(Sort.Direction.DESC, st.getSortkey()));
                }
            }
        }
        //更新时间
//        orderList.add(new Sort.Order(Sort.Direction.DESC, "lastModifiedWhen"));
        return new Sort(orderList);
    }

    /**
     * 根据字段排序
     * @param sort
     * @return
     */
    protected Sort buildSort(String sort){
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        if(StringUtils.isEmpty(sort)){
            sort="-lastModifiedWhen";
        }
        String[] sortList=sort.split(",");
        for(String sortKey:sortList){
            orderList.add(convertSort(sortKey));
        }
        return new Sort(orderList);
    }

    private Sort.Order convertSort(String sortKey){
        String key=sortKey.substring(1);
        if(sortKey.startsWith("-")){
            return new Sort.Order(Sort.Direction.DESC, key);
        }
        return new Sort.Order(Sort.Direction.ASC, key);
    }

    /**
     * 执行sql
     * @param sql
     * @param param
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> List<T> excuteSql(String sql, Map<Integer, Object> param, Class<T> clazz){
        //创建查询
        Query query = entityManager.createNativeQuery(sql.toString(),clazz);

        //加入参数
        for (Integer key : param.keySet()) {
            if(param.get(key) instanceof Date){
                query.setParameter(key,(Date)param.get(key), TemporalType.TIMESTAMP);
            }else{
                query.setParameter(key, param.get(key));
            }
        }

        return query.getResultList();
    }

}
