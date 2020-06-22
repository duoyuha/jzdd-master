package cn.backend.service.contractdetail;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.contractdetail.ContractDetailRepository;
import cn.backend.model.primary.contractarea.ContractareaEntity;
import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.backend.model.primary.contractdetail.ContractDetailQuery;
import cn.backend.model.primary.contractfee.ContractfeeEntity;
import cn.backend.service.BaseService;
import cn.backend.service.contractarea.IContractareaService;
import cn.backend.service.contractfee.IContractfeeService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.CustomCollectionUtils;
import cn.zdwl.common.util.IdUtils;
import com.alibaba.fastjson.JSONArray;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/07/09
 */
@Service(value = "contractDetailService")
public class ContractDetailService extends BaseService implements IContractDetailService {

    @Resource
    private ContractDetailRepository contractDetailRepository;

    @Autowired
    private IContractareaService contractareaService;

    @Autowired
    private IContractfeeService contractfeeService;

    /**
     * 分页查询
     *
     * @param contractDetailQuery
     * @return
     */
    @Override
    public Page<ContractDetailEntity> findList(ContractDetailQuery contractDetailQuery) {
        Sort sort = buildSort(contractDetailQuery.getSort());
        Pageable pageable = new PageRequest(contractDetailQuery.getPageNum(), contractDetailQuery.getPageSize(), sort);
        Page<ContractDetailEntity> entityPage = contractDetailRepository.findAll(createSpecification(contractDetailQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param contractDetailQuery
     * @return
     */
    @Override
    public List<ContractDetailEntity> findAll(ContractDetailQuery contractDetailQuery) {
        Sort sort = buildSort(contractDetailQuery.getSort());
        List<ContractDetailEntity> entityList = contractDetailRepository.findAll(createSpecification(contractDetailQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param contractDetailQuery
     * @return
     */
    private Specification createSpecification(ContractDetailQuery contractDetailQuery) {
        return new Specification<ContractDetailEntity>() {

            @Override
            public Predicate toPredicate(Root<ContractDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //车型
                if (!StringUtils.isEmpty(contractDetailQuery.getVehicleNo())) {
                    predicate.add(cb.like(root.get("vehicleNos").as(String.class), "%" + contractDetailQuery.getVehicleNo() + "%"));
                }

                //充电桩
                if (!StringUtils.isEmpty(contractDetailQuery.getPileNo())) {
                    predicate.add(cb.like(root.get("pileNos").as(String.class), "%" + contractDetailQuery.getPileNo() + "%"));
                }

                //公司
                if (!isEmpty(contractDetailQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), contractDetailQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
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
    @Transactional
    public ContractDetailEntity add(ContractDetailEntity entity) {
        //明细编码
        entity.setDetailNo(SysConstant.Contract.PREFIX_NUMBER_DETAIL + IdUtils.getGenerateNumber());
        contractDetailRepository.saveAndFlush(entity);
        //区域
        List<ContractareaEntity> areaList = entity.getContractareaEntityList();
        for (ContractareaEntity item : areaList) {
            item.setCorpNo(entity.getCorpNo());
            item.setDetailNo(entity.getDetailNo());
        }
        contractareaService.saveBatch(areaList);
        //费用
        for (ContractfeeEntity contractfeeEntity : entity.getContractfeeEntityList()) {
            contractfeeEntity.setCorpNo(entity.getCorpNo());
            contractfeeEntity.setDetailNo(entity.getDetailNo());
            contractfeeService.add(contractfeeEntity);
        }
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ContractDetailEntity edit(ContractDetailEntity entity) {
        contractDetailRepository.saveAndFlush(entity);
        //区域信息
        //关系是否变化，变化才处理
        // Set<ContractareaEntity> areaList = entity.getContractareaEntitySet();
        // if (!CollectionUtils.isEmpty(areaList)) {
        //     //原绑定的
        //     Set<ContractareaEntity> originalAreas = contractareaService.findByNo(entity.getDetailNo());
        //     //不一样才做处理
        //     if (!originalAreas.equals(areaList)) {
        //         Set<ContractareaEntity> addSet = (Set<ContractareaEntity>) CustomCollectionUtils.subtract(areaList, originalAreas);
        //         Set<ContractareaEntity> deleteSet = (Set<ContractareaEntity>) CustomCollectionUtils.subtract(originalAreas, areaList);
        //         //删除操作
        //         if (deleteSet.size() > 0) {
        //             contractareaService.deleteBySet(deleteSet);
        //         }
        //         //新增操作
        //         if (addSet.size() > 0) {
        //             for (ContractareaEntity item : addSet) {
        //                 item.setCorpNo(entity.getCorpNo());
        //                 item.setDetailNo(entity.getDetailNo());
        //             }
        //             contractareaService.saveBatch(addSet);
        //         }
        //     }
        // }
        //20190828 baimin 先删除旧的，再增加新的
        List<ContractareaEntity> oldContractareaEntityList = contractareaService.findByNo(entity.getDetailNo());
        Set<String> areaIds = oldContractareaEntityList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        contractareaService.deleteBatch(areaIds);

        //费用
        // for (ContractfeeEntity contractfeeEntity : entity.getContractfeeEntityList()) {
        //     contractfeeService.editOrSave(contractfeeEntity);
        // }
        //20190828 baimin 先删除旧的，再增加新的
        List<ContractfeeEntity> oldContractfeeEntityList = contractfeeService.findByNo(entity.getDetailNo());
        Set<String> feeIds = oldContractfeeEntityList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        contractfeeService.deleteBatch(feeIds);

        List<ContractareaEntity> contractareaEntityList = entity.getContractareaEntityList();
        for(ContractareaEntity item : contractareaEntityList){
            item.setDetailNo(entity.getDetailNo());
            item.setCorpNo(entity.getCorpNo());
        }
        contractareaService.saveBatch(contractareaEntityList);

        List<ContractfeeEntity> contractfeeEntityList = entity.getContractfeeEntityList();
        for(ContractfeeEntity item : contractfeeEntityList){
            item.setDetailNo(entity.getDetailNo());
            item.setCorpNo(entity.getCorpNo());
        }
        contractfeeService.addBatch(contractfeeEntityList);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean delete(String id) {
        ContractDetailEntity entity = contractDetailRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        List<ContractareaEntity> contractareaEntityList = contractareaService.findByNo(entity.getDetailNo());
        Set<String> ids = contractareaEntityList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        contractareaService.deleteBatch(ids);
        List<ContractfeeEntity> feeList = contractfeeService.findByNo(entity.getDetailNo());
        Set<String> feeIds = feeList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        for (String feeId : feeIds) {
            contractfeeService.delete(feeId);
        }
        //删除标记
        contractDetailRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ContractDetailEntity findById(String id) {
        ContractDetailEntity entity = contractDetailRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public ContractDetailEntity checkById(String id) {
        ContractDetailEntity entity = contractDetailRepository.findById(SysConstant.IS_DEL_N, id);
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
    public List<ContractDetailEntity> findByNo(String no) {
        List<ContractDetailEntity> list = contractDetailRepository.findByNo(SysConstant.IS_DEL_N, no);
        return list;
    }


}

