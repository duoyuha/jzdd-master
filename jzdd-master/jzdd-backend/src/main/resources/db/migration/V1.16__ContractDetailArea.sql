drop table if exists m_contract_detail_area;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_contract_detail_area
(
   AREA_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   DETAIL_NO             varchar(32) comment '合同编码',
   CONTRACT_PROVINCE      varchar(32) comment '省份',
   CONTRACT_CITY          varchar(32) comment '城市',
   CONTRACT_RAGION        varchar(32) comment '区域',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (AREA_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '合同区域表';
