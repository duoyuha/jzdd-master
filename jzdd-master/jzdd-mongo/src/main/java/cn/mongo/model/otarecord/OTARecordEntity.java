package cn.mongo.model.otarecord;

import cn.mongo.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * create by pengsy at 2017年12月13日
 */
@Getter
@Setter
public class OTARecordEntity extends BaseEntity {

    private String messageId;

    /**
     * 单位编码
     */
    private String corpNo;

    /**
     * 终端编号
     */
    private String tmnlNo;

    /**
     * 设备码
     */
    private String tmnlCode;

    /**
     * OTA服务的IP地址
     */
    private String otaIp;

    /**
     * TBOX的IP地址
     */
    private String tboxIp;

    /**
     * 消息类型: 01 登入 02实时信息上报 03补发信息 04 车辆登出 05 平台登入 06 平台登出  07 心跳 08 终端校时 80 查询命令 81 设置命令 82 车载终端控制命令 83  还车验证 84 召测 
     */
    private String firstMessageType;

    /**
     * 消息类型: 01 登入 02实时信息上报 03补发信息 04 车辆登出 05 平台登入 06 平台登出  07 心跳 08 终端校时 80 查询命令 81 设置命令 82 车载终端控制命令 83  还车验证 84 召测 
     */
    private String firstMessageTypeName;

    /**
     * 第一条消息消息
     */
    private String firstMessage;

    /**
     * 第一条消息消息备注
     */
    private String firstMessageRemark;

    /**
     * 消息类型:01 成功 02 失败 03 VIN 重复 FE 命令包非应答包
     */
    private String secondMessageType;

    /**
     * 消息类型:01 成功 02 失败 03 VIN 重复 FE 命令包非应答包
     */
    private String secondMessageTypeName;

    /**
     * 第二条消息消息
     */
    private String secondMessage;

    /**
     * 第二条消息消息备注
     */
    private String secondMessageRemark;

    /**
     * 
     */
    private String thirdMessageType;

    /**
     * 
     */
    private String thirdMessageTypeName;

    /**
     * 
     */
    private String thirdMessage;

    /**
     * 
     */
    private String thirdMessageRemark;

    public OTARecordEntity(String tmnlCode) {
        this.tmnlCode=tmnlCode;
    }

    public OTARecordEntity(){}
}

