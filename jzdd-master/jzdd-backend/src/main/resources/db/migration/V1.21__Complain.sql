drop table if exists t_complain_order;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_complain_order
(
   COMPLAIN_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   COMPLAIN_NO             varchar(32) comment '订单编码',
   ORDER_NO             varchar(32) comment '安装订单编码',
   CONTACT_CUST_TIME         datetime comment '联系客户时间',
   SUPPLIER_NO       varchar(32)  comment '服务商编码',
   SUPPLIER_NAME       varchar(32)  comment '服务商名称',
   SCRM_CONTENT_DESC       varchar(256)  comment 'SCRM投诉描述',
   RECEIVE_STATUS       varchar(32)  comment '签收状态：01未签收，02签收，03拒绝',
   RECEIVE_STATUS_NAME       varchar(32)  comment '签收状态：01未签收，02签收，03拒绝',
   COMPLAIN_DESC       varchar(256)  comment '投诉内容 01乱收费 02态度差 03其他',
   COMPLAIN_DESC_NAME       varchar(256)  comment '投诉内容 01乱收费 02态度差 03其他',
   COMPLAIN_REMARK       varchar(256)  comment '投诉备注',
   COMPLAIN_TIME      datetime comment '投诉时间 yyyymmdd hhmmss',
   RECEIVE_TIME      datetime comment '签收时间/拒绝时间 yyyymmdd hhmmss',
   SOLUTION_TIME      datetime comment '解决时间 yyyymmdd hhmmss',
   RECEIVE_DESC       varchar(256)  comment '签收备注或拒绝备注',
   SOLUTION_DESC       varchar(256)  comment '解决方案',
   SOLUTION_SUPPLIER_NO       varchar(32)  comment '解决服务商编码',
   SOLUTION_SUPPLIER_NAME       varchar(256)  comment '解决服务商名称',
   SOLUTION_REMARK       varchar(256)  comment '备注',
   CAR_OWNER           varchar(64) comment '车主名称',
   CAR_OWNER_PHONE      varchar(64) comment '车主电话',
   VIN_NO      varchar(32) comment '车架号',
   SCRM_NO                varchar(32) comment '编号',
   FINISH_FLG      varchar(8)  default 'N' comment '是否完成 Y 是 N 否',
   FINISH_TIME     datetime comment '安装完成时间',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (COMPLAIN_ID),
  KEY `IDX11_CORP_NO` (`CORP_NO`) USING BTREE,
  KEY `IDX11_ORDER_NO` (`ORDER_NO`) USING BTREE,
  KEY `IDX11_SUPPLIER_NO` (`SUPPLIER_NO`) USING BTREE,
  KEY `IDX11_SUPPLIER_NAME` (`SUPPLIER_NAME`) USING BTREE,
  KEY `IDX11_VIN_NO` (`VIN_NO`) USING BTREE,
  KEY `IDX11_FINISH_FLG` (`FINISH_FLG`) USING BTREE,
  KEY `IDX11_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '投诉订单';
