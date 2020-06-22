package cn.backend.model.primary.orderstep;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
public class OrderStepQuery extends BaseQuery{

    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 流程编号
     */
    private String followNo;

    /**
     * 流程编码
     */
    private String followCode;

    /**
     * 步骤号
     */
    private Integer followSeq;

    /**
     * 超时状态
     */
    private String timeoutStatus;


    /**
     * 是否是最新步骤
     */
    private String currentStepFlg;

    /**
     * 是否是最后步骤
     */
    private String lastStep;

    private String positionCode;

    private String stepMatrix;

    @JsonIgnore
    private  String corpNo;


    @JsonIgnore
    private String followCodes;

    private Set<String> orderNos;

}
