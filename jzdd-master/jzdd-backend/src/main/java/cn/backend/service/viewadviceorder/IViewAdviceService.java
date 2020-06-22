package cn.backend.service.viewadviceorder;

import cn.backend.model.primary.adviceorder.AdviceQuery;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.viewadviceorder.ViewAdviceEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.IBaseService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IViewAdviceService {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<ViewAdviceEntity> findList(AdviceQuery query);

    /**
     * 获取全部
     *
     * @param query
     * @return
     */
    List<ViewAdviceEntity> findAll(AdviceQuery query);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    ViewAdviceEntity findById(String id);

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    ViewAdviceEntity checkById(String id);


}

