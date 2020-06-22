drop table if exists s_menu;

/*==============================================================*/
/* Table: S_MENU                                                */
/*==============================================================*/
create table s_menu
(
   MENU_ID              varchar(100) not null comment '主键列',
   MENU_NAME            varchar(32) comment '菜单名',
   TARGET_URL           varchar(256) comment '路径',
   MENU_CLASS           varchar(32) comment '菜单类名',
   MENU_LEVEL           int comment '菜单层级',
   SORT                 int comment '排序',
   PARENT_ID            varchar(100) comment '父级ID',
   MENU_TYPE            varchar(8) comment '菜单类型: 01 菜单 02 按钮',
   MENU_TYPE_NAME       varchar(256) comment '菜单类型: 01 菜单 02 按钮',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (MENU_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '菜单';
