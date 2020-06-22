package cn.mongo.dao.otarecord;


import cn.mongo.config.constant.SysConstant;
import cn.mongo.model.otarecord.OTARecordEntity;
import cn.zdwl.common.mongo.MongoBaseImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.stereotype.Component;

/**
 * @Author dnc
 * @Description:
 * @Date Created in 11:38 2018/12/6
 * @Modified By:
 */
@Component
public class OTARecordRepository extends MongoBaseImpl<OTARecordEntity> {

    @Override
    public OTARecordEntity insert(OTARecordEntity entity) {
        return super.insert(entity);
    }

    @Override
    public OTARecordEntity insert(OTARecordEntity entity, String collectionName) {
        IndexOperations io = mongoTemplate.indexOps(collectionName);
        // 查看索引是否存在，如果不存在就创建索引，否则直接插入
        IndexInfo createdWhenIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.CREATED_TIME_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo lastModifiedWhenIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.LASTMODIFIED_TIME_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo tmnlCodeIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.TMNLCODE_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo corpNoIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.CORPNO_IDX.equals(i.getName())).findFirst().orElse(null);

        if (createdWhenIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.CREATED_TIME_IDX);
            index.on("createdWhen", Sort.Direction.DESC);
            index.background();
            io.ensureIndex(index);
        }

        if (lastModifiedWhenIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.LASTMODIFIED_TIME_IDX);
            index.on("lastModifiedWhen", Sort.Direction.DESC);
            index.background();
            io.ensureIndex(index);
        }

        if (corpNoIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.CORPNO_IDX);
            index.on("corpNo", Sort.Direction.ASC);
            index.background();
            io.ensureIndex(index);
        }

        if (tmnlCodeIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.TMNLCODE_IDX);
            index.on("tmnlCode", Sort.Direction.ASC);
            index.background();
            io.ensureIndex(index);
        }

        return super.insert(entity, collectionName);
    }


    @Override
    public OTARecordEntity insertOrUpdate(OTARecordEntity entity, String collectionName) {
        IndexOperations io = mongoTemplate.indexOps(collectionName);
        // 查看索引是否存在，如果不存在就创建索引，否则直接插入
        IndexInfo createdWhenIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.CREATED_TIME_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo lastModifiedWhenIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.LASTMODIFIED_TIME_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo tmnlCodeIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.TMNLCODE_IDX.equals(i.getName())).findFirst().orElse(null);
        IndexInfo corpNoIndexInfo = io.getIndexInfo().stream().filter(i -> SysConstant.MongoIndex.CORPNO_IDX.equals(i.getName())).findFirst().orElse(null);

        if (createdWhenIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.CREATED_TIME_IDX);
            index.on("createdWhen", Sort.Direction.DESC);
            index.background();
            io.ensureIndex(index);
        }

        if (lastModifiedWhenIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.LASTMODIFIED_TIME_IDX);
            index.on("lastModifiedWhen", Sort.Direction.DESC);
            index.background();
            io.ensureIndex(index);
        }

        if (corpNoIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.CORPNO_IDX);
            index.on("corpNo", Sort.Direction.ASC);
            index.background();
            io.ensureIndex(index);
        }

        if (tmnlCodeIndexInfo == null) {
            Index index = new Index();
            index.named(SysConstant.MongoIndex.TMNLCODE_IDX);
            index.on("tmnlCode", Sort.Direction.ASC);
            index.background();
            io.ensureIndex(index);
        }

        return super.insertOrUpdate(entity, collectionName);
    }

}
