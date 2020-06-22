package cn.backend.controller.menu;

import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.menu.MenuEntity;
import cn.backend.model.primary.menu.MenuQuery;
import cn.backend.service.menu.IMenuService;
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

import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Controller
@RequestMapping(value = "/menu")
@Api(description = "菜单对应的接口")
public class MenuController extends BaseController {

    //protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IMenuService menuService;

    /**
     * 列表，分页查询功能
     *
     * @param menuQuery
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



    })
    //@ApiImplicitParam(name = "menuQuery", value = "查询实体menuQuery", required = true, dataType = "MenuQuery")
    public Page<MenuEntity> getList(@Validated({PageGroup.class}) MenuQuery menuQuery) {
        return menuService.findList(menuQuery);
    }

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "menuEntity", value = "实体menuEntity", required = true, dataType = "MenuEntity")
    @CustomResult
    @PostMapping()
    public MenuEntity saveMenu(@Validated({CreateGroup.class}) @RequestBody MenuEntity menu) {
        return menuService.add(menu);
    }

    /**
     * 修改
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "menuEntity", value = "实体menuEntity", required = true, dataType = "MenuEntity")
    @CustomResult
    @PutMapping()
    public MenuEntity updateMenu(@Validated({UpdateGroup.class}) @RequestBody MenuEntity menu) {
        return menuService.edit(menu);
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
    public Boolean deleteMenu(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return menuService.delete(id);
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
    public MenuEntity findMenuById(@PathVariable String id) {
        return menuService.findById(id);
    }


    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId
     * @return
     */

    @CustomResult
    @GetMapping(value = "/role/{roleId}")
    @ApiOperation(value = "根据角色ID获取菜单列表", notes = "根据角色ID获取菜单列表")
    @ApiImplicitParam(name = "roleId", value = "roleId", required = true, dataType = "String", paramType = "path")
    public List<MenuEntity> findMenuByRoleId(@PathVariable String roleId) {
        return menuService.findByRoleEntity(roleId);
    }
}

