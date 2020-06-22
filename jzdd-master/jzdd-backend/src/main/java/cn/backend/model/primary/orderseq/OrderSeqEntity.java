package cn.backend.model.primary.orderseq;

import cn.backend.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * create by keven at 2019年04月08日
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_seq")
public class OrderSeqEntity extends BaseEntity{


    /**
     * 本实体记录的唯一标识，产生规则为流水号
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @Column(name = "ID")
    private String id;
    /**
     * 公司编码
     */
    // @NotEmpty(message = "{message.CustomMessage.DocBasicSeqcorpIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    private String corpNo;

    /**
     * 订单代码
     */
    // @NotEmpty(message = "{message.CustomMessage.DocBasicSeqcorpIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE")
    private String orderType;

    /**
     * 订单名称
     */
    // @NotEmpty(message = "{message.CustomMessage.DocBasicSeqcorpIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE_NAME")
    private String orderTypeName;

    /**
     * 所在月份
     */
    //  @NotEmpty(message = "{message.CustomMessage.DocBasicSeqoccurMonthNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="OCCUR_DATE")
    private String occurDate;

    /**
     * 序列号
     */
    //  @NotNull(message = "{message.CustomMessage.DocBasicSeqdocSeqNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_SEQ")
    private Integer orderSeq;

    /**
     * 备注
     */
    //  @NotEmpty(message = "{message.CustomMessage.DocBasicSeqremarkNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    private String remark;




}
