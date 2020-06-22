package cn.backend.service.dashbord;

import cn.backend.model.primary.dashbord.DashbordQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoQuery;
import org.springframework.data.domain.Page;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IDashbordService {

    /**
     * 待办事项
     *
     * @param dashbordQuery
     * @return
     */
    Page<ViewDashbordTodoEntity> todoList(DashbordQuery dashbordQuery);





}

