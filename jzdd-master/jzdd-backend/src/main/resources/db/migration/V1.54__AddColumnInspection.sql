ALTER TABLE `t_order_inspection`
ADD COLUMN `INSPECTION_RESULT`  varchar(256) comment '巡检结果' AFTER `INSPECTION_DESC`,
ADD COLUMN `INSPECTION_STATUS`  varchar(8) comment '巡检状态 Y已巡检 N未巡检' AFTER `INSPECTION_RESULT`;

