package cn.backend.service.scrminfo;

import cn.backend.access.webchat.Result;
import cn.backend.access.webchat.ZhongouSignUtil;
import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.scrminfo.ScrmInfoRepository;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.scrminfo.ScrmCarInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoQuery;
import cn.backend.service.BaseService;
import cn.backend.service.adviceorder.IAdviceService;
import cn.backend.service.complainorder.IComplainOrderService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.wsdl.caseback.ChargingPileFollowUpInfo;
import cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebservicePortType;
import cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceServiceLocator;
import cn.backend.service.wsdl.login.LoginResult;
import cn.backend.service.wsdl.login.SforceServiceLocator;
import cn.backend.service.wsdl.login.Soap_PortType;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.DateUtils;
import cn.zdwl.common.util.HttpUtils;
import cn.zdwl.common.util.IdUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Slf4j
@Service(value = "scrmInfoService")
public class ScrmInfoService extends BaseService implements IScrmInfoService{

    @Resource
    private ScrmInfoRepository scrmInfoRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IAdviceService adviceService;

    @Autowired
    private IComplainOrderService complainOrderService;

    @Value("${corpno.oula}")
    private String oulaCorpNo;

    @Value("${corpno.wey}")
    private String weyCorpNo;

    @Value("${scrm.baseurl}")
    private String baseurl;

    @Value("${scrm.caseresulturl}")
    private String caseresulturl;

    @Value("${scrm.carinfourl}")
    private String carinfourl;

    @Value("${scrm.checktokenurl}")
    private String checktokenurl;

    @Value("${scrm.submitpileorderurl}")
    private String submitpileorderurl;

    @Value("${scrm.key}")
    private String appKey;

    @Value("${scrm.scrmaccount}")
    private String scrmaccount;

    @Value("${scrm.scrmpassword}")
    private String scrmpassword;

    @Autowired
    private SforceServiceLocator sforceServiceLocator;

    @Autowired
    private ChargingPileInfoFollowUpWebserviceServiceLocator chargingPileInfoFollowUpWebserviceServiceLocator;

    /**
     * 分页查询
     * @param scrmInfoQuery
     * @return
     */
    @Override
    public Page<ScrmInfoEntity> findList(ScrmInfoQuery scrmInfoQuery) {
        Sort sort=buildSort(scrmInfoQuery.getSort());
        Pageable pageable = new PageRequest(scrmInfoQuery.getPageNum(), scrmInfoQuery.getPageSize(),sort);
        Page<ScrmInfoEntity> entityPage = scrmInfoRepository.findAll(createSpecification(scrmInfoQuery),pageable);
        return entityPage;
    }

    /**
     * 列表查询
     * @param scrmInfoQuery
     * @return
     */
    @Override
    public List<ScrmInfoEntity> findAll(ScrmInfoQuery scrmInfoQuery) {
        Sort sort=buildSort(scrmInfoQuery.getSort());
        List<ScrmInfoEntity> entityList = scrmInfoRepository.findAll(createSpecification(scrmInfoQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param scrmInfoQuery
     * @return
     */
    private Specification createSpecification(ScrmInfoQuery scrmInfoQuery){
        return new Specification<ScrmInfoEntity>(){

           @Override
           public Predicate toPredicate(Root<ScrmInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicate = new ArrayList<>();

               //筛选已删除
               predicate.add(cb.equal(root.get("isDel").as(String.class),SysConstant.IS_DEL_N));

               Predicate[] p = new Predicate[predicate.size()];
               return cb.and(predicate.toArray(p));
           }

       };
    }

    /**
     * 新建
     * @param entity
     * @return
     */
    @Override
    public ScrmInfoEntity add(ScrmInfoEntity entity) {
        configService.convert(entity);
        scrmInfoRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    public ScrmInfoEntity edit(ScrmInfoEntity entity) {
        configService.convert(entity);
        scrmInfoRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 新增或更新
     * @param entity
     * @return
     */
    @Override
    public synchronized  ScrmInfoEntity saveOrUpdateCase(ScrmInfoEntity entity) {
        Map<String, String> map = new HashMap<>();
        map.put("custTel",entity.getCustTel());
//        map.put("vin",entity.getVin());
        String sign = ZhongouSignUtil.generateSign(map, appKey);
        log.error("==========投诉咨询签名=========={}",sign);
        if(!sign.equals(entity.getSign())){
            throw new AppException(CustomMessage.SIGN_ERROR);
        }

        ScrmInfoEntity oldEntity=scrmInfoRepository.findByCaseId(SysConstant.IS_DEL_N,entity.getCaseId());

        if(oldEntity!=null){
            entity.setId(oldEntity.getId());
            entity.setScrmNo(oldEntity.getScrmNo()==null?SysConstant.Scrm.PREFIX_NUMBER + IdUtils.getGenerateNumber():oldEntity.getScrmNo());
            entity.setNew(false);
        }else{
            entity.setScrmNo(SysConstant.Scrm.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            entity.setNew(true);
        }
        //工单类型(1.欧拉销售咨询2.欧拉售后咨询3.欧拉售后投诉4.WEY销售咨询5.WEY售后咨询6.WEY售后投诉)
        switch (entity.getTypeCode()){
            case "1":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_ADVICE);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_OULA_SALE_ADVICE);
                entity.setCorpNo(oulaCorpNo);
                break;
            case "2":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_ADVICE);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_OULA_AFTER_SALE_ADVICE);
                entity.setCorpNo(oulaCorpNo);
                break;
            case "3":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_COMPLAIN);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_OULA_AFTER_SALE_COMPLAIN);
                entity.setCorpNo(oulaCorpNo);
                break;
            case "4":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_ADVICE);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_WEY_SALE_ADVICE);
                entity.setCorpNo(weyCorpNo);
                break;
            case "5":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_COMPLAIN);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_WEY_AFTER_SALE_ADVICE);
                entity.setCorpNo(weyCorpNo);
                break;
            case "6":
                entity.setAdviceOrComplain(SysConstant.Scrm.CASE_TYPE_COMPLAIN);
                entity.setTypeCode(SysConstant.Scrm.CASE_TYPE_WEY_AFTER_SALE_COMPLAIN);
                entity.setCorpNo(weyCorpNo);
                break;
            default:break;
        }

        ConfigEntity configEntity = configService.findByTypeAndCode(SysConstant.Scrm.CASE_TYPE,entity.getTypeCode(),entity.getCorpNo());
        entity.setTypeCodeName(configEntity==null?"":configEntity.getConfigName());
        scrmInfoRepository.saveAndFlush(entity);
        String json = JSON.toJSONString(entity);
        log.error("==========咨询，投诉新增========保存={}",json);
        if(SysConstant.Scrm.CASE_TYPE_ADVICE==entity.getAdviceOrComplain()){
            adviceService.saveOrUpdate(entity);
        }else{
            complainOrderService.saveOrUpdate(entity);
        }

        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        ScrmInfoEntity entity=scrmInfoRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        scrmInfoRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public ScrmInfoEntity findById(String id) {
        ScrmInfoEntity entity=scrmInfoRepository.findById(SysConstant.IS_DEL_N,id);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public ScrmInfoEntity checkById(String id) {
        ScrmInfoEntity entity=scrmInfoRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    @Override
    public ScrmInfoEntity findByNo(String no) {
        ScrmInfoEntity entity=scrmInfoRepository.findByNo(SysConstant.IS_DEL_N,no);
        return entity;
    }


    /**
     * 咨询、投诉处理结果
     * @param entity
     * @return
     */
    @Override
    public boolean caseResult(AdviceEntity entity) {
        String sessionid = getSessionId();
        ScrmInfoEntity scrm =scrmInfoRepository.findByNo(SysConstant.IS_DEL_N,entity.getScrmNo());

        try {
            ChargingPileFollowUpInfo followupInfo = new ChargingPileFollowUpInfo();
//            ChargingPileInfoFollowUpWebserviceServiceLocator serviceLocator = new ChargingPileInfoFollowUpWebserviceServiceLocator();
            ChargingPileInfoFollowUpWebservicePortType webservicePortType = chargingPileInfoFollowUpWebserviceServiceLocator.getChargingPileInfoFollowUpWebservice();

            followupInfo.setCaseId(scrm.getCaseId());
            followupInfo.setContactCustTime(DateUtils.dateToStr(entity.getContactCustTime(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setSolveTime(DateUtils.dateToStr(entity.getSolutionTime(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setSolveMethod(entity.getSolutionDesc());
            followupInfo.setFollowUpType("充电桩跟进");
            followupInfo.setCaseFollowUpHistoryID(scrm.getScrmNo());
            followupInfo.setRepliedName(entity.getLastModifiedByName());
            followupInfo.setRepliedTime(DateUtils.dateToStr(entity.getLastModifiedWhen(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setRemark(entity.getRemark());

            String json = JSON.toJSONString(followupInfo);
            log.error("==========咨询，投诉结果========推送={}",json);
            boolean bool = webservicePortType.generateCaseFollowUp(followupInfo,sessionid);
            log.error("==========咨询，投诉结果========结果={}",bool);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean caseResult(ComplainOrderEntity entity) {
        String sessionid = getSessionId();
        ScrmInfoEntity scrm =scrmInfoRepository.findByNo(SysConstant.IS_DEL_N,entity.getScrmNo());

        try {
            ChargingPileFollowUpInfo followupInfo = new ChargingPileFollowUpInfo();
//            ChargingPileInfoFollowUpWebserviceServiceLocator serviceLocator = new ChargingPileInfoFollowUpWebserviceServiceLocator();
            ChargingPileInfoFollowUpWebservicePortType webservicePortType = chargingPileInfoFollowUpWebserviceServiceLocator.getChargingPileInfoFollowUpWebservice();

            followupInfo.setCaseId(scrm.getCaseId());
            followupInfo.setContactCustTime(DateUtils.dateToStr(entity.getContactCustTime(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setSolveTime(DateUtils.dateToStr(entity.getSolutionTime(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setSolveMethod(entity.getSolutionDesc());
            followupInfo.setFollowUpType("充电桩跟进");
            followupInfo.setCaseFollowUpHistoryID(scrm.getScrmNo());
            followupInfo.setRepliedName(entity.getLastModifiedByName());
            followupInfo.setRepliedTime(DateUtils.dateToStr(entity.getLastModifiedWhen(),"yyyy-MM-dd hh:mm:ss"));
            followupInfo.setRemark(entity.getRemark());

            String json = JSON.toJSONString(followupInfo);
            log.error("==========咨询，投诉结果========推送={}",json);
            boolean bool = webservicePortType.generateCaseFollowUp(followupInfo,sessionid);
            log.error("==========咨询，投诉结果========结果={}",bool);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



    /**
     * 校验token是否合法
     * @param token
     * @param brand
     * @return
     */
    @Override
    public Boolean checkToken(String token, Integer brand) {

        HashMap<String,String> signmap = new HashMap();
        signmap.put("token",token);
        signmap.put("brand",brand+"");
        String params = connectParams(signmap);
        log.error("==========校验token是否合法========={}",params);
        ResponseEntity<Result> response = HttpUtils.doGet(baseurl + checktokenurl + params, null, Result.class);
        log.error("==========校验token是否合法========结果={}",JSON.toJSONString(response.getBody()));
        Boolean result = (Boolean)response.getBody().getResult();
        return result;
    }

    /**
     * 查询车主、车辆及经销商信息
     * @param token
     * @param vin
     * @return
     */
    @Override
    public ScrmCarInfoEntity carInfo(String token,String vin,Integer brand) {

        HashMap<String,String> signmap = new HashMap();
        signmap.put("token",token);
        signmap.put("vin",vin);
        signmap.put("brand",brand+"");
        String params = connectParams(signmap);

        log.error("==========查询车主、车辆及经销商信息========={}",params);
        ResponseEntity<Result> response = HttpUtils.doGet(baseurl + carinfourl + params, null,  Result.class);
        log.error("==========查询车主、车辆及经销商信息========结果={}",JSON.toJSONString(response.getBody()));

        HashMap resultMap = (HashMap)response.getBody().getResult();
        if(resultMap == null){
            return null;
        }
        ArrayList list = (ArrayList) resultMap.get("list");
        if(list==null || list.size()==0){
            return null;
        }
        ScrmCarInfoEntity scrmCarInfo = JSON.parseObject(JSON.toJSONString(list.get(0)), ScrmCarInfoEntity.class);
        scrmCarInfo.setSaleDateStr(new Date(scrmCarInfo.getSaleDate()));
        return scrmCarInfo;
    }

    /**
     * 提交充电桩订单
     * @param token
     * @param brand
     * @param state 1.已提报；2.待回访；3.订单完成
     * @return
     */
    @Override
    public Result submitPileOrder(String token,String vin,Integer brand, Integer state) {
        log.error("==========submitPileOrder==========vin-{},brand-{},state-{}",vin,brand,state);
        HashMap<String,String> signmap = new HashMap();
//        signmap.put("token",token);
        signmap.put("vin",vin);
        signmap.put("brand",brand+"");
        signmap.put("state",state+"");
        String params = connectParams(signmap);

        log.error("==========提交充电桩订单========={}",params);
        ResponseEntity<Result> response = HttpUtils.doGet(baseurl + submitpileorderurl + params, null, Result.class);
        log.error("==========提交充电桩订单========结果={}",JSON.toJSONString(response.getBody()));

        String message = response.getBody().getMessage();
        return response.getBody();
    }

//    private String sign(Long dt) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("appKey", appKey);
//        map.put("timeStamp", "" + dt);
//        map.put("nonceStr", nonceStr);
//
//        String[] ap = new String[]{"timeStamp", "appKey", "nonceStr"};
//        String reqSign = ZhongouSignUtil.generateSign(map, Arrays.asList(ap), appSecret);
//        return reqSign;
//    }

    private String connectParams(HashMap<String,String> map){

//        ?token=83dded3a0cde49bf807c430783e835a7&brand=3&sign=99DE800F332145AB1107B892AF90EA33

        String str = "?";
        for (Map.Entry entry : map.entrySet()) {
            str = str + entry.getKey() + "=" +entry.getValue() + "&";
        }
        String sign = ZhongouSignUtil.generateSign(map, appKey);
        str = str + "sign=" + sign;
        return str;

    }

    private String getSessionId(){
        try {
//            SforceServiceLocator locator = new SforceServiceLocator();
            Soap_PortType soapBindingStub = sforceServiceLocator.getSoap();
            LoginResult loginResult = soapBindingStub.login(scrmaccount,scrmpassword);
            log.error("==========scrm wsdl sessionid========={}",loginResult.getSessionId());
            return loginResult.getSessionId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

