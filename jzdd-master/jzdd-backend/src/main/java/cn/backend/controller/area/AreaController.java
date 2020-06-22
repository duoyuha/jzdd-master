package cn.backend.controller.area;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.area.AreaEntity;
import cn.backend.model.primary.area.AreaQuery;
import cn.backend.service.area.IAreaService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/area")
@Api(description = "地区对应的接口")
public class AreaController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IAreaService _areaService;

    /**
     * 列表，分页查询功能
     *
     * @param areaQuery
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
    public Page<AreaEntity> getList(@Validated({PageGroup.class}) AreaQuery areaQuery) {
        return _areaService.findList(areaQuery);
    }

    /**
     * 列表，不分页查询功能
     *
     * @param areaQuery
     * @return
     */
    @CustomResult
    @GetMapping("/listall")
    @ApiOperation(value = "全部列表", notes = "获取全部列表")
    @ApiImplicitParam(name = "areaQuery", value = "实体areaQuery", required = true, dataType = "AreaQuery")
    public List<AreaEntity> listAll(AreaQuery areaQuery) {
        return _areaService.findAll(areaQuery);
    }

    /**
     * 保存
     *
     * @param area
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "areaEntity", value = "实体areaEntity", required = true, dataType = "AreaEntity")
    @CustomResult
    @PostMapping()
    public AreaEntity saveArea(@Validated({CreateGroup.class}) @RequestBody AreaEntity area) {
        return _areaService.add(area);
    }

    /**
     * 修改
     *
     * @param area
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "areaEntity", value = "实体areaEntity", required = true, dataType = "AreaEntity")
    @CustomResult
    @PutMapping()
    public AreaEntity updateArea(@Validated({UpdateGroup.class}) @RequestBody AreaEntity area) {
        return _areaService.edit(area);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    @CustomResult
    @DeleteMapping(value = "/{id}")
    public Boolean deleteArea(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _areaService.delete(id);
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
    public AreaEntity findAreaById(@PathVariable String id) {
        return _areaService.findById(id);
    }


}

