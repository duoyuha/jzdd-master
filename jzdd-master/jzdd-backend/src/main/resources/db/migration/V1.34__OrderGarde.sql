drop table if exists t_order_grade;

/*==============================================================*/
/* Table: 满意度回访                                                */
/*==============================================================*/
create table t_order_grade
(
   GRADE_ID             varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO              varchar(32) comment '公司编号',
   GRADE_NO             varchar(32) comment '满意度编码',
   ORDER_NO             varchar(32) comment '订单编码',
   ORDER_TYPE             varchar(32) comment '订单类型：01 安装订单 02 配送订单',
   ORDER_TYPE_NAME             varchar(32) comment '订单类型 01 安装订单 02 配送订单',
   ONTIME_FLG        varchar(8) comment '安装工人是否按时到达 Y 是 N 否',
   ILLEGAL_CHARGE_FLG        varchar(8) comment '是否乱收费 Y 是 N 否',
   TRAIN_FLG                varchar(8) comment '是否乱收费 Y 是 N 否',
   SUPPLIER_GRADE  int comment '安装服务商评分 1非常不满意 2不满意 3一般 4满意 5非常满意',
   DEALER_GRADE       int comment '经销商评分 1非常不满意 2不满意 3一般 4满意 5非常满意',
   REMARK               varchar(256)  comment '意见和建议',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (GRADE_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '满意度';
