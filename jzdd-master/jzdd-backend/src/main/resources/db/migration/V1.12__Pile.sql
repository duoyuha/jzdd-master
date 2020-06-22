drop table if exists m_pile;

/*==============================================================*/
/* Table: S_CORP                                                */
/*==============================================================*/
create table m_pile
(
   PILE_ID              varchar(100) not null comment '本实体记录的唯一标识，产生规则为流水号',
   CORP_NO                varchar(32) comment '公司编号',
   PILE_NO             varchar(32) comment '桩编码,即PILE_MODEL',
   SUPPLIER_NO       varchar(32)  comment '服务商编码',
   SUPPLIER_NAME       varchar(32)  comment '服务商名称',
   PILE_NAME           varchar(32) comment '桩名称',
   PILE_PIC_ONE          varchar(256) comment '正面照片',
   PILE_PIC_SECOND        varchar(256) comment '背面照片',
   PILE_PIC_THIRD        varchar(256) comment '侧面照片',
   PILE_USE        varchar(256) comment '说明书',
   REMARK               varchar(256) comment '备注',
   IS_DEL               varchar(8) comment '是否删除:Y删除 N有效',
   CREATED_BY_NAME      varchar(256) comment '创建者',
   CREATED_WHEN         datetime comment '创建时间',
   LAST_MODIFIED_BY_NAME varchar(256) comment '更新者',
   LAST_MODIFIED_WHEN   datetime comment '更新时间',
   primary key (PILE_ID)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '车型表';
