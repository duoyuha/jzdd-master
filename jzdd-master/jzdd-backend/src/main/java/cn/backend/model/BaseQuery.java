package cn.backend.model;

import cn.zdwl.common.validgroup.PageGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 通用查询父类
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //如果前台参数传入过多也不会有影响
public class BaseQuery {

    /**
     * 每页大小
     */
   // @NotNull(message = "{message.CustomMessage.PageSizeNotEmpty.body}", groups = {PageGroup.class})
    @Min(value = 1 ,message = "{message.CustomMessage.PageSizeUnderSize.body}" ,groups = {PageGroup.class})
    private Integer pageSize=10;

    /**
     * 分页页码
     */
 //   @NotNull(message = "{message.CustomMessage.PageNumberNotEmpty.body}", groups = {PageGroup.class})
    private Integer pageNum=0;

    /**
     * 排序
     * 非json格式下的排序
     * 逗号分隔 + 正序 -倒序
     * + createdWhen
     */
    private String sort;

    /**
     * 排序
     */
    private List<SortEntity> sortEntityList;

}
