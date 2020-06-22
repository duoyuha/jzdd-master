package cn.backend.dao.primary.config;

import cn.backend.model.primary.config.ConfigEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Repository("configRepository")
public interface ConfigRepository extends JpaRepository<ConfigEntity, String>, JpaSpecificationExecutor<ConfigEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from ConfigEntity e where e.isDel=:deleteStatus")
    List<ConfigEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ConfigEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from ConfigEntity e where e.isDel=:deleteStatus and e.id=:id")
    ConfigEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据type获取
     *
     * @param configType
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from ConfigEntity e where e.isDel=:deleteStatus and e.configType=:configType and e.configOrg=:configOrg")
    List<ConfigEntity> findByType(@Param("deleteStatus") String deleteStatus, @Param("configType") String configType, @Param("configOrg") String configOrg);

    /**
     * 根据type和value查找
     *
     * @param configType
     * @param configValue
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select e from ConfigEntity e where e.isDel=:deleteStatus and e.configType=:configType and e.configVal=:configValue and e.configOrg=:configOrg")
    ConfigEntity findByTypeVal(@Param("deleteStatus") String deleteStatus, @Param("configType") String configType, @Param("configValue") String configValue, @Param("configOrg") String configOrg);

    /**
     * 根据type和name查找
     *
     * @param configType
     * @param configName
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select e from ConfigEntity e where e.configType = :configType and e.configName= :configName and e.configOrg=:configOrg")
    ConfigEntity findByTypeName(@Param("configType") String configType, @Param("configName") String configName, @Param("configOrg") String configOrg);


    /**
     * 根据类型配置类型获取配置列表
     *
     * @param deleteStatus
     * @param codeTypes
     * @return
     */
    // @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(nativeQuery = true, value = "select * from s_config e where e.IS_DEL=?1 and e.CONFIG_TYPE in (?2)  ORDER  BY  e.CONFIG_TYPE,e.CONFIG_DISP")
    List<ConfigEntity> findByConfigTypes(String deleteStatus, String[] codeTypes);


    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from ConfigEntity e where e.isDel=:deleteStatus and e.configType=:configType and e.configVal=:configVal and e.configOrg=:configOrg")
    ConfigEntity findByTypeAndCode(@Param("deleteStatus") String deleteStatus, @Param("configType") String configType, @Param("configVal") String configVal, @Param("configOrg") String configOrg);


}

