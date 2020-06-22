package cn.backend.service.vin;

import cn.backend.model.primary.vin.VinEntity;
import cn.backend.model.primary.vin.VinQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
public interface IVinService extends IBaseService<VinEntity, VinQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    VinEntity findByNo(String no);

    /**
     * 根据编码查找
     *
     * @param code
     * @return
     */
    VinEntity findByCode(String code);

    /**
     * 删除根据创建时间查询出的数据
     *
     * @param vinQuery
     * @return
     */
    boolean delete(VinQuery vinQuery);

    /**
     * 获取不送桩原因
     * @return
     */
    List<String> getRemark();

}

