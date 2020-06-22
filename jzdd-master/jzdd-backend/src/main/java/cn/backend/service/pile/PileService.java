package cn.backend.service.pile;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.pile.PileRepository;
import cn.backend.model.primary.pile.PileEntity;
import cn.backend.model.primary.pile.PileQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Service(value = "pileService")
public class PileService extends BaseService implements IPileService {

    @Resource
    private PileRepository pileRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param pileQuery
     * @return
     */
    @Override
    public Page<PileEntity> findList(PileQuery pileQuery) {
        Sort sort = buildSort(pileQuery.getSort());
        Pageable pageable = new PageRequest(pileQuery.getPageNum(), pileQuery.getPageSize(), sort);
        Page<PileEntity> entityPage = pileRepository.findAll(createSpecification(pileQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param pileQuery
     * @return
     */
    @Override
    public List<PileEntity> findAll(PileQuery pileQuery) {
        Sort sort = buildSort(pileQuery.getSort());
        List<PileEntity> entityList = pileRepository.findAll(createSpecification(pileQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param pileQuery
     * @return
     */
    private Specification createSpecification(PileQuery pileQuery) {
        return new Specification<PileEntity>() {

            @Override
            public Predicate toPredicate(Root<PileEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //型号名称
                if (!isEmpty(pileQuery.getPileName())) {
                    predicate.add(cb.equal(root.get("pileName").as(String.class), pileQuery.getPileName()));
                }

                //型号名称
                if (!isEmpty(pileQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), pileQuery.getSupplierNo()));
                }

                //公司
                if (!isEmpty(pileQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), pileQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                if(!isEmpty(pileQuery.getPileNos())){
                    Expression<String> exp = root.<String>get("pileNo");
                    String str = pileQuery.getPileNos();
                    String[] strarr = str.split(",");
                    List<String> stringList = new ArrayList<>();
                    for (int i = 0; i < strarr.length; i++) {
                        stringList.add(strarr[i]);

                    }

                    predicate.add(exp.in(stringList));
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
    public PileEntity add(PileEntity entity) {
        //型号名称不能重复
        PileEntity checkEntity = findByNameAndCorp(entity.getPileName(), entity.getCorpNo());
        if (checkEntity != null) {
            throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
        }
        entity.setPileNo(SysConstant.Pile.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        pileRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 新建和上传
     *
     * @param entity
     * @param uploadFiles
     * @return
     */
    @Override
    public PileEntity addAndUpload(PileEntity entity, List<UploadFile> uploadFiles) {
        return add(setUpload(entity, uploadFiles));
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public PileEntity edit(PileEntity entity) {
        PileEntity dbEntity = findById(entity.getId());
        //型号名称不能重复
        if (!dbEntity.getPileName().equals(entity.getPileName())) {
            PileEntity checkEntity = findByNameAndCorp(entity.getPileName(), entity.getCorpNo());
            if (checkEntity != null) {
                throw new AppException(CustomMessage.DATA_ALREADY_EXIST);
            }
        }
        pileRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 编辑和上传
     *
     * @param entity
     * @param uploadFiles
     * @return
     */
    @Override
    public PileEntity editAndUpload(PileEntity entity, List<UploadFile> uploadFiles) {
        return edit(setUpload(entity, uploadFiles));
    }

    /**
     * 上传文件路径设置
     *
     * @param entity
     * @param uploadFiles
     * @return
     */
    private PileEntity setUpload(PileEntity entity, List<UploadFile> uploadFiles) {
        for (int i = 0; i < uploadFiles.size(); i++) {
            UploadFile uploadFile = uploadFiles.get(i);
            if (uploadFile != null) {
                switch (i) {
                    case SysConstant.Pile.POSITIVE_INDEX:
                        entity.setPilePicOne(uploadFile.getViewPath());
                        break;
                    case SysConstant.Pile.BACK_INDEX:
                        entity.setPilePicSecond(uploadFile.getViewPath());
                        break;
                    case SysConstant.Pile.SIDE_INDEX:
                        entity.setPilePicThird(uploadFile.getViewPath());
                        break;
                    case SysConstant.Pile.INSTRUCTION_INDEX:
                        entity.setPileUse(uploadFile.getViewPath());
                        break;
                }
            }
        }
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
        PileEntity entity = pileRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        pileRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public PileEntity findById(String id) {
        PileEntity entity = pileRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public PileEntity checkById(String id) {
        PileEntity entity = pileRepository.findById(SysConstant.IS_DEL_N, id);
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
    public PileEntity findByNo(String no) {
        PileEntity entity = pileRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据名称查找
     *
     * @param name
     * @param corpNo
     * @return
     */
    @Override
    public PileEntity findByNameAndCorp(String name, String corpNo) {
        PileEntity entity = pileRepository.findByNameAndCorp(SysConstant.IS_DEL_N, name, corpNo);
        return entity;
    }

}

