package cn.backend.model.primary.corp;

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
public class CorpQuery extends BaseQuery{

    /**
     * 公司名
     */
    private String corpName;

    /**
     * 公司编码
     */
    private String corpNo;

}
