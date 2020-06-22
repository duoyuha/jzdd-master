package cn.backend.service.rolemenu;

import cn.zdwl.common.exception.AppException;
import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.rolemenu.RoleMenuRepository;
import cn.backend.model.primary.rolemenu.RoleMenuEntity;
import cn.backend.model.primary.rolemenu.RoleMenuQuery;
import cn.backend.service.BaseService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Service(value = "roleMenuService")
public class RoleMenuService extends BaseService implements IRoleMenuService{

    @Resource
    private RoleMenuRepository roleMenuRepository;

    /**
     * 分页查询
     * @param roleMenuQuery
     * @return
     */
    @Override
    public Page<RoleMenuEntity> findList(RoleMenuQuery roleMenuQuery) {
        Sort sort=buildSort(roleMenuQuery.getSort());
        Pageable pageable = new PageRequest(roleMenuQuery.getPageNum(), roleMenuQuery.getPageSize(),sort);
        Page<RoleMenuEntity> entityPage = roleMenuRepository.findAll(createSpecification(roleMenuQuery),pageable);
        return entityPage;
    }

    /**
     * 列表查询
     * @param roleMenuQuery
     * @return
     */
    @Override
    public List<RoleMenuEntity> findAll(RoleMenuQuery roleMenuQuery) {
        Sort sort=buildSort(roleMenuQuery.getSort());
        List<RoleMenuEntity> entityList = roleMenuRepository.findAll(createSpecification(roleMenuQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param roleMenuQuery
     * @return
     */
    private Specification createSpecification(RoleMenuQuery roleMenuQuery){
        return new Specification<RoleMenuEntity>(){

           @Override
           public Predicate toPredicate(Root<RoleMenuEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicate = new ArrayList<>();

               //筛选已删除
               predicate.add(cb.equal(root.get("isDel").as(String.class),SysConstant.IS_DEL_N));

               Predicate[] p = new Predicate[predicate.size()];
               return cb.and(predicate.toArray(p));
           }

       };
    }

    /**
     * 新建
     * @param entity
     * @return
     */
    @Override
    public RoleMenuEntity add(RoleMenuEntity entity) {
        roleMenuRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    public RoleMenuEntity edit(RoleMenuEntity entity) {
        roleMenuRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        RoleMenuEntity entity=roleMenuRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        roleMenuRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public RoleMenuEntity findById(String id) {
        RoleMenuEntity entity=roleMenuRepository.findById(SysConstant.IS_DEL_N,id);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public RoleMenuEntity checkById(String id) {
        RoleMenuEntity entity=roleMenuRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }


    /**
     * 批量更新
     *
     * @param entityList
     * @return
     */
    @Override
    public List<RoleMenuEntity> save(List<RoleMenuEntity> entityList) {
        roleMenuRepository.save(entityList);
        return entityList;
    }

    /**
     * 根据角色id查询菜单ids
     *
     * @param roleId
     * @return
     */
    @Override
    public Set<String> findMenuIdsByRoleId(String roleId) {
        return roleMenuRepository.findMenuIdsByRoleId(SysConstant.IS_DEL_N, roleId);
    }

    /**
     * 批量删除
     *
     * @param deleteSet
     * @param roleId
     * @return
     */
    @Override
    public boolean deleteBySet(Set<String> deleteSet, String roleId) {
        roleMenuRepository.deleteBySet(SysConstant.IS_DEL_Y, deleteSet, roleId);
        return true;
    }



}

