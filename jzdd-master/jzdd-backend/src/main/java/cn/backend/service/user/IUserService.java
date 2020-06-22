package cn.backend.service.user;


import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.user.UserQuery;
import cn.backend.service.IBaseService;

public interface IUserService extends IBaseService<UserEntity, UserQuery> {

    /**
     * 根据编号查询
     *
     * @param userNo
     * @return
     */
    UserEntity findByNo(String userNo);

    /**
     * 根据编号校验
     *
     * @param userNo
     * @return
     */
    UserEntity checkByNo(String userNo);

    /**
     * 根据账号和公司查询
     *
     * @param userAcc
     * @param corpNo
     * @return
     */
    UserEntity findByUserAccAndCorp(String userAcc, String corpNo);

    /**
     * 重置密码
     *
     * @param userEntity
     * @return
     */
    UserEntity userPwdReset(UserEntity userEntity);

    /**
     * 根据用户名和公司查询
     *
     * @param userName
     * @param corpNo
     * @return
     */
    UserEntity findByUserNameAndCorp(String userName, String corpNo);

    /**
     * 根据账号查询（弃用）
     *
     * @param userAcc
     * @return
     */
    UserEntity findByUserAcc(String userAcc);


}