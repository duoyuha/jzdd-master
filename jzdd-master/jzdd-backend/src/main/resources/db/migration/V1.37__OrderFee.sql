drop table if exists t_order_fee;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_order_fee
(
   FEE_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   ORDER_NO             varchar(32) comment '安装订单编码',
   SETTLE_AMT       decimal(12,2)  comment '结算金额',
   SUPPLIER_NO       varchar(32)  comment '服务商编码',
   SUPPLIER_NAME       varchar(32)  comment '服务商名称',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (FEE_ID),
  KEY `IDX05_CORP_NO` (`CORP_NO`) USING BTREE,
  KEY `IDX05_ORDER_NO` (`ORDER_NO`) USING BTREE,
  KEY `IDX05_SUPPLIER_NO` (`SUPPLIER_NO`) USING BTREE,
  KEY `IDX05_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '订单金额';
