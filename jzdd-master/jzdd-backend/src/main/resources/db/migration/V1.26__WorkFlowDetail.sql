drop table if exists m_work_follow_detail;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_work_follow_detail
(
   DETAIL_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   DETAIL_NO                varchar(32) comment '流程明细编号',
   FOLLOW_NO                varchar(32) comment '流程编号',
   FOLLOW_CODE             varchar(32) comment '流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废',
   FOLLOW_SEQ             int default  0 comment '审批步骤',
   FOLLOW_NAME             varchar(32) comment '流程名称',
   POSITION_CODES             varchar(256) comment '审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员',
   POSITION_NAME             varchar(32) comment '审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员',
   STEP_NAME             varchar(32) comment '审批名称',
   ROLLBACK_SEQ             int default  0 comment '回退步骤',
   PARENT_SEQ             int default  0 comment '上级步骤',
   ROLLBACK_FLG               varchar(8) comment '是否回退:Y 是 N 否',
   CANCLE_FLG               varchar(8) comment '是否允许作废:Y 是 N 否',
   CHECK_FLG               varchar(8) comment '是否审核:Y 是 N 否',
   CHECK_REBUILD_FLG     varchar(8) comment '是否重新生成:Y 是 N 否',
   TIMEOUT_FLG               varchar(8) comment '是否检查超时:Y 是 N 否',
   CONFIRM_STEP               varchar(8) comment '是否服务商确认:Y 是 N 否',
   TIMEOUT_RADIO             int comment '超时时间，单位：小时',
   STEP_MATRIX           varchar(64) comment '步骤矩阵',
   LAST_STEP               varchar(8) comment '是否最后一步:Y 最后一步 N 不是最后一步',
   TIMEOUT_TYPE         varchar(8) default null comment '超时类型 01 安装签收超时 02 安装联系客户超时 03 安装勘察超时 04 安装超时',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (DETAIL_ID),
  KEY `IDX06_FOLLOW_NO` (`FOLLOW_NO`) USING BTREE,
  KEY `IDX06_CHECK_FLG` (`CHECK_FLG`) USING BTREE,
  KEY `IDX06_TIMEOUT_FLG` (`TIMEOUT_FLG`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '流程订单明细';
