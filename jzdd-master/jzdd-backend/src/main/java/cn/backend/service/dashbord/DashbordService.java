package cn.backend.service.dashbord;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.primary.dashbord.DashbordQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoEntity;
import cn.backend.model.primary.viewdashboardtodo.ViewDashbordTodoQuery;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.orderauto.IOrderAutoService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.user.IUserService;
import cn.backend.service.vehicle.IVehicleService;
import cn.backend.service.viewdashboardtodo.IViewDashbordTodoService;
import cn.zdwl.common.context.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "dashbordService")
public class DashbordService implements IDashbordService {


    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IViewDashbordTodoService viewDashbordTodoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IOrderAutoService orderAutoService;

    /**
     * 待办事项
     *
     * @param dashbordQuery
     * @return
     */
    @Override
    public Page<ViewDashbordTodoEntity> todoList(DashbordQuery dashbordQuery) {
        // OrderStepQuery orderStepQuery = new OrderStepQuery();
        // orderStepQuery.setCurrentStepFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
        // orderStepQuery.setPositionCode(dashbordQuery.getPositionCode());
        // orderStepQuery.setPageNum(dashbordQuery.getPageNum());
        // orderStepQuery.setPageSize(dashbordQuery.getPageSize());
        // orderStepQuery.setCorpNo(dashbordQuery.getCorpNo());
        // orderStepQuery.setFollowCodes(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL+","+SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        // Page<OrderStepEntity> orderStepEntityList = orderStepService.findFinishList(orderStepQuery);

        ViewDashbordTodoQuery viewDashbordTodoQuery = new ViewDashbordTodoQuery();
        viewDashbordTodoQuery.setSupplierNo(dashbordQuery.getSupplierNo());
        viewDashbordTodoQuery.setUserType(dashbordQuery.getPositionCode());
        viewDashbordTodoQuery.setPageNum(dashbordQuery.getPageNum());
        viewDashbordTodoQuery.setPageSize(dashbordQuery.getPageSize());
        viewDashbordTodoQuery.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        Page<ViewDashbordTodoEntity> viewDashbordTodoEntityPage = viewDashbordTodoService.findList(viewDashbordTodoQuery);
        return viewDashbordTodoEntityPage;
    }

}

