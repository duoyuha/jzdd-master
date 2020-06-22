package cn.backend.service.dept;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.dept.DeptRepository;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.dept.DeptEntity;
import cn.backend.model.primary.dept.DeptQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.CompareObj;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xsj
 * @date 2019/03/14
 */
@Service(value = "deptService")
public class DeptService extends BaseService implements IDeptService {

    @Resource
    private DeptRepository deptRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param deptQuery
     * @return
     */
    @Override
    public Page<DeptEntity> findList(DeptQuery deptQuery) {
        Sort sort = buildSort(deptQuery.getSort());
        Pageable pageable = new PageRequest(deptQuery.getPageNum(), deptQuery.getPageSize(), sort);
        Page<DeptEntity> entityPage = deptRepository.findAll(createSpecification(deptQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param deptQuery
     * @return
     */
    @Override
    public List<DeptEntity> findAll(DeptQuery deptQuery) {
        Sort sort = buildSort(deptQuery.getSort());
        List<DeptEntity> entityList = deptRepository.findAll(createSpecification(deptQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param deptQuery
     * @return
     */
    private Specification createSpecification(DeptQuery deptQuery) {
        return new Specification<DeptEntity>() {

            @Override
            public Predicate toPredicate(Root<DeptEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //部门名称
                if (!StringUtils.isEmpty(deptQuery.getDeptName())) {
                    predicate.add(cb.like(root.get("deptName").as(String.class), "%" + deptQuery.getDeptName() + "%"));
                }

                //部门类别
                if (!StringUtils.isEmpty(deptQuery.getDeptTypeNos())) {
                    predicate.add(cb.like(root.get("deptTypeNos").as(String.class), "%" + deptQuery.getDeptTypeNos() + "%"));
                }

                //公司
                if (!StringUtils.isEmpty(deptQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), deptQuery.getCorpNo()));
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
    public DeptEntity add(DeptEntity entity) {
        //名称重复校验
        DeptEntity checkEntity = deptRepository.findByName(SysConstant.IS_DEL_N, entity.getDeptName());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }

        //entity.setDeptTypeNames(configService.getConfigCodeVal(SysConstant.Dept.PREFIX_DEPT_TYPE, entity.getDeptTypeNos()));
        //设置编码
        entity.setDeptNo(SysConstant.Dept.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        deptRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public DeptEntity edit(DeptEntity entity) {
        //重复校验
        DeptEntity oldEntity = this.findById(entity.getId());
        if (!oldEntity.getDeptName().equals(entity.getDeptName())) {
            DeptEntity checkEntity = deptRepository.findByName(SysConstant.IS_DEL_N, entity.getDeptName());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }

        CompareObj<DeptEntity> compareObj= new CompareObj<>();
        String s1= compareObj.compare(entity,oldEntity);
        System.out.println(s1);

       // entity.setDeptTypeNames(configService.getConfigCodeVal(SysConstant.Dept.PREFIX_DEPT_TYPE, entity.getDeptTypeNos()));
        configService.convert(entity);
        deptRepository.saveAndFlush(entity);
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
        DeptEntity entity = deptRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        deptRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public DeptEntity findById(String id) {
        DeptEntity entity = deptRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public DeptEntity checkById(String id) {
        DeptEntity entity = deptRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    @Override
    public DeptEntity findByNo(String no) {
        DeptEntity entity = deptRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    @Override
    public DeptEntity findByName(String name) {
        return deptRepository.findByName(SysConstant.IS_DEL_N, name);
    }


}

