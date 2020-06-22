package cn.backend.service.viewdashboardtodo;

import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/23
 */
public interface IViewDashbordTodoService {

    Page<ViewDashbordTodoEntity> findList(ViewDashbordTodoQuery viewDashbordTodoQuery);

    List<ViewDashbordTodoEntity> findAll(ViewDashbordTodoQuery viewDashbordTodoQuery);


}

