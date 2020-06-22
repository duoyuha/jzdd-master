package cn.backend.controller.orderstep;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.service.orderstep.IOrderStepService;
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


/**
 * @author sunkw
 * @date 2019/08/01
 */
@Controller
@RequestMapping(value = "/orderStep")
@Api(description = "订单步骤")
public class OrderStepController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IOrderStepService _orderStepService;

    /**
     * 列表，分页查询功能
     *
     * @param orderStepQuery
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
            @ApiImplicitParam(
                    name = "orderNo",
                    value = "订单编号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "followNo",
                    value = "流程编号 1安装 2配送 3作废 4结算 5售后 6咨询",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "followCode",
                    value = "流程编码 01安装 02配送 03作废 04结算 05售后 06咨询",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING),
            @ApiImplicitParam(
                    name = "followSeq",
                    value = "步骤号",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "timeoutStatus",
                    value = "超时状态",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })
    public Page<OrderStepEntity> getList(@Validated({PageGroup.class}) OrderStepQuery orderStepQuery) {
        orderStepQuery.setCorpNo(getUserEntity().getCorpNo());
        return _orderStepService.findList(orderStepQuery);
    }

    // /**
    //  * 保存
    //  *
    //  * @param orderStep
    //  * @return
    //  */
    // @ApiOperation(value = "新增", notes = "新增")
    // @ApiImplicitParam(name = "orderStepEntity", value = "实体orderStepEntity", required = true, dataType = "OrderStepEntity")
    // @CustomResult
    // @PostMapping()
    // public OrderStepEntity saveOrderStep(@Validated({CreateGroup.class}) @RequestBody OrderStepEntity orderStep) {
    //     return _orderStepService.add(orderStep);
    // }
    //
    // /**
    //  * 修改
    //  *
    //  * @param orderStep
    //  * @return
    //  */
    // @ApiOperation(value = "修改", notes = "修改")
    // @ApiImplicitParam(name = "orderStepEntity", value = "实体orderStepEntity", required = true, dataType = "OrderStepEntity")
    // @CustomResult
    // @PutMapping()
    // public OrderStepEntity updateOrderStep(@Validated({UpdateGroup.class}) @RequestBody OrderStepEntity orderStep) {
    //     return _orderStepService.edit(orderStep);
    // }
    //
    // /**
    //  * 删除
    //  * @param id
    //  * @return
    //  */
    // @ApiOperation(value = "删除", notes = "删除")
    // @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    // @CustomResult
    // @DeleteMapping(value = "/{id}")
    // public Boolean deleteOrderStep(@Validated({DeleteGroup.class}) @PathVariable String id) {
    //     return _orderStepService.delete(id);
    // }
    //
     /**
      * 根据id获取详情
      *
      * @param id
      * @return
      */
     @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
     @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
     @CustomResult
     @GetMapping(value = "/{id}")
     public OrderStepEntity findOrderStepById(@PathVariable String id) {
         return _orderStepService.findById(id);
     }


}

