package cn.backend.controller.scrminfo;

import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoQuery;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import cn.backend.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.zdwl.common.jsonfilter.CustomResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;


/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/scrmInfo")
@Api(description = "scrmInfo")
public class ScrmInfoController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IScrmInfoService _scrmInfoService;

    /**
     * 列表，分页查询功能
     *
     * @param scrmInfoQuery
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
                        dataType = DataType.INT),
                @ApiImplicitParam(
                        name = "pageNum",
                        value = "查询分页索引从0开始",
                        required = false,
                        paramType = ParamType.QUERY,
                        dataType = DataType.INT)
        })
    public Page<ScrmInfoEntity> getList(@Validated({PageGroup.class}) ScrmInfoQuery scrmInfoQuery){
        return _scrmInfoService.findList(scrmInfoQuery);
    }

    /**
     * 保存
     *
     * @param scrmInfo
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "scrmInfoEntity", value = "实体scrmInfoEntity", required = true, dataType = "ScrmInfoEntity")
    @CustomResult
    @PostMapping()
    public ScrmInfoEntity saveScrmInfo(@Validated({CreateGroup.class}) @RequestBody ScrmInfoEntity scrmInfo) {
        return _scrmInfoService.add(scrmInfo);
    }

    /**
     * 修改
     *
     * @param scrmInfo
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "scrmInfoEntity", value = "实体scrmInfoEntity", required = true, dataType = "ScrmInfoEntity")
    @CustomResult
    @PutMapping()
    public ScrmInfoEntity updateScrmInfo(@Validated({UpdateGroup.class}) @RequestBody ScrmInfoEntity scrmInfo) {
        return _scrmInfoService.edit(scrmInfo);
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
    public Boolean deleteScrmInfo(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _scrmInfoService.delete(id);
    }

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
    public ScrmInfoEntity findScrmInfoById(@PathVariable String id) {
        return _scrmInfoService.findById(id);
    }


}

