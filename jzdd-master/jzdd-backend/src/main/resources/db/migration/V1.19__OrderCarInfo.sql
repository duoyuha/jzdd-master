drop table if exists t_order_car;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_order_car
(
   CAR_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   ORDER_NO             varchar(32) comment '订单编码',
   CAR_OWNER           varchar(64) comment '车主名称',
   CAR_OWNER_PHONE      varchar(64) comment '车主电话',
   DEALER_NAME      varchar(64) comment '经销商名称',
   DEALER_CONTACT      varchar(32) comment '经销商联系人',
   DEALER_TEL      varchar(32) comment '经销商电话',
   CAR_VEHICLE      varchar(32) comment '车型',
   VEHICLE_DETAIL      varchar(32) comment '车型明细',
   VIN_NO      varchar(32) comment '车架号',
   ENGINE_NO      varchar(32) comment '发动机编号',
   SALE_DATE      date comment '销售日期 yyyymmdd',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (CAR_ID),
  KEY `IDX03_ORDER_NO` (`ORDER_NO`) USING BTREE,
  KEY `IDX03_CORP_NO` (`CORP_NO`) USING BTREE,
  KEY `IDX03_DEALER_NAME` (`DEALER_NAME`) USING BTREE,
  KEY `IDX03_VIN_NO` (`VIN_NO`) USING BTREE,
  KEY `IDX03_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '安装订单表';
