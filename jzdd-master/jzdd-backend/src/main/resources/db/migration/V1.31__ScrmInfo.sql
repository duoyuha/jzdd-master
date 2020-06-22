drop table if exists t_scrm_info;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_scrm_info
(
   SCRM_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CASE_ID              varchar(100)  comment 'SCRM ID',
   CORP_NO                varchar(32) comment '公司编号',
   SCRM_NO                varchar(32) comment '编号',
   CASE_STATUS              varchar(16)  comment '订单状态',
   CUST_NAME              varchar(32)  comment '车主姓名',
   CUST_TEL              varchar(32)  comment '车主电话',
   TYPE_CODE              varchar(32)  comment '工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉',
   TYPE_CODE_NAME              varchar(64)  comment '工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉',
   VIN              varchar(32)  comment 'VIN',
   VEHICLE_TYPE_NAME              varchar(16)  comment '车型名称',
   MILLEAGE              varchar(16)  comment '行驶里程',
   PLATE_NUMBER              varchar(16)  comment '车牌号',
   SUBJECT              varchar(50)  comment '主题',
   CATEGORY_1              varchar(100)  comment '一级分类',
   CATEGORY_2              varchar(100)  comment '二级分类',
   CATEGORY_3              varchar(100)  comment '三级分类',
   CONTENT_DESC              varchar(256)  comment '投诉描述',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (SCRM_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = 'SCRM同步信息';
