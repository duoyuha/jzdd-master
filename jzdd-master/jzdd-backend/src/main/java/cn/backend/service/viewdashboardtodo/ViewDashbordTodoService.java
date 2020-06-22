package cn.backend.service.viewdashboardtodo;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.viewdashboardtodo.ViewDashbordTodoRepository;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoQuery;
import cn.backend.service.BaseService;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.context.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service(value = "viewDashbordTodoService")
public class ViewDashbordTodoService extends BaseService implements IViewDashbordTodoService {

    @Resource
    private ViewDashbordTodoRepository viewDashbordTodoRepository;

    @Autowired
    private IUserService userService;

    /**
     * 分页查询
     *
     * @param viewDashbordTodoQuery
     * @return
     */
    @Override
    public Page<ViewDashbordTodoEntity> findList(ViewDashbordTodoQuery viewDashbordTodoQuery) {
        Sort sort = buildSort(viewDashbordTodoQuery.getSort());
        Pageable pageable = new PageRequest(viewDashbordTodoQuery.getPageNum(), viewDashbordTodoQuery.getPageSize(), sort);
        Page<ViewDashbordTodoEntity> entityPage = viewDashbordTodoRepository.findAll(createSpecification(viewDashbordTodoQuery), pageable);
        return entityPage;

    }

    /**
     * 列表查询
     *
     * @param viewDashbordTodoQuery
     * @return
     */
    @Override
    public List<ViewDashbordTodoEntity> findAll(ViewDashbordTodoQuery viewDashbordTodoQuery) {
        Sort sort = buildSort(viewDashbordTodoQuery.getSort());
        List<ViewDashbordTodoEntity> entityList = viewDashbordTodoRepository.findAll(createSpecification(viewDashbordTodoQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param viewDashbordTodoQuery
     * @return
     */
    private Specification createSpecification(ViewDashbordTodoQuery viewDashbordTodoQuery) {
        return new Specification<ViewDashbordTodoEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewDashbordTodoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                // UserEntity userEntity = getCurrentUser();
                // if (SysConstant.User.CDJL_USER.equals(userEntity.getUserType())
                //         || SysConstant.User.AZFWS_USER.equals(userEntity.getUserType())
                //         || SysConstant.User.AZRY_USER.equals(userEntity.getUserType())
                //         || SysConstant.User.KCRY_USER.equals(userEntity.getUserType())) {
                //
                //     predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + userEntity.getUserType() + "%"));
                // }

                //服务商
                if (!isEmpty(viewDashbordTodoQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), viewDashbordTodoQuery.getSupplierNo()));
                }

                //职位
                if (!isEmpty(viewDashbordTodoQuery.getUserType())) {
                    predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDashbordTodoQuery.getUserType() + "%"));
                }

                //公司
                if (!isEmpty(viewDashbordTodoQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), viewDashbordTodoQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //是否完成
                if(!isEmpty(viewDashbordTodoQuery.getFinishFlg())){
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), viewDashbordTodoQuery.getFinishFlg()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

}

