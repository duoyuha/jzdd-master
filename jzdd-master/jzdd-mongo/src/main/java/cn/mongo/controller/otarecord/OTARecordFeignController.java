package cn.mongo.controller.otarecord;

import cn.mongo.config.constant.SysConstant;
import cn.mongo.model.otarecord.OTARecordEntity;
import cn.mongo.model.otarecord.OTARecordQuery;
import cn.mongo.service.otarecord.IOTARecordService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.mongo.MongoPage;
import cn.zdwl.common.util.DateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author ysm
 * @Description:
 * @Date Created in 15:09 2018/12/6
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/otarecordmongofeign")
public class OTARecordFeignController {

    @Resource
    private IOTARecordService recordService;

    /**
     * 分页查询
     *
     * @param query 传输数据对象
     * @return 历史数据
     */
    @CustomResult(false)
    @PostMapping(value = "/page")
    public MongoPage<OTARecordEntity> page(@RequestBody OTARecordQuery query) {
        String collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(new Date(), "yyyyMM");
        //时间
        if (!StringUtils.isEmpty(query.getFromTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getFromTime(), "yyyyMM");
        } else if (!StringUtils.isEmpty(query.getToTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getToTime(), "yyyyMM");
        }
        return recordService.page(query, collectionName);
    }

    /**
     * 查询不分页
     *
     * @param query 传输数据对象
     * @return 历史数据
     */
    @CustomResult(false)
    @PostMapping(value = "/findAll")
    public List<OTARecordEntity> getList(@RequestBody OTARecordQuery query) {
        String collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(new Date(), "yyyyMM");
        //时间
        if (!StringUtils.isEmpty(query.getFromTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getFromTime(), "yyyyMM");
        } else if (!StringUtils.isEmpty(query.getToTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getToTime(), "yyyyMM");
        }
        return recordService.list(query,collectionName);
    }

    /**
     * 查询数量
     *
     * @param query 传输数据对象
     * @return 历史数据
     */
    @CustomResult(false)
    @PostMapping(value = "/count")
    public long count(@RequestBody OTARecordQuery query) {
        String collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(new Date(), "yyyyMM");
        //时间
        if (!StringUtils.isEmpty(query.getFromTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getFromTime(), "yyyyMM");
        } else if (!StringUtils.isEmpty(query.getToTime())) {
            collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(query.getToTime(), "yyyyMM");
        }
        return recordService.count(query,collectionName);
    }

    /**
     * 新增
     *
     * @param entity 传输数据对象
     * @return 历史数据
     */
    @CustomResult(false)
    @PostMapping()
    public OTARecordEntity saveOTARecord(@RequestBody OTARecordEntity entity) {
        String collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(new Date(), "yyyyMM");
        return recordService.add(entity, collectionName);
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    @CustomResult(false)
    @GetMapping(value = "/{id}")
    public OTARecordEntity findOTARecordById(@PathVariable("id") String id) {
        String collectionName = SysConstant.MongoCollection.OTA_RECORD_COLLECTION + "_" + DateUtils.dateToStr(new Date(), "yyyyMM");
        return recordService.findById(id,collectionName);
    }

}
