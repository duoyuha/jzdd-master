package cn.backend.controller.cancel;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.viewcancel.ViewCancelEntity;
import cn.backend.model.primary.viewcancel.ViewCancelQuery;
import cn.backend.service.cancel.ICancelService;
import cn.backend.service.ordercancel.IOrderCancelService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.PageGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author sunkw
 * @date 2019/08/01
 */
@Controller
@RequestMapping(value = "/cancel")
@Api(description = "作废")
public class CancelController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ICancelService cancelService;

    @Autowired
    private IOrderCancelService orderCancelService;

    /**
     * 列表，分页查询功能
     *
     * @param viewCancelQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageSize",
                    value = "查询当前分页条数",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "pageNum",
                    value = "查询分页索引从0开始",
                    required = true,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
    })
    public Page<ViewCancelEntity> getList(@Validated({PageGroup.class}) ViewCancelQuery viewCancelQuery) {
        viewCancelQuery.setCorpNo(getCorpNo());
        viewCancelQuery.setPositionCode(getUserEntity().getUserType());
        //viewCancelQuery.setAllOrderFlg("");
        return cancelService.findList(viewCancelQuery);
    }

    /**
     * 根据id获取详情
     *
     * @param no
     * @return
     */
    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/{no}")
    public OrderCancelEntity findByNo(@PathVariable String no) {
        return orderCancelService.findByApplyNo(no);
    }

    /**
     * 根据id获取详情
     *
     * @param vin
     * @return
     */
    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "findbyvin/{vin}")
    public List<OrderStepEntity> findByVin(@PathVariable String vin) {
        return cancelService.findByVin(vin);
    }


}

