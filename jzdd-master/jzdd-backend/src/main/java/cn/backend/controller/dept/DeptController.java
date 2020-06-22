package cn.backend.controller.dept;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.dept.DeptEntity;
import cn.backend.model.primary.dept.DeptQuery;
import cn.backend.service.dept.IDeptService;
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

/**
 * @author xsj
 * @date 2019/03/14
 */
@Controller
@RequestMapping(value = "/dept")
@Api(description = "部门对应的接口")
public class DeptController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IDeptService _deptService;

    /**
     * 列表，分页查询功能
     *
     * @param deptQuery
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
            name = "deptName",
            value = "部门名称作为查询条件",
            required = false,
            paramType = ParamType.QUERY,
            dataType = DataType.STRING)
    })
    public Page<DeptEntity> getList(@Validated({PageGroup.class}) DeptQuery deptQuery) {
        deptQuery.setCorpNo(getCorpNo());
        return _deptService.findList(deptQuery);
    }

    /**
     * 保存
     *
     * @param dept
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "deptEntity", value = "实体deptEntity", required = true, dataType = "DeptEntity")
    @CustomResult
    @PostMapping()
    public DeptEntity saveDept(@Validated({CreateGroup.class}) @RequestBody DeptEntity dept) {
        dept.setCorpNo(getCorpNo());
        return _deptService.add(dept);
    }

    /**
     * 修改
     *
     * @param dept
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "deptEntity", value = "实体deptEntity", required = true, dataType = "DeptEntity")
    @CustomResult
    @PutMapping()
    public DeptEntity updateDept(@Validated({UpdateGroup.class}) @RequestBody DeptEntity dept) {
        return _deptService.edit(dept);
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
    public Boolean deleteDept(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _deptService.delete(id);
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
    public DeptEntity findDeptById(@PathVariable String id) {
        return _deptService.findById(id);
    }


}

