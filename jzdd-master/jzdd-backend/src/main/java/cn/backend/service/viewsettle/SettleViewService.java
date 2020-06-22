package cn.backend.service.viewsettle;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.viewsettle.SettleViewRepository;
import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.viewsettle.SettleViewEntity;
import cn.backend.service.BaseService;
import cn.zdwl.common.context.BaseContextHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author james
 * @date 2019/7/30
 **/
@Service("settleViewService")
public class SettleViewService extends BaseService implements ISettleViewService {

    @Resource
    private SettleViewRepository settleViewRepository;

    /**
     * 查询(分页)
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public Page<SettleViewEntity> findList(SettleQuery query) {
        Sort sort = buildSort(query.getSort());
        Pageable pageable = new PageRequest(query.getPageNum(), query.getPageSize(), sort);
        return settleViewRepository.findAll(createSpecification(query), pageable);
    }

    /**
     * 查询(不分页)
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public List<SettleViewEntity> findAll(SettleQuery query) {
        Sort sort = buildSort(query.getSort());
        return settleViewRepository.findAll(createSpecification(query), sort);
    }


    @Override
    public SettleViewEntity add(SettleViewEntity entity) {
        return null;
    }

    @Override
    public SettleViewEntity edit(SettleViewEntity entity) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public SettleViewEntity findById(String id) {
        SettleViewEntity entity = settleViewRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    @Override
    public SettleViewEntity findBySettleNo(String settleNo) {
        SettleViewEntity entity = settleViewRepository.findBySettleNo(SysConstant.IS_DEL_N, settleNo);
        return entity;
    }

    @Override
    public SettleViewEntity checkById(String id) {
        return null;
    }

    /**
     * 创建查询条件
     *
     * @param settleQuery 查询条件
     * @return 查询条件
     */
    private Specification<SettleViewEntity> createSpecification(SettleQuery settleQuery) {
        return new Specification<SettleViewEntity>() {

            @Override
            public Predicate toPredicate(Root<SettleViewEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(settleQuery.getAllOrderFlg())) {
                    if(!isEmpty(settleQuery.getUserType())){
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + settleQuery.getUserType() + "%"));
                    }
                    if(!isEmpty(settleQuery.getUserTypeWeb())){
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + settleQuery.getUserTypeWeb() + "%"));
                    }
                    //操作数据可以处理
                    if (!isEmpty(settleQuery.getFinishFlg())) {
                        predicate.add(cb.equal(root.get("finishFlg").as(String.class), settleQuery.getFinishFlg()));
                    }
                }

                if (!isEmpty(settleQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), settleQuery.getFinishFlg()));
                }
                //完成日期
                if (!isEmpty(settleQuery.getTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("createdWhen").as(Date.class), settleQuery.getTimeStart()));
                }
                if (!isEmpty(settleQuery.getTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("createdWhen").as(Date.class), settleQuery.getTimeEnd()));
                }

                if (!isEmpty(settleQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), settleQuery.getSupplierNo()));
                }

                if (!isEmpty(settleQuery.getOrderType())) {
                    predicate.add(cb.equal(root.get("orderType").as(String.class), settleQuery.getOrderType()));
                }

                if (!isEmpty(settleQuery.getSettleStatus())) {
                    predicate.add(cb.equal(root.get("settleStatus").as(String.class), settleQuery.getSettleStatus()));
                }

                if (!isEmpty(settleQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), settleQuery.getStepMatrix()));
                }

//                if (!isEmpty(settleQuery.getVinNo())) {
//                    predicate.add(cb.equal(root.get("vinNo").as(String.class), settleQuery.getVinNo()));
//                }
//
//                if (!isEmpty(settleQuery.getPileType())) {
//                    predicate.add(cb.equal(root.get("pileType").as(String.class), settleQuery.getPileType()));
//                }


                predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }
}
