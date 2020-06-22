drop table if exists s_user;

/*==============================================================*/
/* Table: S_USER                                                */
/*==============================================================*/
create table s_user
(
   USER_ID              varchar(100) not null comment '主键',
   USER_ACC             varchar(32) comment '用户账号',
   USER_NO              varchar(32) comment '用户编号',
   USER_NAME            varchar(256) comment '用户名',
   USER_PASSWORD        varchar(32) comment '密码',
   USER_MAIL            varchar(32) comment '用户邮箱',
   USER_PHONE           varchar(32) comment '用户手机号',
   CORP_NO              varchar(32) comment '公司编号',
   ROLE_ID              varchar(100) comment '角色ID',
   ROLE_NAME            varchar(64) comment '角色名称',
   DATA_CHECK           varchar(8) comment '是否为管理员:Y /N',
   USER_TYPE            varchar(32) comment '用户类型 01 充电经理 02 整车事业部经理 03 安装服务商',
   USER_TYPE_NAME       varchar(64) comment '用户类型 01 充电经理 02 整车事业部经理 03 安装服务商',
   USER_POSITION        varchar(32) comment '服务商岗位 01 业务经理 02 勘察人员 03 施工人员',
   USER_POSITION_NAME        varchar(64) comment '服务商岗位 01 业务经理 02 勘察人员 03 施工人员',
   SUPPLER_NO              varchar(32) comment '安装服务商编号',
   CERTIFICATE_FILE            varchar(256) comment '证书路径',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (USER_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '用户';
