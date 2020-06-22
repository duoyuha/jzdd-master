package cn.backend.service.viewdashbordrank;

import cn.backend.model.primary.viewdashbordrank.ViewDashbordRankEntity;
import cn.backend.model.primary.viewdashbordrank.ViewDashbordRankQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
public interface IViewDashbordRankService {

    Page<ViewDashbordRankEntity> findList(ViewDashbordRankQuery viewDashbordRankQuery);

    List<ViewDashbordRankEntity> findAll(ViewDashbordRankQuery viewDashbordRankQuery);

    long count(ViewDashbordRankQuery viewDashbordRankQuery);

    List<ViewDashbordRankEntity> findBySupplierNoAndCarVehicle(String supplierNo, String carVehicle);
}

