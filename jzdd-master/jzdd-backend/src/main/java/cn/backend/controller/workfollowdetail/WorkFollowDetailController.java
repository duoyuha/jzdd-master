package cn.backend.controller.workfollowdetail;

import cn.backend.controller.BaseController;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.jsonfilter.CustomResult;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Controller
@RequestMapping(value = "/workflowdetail")
@Api(description = "超时配置对应的接口")

public class WorkFollowDetailController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    /**
     * 列表，不分页查询功能
     *
     * @param workFollowDetailQuery
     * @return
     */
    @CustomResult
    @GetMapping()
    @ApiOperation(value = "全部列表", notes = "获取全部列表")
    @ApiImplicitParam(name = "workFollowDetailQuery", value = "实体workFollowDetailQuery", required = true, dataType = "WorkFollowDetailQuery")
    public List<WorkFollowDetailEntity> listAll(WorkFollowDetailQuery workFollowDetailQuery) {
        return workFollowDetailService.findAll(workFollowDetailQuery);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParam(name = "workFollowDetailEntity", value = "实体workFollowDetailEntity", required = true, dataType = "WorkFollowDetailEntity")
    @CustomResult
    @PutMapping()
    public WorkFollowDetailEntity edit(@Validated({UpdateGroup.class}) @RequestBody WorkFollowDetailEntity entity) {
        return workFollowDetailService.edit(entity);
    }


}

