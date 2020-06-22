package cn.mongo.service.otarecord;

import cn.mongo.config.constant.SysConstant;
import cn.mongo.dao.otarecord.OTARecordRepository;
import cn.mongo.model.SortEntity;
import cn.mongo.model.otarecord.OTARecordEntity;
import cn.mongo.model.otarecord.OTARecordQuery;
import cn.mongo.service.BaseService;
import cn.zdwl.common.mongo.MongoPage;
import cn.zdwl.common.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by dnc at 2018年12月06日
 */
@Slf4j
@Service(value = "otaRecordService")
public class OTARecordService extends BaseService implements IOTARecordService {

    @Resource
    private OTARecordRepository recordRepository;

    @Override
    public OTARecordEntity add(OTARecordEntity entity, String collectionName) {
        if(entity.getCreatedWhen()==null){
            Date currentDate = new Date();
            entity.setMessageId(IdUtils.getUniqueId());
            entity.setCreatedWhen(currentDate);
            entity.setCreatedByName(SysConstant.CREATEDNAME);
            entity.setLastModifiedWhen(currentDate);
            entity.setLastModifiedByName(SysConstant.CREATEDNAME);
        }
        recordRepository.insert(entity, collectionName);
        return entity;
    }

    @Override
    public OTARecordEntity edit(OTARecordEntity entity, String collectionName) {
        entity = recordRepository.insertOrUpdate(entity, collectionName);
        return entity;
    }

    @Override
    public void delete(String id, String collectionName) {
        recordRepository.removeById(id, collectionName);
    }

    @Override
    public MongoPage<OTARecordEntity> page(OTARecordQuery recordQuery, String collectionName) {
        log.info("=====formtime-{}=====totime-{}=====",recordQuery.getFromTime(),recordQuery.getToTime());
        MongoPage<OTARecordEntity> page = new MongoPage<>();
        page.setCurrentPage(recordQuery.getPageNum() + 1);
        page.setNumberOfElements(recordQuery.getPageSize());
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(recordQuery.getCorpNo())){
            criteria.and("corpNo").is(recordQuery.getCorpNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlNo())) {
            criteria.and("tmnlNo").in(recordQuery.getTmnlNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlCode())) {
            criteria.and("tmnlCode").in(recordQuery.getTmnlCode());
        }

        //时间
        if (!StringUtils.isEmpty(recordQuery.getFromTime()) && !StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").gt(recordQuery.getFromTime()).lt(recordQuery.getToTime());
        } else if (!StringUtils.isEmpty(recordQuery.getFromTime())) {
            criteria.and("lastModifiedWhen").gte(recordQuery.getFromTime());
        } else if (!StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").lte(recordQuery.getToTime());
        }
        query.addCriteria(criteria);

        List<SortEntity> sortEntityList = new ArrayList<>();
        SortEntity st = new SortEntity();
        st.setSortkey("lastModifiedWhen");
        st.setSortvalue("DESC");
        sortEntityList.add(st);

        Sort sort = buildSort(sortEntityList);
        query.with(sort);
        return recordRepository.findPage(page, query, collectionName);
    }

    @Override
    public List<OTARecordEntity> list(OTARecordQuery recordQuery, String collectionName) {
        log.info("=====formtime-{}=====totime-{}=====",recordQuery.getFromTime(),recordQuery.getToTime());
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(recordQuery.getCorpNo())){
            criteria.and("corpNo").is(recordQuery.getCorpNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlNo())) {
            criteria.and("tmnlNo").in(recordQuery.getTmnlNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlCode())) {
            criteria.and("tmnlCode").in(recordQuery.getTmnlCode());
        }

        //时间
        if (!StringUtils.isEmpty(recordQuery.getFromTime()) && !StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").gt(recordQuery.getFromTime()).lt(recordQuery.getToTime());
        } else if (!StringUtils.isEmpty(recordQuery.getFromTime())) {
            criteria.and("lastModifiedWhen").gte(recordQuery.getFromTime());
        } else if (!StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").lte(recordQuery.getToTime());
        }

        query.addCriteria(criteria);
        List<SortEntity> sortEntityList = new ArrayList<>();
        SortEntity st = new SortEntity();
        st.setSortkey("lastModifiedWhen");
        st.setSortvalue("DESC");
        sortEntityList.add(st);

        Sort sort = buildSort(sortEntityList);
        query.with(sort);
        return recordRepository.find(query, collectionName);
    }

    @Override
    public OTARecordEntity findById(String id, String collectionName) {
        return recordRepository.findById(id, collectionName);
    }

    @Override
    public long count(OTARecordQuery recordQuery, String collectionName) {
        log.info("=====formtime-{}=====totime-{}=====",recordQuery.getFromTime(),recordQuery.getToTime());
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(recordQuery.getCorpNo())){
            criteria.and("corpNo").is(recordQuery.getCorpNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlNo())) {
            criteria.and("tmnlNo").in(recordQuery.getTmnlNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlCode())) {
            criteria.and("tmnlCode").in(recordQuery.getTmnlCode());
        }

        //时间
        if (!StringUtils.isEmpty(recordQuery.getFromTime()) && !StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").gt(recordQuery.getFromTime()).lt(recordQuery.getToTime());
        } else if (!StringUtils.isEmpty(recordQuery.getFromTime())) {
            criteria.and("lastModifiedWhen").gte(recordQuery.getFromTime());
        } else if (!StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").lte(recordQuery.getToTime());
        }

        query.addCriteria(criteria);
        return recordRepository.count(query, collectionName);
    }

    @Override
    public void remove(OTARecordQuery recordQuery, String collectionName) {
        log.info("=====formtime-{}=====totime-{}=====",recordQuery.getFromTime(),recordQuery.getToTime());
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(recordQuery.getCorpNo())){
            criteria.and("corpNo").is(recordQuery.getCorpNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlNo())) {
            criteria.and("tmnlNo").in(recordQuery.getTmnlNo());
        }

        if (!StringUtils.isEmpty(recordQuery.getTmnlCode())) {
            criteria.and("tmnlCode").in(recordQuery.getTmnlCode());
        }

        //时间
        if (!StringUtils.isEmpty(recordQuery.getFromTime()) && !StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").gt(recordQuery.getFromTime()).lt(recordQuery.getToTime());
        } else if (!StringUtils.isEmpty(recordQuery.getFromTime())) {
            criteria.and("lastModifiedWhen").gte(recordQuery.getFromTime());
        } else if (!StringUtils.isEmpty(recordQuery.getToTime())) {
            criteria.and("lastModifiedWhen").lte(recordQuery.getToTime());
        }

        query.addCriteria(criteria);
        recordRepository.remove(query, collectionName);
    }
}

