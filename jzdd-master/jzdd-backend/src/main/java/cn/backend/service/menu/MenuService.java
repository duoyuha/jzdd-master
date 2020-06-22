package cn.backend.service.menu;

import cn.zdwl.common.exception.AppException;
import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.menu.MenuRepository;
import cn.backend.model.primary.menu.MenuEntity;
import cn.backend.model.primary.menu.MenuQuery;
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
@Service(value = "menuService")
public class MenuService extends BaseService implements IMenuService{

    @Resource
    private MenuRepository menuRepository;

    /**
     * 分页查询
     * @param menuQuery
     * @return
     */
    @Override
    public Page<MenuEntity> findList(MenuQuery menuQuery) {
        Sort sort=buildSort(menuQuery.getSort());
        Pageable pageable = new PageRequest(menuQuery.getPageNum(), menuQuery.getPageSize(),sort);
        Page<MenuEntity> entityPage = menuRepository.findAll(createSpecification(menuQuery),pageable);
        return entityPage;
    }

    /**
     * 列表查询
     * @param menuQuery
     * @return
     */
    @Override
    public List<MenuEntity> findAll(MenuQuery menuQuery) {
        Sort sort=buildSort(menuQuery.getSort());
        List<MenuEntity> entityList = menuRepository.findAll(createSpecification(menuQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param menuQuery
     * @return
     */
    private Specification createSpecification(MenuQuery menuQuery){
        return new Specification<MenuEntity>(){

           @Override
           public Predicate toPredicate(Root<MenuEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public MenuEntity add(MenuEntity entity) {
        menuRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    public MenuEntity edit(MenuEntity entity) {
        menuRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        MenuEntity entity=menuRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        menuRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public MenuEntity findById(String id) {
        MenuEntity entity=menuRepository.findById(SysConstant.IS_DEL_N,id);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public MenuEntity checkById(String id) {
        MenuEntity entity=menuRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }


    /**
     * 根据判断ids是否存在
     *
     * @param menuIds
     * @return
     */
    @Override
    public boolean checkMenuExistsByIds(Set<String> menuIds) {
        int count = menuRepository.getMenuCountBySet(menuIds);
        return count == menuIds.size();
    }

    /**
     * 根据角色获取菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<MenuEntity> findByRoleEntity(String roleId) {
        return menuRepository.findByRoleId(SysConstant.IS_DEL_N, roleId);
    }



}

