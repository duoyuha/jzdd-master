drop table if exists m_contract_detail_fee;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_contract_detail_fee
(
   FEE_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   DETAIL_NO             varchar(32) comment '合同编码',
   FEE_TYPE             varchar(32) comment '费用类型：01 安装订单 02 配送订单',
   FEE_TYPE_NAME             varchar(64) comment '费用类型：01 安装订单 02 配送订单',
   FEE_CODE      varchar(8) comment '费用代码',
   FEE_NAME          varchar(32) comment '费用名称',
   FEE_AMT          decimal(12,2) comment '费用金额',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (FEE_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '合同费用表';
