package cn.backend.service.config;

import cn.backend.model.primary.config.ConfigDto;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.config.ConfigQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author xsj
 * @date 2019/03/13
 */
public interface IConfigService extends IBaseService<ConfigEntity, ConfigQuery> {

    /**
     * 根据code获取name
     *
     * @param object 实体
     */
    void convert(Object object);


    /**
     * 根据name获取code
     *
     * @param object 实体
     */
    void disConvert(Object object);


    /**
     * 根据types获取对应list
     *
     * @param configTypes 系统配置类型
     * @return 系统配置列表
     */
    List<ConfigDto> findByType(String configTypes, String corpNo);

    /**
     * 根据type和codes获取
     *
     * @param configType  系统配置类型
     * @param configCode 配置值
     * @return 系统配置
     */
    ConfigEntity findByTypeAndCode(String configType, String configCode, String corpNo);

    /**
     * 根据代码获取
     *
     * @param configType 系统配置类型
     * @param configCodes 配置值
     * @return 配置说明
     */
    String getConfigCodeVal(String configType, String configCodes, String corpNo);

    /**
     * 根据代码获取
     *
     * @param configType 系统配置类型
     * @param configName 配置说明
     * @return 配置值
     */
    String getConfigCodeName(String configType, String configName, String corpNo);
}

