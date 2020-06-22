package cn.mongo.service.otarecord;

import cn.mongo.model.otarecord.OTARecordEntity;
import cn.mongo.model.otarecord.OTARecordQuery;
import cn.zdwl.common.mongo.MongoPage;

import java.util.List;

/**
 * create by dnc at 2018年12月06日
 */
public interface IOTARecordService {

    /**
     * 添加
     *
     * @param entity 添加对象
     * @param collectionName collection名称
     * @return 添加对象
     * */
    OTARecordEntity add(OTARecordEntity entity, String collectionName);

    /**
     * 更新
     *
     * @param entity 更新对象
     * @param collectionName collection名称
     * @return 更新对象
     * */
    OTARecordEntity edit(OTARecordEntity entity, String collectionName);

    /**
     * 按id删除
     *
     * @param id id
     * @param collectionName collection名称
     * */
    void delete(String id, String collectionName);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param collectionName collection名称
     * @return 分页查询结果
     */
    MongoPage<OTARecordEntity> page(OTARecordQuery query, String collectionName);

    /**
     * 按条件查询
     *
     * @param query 查询条件
     * @param collectionName collection名称
     * @return 按条件查询结果
     * */
    List<OTARecordEntity> list(OTARecordQuery query, String collectionName);

    /**
     * 按id查询
     *
     * @param id id
     * @param collectionName collection名称
     * @return 查询结果
     * */
    OTARecordEntity findById(String id, String collectionName);

    /**
     * 统计个数
     *
     * @param query 查询条件
     * @param collectionName collection名称
     * @return 个数
     * */
    long count(OTARecordQuery query, String collectionName);

    /**
     * 按条件删除
     *
     * @param query 查询条件
     * @param collectionName collection名称
     * */
    void remove(OTARecordQuery query, String collectionName);
}

