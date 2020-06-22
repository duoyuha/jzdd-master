drop table if exists m_area;

/*==============================================================*/
/* Table: S_DEPT                                                */
/*==============================================================*/
create table m_area
(
   AREA_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   AREA_NO              varchar(32) comment '部门编号',
   AREA_NAME            varchar(64) comment '部门名称',
   POST_CODE      varchar(32) comment '邮政编码',
   PARENT_POST_CODE      varchar(32) comment '上级邮政编码',
   PARENT_AREA_NAME      varchar(32) comment '上级名称',
   AREA_LEVEL            varchar(8) comment '1省 2市 3区',
   AREA_ZONE            varchar(8) comment '华中 华南 华北',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (AREA_ID),
  KEY `IDX08_AREA_NAME` (`AREA_NAME`) USING BTREE,
  KEY `IDX08_POST_CODE` (`POST_CODE`) USING BTREE,
  KEY `IDX08_PARENT_POST_CODE` (`PARENT_POST_CODE`) USING BTREE,
  KEY `IDx08_AREA_LEVEL` (`AREA_LEVEL`) USING BTREE,
  KEY `IDx08_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '区域表';

