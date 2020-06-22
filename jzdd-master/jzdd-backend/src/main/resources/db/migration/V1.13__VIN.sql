drop table if exists m_vin;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_vin
(
   VIN_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   VIN_NO             varchar(32) comment '车架编码',
   VIN_CODE           varchar(32) comment '车架号',
   GIVE_PILE           varchar(8) comment '是否提供桩 01 提供 02 不提供',
   GIVE_PILE_NMAE       varchar(64) comment '是否提供桩 01 提供 02 不提供',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (VIN_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '车架号表';
