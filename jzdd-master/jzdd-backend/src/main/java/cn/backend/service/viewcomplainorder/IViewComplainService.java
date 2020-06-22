package cn.backend.service.viewcomplainorder;

import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.viewcomplainorder.ViewComplainEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IViewComplainService {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<ViewComplainEntity> findList(ComplainOrderQuery query);

    /**
     * 获取全部
     *
     * @param query
     * @return
     */
    List<ViewComplainEntity> findAll(ComplainOrderQuery query);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    ViewComplainEntity findById(String id);

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    ViewComplainEntity checkById(String id);


}

