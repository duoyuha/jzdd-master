drop table if exists s_user_area;

/*==============================================================*/
/* Table: S_USER_AREA                                           */
/*==============================================================*/
create table s_user_area
(
   AREA_ID              varchar(100) not null comment '主键',
   USER_ACC             varchar(32) comment '用户账号',
   USER_NO              varchar(32) comment '用户编号',
   CORP_NO              varchar(32) comment '公司编号',
   USER_PROVINCE        varchar(32) comment '省份',
   USER_CITY            varchar(32) comment '城市',
   USER_RAGION          varchar(32) comment '区域',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (AREA_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '用户区域';
