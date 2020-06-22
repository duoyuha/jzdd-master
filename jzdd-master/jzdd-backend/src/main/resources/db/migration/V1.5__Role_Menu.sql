drop table if exists s_role_menu;

/*==============================================================*/
/* Table: S_ROLE_MENU                                           */
/*==============================================================*/
create table s_role_menu
(
   ROLE_MENU_ID         varchar(100) not null comment '主键列',
   MENU_ID              varchar(100) comment '菜单ID',
   ROLE_ID              varchar(100) comment '角色ID',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (ROLE_MENU_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '角色菜单';
