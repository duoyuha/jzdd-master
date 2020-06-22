drop table if exists s_config;

/*==============================================================*/
/* Table: S_CONFIG                                              */
/*==============================================================*/
create table s_config
(
   CONFIG_ID            varchar(100) not null comment '本实体记录的唯一标识',
   CONFIG_TYPE          varchar(256) comment '代码分类标识',
   CONFIG_TYPE_NAME     varchar(256) comment '代码名称',
   CONFIG_ORG           varchar(32) comment '代码所属单位',
   CONFIG_VAL           varchar(256) comment '代码值',
   CONFIG_NAME          varchar(256) comment '代码值的名称',
   CONFIG_DISP          numeric(5) comment '代码显示顺序号',
   CONFIG_CONT1         varchar(256) comment '代码的内容1',
   CONFIG_CONT2         varchar(256) comment '代码的内容2',
   CONFIG_CONT3         varchar(256) comment '代码的内容3',
   CONFIG_CONT4         varchar(256) comment '代码的内容4',
   CONFIG_CONT5         varchar(256) comment '代码的内容5',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (CONFIG_ID),
  KEY `IDX10_CONFIG_TYPE` (`CONFIG_TYPE`) USING BTREE,
  KEY `IDX10_CONFIG_ORG` (`CONFIG_ORG`) USING BTREE,
  KEY `IDX10_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT '系统参数配置';
