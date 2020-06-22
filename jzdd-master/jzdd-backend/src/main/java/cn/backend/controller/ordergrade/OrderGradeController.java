package cn.backend.controller.ordergrade;

import cn.backend.controller.BaseController;
import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import cn.backend.service.ordergrade.IOrderGradeService;
import cn.zdwl.common.jsonfilter.CustomResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author sunkw
 * @date 2019/08/19
 */
@Controller
@RequestMapping(value = "/ordergrade")
@Api(description = "满意度")
public class OrderGradeController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IOrderGradeService _orderGradeService;

//    /**
//     * 列表，分页查询功能
//     *
//     * @param orderGradeQuery
//     * @return
//     */
//    @CustomResult
//    @GetMapping()
//    @ApiOperation(value = "分页列表", notes = "获取列表")
//    @ApiImplicitParams({
//                @ApiImplicitParam(
//                        name = "pageSize",
//                        value = "查询当前分页条数",
//                        required = false,
//                        paramType = ParamType.QUERY,
//                        dataType = DataType.INT),
//                @ApiImplicitParam(
//                        name = "pageNum",
//                        value = "查询分页索引从0开始",
//                        required = false,
//                        paramType = ParamType.QUERY,
//                        dataType = DataType.INT)
//        })
//    public Page<OrderGradeEntity> getList(@Validated({PageGroup.class}) OrderGradeQuery orderGradeQuery){
//        return _orderGradeService.findList(orderGradeQuery);
//    }

    // /**
    //  * 保存
    //  *
    //  * @param orderAttach
    //  * @return
    //  */
    // @ApiOperation(value = "新增", notes = "新增")
    // @ApiImplicitParam(name = "orderAttachEntity", value = "实体orderAttachEntity", required = true, dataType = "OrderAttachEntity")
    // @CustomResult
    // @PostMapping()
    // public OrderAttachEntity saveOrderAttach(@Validated({CreateGroup.class}) @RequestBody OrderAttachEntity orderAttach) {
    //     return _orderAttachService.add(orderAttach);
    // }
    //
    // /**
    //  * 修改
    //  *
    //  * @param orderAttach
    //  * @return
    //  */
    // @ApiOperation(value = "修改", notes = "修改")
    // @ApiImplicitParam(name = "orderAttachEntity", value = "实体orderAttachEntity", required = true, dataType = "OrderAttachEntity")
    // @CustomResult
    // @PutMapping()
    // public OrderAttachEntity updateOrderAttach(@Validated({UpdateGroup.class}) @RequestBody OrderAttachEntity orderAttach) {
    //     return _orderAttachService.edit(orderAttach);
    // }

//    /**
//     * 删除
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "删除", notes = "删除")
//    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
//    @CustomResult
//    @DeleteMapping(value = "/{id}")
//    public Boolean deleteOrderAttach(@Validated({DeleteGroup.class}) @PathVariable String id) {
//        return _orderAttachService.delete(id);
//    }

//    /**
//     * 根据id获取详情
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
//    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
//    @CustomResult
//    @GetMapping(value = "/{id}")
//    public OrderAttachEntity findOrderAttachById(@PathVariable String id) {
//        return _orderAttachService.findById(id);
//    }

    @ApiOperation(value = "根据订单编号获取", notes = "根据订单编号获取")
    @ApiImplicitParam(name = "orderNo", value = "orderNo", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/findbyorderno/{orderNo}")
    public OrderGradeEntity findOrderAttachById(@PathVariable String orderNo) {
        return _orderGradeService.findByOrderNo(orderNo);
    }



}

