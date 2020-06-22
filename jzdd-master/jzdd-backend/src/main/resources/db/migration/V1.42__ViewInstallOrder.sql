CREATE
	OR REPLACE VIEW v_install_order AS SELECT
	T1.ORDER_ID AS ORDER_ID,
	T1.CORP_NO AS CORP_NO,
	T1.ORDER_NO AS ORDER_NO,
	T1.ORDER_TIME AS ORDER_TIME,
	T1.CHECK_NO AS CHECK_NO,
	T1.INSTALL_CONTACT AS INSTALL_CONTACT,
	T1.INSTALL_CONTACT_PHONE AS INSTALL_CONTACT_PHONE,
	T1.INSTALL_PROVINCE AS INSTALL_PROVINCE,
	T1.INSTALL_CITY AS INSTALL_CITY,
	T1.INSTALL_REGION AS INSTALL_REGION,
	T1.INSTALL_ADDRESS AS INSTALL_ADDRESS,
	T1.ORDER_REMARK AS ORDER_REMARK,
	T1.PILE_CODE AS PILE_CODE,
	T1.PILE_MODEL AS PILE_MODEL,
	T1.PILE_MODEL_NAME AS PILE_MODEL_NAME,
	T1.PILE_TYPE AS PILE_TYPE,
	T1.PILE_TYPE_NAME AS PILE_TYPE_NAME,
	T1.SUPPLIER_NO AS SUPPLIER_NO,
	T1.SUPPLIER_NAME AS SUPPLIER_NAME,
	T1.DISPATCH_REMARK AS DISPATCH_REMARK,
	T1.RECEIVE_STATUS AS RECEIVE_STATUS,
	T1.RECEIVE_STATUS_NAME AS RECEIVE_STATUS_NAME,
	T1.RECEIVE_REMARK AS RECEIVE_REMARK,
	T1.CONTACT_CUST_TIME AS CONTACT_CUST_TIME,
	T1.APPOINTMENT_TIME AS APPOINTMENT_TIME,
	T1.APPOINTMENT_REMARK AS APPOINTMENT_REMARK,
	T1.CHECK_USER_NO AS CHECK_USER_NO,
	T1.CHECK_USER_NAME AS CHECK_USER_NAME,
	T1.CHECK_USER_PHONE AS CHECK_USER_PHONE,
	T1.ASSIGN_CHECK_REMARK AS ASSIGN_CHECK_REMARK,
	T1.CHECK_TIME AS CHECK_TIME,
	T1.CHECK_FINISH_TIME AS CHECK_FINISH_TIME,
	T1.DISPATCH_TIME AS DISPATCH_TIME,
	T1.WIRE_LENGTH AS WIRE_LENGTH,
	T1.INSTALL_FLG AS INSTALL_FLG,
	T1.INSTALL_FLG_NAME AS INSTALL_FLG_NAME,
	T1.ESTIMATE_PRICE AS ESTIMATE_PRICE,
	T1.ELEC_TYPE AS ELEC_TYPE,
	T1.ELEC_TYPE_NAME AS ELEC_TYPE_NAME,
	T1.RECEIVE_TIME AS RECEIVE_TIME, -- 签收时间
	T1.INSTALL_METHOD_FILE AS INSTALL_METHOD_FILE,
	T1.OTHER_FILE AS OTHER_FILE,
	T1.CHECK_REMARK AS CHECK_REMARK,
	T1.APPOINTMENT_INSTALL_TIME AS APPOINTMENT_INSTALL_TIME,
	T1.INSTALL_CONTACT_CUST_TIME AS INSTALL_CONTACT_CUST_TIME,
	T1.APPOINTMENT_INSTALL_REMARK AS APPOINTMENT_INSTALL_REMARK,
	T1.INSTALL_USER_NO AS INSTALL_USER_NO,
	T1.INSTALL_USER_NAME AS INSTALL_USER_NAME,
	T1.INSTALL_USER_PHONE AS INSTALL_USER_PHONE,
	T1.ASSIGN_INSTALL_REMARK AS ASSIGN_INSTALL_REMARK,
	T1.INSTALL_TIME,
	T1.INSTALL_FINISH_TIME, -- 安装完成时间
	T1.INSTALL_TYPE AS INSTALL_TYPE,
	T1.INSTALL_TYPE_NAME AS INSTALL_TYPE_NAME,
	T1.ACTUAL_PRICE AS ACTUAL_PRICE,
	T1.CONNECT_FLG AS CONNECT_FLG,
	T1.CONNECT_FLG_NAME AS CONNECT_FLG_NAME,
	T1.GPS_LON AS GPS_LON,
	T1.GPS_LAT AS GPS_LAT,
	T1.PERSON_PILE_PIC AS PERSON_PILE_PIC,
	T1.INSTALL_CONFIRM_FILE AS INSTALL_CONFIRM_FILE,
	T1.OTHER_INSTALL_FILE AS OTHER_INSTALL_FILE,
	T1.INSTALL_REMARK AS INSTALL_REMARK,
	T1.SUPPLIER_CONFIRM_TIME AS SUPPLIER_CONFIRM_TIME,
	T1.SUPPLIER_CONFIRM_FLG AS SUPPLIER_CONFIRM_FLG,
	T1.SUPPLIER_CONFIRM_FLG_NAME AS SUPPLIER_CONFIRM_FLG_NAME,
	T1.VERIFY_FLG AS VERIFY_FLG,
	T1.VERIFY_FLG_NAME AS VERIFY_FLG_NAME,
	T1.VERIFY_REMARK AS VERIFY_REMARK,
	T1.VERIFY_TIME AS VERIFY_TIME,
	T1.CRM_ROLLBACK_REMARK AS CRM_ROLLBACK_REMARK,
	T1.BEGIN_CACLE AS BEGIN_CACLE,
	T1.SETTLE_FLG AS SETTLE_FLG,
	T1.FINISH_FLG AS FINISH_FLG,
	T1.FINISH_TIME AS ORDER_FINISH_TIME, -- 订单完成时间
	T1.INSPECTION_TOUR_FLG AS INSPECTION_TOUR_FLG,
	T1.DISPATCH_CHECK_SUBMIT_TIME AS DISPATCH_CHECK_SUBMIT_TIME, -- 派单勘察提交时间
	T1.DISPATCH_INSTALL_SUBMIT_TIME AS DISPATCH_INSTALL_SUBMIT_TIME, -- 派单安装提交时间
	T1.GRADE_SUBMIT_TIME AS GRADE_SUBMIT_TIME, -- 满意度提交时间
	T1.SETTLE_REMARK AS SETTLE_REMARK, -- 申请回退原因
	T1.SETTLE_VERIFY_FLG AS SETTLE_VERIFY_FLG, -- 申请结算标识
	T1.IS_DEL AS IS_DEL,
	T1.CREATED_BY_NAME AS CREATED_BY_NAME,
	T1.CREATED_WHEN AS CREATED_WHEN,
	T1.LAST_MODIFIED_BY_NAME AS LAST_MODIFIED_BY_NAME,
	T1.LAST_MODIFIED_WHEN AS LAST_MODIFIED_WHEN,
	T2.FOLLOW_SEQ AS FOLLOW_SEQ, -- 步骤号
	T2.STEP_NAME AS STEP_NAME, -- 步骤名称
	T2.FOLLOW_CODE AS FOLLOW_CODE, -- 流程编码
	T2.FOLLOW_NO AS FOLLOW_NO, -- 流程编号
	T2.POSITION_CODES AS POSITION_CODES, -- 审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员
	T2.STEP_MATRIX AS STEP_MATRIX, -- 步骤矩阵
	T3.CAR_OWNER AS CAR_OWNER, -- 车主名称
	T3.CAR_OWNER_PHONE AS CAR_OWNER_PHONE, -- 车主电话
	T3.DEALER_NAME AS DEALER_NAME, -- 经销商名称
	T3.DEALER_CONTACT AS DEALER_CONTACT, -- 经销商联系人
	T3.DEALER_TEL AS DEALER_TEL, -- 经销商电话
	T3.SALE_DATE AS SALE_DATE, -- 销售时间
	T3.CAR_VEHICLE AS CAR_VEHICLE, -- 车型
	T3.VIN_NO AS VIN_NO, -- 车架号
	T3.ENGINE_NO AS ENGINE_NO, -- 发动机编号
	T3.VEHICLE_DETAIL AS VEHICLE_DETAIL, -- 车型明细
	ifnull( T4.SETTLE_AMT, 0 ) AS SETTLE_AMT,  -- 订单金额
	(TIMESTAMPDIFF(SECOND,T1.INSTALL_TIME,T1.INSTALL_FINISH_TIME)) AS INSTALL_SPAN,
	   (case when LENGTH(t1.INSTALL_USER_NO)>0 then
				t1.INSTALL_USER_NO else
       t1.CHECK_USER_NO
   end) as  CHECK_USER_QUERY
FROM
	t_install_order T1
	LEFT JOIN t_order_step T2 ON T1.ORDER_NO = T2.ORDER_NO
	AND T2.CURRENT_STEP_FLG = 'Y'
	AND T2.FOLLOW_CODE = '01'
	LEFT JOIN t_order_car T3 ON T1.ORDER_NO = T3.ORDER_NO
	LEFT JOIN t_order_fee T4 ON T1.ORDER_NO = T4.ORDER_NO
	AND T4.IS_DEL = 'N'
WHERE
	T1.IS_DEL = 'N'
	AND T2.IS_DEL = 'N'
	AND T3.IS_DEL = 'N'
  AND T1.CRM_ROLLBACK_FLG='N'