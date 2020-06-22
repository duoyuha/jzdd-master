package cn.backend.controller.scrminfo;

import cn.backend.controller.BaseController;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.zdwl.common.jsonfilter.CustomResult;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Controller
@RequestMapping(value = "/case")
@Api(description = "SCRM接口")
@Slf4j
public class CaseController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IScrmInfoService _scrmInfoService;

    /**
     * 保存
     *
     * @param scrmInfo
     * @return
     */
    @ApiOperation(value = "新增或更新", notes = "新增或更新")
    @ApiImplicitParam(name = "scrmInfoEntity", value = "实体scrmInfoEntity", required = true, dataType = "ScrmInfoEntity")
    @CustomResult
    @PostMapping("/afterorder")
    public ScrmInfoEntity saveOrUpdateCase(@RequestBody ScrmInfoEntity scrmInfo) {
        String json = JSON.toJSONString(scrmInfo);
        log.warn("==========咨询，投诉新增========入参={}",json);
        ScrmInfoEntity scrm = _scrmInfoService.saveOrUpdateCase(scrmInfo);
        return scrm;
    }



}

