package cn.backend.service.viewcomplainorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.viewcomplainorder.ViewComplainRepository;
import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.backend.model.primary.settledetail.SettleDetailQuery;
import cn.backend.model.primary.viewcomplainorder.ViewComplainEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "viewComplainService")
public class ViewComplainService extends BaseService implements IViewComplainService {

    @Resource
    private ViewComplainRepository viewComplainRepository;

    @Autowired
    private IScrmInfoService scrmInfoService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IOrderStepService orderStepService;


    /**
     * 分页查询
     *
     * @param complainOrderQuery
     * @return
     */
    @Override
    public Page<ViewComplainEntity> findList(ComplainOrderQuery complainOrderQuery) {
        Sort sort = buildSort(complainOrderQuery.getSort());
        Pageable pageable = new PageRequest(complainOrderQuery.getPageNum(), complainOrderQuery.getPageSize(), sort);
        Page<ViewComplainEntity> entityPage = viewComplainRepository.findAll(createSpecification(complainOrderQuery), pageable);

        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);
        WorkFollowDetailEntity delWrok = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07.equals(x.getTimeoutType()))
                .findFirst().orElse(null);
        for (ViewComplainEntity viewComplainEntity : entityPage) {
            //联系客户时间是否超时
            calcOVerTime(delWrok, viewComplainEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07);
        }
        return entityPage;
    }

    private void calcOVerTime(WorkFollowDetailEntity workFollowDetailEntity, ViewComplainEntity viewComplainEntity, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && isEmpty(viewComplainEntity)) {
            int overMinutes = 0;
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07:
                    if (isEmpty(viewComplainEntity.getContactCustTime())) {
                        overMinutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), viewComplainEntity.getReceiveTime(), new Date(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                        viewComplainEntity.setContactOverTime(overMinutes);
                        if (overMinutes > 0) {
                            viewComplainEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                        }
                    } else {
                        overMinutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), viewComplainEntity.getReceiveTime(), viewComplainEntity.getContactCustTime(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                        viewComplainEntity.setContactOverTime(overMinutes);
                        if (overMinutes > 0) {
                            viewComplainEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                        }
                        // viewComplainEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                    }
            }

        }

    }

    /**
     * 列表查询
     *
     * @param complainOrderQuery
     * @return
     */
    @Override
    public List<ViewComplainEntity> findAll(ComplainOrderQuery complainOrderQuery) {
        Sort sort = buildSort(complainOrderQuery.getSort());
        List<ViewComplainEntity> entityList = viewComplainRepository.findAll(createSpecification(complainOrderQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param complainOrderQuery
     * @return
     */
    private Specification createSpecification(ComplainOrderQuery complainOrderQuery) {
        return new Specification<ViewComplainEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewComplainEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单号
                if (!isEmpty(complainOrderQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class),  complainOrderQuery.getOrderNo() ));
                }

                //VIN
                if (!isEmpty(complainOrderQuery.getVinNo())) {
                    predicate.add(cb.equal(root.get("vinNo").as(String.class), complainOrderQuery.getVinNo()));
                }

                //步骤矩阵
                if (!StringUtils.isEmpty(complainOrderQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), complainOrderQuery.getStepMatrix()));
                }

                //经销商
                if (!isEmpty(complainOrderQuery.getDealerName())) {
                    predicate.add(cb.like(root.get("dealerName").as(String.class), "%" + complainOrderQuery.getDealerName() + "%"));
                }

                //服务商
                if (!isEmpty(complainOrderQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), complainOrderQuery.getSupplierNo()));
                }

                //解决服务商
                if (!isEmpty(complainOrderQuery.getSolutionSupplierNo())) {
                    predicate.add(cb.equal(root.get("solutionSupplierNo").as(String.class), complainOrderQuery.getSolutionSupplierNo()));
                }

                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(complainOrderQuery.getAllOrderFlg())) {
                    if (!isEmpty(complainOrderQuery.getUserType())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + complainOrderQuery.getUserType() + "%"));
                    }
                    if (!isEmpty(complainOrderQuery.getUserTypeWeb())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + complainOrderQuery.getUserTypeWeb() + "%"));
                    }

                    //操作数据可以处理
                    if (!isEmpty(complainOrderQuery.getFinishFlg())) {
                        predicate.add(cb.equal(root.get("finishFlg").as(String.class), complainOrderQuery.getFinishFlg()));
                    }
                }

                //投诉日期
                if (!isEmpty(complainOrderQuery.getComplainTimeStart())) {
                    //大于或等于安装完成日期开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("complainTime").as(Date.class), complainOrderQuery.getComplainTimeStart()));
                }
                if (!isEmpty(complainOrderQuery.getComplainTimeEnd())) {
                    //小于或等于安装完成日期结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("complainTime").as(Date.class), complainOrderQuery.getComplainTimeEnd()));
                }

                //操作数据可以处理
                if (!isEmpty(complainOrderQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), complainOrderQuery.getFinishFlg()));
                }

                //公司
                if (!isEmpty(complainOrderQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), complainOrderQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    @Override
    public ViewComplainEntity findById(String id) {
        ViewComplainEntity viewComplainEntity = viewComplainRepository.findById(SysConstant.IS_DEL_N, id);
        if (!isEmpty(viewComplainEntity.getScrmNo())) {
            ScrmInfoEntity scrmInfoEntity = scrmInfoService.findByNo(viewComplainEntity.getScrmNo());
            viewComplainEntity.setScrmInfoEntity(scrmInfoEntity);
        }
        return viewComplainEntity;
    }

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    @Override
    public ViewComplainEntity checkById(String id) {
        ViewComplainEntity checkEntity = findById(id);
        if (checkEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return checkEntity;
    }

}

