package cn.backend.service.viewdashboardcalendar;

import cn.backend.model.primary.viewdashboardcalendar.ViewDashbordCalendarEntity;
import cn.backend.model.primary.viewdashboardcalendar.ViewDashbordCalendarQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/23
 */
public interface IViewDashbordCalendarService {

    Page<ViewDashbordCalendarEntity> findList(ViewDashbordCalendarQuery viewDashbordCalendarQuery);

    List<ViewDashbordCalendarEntity> findAll(ViewDashbordCalendarQuery viewDashbordCalendarQuery);


}

