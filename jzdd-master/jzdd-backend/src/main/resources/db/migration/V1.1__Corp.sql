drop table if exists s_corp;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table s_corp
(
   CORP_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO              varchar(32) comment '品牌编号',
   CORP_NAME            varchar(64) comment '品牌名称',
   CHARGE_PERSON        varchar(32) comment '负责人',
   CHARGE_PERSON_PHONE        varchar(32) comment '负责人联系方式',
   CHARGE_PERSON_MAIL        varchar(32) comment '负责人联系邮箱',
   CHARGE_PERSON_FAX        varchar(32) comment '负责人联系传真',
   CHARGE_PERSON_ADDR        varchar(32) comment '负责人联系传真',

   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   CORP_BRAND        int comment '品牌',
   primary key (CORP_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '公司';
