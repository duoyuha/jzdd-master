drop table if exists t_order_attach;

/*==============================================================*/
/* Table: T_ORDER_ATTACH                                               */
/*==============================================================*/
create table t_order_attach
(
   ATTACH_ID               varchar(100) not null comment '本实体记录的唯一标识',
   ATTACH_NO               varchar(32) comment '附件编码',
   ATTACH_TYPE             varchar(8) comment '01 安装方案 02 安装勘察其他附件 03 人桩合影 04 安装确认书 05 安装其他附件 06 配送附件 07 签收证明',
   ATTACH_PATH             varchar(256) comment '路径',
   ORDER_TYPE              varchar(32) comment '订单类型：01 安装订单 02 配送订单',
   ORDER_TYPE_NAME         varchar(256) comment '订单类型：01 安装订单 02 配送订单',
   FILE_NAME               varchar(256) comment '文件名称',
   ATTACH_DISP             int comment '附件顺序号',
   ORDER_NO                varchar(32) comment '订单编号',
   IS_DEL                  varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME         varchar(256) comment '创建者',
   CREATED_WHEN            datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME   varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN      datetime comment '更新时间',
   primary key (ATTACH_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT '订单附件';
