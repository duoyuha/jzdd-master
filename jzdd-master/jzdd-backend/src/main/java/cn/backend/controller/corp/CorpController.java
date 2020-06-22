package cn.backend.controller.corp;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.corp.CorpQuery;
import cn.backend.service.corp.ICorpService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Controller
@RequestMapping(value = "/corp")
@Api(description = "公司对应的接口")
public class CorpController extends BaseController{



    @Autowired
    private ICorpService corpService;

    /**
     * 列表，分页查询功能
     *
     * @param corpQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageSize",
                    value = "查询当前分页条数",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.INT)
            , @ApiImplicitParam(
            name = "pageNum",
            value = "查询分页索引从0开始",
            required = false,
            paramType = ParamType.QUERY,
            dataType = DataType.INT)
            , @ApiImplicitParam(
            name = "corpName",
            value = "公司名称作为查询条件",
            required = false,
            paramType = ParamType.QUERY,
            dataType = DataType.STRING)
    })
    public Page<CorpEntity> getList(@Validated({PageGroup.class}) CorpQuery corpQuery){
        return corpService.findList(corpQuery);
    }

    /**
     * 保存
     *
     * @param corp
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "corpEntity", value = "实体corpEntity", required = true, dataType = "CorpEntity")
    @CustomResult
    @PostMapping()
    public CorpEntity saveCorp(@Validated({CreateGroup.class}) @RequestBody CorpEntity corp) {
        return corpService.add(corp);
    }

    /**
     * 修改
     *
     * @param corp
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "corpEntity", value = "实体corpEntity", required = true, dataType = "CorpEntity")
    @CustomResult
    @PutMapping()
    public CorpEntity updateCorp(@Validated({UpdateGroup.class}) @RequestBody CorpEntity corp) {
        return corpService.edit(corp);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @DeleteMapping(value = "/{id}")
    public Boolean deleteCorp(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return corpService.delete(id);
    }

    /**
     * 根据id获取详情
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @GetMapping(value = "/{id}")
    public CorpEntity findCorpById(@PathVariable String id) {
        return corpService.findById(id);
    }


}

