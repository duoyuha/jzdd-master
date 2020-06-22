package cn.backend.service.userarea;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.userarea.UserAreaRepository;
import cn.backend.model.primary.userarea.UserAreaEntity;
import cn.backend.model.primary.userarea.UserAreaQuery;
import cn.zdwl.common.exception.AppException;
import cn.backend.config.message.CustomMessage;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/10/08
 */
@Service(value = "userAreaService")
public class UserAreaService extends BaseService implements IUserAreaService {

    @Resource
    private UserAreaRepository userAreaRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param userAreaQuery
     * @return
     */
    @Override
    public Page<UserAreaEntity> findList(UserAreaQuery userAreaQuery) {
        Sort sort = buildSort(userAreaQuery.getSort());
        Pageable pageable = new PageRequest(userAreaQuery.getPageNum(), userAreaQuery.getPageSize(), sort);
        Page<UserAreaEntity> entityPage = userAreaRepository.findAll(createSpecification(userAreaQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param userAreaQuery
     * @return
     */
    @Override
    public List<UserAreaEntity> findAll(UserAreaQuery userAreaQuery) {
        Sort sort = buildSort(userAreaQuery.getSort());
        List<UserAreaEntity> entityList = userAreaRepository.findAll(createSpecification(userAreaQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param userAreaQuery
     * @return
     */
    private Specification createSpecification(UserAreaQuery userAreaQuery) {
        return new Specification<UserAreaEntity>() {

            @Override
            public Predicate toPredicate(Root<UserAreaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //用户编号
                if (!isEmpty(userAreaQuery.getUserNo())) {
                    predicate.add(cb.equal(root.get("userNo").as(String.class), userAreaQuery.getUserNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 新建
     *
     * @param entity
     * @return
     */
    @Override
    public UserAreaEntity add(UserAreaEntity entity) {
        userAreaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public UserAreaEntity edit(UserAreaEntity entity) {
        userAreaRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        UserAreaEntity entity = userAreaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        userAreaRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public UserAreaEntity findById(String id) {
        UserAreaEntity entity = userAreaRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public UserAreaEntity checkById(String id) {
        UserAreaEntity entity = userAreaRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 批量保存
     *
     * @param userAreaEntityList
     * @return
     */
    @Override
    public List<UserAreaEntity> saveBatch(List<UserAreaEntity> userAreaEntityList) {
        userAreaRepository.save(userAreaEntityList);
        return userAreaEntityList;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Set<String> deleteBatch(Set<String> ids) {
        userAreaRepository.deleteBatch(SysConstant.IS_DEL_Y, ids);
        return null;
    }

    /**
     * 根据编号查找
     *
     * @param userNo
     * @return
     */
    @Override
    public List<UserAreaEntity> findByUserNo(String userNo) {
        List<UserAreaEntity> userAreaEntityList = userAreaRepository.findByUserNo(SysConstant.IS_DEL_N, userNo);
        return userAreaEntityList;
    }


}

