package cn.backend.model;

import cn.backend.config.swagger.DataType;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunkuiwei on 2017/11/2.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//如果前台参数传入过多也不会有影响
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = -7674269980281525370L;

    /**
     * 创建者名字
     */
    @Column(name = "CREATED_BY_NAME" ,updatable = false)
    @CreatedBy
    @ApiModelProperty(value = "创建者名字", dataType = DataType.STRING)
    protected  String createdByName;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_WHEN" ,updatable = false)
    @CreatedDate
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = DataType.LOCAL_DATE_TIME)
    protected Date createdWhen;

    /**
     * 更新者名字
     */
    @Column(name = "LAST_MODIFIED_BY_NAME")
    @LastModifiedBy
    @ApiModelProperty(value = "更新者名字", dataType = DataType.STRING)
    protected  String lastModifiedByName;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_WHEN")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", dataType = DataType.LOCAL_DATE_TIME)
    protected Date lastModifiedWhen;

    /**
     * 是否删除
     */
    @Column(name = "IS_DEL")
    @ApiModelProperty(value = "是否删除", dataType = DataType.STRING)
    protected  String isDel = "N";
}
