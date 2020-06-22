package cn.backend.service.installorderview;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IInstallOrderViewService {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<ViewInstallOrderEntity> findList(InstallOrderQuery query);

    /**
     * 获取全部
     *
     * @param query
     * @return
     */
    List<ViewInstallOrderEntity> findAll(InstallOrderQuery query);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    ViewInstallOrderEntity findById(String id);

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    ViewInstallOrderEntity checkById(String id);

    ViewInstallOrderEntity findByOrderNo(String orderNo, String corpNo);

}

