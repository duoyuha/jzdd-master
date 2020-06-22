package cn.backend.service.viewdashboardcalendar;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.viewdashboardcalendar.ViewDashbordCalendarRepository;
import cn.backend.model.primary.viewdashboardcalendar.ViewDashbordCalendarEntity;
import cn.backend.model.primary.viewdashboardcalendar.ViewDashbordCalendarQuery;
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
@Service(value = "viewDashbordCalendarService")
public class ViewDashbordCalendarService extends BaseService implements IViewDashbordCalendarService {

    @Resource
    private ViewDashbordCalendarRepository viewDashbordCalendarRepository;

    /**
     * 分页查询
     *
     * @param viewDashbordCalendarQuery
     * @return
     */
    @Override
    public Page<ViewDashbordCalendarEntity> findList(ViewDashbordCalendarQuery viewDashbordCalendarQuery) {
        Sort sort = buildSort(viewDashbordCalendarQuery.getSort());
        Pageable pageable = new PageRequest(viewDashbordCalendarQuery.getPageNum(), viewDashbordCalendarQuery.getPageSize(), sort);
        Page<ViewDashbordCalendarEntity> entityPage = viewDashbordCalendarRepository.findAll(createSpecification(viewDashbordCalendarQuery), pageable);
        return entityPage;

    }

    /**
     * 列表查询
     *
     * @param viewDashbordCalendarQuery
     * @return
     */
    @Override
    public List<ViewDashbordCalendarEntity> findAll(ViewDashbordCalendarQuery viewDashbordCalendarQuery) {
        Sort sort = buildSort(viewDashbordCalendarQuery.getSort());
        List<ViewDashbordCalendarEntity> entityList = viewDashbordCalendarRepository.findAll(createSpecification(viewDashbordCalendarQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param viewDashbordCalendarQuery
     * @return
     */
    private Specification createSpecification(ViewDashbordCalendarQuery viewDashbordCalendarQuery) {
        return new Specification<ViewDashbordCalendarEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewDashbordCalendarEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }


}

