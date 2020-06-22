package cn.backend.service.inspection;


import cn.backend.model.primary.inspection.InspectionEntity;
import cn.backend.model.primary.inspection.InspectionQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/29
 */
public interface IInspectionService extends IBaseService<InspectionEntity, InspectionQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    InspectionEntity findByNo(String no);

    //List<InspectionDto> findPage(InspectionQuery inspectionQuery);
    /**
     *新建
     *
     * @param entity
     * @return
     */
    List<InspectionEntity> addList(InspectionEntity entity);


    /**
     * 添加已完成的订单信息
     *
     * @param entity
     * @return
     */
    List<InspectionEntity> addList2(InspectionEntity entity);


    /**
     * 定时任务
     */
    void timer();

}

