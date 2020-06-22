drop table if exists t_order_step;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table t_order_step
(
   STEP_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   FOLLOW_NO                varchar(32) comment '流程编号',
   ORDER_NO             varchar(32) comment '订单编码',
   CORP_NO                varchar(32) comment '公司编号',
   FOLLOW_CODE             varchar(32) comment '流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废',
   FOLLOW_NAME             varchar(32) comment '流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废',
   DETAIL_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   DETAIL_NO                varchar(32) comment '流程明细编号',
   STEP_SEQ             int default  0 comment '业务流程号',
   FOLLOW_SEQ             int default  0 comment '步骤号',
   STEP_NAME                varchar(64) comment '步骤名称',
   CANCLE_FLG               varchar(8) comment '是否允许作废:Y 是 N 否',
   CHECK_FLG               varchar(8) comment '是否审核:Y 是 N 否',
   PARENT_SEQ             int default  0 comment '上级步骤',
   ROLLBACK_FLG               varchar(8) comment '是否回退:Y 是 N 否',
   POSITION_CODES             varchar(256) comment '审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员 ',
   ROLLBACK_SEQ             int default  0 comment '回退步骤',
   CURRENT_STEP_FLG               varchar(8) default  'N' comment '是否当前步骤:Y 是 N 否',
   STEP_TIME             datetime comment '操作时间',
   STEP_USER_NO              varchar(32)  comment '操作人',
   STEP_USER_NAME              varchar(64)  comment '操作人',
   STEP_REMARK           text comment '操作日志',
   RESULT_CODE             varchar(8) comment '审批结果：01 同意 02 拒绝',
   RESULT_NAME             varchar(32) comment '审批结果 01 同意 02 拒绝',
   TIMEOUT_STATUS          varchar(8) comment '超时状态：01 超时 02 未超时',
   TIMEOUT_STATUS_NAME     varchar(256) comment '超时状态：01 超时 02 未超时',
   BASE_TIME               datetime comment '基准时间',
   TIMEOUT_FLG               varchar(8) comment '是否检查超时:Y 是 N 否',
   TIMEOUT_RADIO             int comment '超时时间，单位：小时',
   OUTTIME                 int comment '超时时间',
   RESULT_DESC             varchar(256) comment '审批备注',
   STEP_MATRIX           varchar(64) comment '步骤矩阵',
   CONFIRM_STEP               varchar(8) comment '是否服务商确认:Y 是 N 否',
   LAST_STEP               varchar(8) comment '是否最后一步:Y 最后一步 N 不是最后一步',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (STEP_ID),
    KEY `IDX02_ORDER_NO` (`ORDER_NO`) USING BTREE,
  KEY `IDX02_FOLLOW_NO` (`FOLLOW_NO`) USING BTREE,
  KEY `IDX02_CURRENT_STEP_FLG` (`CURRENT_STEP_FLG`) USING BTREE,
  KEY `IDX02_LAST_STEP` (`LAST_STEP`) USING BTREE,
  KEY `IDX02_TIMEOUT_FLG` (`TIMEOUT_FLG`) USING BTREE,
  KEY `IDX02_IS_DEL` (`IS_DEL`) USING BTREE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '流程订单明细';
