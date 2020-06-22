package cn.mongo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunkuiwei on 2017/11/2.
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    /**
     * 创建者名字
     */
    protected  String createdByName;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    protected Date createdWhen;

    /**
     * 更新者名字
     */
    protected  String lastModifiedByName;

    /**
     * 更新时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    protected Date lastModifiedWhen;

    /**
     * 是否删除
     */
    protected  String isDel = "N";
}
