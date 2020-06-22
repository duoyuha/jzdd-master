package cn.backend.service.viewdashbordrank;

import cn.backend.dao.primary.viewdashbordrank.ViewDashbordRankRepository;
import cn.backend.model.primary.viewdashbordrank.ViewDashbordRankEntity;
import cn.backend.model.primary.viewdashbordrank.ViewDashbordRankQuery;
import cn.backend.service.BaseService;
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

/**
 * @author sunkw
 * @date 2019/07/23
 */
@Service(value = "viewDashbordRankService")
public class ViewDashbordRankService extends BaseService implements IViewDashbordRankService {

    @Resource
    private ViewDashbordRankRepository viewDashbordRankRepository;

    /**
     * 分页查询
     *
     * @param viewDashbordRankQuery
     * @return
     */
    @Override
    public Page<ViewDashbordRankEntity> findList(ViewDashbordRankQuery viewDashbordRankQuery) {
        Sort sort = buildSort(viewDashbordRankQuery.getSort());
        Pageable pageable = new PageRequest(viewDashbordRankQuery.getPageNum(), viewDashbordRankQuery.getPageSize(), sort);
        Page<ViewDashbordRankEntity> entityPage = viewDashbordRankRepository.findAll(createSpecification(viewDashbordRankQuery), pageable);
        return entityPage;

    }

    /**
     * 列表查询
     *
     * @param viewDashbordRankQuery
     * @return
     */
    @Override
    public List<ViewDashbordRankEntity> findAll(ViewDashbordRankQuery viewDashbordRankQuery) {
        Sort sort = buildSort(viewDashbordRankQuery.getSort());
        List<ViewDashbordRankEntity> entityList = viewDashbordRankRepository.findAll(createSpecification(viewDashbordRankQuery), sort);
        return entityList;
    }

    @Override
    public long count(ViewDashbordRankQuery viewDashbordRankQuery) {
        long count = viewDashbordRankRepository.count(createSpecification(viewDashbordRankQuery));
        return count;
    }

    @Override
    public List<ViewDashbordRankEntity> findBySupplierNoAndCarVehicle(String supplierNo, String carVehicle) {
       return viewDashbordRankRepository.findBySupplierNoAndCarVehicle(supplierNo,carVehicle);
    }

    /**
     * 创建查询条件
     *
     * @param viewDashbordRankQuery
     * @return
     */
    private Specification createSpecification(ViewDashbordRankQuery viewDashbordRankQuery) {
        return new Specification<ViewDashbordRankEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewDashbordRankEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();


                if (!isEmpty(viewDashbordRankQuery.getCarVehicle())) {
                    predicate.add(cb.equal(root.get("carVehicle").as(String.class), viewDashbordRankQuery.getCarVehicle()));
                }

                if (!isEmpty(viewDashbordRankQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), viewDashbordRankQuery.getSupplierNo()));
                }

                if (!isEmpty(viewDashbordRankQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), viewDashbordRankQuery.getFinishFlg()));
                }


//                //筛选已删除
//                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));


                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }


}

