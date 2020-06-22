package cn.backend.controller.role;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.role.RoleEntity;
import cn.backend.model.primary.role.RoleQuery;
import cn.backend.service.role.IRoleService;
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
 * @author sunkw
 * @date 2019/03/13
 */
@Controller
@RequestMapping(value = "/role")
@Api(description = "角色对应的接口")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService _roleService;

    /**
     * 列表，分页查询功能
     *
     * @param roleQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "分页列表", notes = "获取分页列表")
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
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "roleName",
                    value = "角色名",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })
    public Page<RoleEntity> getList(@Validated({PageGroup.class}) RoleQuery roleQuery) {
        return _roleService.findList(roleQuery);
    }

    /**
     * 保存
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "roleEntity", value = "实体roleEntity", required = true, dataType = "RoleEntity")
    @CustomResult
    @PostMapping()
    public RoleEntity saveRole(@Validated({CreateGroup.class}) @RequestBody RoleEntity role) {
        role.setCorpNo(getCorpNo());
        return _roleService.add(role);
    }

    /**
     * 修改
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "roleEntity", value = "实体roleEntity", required = true, dataType = "RoleEntity")
    @CustomResult
    @PutMapping()
    public RoleEntity updateRole(@Validated({UpdateGroup.class}) @RequestBody RoleEntity role) {
        return _roleService.edit(role);
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
    public Boolean deleteRole(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _roleService.delete(id);
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
    public RoleEntity findRoleById(@PathVariable String id) {
        return _roleService.findByIdWithMenus(id);
    }


}

