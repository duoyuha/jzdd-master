package cn.backend.controller.user;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.config.swagger.ParamType;
import cn.backend.controller.BaseController;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.user.UserQuery;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.jsonfilter.JSONFilter;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.PageGroup;
import cn.zdwl.common.validgroup.RetrieveGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/user")
@Api(description = "系统用户对应的接口")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    /**
     * 商户Id
     */
    @Value("${WeixinConfig.url}")
    private String url;

    /**
     * 商户Id
     */
    @Value("${WeixinConfig.appId}")
    private String appId;

    /**
     * 商户Id
     */
    @Value("${WeixinConfig.appSecret}")
    private String appSecret;

    private Gson gson = new Gson();

    /**
     * 列表，分页查询功能
     *
     * @return
     */
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
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
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "userName",
                    value = "用户名",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })
    public Page<UserEntity> getList(@Validated({PageGroup.class}) UserQuery userQuery) {
        // userQuery.setCorpNo(getCorpNo());
        return userService.findList(userQuery);
    }

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @CustomResult
    @PostMapping()
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParam(name = "user", value = "实体user", required = true, dataType = "UserEntity")
    public UserEntity addUser(@Validated({CreateGroup.class}) @RequestBody UserEntity user) {
        // user.setCorpNo(getCorpNo());
        return userService.add(user);
    }

    /**
     * 服务商成员保存
     *
     * @param user
     * @return
     */
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @CustomResult
    @PostMapping("/supplier/add")
    @ApiOperation(value = "服务商成员保存", notes = "服务商成员保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片附件", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "user", value = "实体user", required = true, dataType = "UserEntity")
    })
    public UserEntity addSupplierUser(@RequestParam(value = "file", required = false) MultipartFile file,
                                      UserEntity user) {
        //上传附件
        if (file != null) {
            UploadFile uploadFile = getFile(file, SysConstant.Supplier.CONFIG_FOLDER_NAME);
            user.setCertificateFile(uploadFile.getViewPath());
        }
        return userService.add(user);
    }

    /**
     * 服务商成员编辑
     *
     * @param user
     * @return
     */
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @CustomResult
    @PostMapping("/supplier/edit")
    @ApiOperation(value = "服务商成员编辑", notes = "服务商成员编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片附件", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "user", value = "实体user", required = true, dataType = "UserEntity")
    })
    public UserEntity editSupplierUser(@RequestParam(value = "file", required = false) MultipartFile file,
                                       UserEntity user) {
        //上传附件
        if (file != null) {
            UploadFile uploadFile = getFile(file, SysConstant.Supplier.CONFIG_FOLDER_NAME);
            user.setCertificateFile(uploadFile.getViewPath());
        }
        return userService.edit(user);
    }

    /**
     * 修改(Put 全局修改,Patch 局部修改)
     *
     * @param user
     * @return
     */
    @CustomResult
    @PutMapping()
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "user", value = "实体user", required = true, dataType = "UserEntity")
    public UserEntity editUser(@Validated({UpdateGroup.class}) @RequestBody UserEntity user) {
        return userService.edit(user);
    }

    /**
     * 密码重置(Put 全局修改,Patch 局部修改)
     *
     * @param user
     * @return
     */
    @CustomResult
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @PatchMapping()
    @ApiOperation(value = "密码重置", notes = "密码重置")
    @ApiImplicitParam(name = "user", value = "实体user", required = true, dataType = "UserEntity")
    public UserEntity userPwdReset(@Validated({RetrieveGroup.class}) @RequestBody UserEntity user) {
        return userService.userPwdReset(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @CustomResult
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public Boolean deleteUser(@PathVariable String id) {
        return userService.delete(id);
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    @CustomResult
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public UserEntity findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    @CustomResult
    @JSONFilter(type = UserEntity.class, filter = "userPassword")
    @GetMapping(value = "/info/current")
    @ApiOperation(value = "获取当前用户", notes = "获取当前用户")
    public UserEntity getCurrentUser() {
        return getUserEntity();
    }


    /**
     * Service接口
     * 获取用户
     *
     * @param userAcc
     * @return
     */
    @PostMapping(value = "/getUserByAcc")
    @CustomResult(false)
    public UserEntity getUserByAcc(@RequestParam("userAcc") String userAcc, @RequestParam("corpNo") String corpNo) {
        return userService.findByUserAccAndCorp(userAcc, corpNo);
    }


}

