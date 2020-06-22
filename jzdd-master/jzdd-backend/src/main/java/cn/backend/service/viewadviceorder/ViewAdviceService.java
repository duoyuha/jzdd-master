package cn.backend.service.viewadviceorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.viewadviceorder.ViewAdviceRepository;
import cn.backend.model.primary.adviceorder.AdviceQuery;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.viewadviceorder.ViewAdviceEntity;
import cn.backend.model.primary.viewcomplainorder.ViewComplainEntity;
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
import java.util.*;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "viewAdviceService")
public class ViewAdviceService extends BaseService implements IViewAdviceService {

    @Resource
    private ViewAdviceRepository viewAdviceRepository;

    @Autowired
    private IScrmInfoService scrmInfoService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IOrderStepService orderStepService;


    /**
     * 分页查询
     *
     * @param adviceQuery
     * @return
     */
    @Override
    public Page<ViewAdviceEntity> findList(AdviceQuery adviceQuery) {
        Sort sort = buildSort(adviceQuery.getSort());
        Pageable pageable = new PageRequest(adviceQuery.getPageNum(), adviceQuery.getPageSize(), sort);
        Page<ViewAdviceEntity> entityPage = viewAdviceRepository.findAll(createSpecification(adviceQuery), pageable);

        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);
        WorkFollowDetailEntity delWrok = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE08.equals(x.getTimeoutType()))
                .findFirst().orElse(null);
        for (ViewAdviceEntity viewAdviceEntity : entityPage) {
            //联系客户时间是否超时
            calcOVerTime(delWrok, viewAdviceEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE08);
        }

        return entityPage;
    }

    private void calcOVerTime(WorkFollowDetailEntity workFollowDetailEntity, ViewAdviceEntity viewAdviceEntity, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && isEmpty(viewAdviceEntity)) {
            int overMinutes = 0;
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07:
                    if (isEmpty(viewAdviceEntity.getContactCustTime())) {
                        overMinutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), viewAdviceEntity.getReceiveTime(), new Date(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                        viewAdviceEntity.setContactOverTime(overMinutes);
                        if (overMinutes > 0) {
                            viewAdviceEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                        }
                    } else {
                        overMinutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), viewAdviceEntity.getReceiveTime(), viewAdviceEntity.getContactCustTime(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                        viewAdviceEntity.setContactOverTime(overMinutes);
                        if (overMinutes > 0) {
                            viewAdviceEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                        }
                        // viewComplainEntity.setContactOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                    }
            }

        }

    }

    /**
     * 列表查询
     *
     * @param adviceQuery
     * @return
     */
    @Override
    public List<ViewAdviceEntity> findAll(AdviceQuery adviceQuery) {
        Sort sort = buildSort(adviceQuery.getSort());
        List<ViewAdviceEntity> entityList = viewAdviceRepository.findAll(createSpecification(adviceQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param adviceQuery
     * @return
     */
    private Specification createSpecification(AdviceQuery adviceQuery) {
        return new Specification<ViewAdviceEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewAdviceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(adviceQuery.getAllOrderFlg())) {
                    if (!isEmpty(adviceQuery.getUserType())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + adviceQuery.getUserType() + "%"));
                    }
                    if (!isEmpty(adviceQuery.getUserTypeWeb())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + adviceQuery.getUserTypeWeb() + "%"));
                    }

                    //操作数据可以处理
                    if (!isEmpty(adviceQuery.getFinishFlg())) {
                        predicate.add(cb.equal(root.get("finishFlg").as(String.class), adviceQuery.getFinishFlg()));
                    }
                }


                //订单号
                if (!isEmpty(adviceQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), adviceQuery.getOrderNo() ));
                }

                //VIN
                if (!isEmpty(adviceQuery.getVinNo())) {
                    predicate.add(cb.equal(root.get("vinNo").as(String.class),  adviceQuery.getVinNo()));
                }

                //经销商
                if (!isEmpty(adviceQuery.getDealerName())) {
                    predicate.add(cb.like(root.get("dealerName").as(String.class), "%" + adviceQuery.getDealerName() + "%"));
                }

                //服务商
                if (!isEmpty(adviceQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), adviceQuery.getSupplierNo()));
                }

                //解决服务商
                if (!isEmpty(adviceQuery.getSolutionSupplierNo())) {
                    predicate.add(cb.equal(root.get("solutionSupplierNo").as(String.class), adviceQuery.getSolutionSupplierNo()));
                }

                //步骤矩阵
                if (!StringUtils.isEmpty(adviceQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), adviceQuery.getStepMatrix()));
                }
                //操作数据可以处理
                if (!isEmpty(adviceQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), adviceQuery.getFinishFlg()));
                }

                //投诉日期
                if (!isEmpty(adviceQuery.getAdviceTimeStart())) {
                    //大于或等于安装完成日期开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("adviceTime").as(Date.class), adviceQuery.getAdviceTimeStart()));
                }
                if (!isEmpty(adviceQuery.getAdviceTimeEnd())) {
                    //小于或等于安装完成日期结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("adviceTime").as(Date.class), adviceQuery.getAdviceTimeEnd()));
                }

                //需要的步骤
                // if (!StringUtils.isEmpty(adviceQuery.getUseSteps())) {
                //     String[] useCodes = adviceQuery.getUseSteps().split(",");
                //     if (useCodes.length > 1) {
                //         Set<String> useList = new HashSet<>();
                //         Collections.addAll(useList, useCodes);
                //         CriteriaBuilder.In<String> in = cb.in(root.get("followSeq").as(String.class));
                //         for (String code : useList) {
                //             in.value(code);
                //         }
                //         predicate.add(in);
                //     } else {
                //         predicate.add(cb.equal(root.get("followSeq").as(String.class), adviceQuery.getUseSteps()));
                //     }
                // }

                //公司
                if (!isEmpty(adviceQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), adviceQuery.getCorpNo()));
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
    public ViewAdviceEntity findById(String id) {
        ViewAdviceEntity viewAdviceEntity = viewAdviceRepository.findById(SysConstant.IS_DEL_N, id);
        if (!isEmpty(viewAdviceEntity.getScrmNo())) {
            ScrmInfoEntity scrmInfoEntity = scrmInfoService.findByNo(viewAdviceEntity.getScrmNo());
            viewAdviceEntity.setScrmInfoEntity(scrmInfoEntity);
        }
        return viewAdviceEntity;
    }

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    @Override
    public ViewAdviceEntity checkById(String id) {
        ViewAdviceEntity checkEntity = findById(id);
        if (checkEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return checkEntity;
    }

}

