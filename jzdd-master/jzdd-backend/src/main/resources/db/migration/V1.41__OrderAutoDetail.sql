drop table if exists t_order_auto_detail;

/*==============================================================*/
/* Table: T_INV_HEAD                                            */
/*==============================================================*/
create table t_order_auto_detail
(
   ID                   varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   ORDER_AUTO_NO              varchar(32) comment '自动派单编码',
   SUPPLIER_NO          varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '安装服务商编码',
   ORDER_NO          varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编号',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',

   primary key (ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;
