drop table if exists t_settle;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_settle
(
   SETTLE_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   SETTLE_NO             varchar(32) comment '订单编码',
   ORDER_NO             varchar(32) comment '订单编码',
   FINISH_FLG            varchar(8) NULL COMMENT '完成标志',
   FINISH_TIME           datetime NULL COMMENT '完成时间',
   ORDER_TYPE       varchar(8)   comment '订单类型：01 安装 02 配送',
   ORDER_TYPE_NAME    varchar(64)    comment '订单类型：01 安装 02 配送',
   SETTLE_STATUS       varchar(8) DEFAULT  '01' comment '订单状态：01 有效 02 无效',
   SETTLE_STATUS_NAME    varchar(64) DEFAULT  '有效'  comment '订单状态：01 有效 02 无效',
   TTL_AMT       decimal(12,2)  comment '结算总金额',
   SUPPLIER_NO       varchar(32)  comment '服务商编码',
   SUPPLIER_NAME       varchar(32)  comment '服务商名称',
   INVOICE_STATUS      varchar(8)  comment '开票状态：01已开票，02未开票',
   INVOICE_STATUS_NAME      varchar(64)  comment '开票状态：01已开票，02未开票',
   FIRST_VERIFY_FLG        varchar(8)  comment '初审 01 未审核 02 审核通过 03 审核拒绝',
   FIRST_VERIFY_FLG_NAME     varchar(64) comment '初审 01 未审核 02 审核通过 03 审核拒绝',
   SETTLE_FILE     varchar(256) comment '结算附件',
   FIRST_VERIFY_REMARK        varchar(256)  comment '初审备注',
   SECOND_VERIFY_FLG        varchar(8)  comment '复审 01 未审核 02 审核通过 03 审核拒绝',
   SECOND_VERIFY_FLG_NAME     varchar(64) comment '复审 01 未审核 02 审核通过 03 审核拒绝',
   SECOND_VERIFY_REMARK        varchar(256)  comment '复审备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (SETTLE_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '咨询订单';
