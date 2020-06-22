drop table if exists m_vehicle;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_vehicle
(
   VEHICLE_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   VEHICLE_NO             varchar(32) comment '车型编码',
   VEHICLE_NAME           varchar(32) comment '车型名称',
--    SECOND_INSTALL          varchar(32) comment '是否二次安装:Y /N',
--    SECOND_DISTRIBUTION        varchar(32) comment '是否二次配送:Y /N',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (VEHICLE_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '车型表';
