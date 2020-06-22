drop table if exists t_order_inspection;

/*==============================================================*/
/* Table: T_ORDER_ATTACH                                               */
/*==============================================================*/
create table t_order_inspection
(
   INSPECTION_ID               varchar(100) not null comment '本实体记录的唯一标识',
   INSPECTION_NO               varchar(32) comment '附件编码',
   ATTACH_PATH             varchar(256) comment '路径',
   FILE_NAME               varchar(32) comment '文件名称',
   ORDER_NO                varchar(32) comment '订单编号',
   INSPECTION_TIME          datetime comment '巡检时间',
   INSPECTION_PERSON        varchar(32) comment '巡检人',
   INSPECTION_DESC          varchar(256) comment '巡检描述',
   FINISH_FLG          varchar(8) comment '是否完成',
   SUPPLIER_NO       varchar(32)  comment '服务商编码',
   SUPPLIER_NAME       varchar(256)  comment '服务商名称',
   IS_DEL                  varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME         varchar(256) comment '创建者',
   CREATED_WHEN            datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME   varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN      datetime comment '更新时间',
   primary key (INSPECTION_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT '巡检表';
