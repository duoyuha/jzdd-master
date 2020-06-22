package cn.backend.service.contract;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.contract.ContractRepository;
import cn.backend.model.primary.contract.ContractEntity;
import cn.backend.model.primary.contract.ContractQuery;
import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.backend.model.primary.contractdetail.ContractDetailQuery;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.contractarea.IContractareaService;
import cn.backend.service.contractdetail.IContractDetailService;
import cn.backend.service.contractfee.IContractfeeService;
import cn.backend.service.pile.IPileService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.vehicle.IVehicleService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Service(value = "contractService")
public class ContractService extends BaseService implements IContractService{

    @Resource
    private ContractRepository contractRepository;

    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private IContractareaService contractareaService;

    @Autowired
    private IContractfeeService contractfeeService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IPileService pileService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    @Autowired
    private ISupplierService supplierService;

    /**
     * 分页查询
     * @param contractQuery
     * @return
     */
    @Override
    public Page<ContractEntity> findList(ContractQuery contractQuery) {
        Sort sort=buildSort(contractQuery.getSort());
        Pageable pageable = new PageRequest(contractQuery.getPageNum(), contractQuery.getPageSize(),sort);
        Page<ContractEntity> entityPage = contractRepository.findAll(createSpecification(contractQuery),pageable);
        for (ContractEntity contractEntity : entityPage) {
            SupplierEntity supplierEntity=supplierService.findByNo(contractEntity.getSupplierNo());
            if (supplierEntity!=null){
                contractEntity.setSupplierName(supplierEntity.getSupplierName());
            }
        }
        return entityPage;
    }

    /**
     * 列表查询
     * @param contractQuery
     * @return
     */
    @Override
    public List<ContractEntity> findAll(ContractQuery contractQuery) {
        Sort sort=buildSort(contractQuery.getSort());
        List<ContractEntity> entityList = contractRepository.findAll(createSpecification(contractQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param contractQuery
     * @return
     */
    private Specification createSpecification(ContractQuery contractQuery){
        return new Specification<ContractEntity>(){

           @Override
           public Predicate toPredicate(Root<ContractEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicate = new ArrayList<>();

               //合同编号
               if (!isEmpty(contractQuery.getContractCode())) {
                   predicate.add(cb.like(root.get("contractCode").as(String.class), "%" + contractQuery.getContractCode() + "%"));
               }
               //
               if (!isEmpty(contractQuery.getEffectiveFlg())) {
                   Date dtNow=new Date();
                   predicate.add(cb.lessThanOrEqualTo(root.get("beginDate").as(Date.class), dtNow));

                   predicate.add(cb.greaterThanOrEqualTo(root.get("endDate").as(Date.class), dtNow));
               }

               //公司
               if (!isEmpty(contractQuery.getCorpNo())) {
                   predicate.add(cb.equal(root.get("corpNo").as(String.class), contractQuery.getCorpNo()));
               } else {
                   predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
               }

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
    public ContractEntity add(ContractEntity entity) {
        //合同编号重复校验
        // 20190910 baimin 合同号可重复
        // ContractEntity contractEntity=contractRepository.findByCode(SysConstant.IS_DEL_N,entity.getCorpNo(),entity.getContractCode());
        // if (contractEntity!=null){
        //     throw new AppException(CustomMessage.CONTRACT_CODE_EXIST);
        // }
        //合同编号
        entity.setContractNo(SysConstant.Contract.PREFIX_NUMBER+ IdUtils.getGenerateNumber());
        //附件地址
        if(entity.getContract() != null){
            entity.setContractFile(entity.getContract().getViewPath());
        }
        contractRepository.saveAndFlush(entity);
        //合同计价新增
//        if (entity.getDetailEntityList()!=null&&entity.getDetailEntityList().size()>0)
//        for (ContractDetailEntity contractDetailEntity : entity.getDetailEntityList()) {
//            //明细编码
//            contractDetailEntity.setCorpNo(entity.getCorpNo());
//            contractDetailEntity.setDetailNo(SysConstant.Contract.PREFIX_NUMBER_DETAIL+IdUtils.getGenerateNumber());
//            contractDetailEntity.setContractNo(entity.getContractNo());
//            contractDetailService.add(contractDetailEntity);
//            //区域
//            Set<ContractareaEntity> contractareaEntitySet = contractDetailEntity.getContractareaEntitySet();
//            for (ContractareaEntity item : contractareaEntitySet) {
//                item.setDetailNo(contractDetailEntity.getDetailNo());
//                item.setCorpNo(entity.getCorpNo());
//                item.setDetailNo(entity.getSupplierNo());
//                contractareaService.add(item);
//            }
//            //费用
//            for (ContractfeeEntity contractfeeEntity : contractDetailEntity.getContractfeeEntityList()) {
//                contractfeeEntity.setCorpNo(contractDetailEntity.getCorpNo());
//                contractfeeEntity.setDetailNo(contractDetailEntity.getDetailNo());
//                contractfeeService.add(contractfeeEntity);
//            }
//        }
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ContractEntity edit(ContractEntity entity) {
        // ContractEntity dbEntity = findById(entity.getId());
        //合同编号不能重复
        // if (!dbEntity.getContractCode().equals(entity.getContractCode())) {
        //     ContractEntity checkEntity = contractRepository.findByCode(SysConstant.IS_DEL_N,entity.getCorpNo(), entity.getContractCode());
        //     if (checkEntity != null) {
        //         throw new AppException(CustomMessage.CONTRACT_CODE_EXIST);
        //     }
        // }
        //附件地址
        if (entity.getContract()!=null){
            entity.setContractFile(entity.getContract().getViewPath());
            entity.setContractFileName(entity.getContract().getOriginalFileName());
        }
        contractRepository.saveAndFlush(entity);
        //合同计价新增
//        if (entity.getDetailEntityList()!=null&&entity.getDetailEntityList().size()>0)
//            for (ContractDetailEntity contractDetailEntity : entity.getDetailEntityList()) {
//                //明细编码
//                contractDetailEntity.setDetailNo(SysConstant.Contract.PREFIX_NUMBER_DETAIL+IdUtils.getGenerateNumber());
//                contractDetailEntity.setContractNo(entity.getContractNo());
//                contractDetailService.add(contractDetailEntity);
//                //区域
//                Set<ContractareaEntity> contractareaEntitySet = contractDetailEntity.getContractareaEntitySet();
//                if (!CollectionUtils.isEmpty(contractareaEntitySet)) {
//                    //原绑定的
//                    Set<ContractareaEntity> originalAreas = contractareaService.findByNo(contractDetailEntity.getDetailNo());
//                    //不一样才做处理
//                    if (!originalAreas.equals(contractareaEntitySet)) {
//                        Set<ContractareaEntity> addSet = (Set<ContractareaEntity>) CustomCollectionUtils.subtract(contractareaEntitySet, originalAreas);
//                        Set<ContractareaEntity> deleteSet = (Set<ContractareaEntity>) CustomCollectionUtils.subtract(originalAreas, contractareaEntitySet);
//                        //删除操作
//                        if (deleteSet.size() > 0) {
//                            contractareaService.deleteBySet(deleteSet);
//                        }
//                        //新增操作
//                        if (addSet.size() > 0) {
//                            for (ContractareaEntity item : addSet) {
//                                item.setCorpNo(entity.getCorpNo());
//                                item.setDetailNo(entity.getSupplierNo());
//                            }
//                            contractareaService.saveBatch(addSet);
//                        }
//                    }
//                }
//                //费用
//                for (ContractfeeEntity contractfeeEntity : contractDetailEntity.getContractfeeEntityList()) {
//                    contractfeeService.editOrSave(contractfeeEntity);
//                }
//            }
        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean delete(String id) {
        ContractEntity entity=contractRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        List<ContractDetailEntity> list=contractDetailService.findByNo(entity.getContractNo());
        // detail删除方法会处理下面数据，这里不需要
        // for (ContractDetailEntity item : list) {
        //             Set<ContractareaEntity> ids=contractareaService.findByNo(item.getDetailNo()).stream().collect(Collectors.toSet());
        //             contractareaService.deleteBySet(ids);
        //             List<ContractfeeEntity> feeList=contractfeeService.findByNo(item.getDetailNo());
        //             Set<String> feeIds=feeList.stream().map(e->e.getId()).collect(Collectors.toSet());
        //             for (String feeId : feeIds) {
        //                 contractfeeService.delete(feeId);
        //             }
        //         }
        Set<String> details=list.stream().map(e->e.getId()).collect(Collectors.toSet());
        for (String detail : details) {
            contractDetailService.delete(detail);
        }
        //删除标记
        contractRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public ContractEntity findById(String id) {
        ContractEntity entity=contractRepository.findById(SysConstant.IS_DEL_N,id);
        List<ContractDetailEntity> list=contractDetailService.findByNo(entity.getContractNo());
        for (ContractDetailEntity item : list) {
            //充电桩型号名称
            if (!StringUtils.isEmpty(item.getPileModels())) {
                //按照逗号分隔
                String[] pileModels = item.getPileModels().split(",");
                List<String> listNos = new ArrayList<>();
                for (int x = 0; x < pileModels.length; x++) {
                    if (pileService.findByNo(pileModels[x]) != null) {
                        listNos.add(pileService.findByNo(pileModels[x]).getPileName());
                    }

                }
                //将获取到的项目成员名字通过/合并展示
                item.setPilenames(StringUtils.join(listNos, "/"));
            } else {
                item.setPilenames("");
            }
            //车型名称
            if (!StringUtils.isEmpty(item.getVehicleNos())) {
                String[] carNos = item.getVehicleNos().split(",");//按照逗号分隔
                List<String> listNos = new ArrayList<>();
                for (int x = 0; x < carNos.length; x++) {
                    if (vehicleService.findByNo(carNos[x]) != null) {
                        listNos.add(vehicleService.findByNo(carNos[x]).getVehicleName());
                    }

                }
                //将获取到的项目成员名字通过/合并展示
                item.setCarsNames(StringUtils.join(listNos, "/"));
            } else {
                item.setCarsNames("");
            }
            //车型明细名称
            if (!StringUtils.isEmpty(item.getDetailNos())) {
                String[] detailNos = item.getDetailNos().split(",");//按照逗号分隔
                List<String> listNos = new ArrayList<>();
                for (int x = 0; x < detailNos.length; x++) {
                    if (vehicleDetailService.findByNo(detailNos[x]) != null) {
                        listNos.add(vehicleDetailService.findByNo(detailNos[x]).getDetailName());
                    }

                }
                //将获取到的项目成员名字通过/合并展示
                item.setVehicleDetailNames(StringUtils.join(listNos, "/"));
            } else {
                item.setVehicleDetailNames("");
            }
            item.setContractareaEntityList(contractareaService.findByNo(item.getDetailNo()));
            item.setContractfeeEntityList(contractfeeService.findByNo(item.getDetailNo()));
        }
        entity.setDetailEntityList(list);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public ContractEntity checkById(String id) {
        ContractEntity entity=contractRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
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
    public ContractEntity findByNo(String no) {
        ContractEntity entity=contractRepository.findByNo(SysConstant.IS_DEL_N,no);
        return entity;
    }

    /**
     * 根据车型和充电桩查找
     *
     * @param vehicleNo
     * @param pileNo
     * @return
     */
    @Override
    public ContractEntity findByVehicleAndPile(String vehicleNo, String pileNo){
        ContractDetailQuery contractDetailQuery = new ContractDetailQuery();
        contractDetailQuery.setVehicleNo(vehicleNo);
        contractDetailQuery.setPileNo(pileNo);
        List<ContractDetailEntity> contractDetailEntityList = contractDetailService.findAll(contractDetailQuery);
        ContractDetailEntity contractDetailEntity = contractDetailEntityList.stream().findFirst().orElse(null);
        if(contractDetailEntity == null){
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        ContractEntity getIdEntity = findByNo(contractDetailEntity.getContractNo());
        if(getIdEntity == null){
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return findById(getIdEntity.getId());

    }



}

