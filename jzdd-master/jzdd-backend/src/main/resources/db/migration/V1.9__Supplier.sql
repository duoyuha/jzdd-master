drop table if exists m_supplier;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_supplier
(
   SUPPLIER_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO              varchar(32) comment '公司编号',
   SUPPLIER_NO              varchar(32) comment '安装服务商编号',
   SUPPLIER_NAME            varchar(64) comment '安装服务商名称',
   SUPPLIER_ABBR            varchar(32) comment '安装服务商简称',
   CHARGE_PERSON        varchar(32) comment '负责人',
   CHARGE_PERSON_PHONE        varchar(32) comment '负责人联系方式',
   CHARGE_PERSON_MAIL        varchar(32) comment '负责人联系邮箱',
   CHARGE_PERSON_FAX        varchar(32) comment '负责人联系传真',
   CHARGE_PERSON_ADDR        varchar(32) comment '负责人联系传真',
   CERTIFICATE_FILE        varchar(256) comment '三证合一',
   PILE_NOS              text comment '充电桩编号',
   AUTO_ORDER_PROP      decimal(12, 2) default 0 comment '自动派单比例',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (SUPPLIER_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '安装服务商';
