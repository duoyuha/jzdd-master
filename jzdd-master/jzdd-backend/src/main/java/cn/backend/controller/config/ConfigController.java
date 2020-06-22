package cn.backend.controller.config;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.config.ConfigDto;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.config.ConfigQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.service.config.IConfigService;
import cn.backend.service.orderauto.IOrderAutoService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.jsonfilter.JSONFilter;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Controller
@RequestMapping(value = "/config")
@Api(description = "系统配置对应的接口")

public class ConfigController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IConfigService configService;

    @Autowired
    private IOrderAutoService orderAutoService;

    /**
     * 列表，分页查询功能
     *
     * @param configQuery
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
    public Page<ConfigEntity> getList(@Validated({PageGroup.class}) ConfigQuery configQuery) {
        return configService.findList(configQuery);
    }

    /**
     * 列表，不分页查询功能
     *
     * @param configQuery
     * @return
     */
    @CustomResult
    @GetMapping("/listall")
    @ApiOperation(value = "全部列表", notes = "获取全部列表")
    @ApiImplicitParam(name = "configQuery", value = "实体configQuery", required = true, dataType = "ConfigQuery")
    public List<ConfigEntity> listAll(ConfigQuery configQuery) {
        return configService.findAll(configQuery);
    }

    /**
     * 保存
     *
     * @param config
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "configEntity", value = "实体configEntity", required = true, dataType = "ConfigEntity")
    @CustomResult
    @PostMapping()
    public ConfigEntity saveConfig(@Validated({CreateGroup.class}) @RequestBody ConfigEntity config) {
        return configService.add(config);
    }

    /**
     * 修改
     *
     * @param config
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "configEntity", value = "实体configEntity", required = true, dataType = "ConfigEntity")
    @CustomResult
    @PutMapping()
    public ConfigEntity updateConfig(@Validated({UpdateGroup.class}) @RequestBody ConfigEntity config) {
        return configService.edit(config);
    }

    /**
     * 自动派单配置变更
     *
     * @param configEntity
     * @return
     */
    @ApiOperation(value = "自动派单配置变更", notes = "自动派单配置变更")
    @ApiImplicitParam(name = "configEntity", value = "实体configEntity", required = true, dataType = "ConfigEntity")
    @CustomResult
    @PutMapping("/orderauto")
    public ConfigEntity orderAutoConfig(@Validated({UpdateGroup.class}) @RequestBody ConfigEntity configEntity) {
        UserEntity userEntity = getUserEntity();
        if(!StringUtils.isEmpty(configEntity.getConfigCont1()) && SysConstant.InstallOrder.CONFIG_FLG_N.equals(configEntity.getConfigCont1())){
            orderAutoService.initAutoOrder(userEntity.getCorpNo());
        }
        return configService.edit(configEntity);
    }
    /**
     * 根据types获取列表
     *
     * @param type
     * @return
     */
    @CustomResult
    @GetMapping(value = "/type/{type}/{corpNo}")
    @ApiOperation(value = "根据type获取列表", notes = "根据type获取列表")
    @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "String", paramType = "path")
    @JSONFilter(type = ConfigEntity.class, include = "configId,configType,configTypeName,configVal,configName,type,configCont1")
    public List<ConfigDto> findByType(@PathVariable String type, @PathVariable String corpNo) {
        return configService.findByType(type, corpNo);
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
    public Boolean deleteConfig(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return configService.delete(id);
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
    public ConfigEntity findConfigById(@PathVariable String id) {
        return configService.findById(id);
    }


}

