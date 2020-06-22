drop table if exists m_work_follow;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_work_follow
(
   FOLLOW_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   FOLLOW_NO                varchar(32) comment '流程编号',
   FOLLOW_CODE             varchar(32) comment '流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废',
   FOLLOW_NAME             varchar(32) comment '流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (FOLLOW_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '流程订单';
