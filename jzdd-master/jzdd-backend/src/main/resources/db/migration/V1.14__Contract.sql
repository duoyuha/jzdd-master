drop table if exists m_contract;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_contract
(
   CONTRACT_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   SUPPLIER_NO            varchar(32) comment '安装服务商编码',
   CONTRACT_NO             varchar(32) comment '合同编码',
   CONTRACT_CODE           varchar(32) comment '合同号',
   BEGIN_DATE             date comment '合同开始日期',
   END_DATE             date comment '合同结束日期',
   SIGN_DATE             date comment '合同签订日期',
   TAX_FLG              varchar(8) comment '是否含税:Y是 N否',
   CONTRACT_FILE         varchar(256) comment '合同附件',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (CONTRACT_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '合同表';
