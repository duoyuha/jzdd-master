package cn.backend.controller.pile;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.primary.pile.PileEntity;
import cn.backend.model.primary.pile.PileQuery;
import cn.backend.service.pile.IPileService;
import cn.zdwl.common.file.UploadFile;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * @author sunkw
 * @date 2019/07/08
 */
@Controller
@RequestMapping(value = "/pile")
@Api(description = "充电桩型号对应的接口")
public class PileController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IPileService _pileService;

    /**
     * 列表，分页查询功能
     *
     * @param pileQuery
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
                    dataType = DataType.INT),
            @ApiImplicitParam(
                    name = "pileName",
                    value = "桩型号名称",
                    required = false,
                    paramType = ParamType.QUERY,
                    dataType = DataType.STRING)
    })
    public Page<PileEntity> getList(@Validated({PageGroup.class}) PileQuery pileQuery) {
        return _pileService.findList(pileQuery);
    }

    /**
     * 保存
     *
     * @param pile
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "positive", value = "正面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "back", value = "背面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "side", value = "侧面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "instruction", value = "使用说明书", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "pileEntity", value = "实体pileEntity", required = true, dataType = "PileEntity")
    })
    @CustomResult
    @PostMapping("/add")
    public PileEntity savePile(@RequestParam(value = "positive", required = false) MultipartFile positive,
                               @RequestParam(value = "back", required = false) MultipartFile back,
                               @RequestParam(value = "side", required = false) MultipartFile side,
                               @RequestParam(value = "instruction", required = false) MultipartFile instruction,
                               @Validated({CreateGroup.class}) PileEntity pile) {
        //图片上传
        //图片上传
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(positive);
        multipartFiles.add(back);
        multipartFiles.add(side);
        multipartFiles.add(instruction);
        List<UploadFile> uploadFiles = upload(multipartFiles);
        return _pileService.addAndUpload(pile, uploadFiles);
    }

    /**
     * 修改
     *
     * @param pile
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "positive", value = "正面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "back", value = "背面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "side", value = "侧面图片", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "instruction", value = "使用说明书", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "pileEntity", value = "实体pileEntity", required = true, dataType = "PileEntity")
    })
    @CustomResult
    @PostMapping("/edit")
    public PileEntity updatePile(@RequestParam(value = "positive", required = false) MultipartFile positive,
                                 @RequestParam(value = "back", required = false) MultipartFile back,
                                 @RequestParam(value = "side", required = false) MultipartFile side,
                                 @RequestParam(value = "instruction", required = false) MultipartFile instruction,
                                 @Validated({UpdateGroup.class}) PileEntity pile) {
        //图片上传
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(positive);
        multipartFiles.add(back);
        multipartFiles.add(side);
        multipartFiles.add(instruction);
        List<UploadFile> uploadFiles = upload(multipartFiles);
        return _pileService.editAndUpload(pile, uploadFiles);
    }

    /**
     * 充电桩型号文件上传
     *
     * @param multipartFiles
     * @return
     */
    private List<UploadFile> upload(List<MultipartFile> multipartFiles) {
        List<UploadFile> uploadFiles = new ArrayList<>();
        UploadFile positiveUpload = null;
        UploadFile backUpload = null;
        UploadFile sideUpload = null;
        UploadFile instructionUpload = null;
        //正面照
        if (multipartFiles.get(SysConstant.Pile.POSITIVE_INDEX) != null) {
            positiveUpload = getFile(multipartFiles.get(SysConstant.Pile.POSITIVE_INDEX), SysConstant.Pile.POSITIVE_FOLDER_NAME);
        }
        uploadFiles.add(positiveUpload);
        //背面照
        if (multipartFiles.get(SysConstant.Pile.BACK_INDEX) != null) {
            backUpload = getFile(multipartFiles.get(SysConstant.Pile.BACK_INDEX), SysConstant.Pile.BACK_FOLDER_NAME);
        }
        uploadFiles.add(backUpload);
        //侧面照
        if (multipartFiles.get(SysConstant.Pile.SIDE_INDEX) != null) {
            sideUpload = getFile(multipartFiles.get(SysConstant.Pile.SIDE_INDEX), SysConstant.Pile.SIDE_FOLDER_NAME);
        }
        uploadFiles.add(sideUpload);
        //使用说明书
        if (multipartFiles.get(SysConstant.Pile.INSTRUCTION_INDEX) != null) {
            instructionUpload = getFile(multipartFiles.get(SysConstant.Pile.INSTRUCTION_INDEX), SysConstant.Pile.INSTRUCTION_FOLDER_NAME);
        }
        uploadFiles.add(instructionUpload);
        return uploadFiles;
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
    public Boolean deletePile(@Validated({DeleteGroup.class}) @PathVariable String id) {
        return _pileService.delete(id);
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
    public PileEntity findPileById(@PathVariable String id) {
        return _pileService.findById(id);
    }


}

