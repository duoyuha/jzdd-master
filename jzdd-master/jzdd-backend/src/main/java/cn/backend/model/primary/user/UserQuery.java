package cn.backend.model.primary.user;

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
public class UserQuery extends BaseQuery{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 服务商
     */
    private String supplierNo;

    /**
     * 用户类型
     */
    private String userType;

    private String province;

    private String city;

}
