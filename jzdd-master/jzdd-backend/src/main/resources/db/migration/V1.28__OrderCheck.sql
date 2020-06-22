drop table if exists t_order_check;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_order_check
(
   CHECK_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   FOLLOW_NO                varchar(32) comment '流程编号',
   ORDER_NO             varchar(32) comment '订单编码',
   CHECK_TIME             datetime comment '巡检时间',
   CHECK_REMARK           varchar(256) comment '巡检结果',
   CHECK_FILE_PATH           varchar(256) comment '巡检文件路径',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (CHECK_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '巡检内容';
