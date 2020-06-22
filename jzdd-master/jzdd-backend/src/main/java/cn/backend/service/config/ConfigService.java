package cn.backend.service.config;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.config.ConfigRepository;
import cn.backend.model.primary.config.ConfigDto;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.config.ConfigQuery;
import cn.backend.service.BaseService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.converters.dictconvert.Dict;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Slf4j
@Service(value = "configService")
public class ConfigService extends BaseService implements IConfigService {

    @Resource
    private ConfigRepository configRepository;

    private ConfigEntity save(ConfigEntity entity) {
        return configRepository.saveAndFlush(entity);
    }

    /**
     * 分页查询
     *
     * @param configQuery 查询条件
     * @return 查询结果
     */
    @Override
    public Page<ConfigEntity> findList(ConfigQuery configQuery) {
        Sort sort = buildSort(configQuery.getSort());
        Pageable pageable = new PageRequest(configQuery.getPageNum(), configQuery.getPageSize(), sort);
        return configRepository.findAll(createSpecification(configQuery), pageable);
    }

    /**
     * 列表查询
     *
     * @param configQuery 查询条件
     * @return 查询结果
     */
    @Override
    public List<ConfigEntity> findAll(ConfigQuery configQuery) {
        Sort sort = buildSort(configQuery.getSort());
        List<ConfigEntity> list = configRepository.findAll(createSpecification(configQuery), sort);
        //是否去重
        if (SysConstant.Config.DISTINCT_Y.equals(configQuery.getDistinct())) {
            List<ConfigEntity> distinctList = list.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getConfigType()))),
                            ArrayList::new));
            return distinctList;
        }
        return list;

    }

    /**
     * 根据系统配置Id获取
     *
     * @param id 系统配置Id
     * @return 系统配置
     */
    @Override
    public ConfigEntity findById(String id) {
        return configRepository.findById(SysConstant.IS_DEL_N, id);
    }

    /**
     * 根据系统配置Id检查
     *
     * @param id 系统配置Id
     * @return 系统配置
     */
    @Override
    public ConfigEntity checkById(String id) {
        return Optional.ofNullable(findById(id))
                .orElseThrow(() -> new AppException(CustomMessage.NO_DATA_FIND));
    }

    /**
     * 根据types获取对应list
     *
     * @param configTypes 系统配置类型
     * @return 系统配置列表
     */
    @Override
    public List<ConfigDto> findByType(String configTypes, String corpNo) {
        // 多个类型之间按照逗号分隔
        String[] configTypeArray = configTypes.split(",");
        List<ConfigDto> resultList = new ArrayList<>();
        for (String configType : configTypeArray) {
            List<ConfigEntity> configEntities = configRepository.findByType(SysConstant.IS_DEL_N, configType, corpNo);
            if (!isEmpty(configEntities)) {
                ConfigDto result = new ConfigDto();
                result.setType(configType);
                result.setList(configEntities);
                resultList.add(result);
            }
        }
        return resultList;
    }

    /**
     * 根据type和codes获取
     *
     * @param configType 系统配置类型
     * @param configCode 配置值
     * @return 系统配置
     */
    @Override
    public ConfigEntity findByTypeAndCode(String configType, String configCode, String corpNo) {
        if (isEmpty(configType) || isEmpty(configCode)) {
            return null;
        }
        return configRepository.findByTypeAndCode(SysConstant.IS_DEL_N, configType, configCode, corpNo);
    }

    /**
     * 根据代码获取
     *
     * @param configType 系统配置类型
     * @param configName 配置说明
     * @return 配置值
     */
    @Override
    public String getConfigCodeName(String configType, String configName, String corpNo) {
        ConfigEntity configEntity = configRepository.findByTypeName(configType, configName, corpNo);
        if (configEntity != null) {
            return configEntity.getConfigVal();
        }
        return null;
    }

    /**
     * 根据代码获取
     *
     * @param configType  系统配置类型
     * @param configCodes 配置值
     * @return 配置说明
     */
    @Override
    public String getConfigCodeVal(String configType, String configCodes, String corpNo) {
        // 多个代码值之间以逗号隔开
        String[] configCodeArray = configCodes.split(",");
        List<String> configNames = new ArrayList<>();
        for (String configCode : configCodeArray) {
            ConfigEntity configEntity = findByTypeAndCode(configType, configCode, corpNo);
            if (!isEmpty(configEntity)) {
                configNames.add(configEntity.getConfigName());
            }
        }
        //将获取到名字通过,合并展示
        return String.join(",", configNames);
    }

    @Override
    public void convert(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Dict.class)) {
                Dict dict = field.getAnnotation(Dict.class);
                String sourceType = dict.sourceType();
                String sourceField = dict.sourceField();
                //获取code
                String dictCode = (String) ObjectUtils.getVal(object, sourceField);
                //baimin 20190812 使其支持多值查询
                if (!isEmpty(dictCode)) {
                    String[] dictCodeArray = dictCode.split(",");
                    StringBuffer dictVal = new StringBuffer();
                    for (int i = 0; i < dictCodeArray.length; i++) {
                        //设置值
                        String corpNo = BaseContextHandler.getCorpNo();
                        ConfigEntity configEntity = configRepository.findByTypeVal(SysConstant.IS_DEL_N, sourceType, dictCodeArray[i], corpNo);
                        if (configEntity != null && i == 0) {
                            dictVal.append(configEntity.getConfigName());
                        } else if (configEntity != null && i > 0) {
                            dictVal.append("," + configEntity.getConfigName());
                        }
                    }
                    ObjectUtils.setVal(object, field, dictVal.toString());
                }
            }
        }

    }

    @Override
    public void disConvert(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Dict.class)) {
                Dict dict = field.getAnnotation(Dict.class);
                String sourceType = dict.sourceType();
                String sourceField = dict.sourceField();

                String dictName = (String) ObjectUtils.getVal(object, field.getName());
                String corpNo = BaseContextHandler.getCorpNo();
                ConfigEntity configEntity = configRepository.findByTypeName(sourceType, dictName, corpNo);
                if (configEntity != null) {
                    String dictVal = configEntity.getConfigVal();
                    ObjectUtils.setVal(object, sourceField, dictVal);
                }

            }
        }
    }

    @Override
    public ConfigEntity add(ConfigEntity entity) {
        return save(entity);
    }

    @Override
    public ConfigEntity edit(ConfigEntity entity) {
        return save(entity);
    }

    @Override
    public boolean delete(String id) {
        //校验
        checkById(id);
        //删除标记
        configRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 创建查询条件
     *
     * @param configQuery 查询条件
     * @return 查询条件
     */
    private Specification<ConfigEntity> createSpecification(ConfigQuery configQuery) {
        return new Specification<ConfigEntity>() {

            @Override
            public Predicate toPredicate(Root<ConfigEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (!isEmpty(configQuery.getConfigType())) {
                    predicate.add(cb.equal(root.get("configType").as(String.class), configQuery.getConfigType()));
                }

                if (!isEmpty(configQuery.getConfigTypeName())) {
                    predicate.add(cb.equal(root.get("configTypeName").as(String.class), configQuery.getConfigTypeName()));
                }

                if (!isEmpty(configQuery.getConfigOrg())) {
                    predicate.add(cb.equal(root.get("configOrg").as(String.class), configQuery.getConfigOrg()));
                } else {
                    predicate.add(cb.equal(root.get("configOrg").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

}

