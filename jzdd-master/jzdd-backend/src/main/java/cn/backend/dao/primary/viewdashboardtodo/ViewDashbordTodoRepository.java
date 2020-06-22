package cn.backend.dao.primary.viewdashboardtodo;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import org.springframework.stereotype.Repository;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Repository("viewDashbordTodoRepository")
public interface ViewDashbordTodoRepository extends IBaseDao<ViewDashbordTodoEntity, String> {


}

