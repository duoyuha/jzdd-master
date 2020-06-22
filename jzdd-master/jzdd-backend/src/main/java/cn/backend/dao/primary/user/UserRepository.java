package cn.backend.dao.primary.user;

import cn.backend.dao.IBaseDao;
import cn.backend.model.primary.user.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;

@Repository("userRepository")
public interface UserRepository extends IBaseDao<UserEntity, String> {

    /**
     * 根据ID查找
     *
     * @param userId
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from UserEntity e where e.isDel=:deleteStatus and e.userId=:userId")
    UserEntity findById(@Param("deleteStatus") String deleteStatus, @Param("userId") String userId);

    /**
     * 密码修改
     *
     * @param userId
     * @param userPwd
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity e set e.userPassword =:userPwd where e.userId =:userId")
    int userPwdReset(@Param("userId") String userId, @Param("userPwd") String userPwd);

    /**
     * 根据账号和corp获取获取
     *
     * @param userAcc
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from UserEntity e where e.isDel=:deleteStatus and e.userAcc =:userAcc and e.corpNo=:corpNo")
    UserEntity findByUserAccAndCorp(@Param("deleteStatus") String deleteStatus, @Param("userAcc") String userAcc, @Param("corpNo") String corpNo);

    /**
     * 根据用户名和corp获取获取
     *
     * @param userName
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from UserEntity e where e.isDel=:deleteStatus and e.userName =:userName and e.corpNo=:corpNo")
    UserEntity findByUserNameAndCorp(@Param("deleteStatus") String deleteStatus, @Param("userName") String userName, @Param("corpNo") String corpNo);

    /**
     * 根据账号获取
     *
     * @param deleteStatus
     * @param userAcc
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from UserEntity e where e.isDel =:deleteStatus and e.userAcc=:userAcc")
    UserEntity findByUserAcc(@Param("deleteStatus") String deleteStatus, @Param("userAcc") String userAcc);

    /**
     * 根据编号获取
     *
     * @param deleteStatus
     * @param userNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from UserEntity e where e.isDel =:deleteStatus and e.userNo=:userNo")
    UserEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("userNo") String userNo);


}

