drop table if exists s_dept;

/*==============================================================*/
/* Table: S_DEPT                                                */
/*==============================================================*/
create table s_dept
(
   DEPT_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   DEPT_NO              varchar(32) comment '部门编号',
   DEPT_NAME            varchar(64) comment '部门名称',
   CORP_NO              varchar(32) comment '公司编号',
   DEPT_CHARGER_NO      varchar(32) comment '部门负责人',
   DEPT_CHARGER_NAME    varchar(32) comment '部门负责人名称',
   DEPT_CHARGER_PHONE   varchar(32) comment '部门负责人电话',
   DEPT_CHARGER_MAIL    varchar(64) comment '部门负责人邮箱',
   DEPT_STAFF_NUM       int comment '部门人数',
   DEPT_TYPE_NOS        varchar(32) comment '部门类型 01 抢修 02 生产',
   DEPT_TYPE_NAMES      varchar(256) comment '部门类型名称 01 抢修 02 生产',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (DEPT_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '部门';
