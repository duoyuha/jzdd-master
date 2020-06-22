package cn.backend.service.settledetail;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.settledetail.SettleDetailRepository;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.backend.model.primary.settledetail.SettleDetailQuery;
import cn.backend.service.BaseService;
import cn.zdwl.common.exception.AppException;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.Optional;

/**
 * @author James
 * @date 2019/07/29
 */
@Slf4j
@Service(value = "settleDetailService")
public class SettleDetailService extends BaseService implements ISettleDetailService {

    @Resource
    private SettleDetailRepository settleDetailRepository;


    /**
     * 分页查询
     *
     * @param settleDetailQuery 查询条件
     * @return 查询结果
     */
    @Override
    public Page<SettleDetailEntity> findList(SettleDetailQuery settleDetailQuery) {
        Sort sort = buildSort(settleDetailQuery.getSort());
        Pageable pageable = new PageRequest(settleDetailQuery.getPageNum(), settleDetailQuery.getPageSize(), sort);
        return settleDetailRepository.findAll(createSpecification(settleDetailQuery), pageable);
    }

    /**
     * 列表查询
     *
     * @param settleDetailQuery 查询条件
     * @return 查询结果
     */
    @Override
    public List<SettleDetailEntity> findAll(SettleDetailQuery settleDetailQuery) {
        Sort sort = buildSort(settleDetailQuery.getSort());
        return settleDetailRepository.findAll(createSpecification(settleDetailQuery), sort);
    }

    /**
     * 列表查询
     *
     * @param no 查询条件
     * @return 查询结果
     */
    @Override
    public List<SettleDetailEntity> findByNo(String no) {

        return settleDetailRepository.findByNo(SysConstant.IS_DEL_N, no);
    }


    /**
     * 根据结算单明细Id获取
     *
     * @param id 结算单明细Id
     * @return 结算单明细
     */
    @Override
    public SettleDetailEntity findById(String id) {
        return settleDetailRepository.findById(SysConstant.IS_DEL_N, id);
    }

    /**
     * 根据结算单明细Id校验
     *
     * @param id 结算单明细Id
     * @return 结算单明细
     */
    @Override
    public SettleDetailEntity checkById(String id) {
        return Optional.ofNullable(findById(id))
                .orElseThrow(() -> new AppException(CustomMessage.SETTLE_DETAIL_NOT_FOUND));
    }


    /**
     * 新增结算单明细
     *
     * @param entity 结算单明细
     * @return 结算单明细
     */
    @Override
    public SettleDetailEntity add(SettleDetailEntity entity) {

        return settleDetailRepository.saveAndFlush(entity);
    }

    /**
     * 编辑结算单明细
     *
     * @param entity 结算单明细
     * @return 结算单明细
     */
    @Override
    public SettleDetailEntity edit(SettleDetailEntity entity) {

        return settleDetailRepository.saveAndFlush(entity);

    }

    /**
     * 删除（逻辑删除）
     *
     * @param id 结算单明细Id
     * @return 删除是否成功
     */
    @Override
    public boolean delete(String id) {
        //删除标记
        settleDetailRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 创建查询条件
     *
     * @param settleDetailQuery 查询条件
     * @return 查询条件
     */
    private Specification<SettleDetailEntity> createSpecification(SettleDetailQuery settleDetailQuery) {
        return new Specification<SettleDetailEntity>() {

            @Override
            public Predicate toPredicate(Root<SettleDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (!isEmpty(settleDetailQuery.getOrderNo())) {
                    predicate.add(cb.like(root.get("orderNo").as(String.class), settleDetailQuery.getOrderNo() ));
                }

                if (!isEmpty(settleDetailQuery.getSettleNo())) {
                    predicate.add(cb.equal(root.get("settleNo").as(String.class), settleDetailQuery.getSettleNo()));
                }

                if (!isEmpty(settleDetailQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), settleDetailQuery.getCorpNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }


}

