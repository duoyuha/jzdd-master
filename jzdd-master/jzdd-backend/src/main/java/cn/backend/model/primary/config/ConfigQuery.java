package cn.backend.model.primary.config;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
public class ConfigQuery extends BaseQuery{

    /**
     * 代码分类标识
     */
    private String configType;

    /**
     * 代码名称
     */
    private String configTypeName;

    /**
     * 代码机构
     */
    private String configOrg;

    /**
     * 是否去重
     */
    private String distinct;

}
