package cn.backend.service.ordercar;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.ordercar.OrderCarRepository;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.ordercar.OrderCarQuery;
import cn.backend.model.primary.ordercar.OrderCarResponseEntity;
import cn.backend.service.BaseService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "orderCarService")
public class OrderCarService extends BaseService implements IOrderCarService {

    @Resource
    private OrderCarRepository orderCarRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${OrderCar.url}")
    private String getInfoUrl;

    /**
     * 分页查询
     *
     * @param orderCarQuery
     * @return
     */
    @Override
    public Page<OrderCarEntity> findList(OrderCarQuery orderCarQuery) {
        Sort sort = buildSort(orderCarQuery.getSort());
        Pageable pageable = new PageRequest(orderCarQuery.getPageNum(), orderCarQuery.getPageSize(), sort);
        Page<OrderCarEntity> entityPage = orderCarRepository.findAll(createSpecification(orderCarQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderCarQuery
     * @return
     */
    @Override
    public List<OrderCarEntity> findAll(OrderCarQuery orderCarQuery) {
        Sort sort = buildSort(orderCarQuery.getSort());
        List<OrderCarEntity> entityList = orderCarRepository.findAll(createSpecification(orderCarQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderCarQuery
     * @return
     */
    private Specification createSpecification(OrderCarQuery orderCarQuery) {
        return new Specification<OrderCarEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderCarEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if(!isEmpty(orderCarQuery.getVin())) {
                    predicate.add(cb.equal(root.get("vinNo").as(String.class), orderCarQuery.getVin()));
                }

                if (!isEmpty(orderCarQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), orderCarQuery.getCorpNo()));
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
    public OrderCarEntity add(OrderCarEntity entity) {
        orderCarRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderCarEntity edit(OrderCarEntity entity) {
        orderCarRepository.saveAndFlush(entity);
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
        OrderCarEntity entity = orderCarRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderCarRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderCarEntity findById(String id) {
        OrderCarEntity entity = orderCarRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderCarEntity checkById(String id) {
        OrderCarEntity entity = orderCarRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderCarEntity findByNo(String no) {
        OrderCarEntity entity = orderCarRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    // /**
    //  * 根据vinNo查找
    //  *
    //  * @param vinNo
    //  * @return
    //  */
    // @Override
    // public OrderCarEntity findByVinNo(String vinNo) {
    //     OrderCarEntity entity = orderCarRepository.findByVinNo(SysConstant.IS_DEL_N, vinNo);
    //     return entity;
    // }

    @Override
    public List<OrderCarEntity> findListByVinNo(String vinNo) {
        List<OrderCarEntity> entitys = orderCarRepository.findListByVinNo(SysConstant.IS_DEL_N, vinNo);
        return entitys;
    }

    @Override
    public OrderCarEntity findByVinNoAndCorp(String vinNo,String corpNo){
        return orderCarRepository.findByVinNoAndCorpNo(SysConstant.IS_DEL_N, vinNo, corpNo);
    }

    /**
     * 从外部接口获取信息
     *
     * @param orderCarQuery
     * @return
     */
    @Override
    public OrderCarEntity getOrderCarInfo(OrderCarQuery orderCarQuery) {
        //调接口根据VIN获取信息
        // String url = getInfoUrl + "?vin=\"" + orderCarQuery.getVin() + "\"";
        // OrderCarResponseEntity responseEntity = restTemplate.getForObject(url, OrderCarResponseEntity.class);
        OrderCarResponseEntity responseEntity = new OrderCarResponseEntity();
        responseEntity.setId("1");
        responseEntity.setCorpNo("CORP1560154054723");
        responseEntity.setCarOwner("张媛红");
        responseEntity.setCarOwnerPhone("13000000000");
        responseEntity.setCarVehicle("欧拉R1");
        responseEntity.setVinNo("ORA_VIN"+ IdUtils.getGenerateNumber());

//        responseEntity.setVinNo("LGWECMA40KE004415");

        responseEntity.setEngineNo(IdUtils.getGenerateNumber());
      //  responseEntity.setVinNo(orderCarQuery.getVin());
        responseEntity.setDealerName("河北大正人飞越汽车销售服务有限公司");
        responseEntity.setDealerTel("18000000000");
        responseEntity.setDealerContact("张媛红");
        responseEntity.setSaleDate(new Date());
        //生成OrderCar
        OrderCarEntity orderCarEntity = new OrderCarEntity();
        orderCarEntity.setVinNo(responseEntity.getVinNo());
        orderCarEntity.setCarOwner(responseEntity.getCarOwner());
        orderCarEntity.setCarOwnerPhone(responseEntity.getCarOwnerPhone());
        orderCarEntity.setCarVehicle(responseEntity.getCarVehicle());
        orderCarEntity.setEngineNo(responseEntity.getEngineNo());
        orderCarEntity.setDealerName(responseEntity.getDealerName());
        orderCarEntity.setDealerTel(responseEntity.getDealerTel());
        orderCarEntity.setDealerContact(responseEntity.getDealerContact());
        orderCarEntity.setSaleDate(responseEntity.getSaleDate());
        orderCarEntity.setVehicleDetail("灵趣版");
        // orderCarEntity.setRemark();
        return orderCarEntity;
    }


}

