package cn.backend.service.role;

import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.CustomCollectionUtils;
import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.role.RoleRepository;
import cn.backend.model.primary.role.RoleEntity;
import cn.backend.model.primary.role.RoleQuery;
import cn.backend.model.primary.rolemenu.RoleMenuEntity;
import cn.backend.service.BaseService;
import cn.backend.service.menu.IMenuService;
import cn.backend.service.rolemenu.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Service(value = "roleService")
public class RoleService extends BaseService implements IRoleService{

    @Resource
    private RoleRepository roleRepository;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IMenuService menuService;

    /**
     * 分页查询
     * @param roleQuery
     * @return
     */
    @Override
    public Page<RoleEntity> findList(RoleQuery roleQuery) {
        Sort sort=buildSort(roleQuery.getSort());
        Pageable pageable = new PageRequest(roleQuery.getPageNum(), roleQuery.getPageSize(),sort);
        Page<RoleEntity> entityPage = roleRepository.findAll(createSpecification(roleQuery),pageable);
        return entityPage;
    }

    /**
     * 列表查询
     * @param roleQuery
     * @return
     */
    @Override
    public List<RoleEntity> findAll(RoleQuery roleQuery) {
        Sort sort=buildSort(roleQuery.getSort());
        List<RoleEntity> entityList = roleRepository.findAll(createSpecification(roleQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param roleQuery
     * @return
     */
    private Specification createSpecification(RoleQuery roleQuery){
        return new Specification<RoleEntity>(){

           @Override
           public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicate = new ArrayList<>();

               //角色名
               if (!StringUtils.isEmpty(roleQuery.getRoleName())) {
                   predicate.add(cb.equal(root.get("roleName").as(String.class), roleQuery.getRoleName()));
               }

               //公司
               predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));

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
    @Transactional
    public RoleEntity add(RoleEntity entity) {
        //角色重复校验
        RoleEntity checkEntity = roleRepository.findByNameAndCorp(SysConstant.IS_DEL_N, entity.getRoleName(), entity.getCorpNo());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        //先保存 用来获取id
        roleRepository.saveAndFlush(entity);
        //绑定菜单
        Set<String> roleMenus = entity.getMenuIds();
        if (!CollectionUtils.isEmpty(roleMenus)) {
            if (!menuService.checkMenuExistsByIds(roleMenus)) {
                throw new AppException(CustomMessage.MENU_SET_ERROR);
            }
            saveRoleMenu(entity.getRoleId(), roleMenus);
        }
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public RoleEntity edit(RoleEntity entity) {
        RoleEntity oldEntity = roleRepository.findById(SysConstant.IS_DEL_N, entity.getRoleId());
        if (oldEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //校验角色名
        if (!oldEntity.getRoleName().equals(entity.getRoleName())) {
            RoleEntity checkEntity = roleRepository.findByNameAndCorp(SysConstant.IS_DEL_N, entity.getRoleName(), entity.getCorpNo());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        roleRepository.saveAndFlush(entity);
        //菜单关系是否变化，变化才处理
        if (entity.getMenuIsChange() != null && !entity.getMenuIsChange().equals("")) {
            //绑定的菜单处理
            Set<String> roleMenus = entity.getMenuIds();
            if (!CollectionUtils.isEmpty(roleMenus)) {
                //原绑定的
                Set<String> originalMenuIds = roleMenuService.findMenuIdsByRoleId(entity.getRoleId());
                //不一样才做处理
                if (!originalMenuIds.equals(entity.getMenuIds())) {
                    Set<String> addSet = (Set<String>) CustomCollectionUtils.subtract(entity.getMenuIds(), originalMenuIds);
                    Set<String> deleteSet = (Set<String>) CustomCollectionUtils.subtract(originalMenuIds, entity.getMenuIds());
                    //删除操作
                    if (deleteSet.size() > 0) {
                        roleMenuService.deleteBySet(deleteSet, entity.getRoleId());
                    }
                    //新增操作
                    if (addSet.size() > 0) {
                        saveRoleMenu(entity.getRoleId(), addSet);
                    }
                }
            }
        }
        return entity;
    }

    /**
     * 中间表插入
     *
     * @param roleId
     * @param menuIds
     */
    private void saveRoleMenu(String roleId, Set<String> menuIds) {
        List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>();
        for (String menuId : menuIds) {
            if (menuId.isEmpty()) {
                continue;
            }
            RoleMenuEntity rme = new RoleMenuEntity();
            rme.setIsDel(SysConstant.IS_DEL_N);
            rme.setRoleId(roleId);
            rme.setMenuId(menuId);
            roleMenuEntityList.add(rme);
        }
        if (roleMenuEntityList.size() > 0) {
            roleMenuService.save(roleMenuEntityList);
        }
    }

    /**
     * 根据id获取，附带菜单
     *
     * @param id
     * @return
     */
    @Override
    public RoleEntity findByIdWithMenus(String id) {
        RoleEntity entity = checkById(id);
        Set<String> originalMenuIds = roleMenuService.findMenuIdsByRoleId(id);
        entity.setMenuIds(originalMenuIds);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        RoleEntity entity=roleRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        roleRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public RoleEntity findById(String id) {
        RoleEntity entity=roleRepository.findById(SysConstant.IS_DEL_N,id);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public RoleEntity checkById(String id) {
        RoleEntity entity=roleRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }




}

