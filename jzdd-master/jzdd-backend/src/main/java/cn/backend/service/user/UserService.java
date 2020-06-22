package cn.backend.service.user;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.user.UserRepository;
import cn.backend.model.primary.role.RoleEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.user.UserQuery;
import cn.backend.model.primary.userarea.UserAreaEntity;
import cn.backend.model.primary.userarea.UserAreaQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.role.IRoleService;
import cn.backend.service.userarea.IUserAreaService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.BeanCopyUtil;
import cn.zdwl.common.util.IdUtils;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Service(value = "userService")
public class UserService extends BaseService implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IUserAreaService userAreaService;


    @Value("${file.savepath}")
    private String sysPath;

    @Value("${file.viewpath}")
    private String readPath;

    /**
     * 分页查询
     *
     * @param userQuery
     * @return
     */
    @Override
    public Page<UserEntity> findList(UserQuery userQuery) {
        Sort sort = buildSort(userQuery.getSort());
        Pageable pageable = new PageRequest(userQuery.getPageNum(), userQuery.getPageSize(), sort);
        Page<UserEntity> entityPage = userRepository.findAll(createSpecification(userQuery), pageable);

        return entityPage;
    }

    /**
     * 创建查询条件
     *
     * @param userQuery
     * @return
     */
    private Specification<UserEntity> createSpecification(UserQuery userQuery) {
        return new Specification<UserEntity>() {

            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //用户名称
                if (!StringUtils.isEmpty(userQuery.getUserName())) {
                    predicate.add(cb.like(root.get("userName").as(String.class), "%" + userQuery.getUserName() + "%"));
                }

                //服务商
                if (!StringUtils.isEmpty(userQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), userQuery.getSupplierNo()));
                }

                //用户类型
                // if (!StringUtils.isEmpty(userQuery.getUserType())) {
                //     predicate.add(cb.like(root.get("userType").as(String.class), "%" + userQuery.getUserType() + "%"));
                // }
                if (!isEmpty(userQuery.getUserType())) {
                    String[] userTypes = userQuery.getUserType().split(",");
                    if (userTypes.length > 1) {
                        Set<String> typeList = new HashSet<>(Arrays.asList(userTypes));
                        CriteriaBuilder.In<String> in = cb.in(root.get("userType").as(String.class));
                        for (String code : typeList) {
                            in.value(code);
                        }
                        predicate.add(in);
                    } else {
                        predicate.add(cb.like(root.get("userType").as(String.class), "%" + userQuery.getUserType() + "%"));
                    }
                }

                //公司
                predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                if (!isEmpty(userQuery.getProvince())&&!isEmpty(userQuery.getCity())){
                    Join<UserEntity, UserAreaEntity> join = root.join("areas", JoinType.INNER);
                    //省份
                    if(!isEmpty(userQuery.getProvince())){
                        //Join<UserEntity, UserAreaEntity> join = root.join("areas", JoinType.INNER);
                        predicate.add(cb.equal(join.get("userProvince").as(String.class), userQuery.getProvince()));
                    }

                    //城市
                    if(!isEmpty(userQuery.getCity())){
                        //Join<UserEntity, UserAreaEntity> join = root.join("areas", JoinType.INNER);
                        predicate.add(cb.equal(join.get("userCity").as(String.class), userQuery.getCity()));
                    }
                }


                //删除标志
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                return handlePredicate(predicate, cb);
            }

        };
    }

    /**
     * 用户新增
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public UserEntity add(UserEntity entity) {
        if (StringUtils.isEmpty(entity.getCorpNo())) {
            entity.setCorpNo(BaseContextHandler.getCorpNo());
        }
        //校验账号
        if (!StringUtils.isEmpty(entity.getUserAcc())) {
            if (findByUserAccAndCorp(entity.getUserAcc(), entity.getCorpNo()) != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }

        //角色名设置
        if (!StringUtils.isEmpty(entity.getRoleId())) {
            RoleEntity roleEntity = roleService.findById(entity.getRoleId());
            entity.setRoleName(roleEntity.getRoleName());
        }

        //加密密码
        if (!StringUtils.isEmpty(entity.getUserPassword())) {
            entity.setUserPassword(DigestUtils.md5DigestAsHex((entity.getUserPassword()).getBytes()));
        }
        //生成编号
        entity.setUserNo(SysConstant.User.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        userRepository.saveAndFlush(entity);
        //20191008 用户增加区域
        if (!isEmpty(entity.getArea())) {
            List<UserAreaEntity> areaList = JSONArray.parseArray(entity.getArea(), UserAreaEntity.class);
            for (UserAreaEntity item : areaList) {
                item.setCorpNo(entity.getCorpNo());
                item.setUserAcc(entity.getUserAcc());
                item.setUserNo(entity.getUserNo());
            }
            userAreaService.saveBatch(areaList);
        }
        return entity;
    }

    /**
     * 用户编辑
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public UserEntity edit(UserEntity entity) {
        UserEntity oldEntity = this.checkById(entity.getUserId());
        BeanCopyUtil.beanCopy(entity, oldEntity);
        //校验账号
        if (!StringUtils.isEmpty(oldEntity.getUserAcc())) {
            if (!oldEntity.getUserAcc().equals(entity.getUserAcc())) {
                UserEntity checkEntity = findByUserAccAndCorp(entity.getUserAcc(), entity.getCorpNo());
                if (checkEntity != null) {
                    throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
                }
            }
        }


        //角色名设置
        if (!StringUtils.isEmpty(oldEntity.getRoleId())) {
            if (!oldEntity.getRoleId().equals(entity.getRoleId())) {
                RoleEntity roleEntity = roleService.checkById(entity.getRoleId());
                oldEntity.setRoleName(roleEntity.getRoleName());
            }
        }
        //加密密码
        if (!StringUtils.isEmpty(oldEntity.getRestPassword())) {
            oldEntity.setUserPassword(DigestUtils.md5DigestAsHex((entity.getUserPassword()).getBytes()));
        }

        //除安装服务商、安装人员、勘察人员，服务商编号应清空
        if (!StringUtils.isEmpty(entity.getUserType())){
            if (!entity.getUserType().contains(SysConstant.User.AZFWS_USER)&&
                    !entity.getUserType().contains(SysConstant.User.AZRY_USER)&&
                    !entity.getUserType().contains(SysConstant.User.KCRY_USER)){
                oldEntity.setSupplierNo("");
            }

        }
//        if (!SysConstant.User.AZFWS_USER.equals(entity.getUserType())
//                && !SysConstant.User.AZRY_USER.equals(entity.getUserType())
//                && !SysConstant.User.KCRY_USER.equals(entity.getUserType())) {
//            oldEntity.setSupplierNo("");
//        }
        configService.convert(oldEntity);
        userRepository.saveAndFlush(oldEntity);
        //区域信息
        //先删除旧的
        UserAreaQuery userAreaQuery = new UserAreaQuery();
        userAreaQuery.setUserNo(oldEntity.getUserNo());
        List<UserAreaEntity> userAreaEntityList = userAreaService.findAll(userAreaQuery);
        for (UserAreaEntity item : userAreaEntityList) {
            item.setIsDel(SysConstant.IS_DEL_Y);
        }
        userAreaService.saveBatch(userAreaEntityList);
        //增加新的
        if (!isEmpty(entity.getArea())) {
            List<UserAreaEntity> areaList = JSONArray.parseArray(entity.getArea(), UserAreaEntity.class);
            for (UserAreaEntity item : areaList) {
                item.setCorpNo(entity.getCorpNo());
                item.setUserAcc(entity.getUserAcc());
                item.setUserNo(entity.getUserNo());
            }
            userAreaService.saveBatch(areaList);
        }
        return oldEntity;
    }


    /**
     * 重置密码
     *
     * @param userEntity
     * @return
     */
    @Override
    public UserEntity userPwdReset(UserEntity userEntity) {
        //修改密码
        String newPassword = DigestUtils.md5DigestAsHex((userEntity.getRestPassword()).getBytes());
        userRepository.userPwdReset(userEntity.getUserId(), newPassword);
        return userEntity;
    }

    /**
     * 根据用户名和公司查询
     *
     * @param userName
     * @param corpNo
     * @return
     */
    @Override
    public UserEntity findByUserNameAndCorp(String userName, String corpNo) {
        UserEntity entity = userRepository.findByUserNameAndCorp(SysConstant.IS_DEL_N, userName, corpNo);
        return entity;
    }

    /**
     * 根据账号查询用户（弃用）
     *
     * @param userAcc
     * @return
     */
    @Override
    public UserEntity findByUserAcc(String userAcc) {
        UserEntity userEntity = userRepository.findByUserAcc(SysConstant.IS_DEL_N, userAcc);
        return userEntity;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        //校验
        this.checkById(id);
        //删除标记
        userRepository.delete(id);
        return true;
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public UserEntity findById(String id) {
        UserEntity entity = userRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 校验ID
     *
     * @param id
     * @return
     */
    @Override
    public UserEntity checkById(String id) {
        UserEntity entity = userRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 列表查询
     *
     * @param userQuery
     * @return
     */
    @Override
    public List<UserEntity> findAll(UserQuery userQuery) {
        List<UserEntity> userEntityList = userRepository.findAll(createSpecification(userQuery));
        return userEntityList;
    }

    /**
     * 根据编号查询
     *
     * @param userNo
     * @return
     */
    @Override
    public UserEntity findByNo(String userNo) {
        return userRepository.findByNo(SysConstant.IS_DEL_N, userNo);
    }

    /**
     * 根据编号校验
     *
     * @param userNo
     * @return
     */
    @Override
    public UserEntity checkByNo(String userNo) {
        return Optional.ofNullable(findByNo(userNo))
                .orElseThrow(() -> new AppException(CustomMessage.NO_DATA_FIND));
    }

    /**
     * 根据公司和用户名查询
     *
     * @param userAcc
     * @param corpNo
     * @return
     */
    @Override
    public UserEntity findByUserAccAndCorp(String userAcc, String corpNo) {

        // String strUserAcc = AesEncryptionUtil.aesDecode(userAcc);
        // String strCorpNo = AesEncryptionUtil.aesDecode(corpNo);

        // String strUserAcc = (userAcc);
        // String strCorpNo = (corpNo);

        // 20190715 这里不应该返回初始化的对象，gateway中有对空的判断
        // UserEntity userEntity = new UserEntity();
        // if (StringUtils.isEmpty(userAcc) || StringUtils.isEmpty(corpNo)) {
        //     return userEntity;
        // }
        UserEntity entity = userRepository.findByUserAccAndCorp(SysConstant.IS_DEL_N, userAcc, corpNo);
        // if (entity == null) {
        //     throw new AppException(CustomMessage.NO_DATA_FIND);
        // }
        return entity;
    }


}





