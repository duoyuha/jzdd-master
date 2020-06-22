package cn.backend.config.constant;

public class SysConstant {

    /**
     * 是否已删除(未删除)
     */
    public static final String IS_DEL_N = "N";

    /**
     * 是否已删除(已删除)
     */
    public static final String IS_DEL_Y = "Y";

    /**
     * 横线分隔符
     */
    public static final String SEPARATE = "-";

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 是否需要数据校验
     */
    public static final String DATA_CHECK = "Y";

    /**
     * 是否需要数据校验
     */
    public static final String NO_DATA_CHECK = "N";

    /**
     * 附件访问前缀
     */
    public static final String ATTACH_VISIT_PERF = "/api/v1/";

    /**
     * 通用配置
     */
    public class Common {

        public static final String OUTPUT_LOG = "Y";//输出日志

        public static final String NO_OUTPUT_LOG = "N";//输出日志

        public static final String LIST_ALL_FLG_YES = "Y";//查询全部数据

        public static final String LIST_ALL_FLG_NO = "N";//不查询全部数据


    }


    /**
     * 用户
     */
    public class User {

        public static final String CDJL_USER = "01"; //安装经理

        public static final String  ZCSYBJL_USER = "02";//整车事业部经理

        public static final String  AZFWS_USER = "03";//安装服务商
        public static final String  AZRY_USER = "05";//安装人员
        public static final String  KCRY_USER = "04";//勘察人员

        public static final String  DEALER_USER = "06";//经销商

        public static final String PREFIX_NUMBER = "USR";

        public static final String CONFIG_USERPOSITION = "001";


        public static final String FIX_PHONE = "17900000001";

    }

    /**
     * 充电桩型号
     */
    public class Pile {

        public static final String PREFIX_NUMBER = "PILE";

        //正面照
        public static final String POSITIVE_FOLDER_NAME = "pile/positive";
        //背面照
        public static final String BACK_FOLDER_NAME = "pile/back";
        //侧面照
        public static final String SIDE_FOLDER_NAME = "pile/side";
        //使用说明书
        public static final String INSTRUCTION_FOLDER_NAME = "pile/instruction";

        public static final int POSITIVE_INDEX = 0;
        public static final int BACK_INDEX = 1;
        public static final int SIDE_INDEX = 2;
        public static final int INSTRUCTION_INDEX = 3;
    }

    /**
     * 车型
     */
    public class Vehicle {


        public static final String PREFIX_NUMBER = "VEH";

    }

    /**
     * 巡检
     */
    public class Inspection {


        public static final String PREFIX_NUMBER = "INSP";

    }

    /**
     * 车型明细
     */
    public class VehicleDetail {


        public static final String PREFIX_NUMBER = "VEHDET";

    }

    /**
     * 公司
     */
    public class Corp {

        public static final String PREFIX_NUMBER = "CORP";

        public static final String BRAND_WEY = "2";
        public static  final String BRAND_ORA = "3";



    }


    /**
     * 部门
     */
    public class Dept {

        public static final String PREFIX_NUMBER = "DEPT";

    }

    /**
     * VIN
     */
    public class Vin {

        public static final String PREFIX_NUMBER = "VIN";

        public static final String CONFIG_GIVEPILE = "004";

    }

    /**
     * 服务商
     */
    public class Supplier {

        public static final String PREFIX_NUMBER = "SUP";

        public static final String CONFIG_FOLDER_NAME = "supplier";

        public static final String CONFIG_TYPE_AUTO_ORDER = "021";

        public static final String CONFIG_VAL_AUTO_ORDER = "01";

    }

    /**
     * 合同
     */
    public class Contract {
        public static final String PREFIX_NUMBER = "CON";

        public static final String PREFIX_NUMBER_DETAIL = "CONTAIL";

        public static final String CONFIG_FOLDER_NAME = "contract";

        public static final String FEE_TYPE = "016";

        public static final String FEE_NAME = "022";
        /**
         * 费用代码 01设备费 02安装服务费 03立柱费
         */
        public static final String FEE_CODE_EQU = "01";
        public static final String FEE_CODE_SERVICE = "02";
        public static final String FEE_CODE_POST = "03";
    }

    /**
     * 配送
     */
    public class Delivery {

        public static final String CONFIG_FOLDER_NAME_WULIU = "delivery/wuliu";

        public static final String CONFIG_FOLDER_NAME_EXPLAIN = "delivery/explain";

        public static final String CONFIG_FOLDER_NAME_SIGN = "delivery/sign";

        public static final String PREFIX_NUMBER = "DEL";

        public static final String CORP_ORA = "CORP1560154054723";

        public static final String ORA = "ORA";

        public static final String CORP_WEIPA = "CORP1560154054724";

        public static final String WEY = "WEY";

        public static final String PREFIX_NUMBER_COMPLAIN = "COMPL";

        public static final String PILE_TYPE = "005";

        public static final String YES_NO = "008";

        public static final String RECEIVE_STATUS = "006";

        public static final String DELIVERY_TYPE = "011";

        public static final String VERIFY_FLG = "010";

        public static final String DELIVERY = "02";

        //充电经理
        public static final String PILE_MANGER = "01";


        //public static final String PREFIX_NUMBER_DETAIL = "CONTAIL";

        public static final int RECEIVE_SEQ = 2;
        public static final int DELIVERY_SEQ = 3;
    }

    /**
     * 配置
     */
    public class Config {
        public static final String PREFIX_NUMBER = "CONF";

        public static final String DISTINCT_Y = "Y";


    }


    /**
     * 安装订单
     */
    public class InstallOrder {

        public static final String CONFIG_FOLDER_NAME = "installorder";

        public static final String CONFIG_FOLDER_NAME_SUBMIT = "installorder/submit";

        public static final String CONFIG_FOLDER_NAME_PLAN = "installorder/plan";

        public static final String CONFIG_FOLDER_NAME_CHECKOTHER = "installorder/checkother";

        public static final String CONFIG_FOLDER_NAME_GROUP = "installorder/group";

        public static final String CONFIG_FOLDER_NAME_CONFIRM = "installorder/confirm";

        public static final String CONFIG_FOLDER_NAME_INSTALLOTHER = "installorder/installother";



        public static final String PREFIX_NUMBER = "INS";

        public static final String PREFIX_NUMBER_ADVICE = "ADV";

        public static final String CONFIG_PILETYPE = "005";

        public static final String CONFIG_RECEIVESTATUS = "006";

        public static final String CONFIG_YORN = "007";

        public static final String CONFIG_ELECTYPE = "008";

        public static final String CONFIG_INSTALLTYPE = "009";

        public static final String CONFIG_VERIFY = "010";
        public static final String CONFIG_VERIFY_N = "01";
        public static final String CONFIG_VERIFY_Y = "02";
        public static final String CONFIG_VERIFY_REFUSE = "03";

        public static final String CONFIG_FLG_Y = "Y";

        public static final String GRADE_STEP_MATRIX = "0,0,0,0,0,0,0,1,0";

        public static final String CONFIG_FLG_N = "N";

        public static final String VERIFY_FLG_REFUSE = "03";

        public static final String RECEIVE_FLG_SIGN = "02";

        public static final String FINISH_FLG_Y = "Y";

        public static final String FINISH_FLG_N = "N";

        public static final String SETTLE_FLG_Y = "Y";

        public static final String SETTLE_FLG_N = "N";

        public static final String AUTO_ORDER_REMARK = "自动派单";

        public static final String REBUILD_FLG_YES = "Y";
        public static final String REBUILD_FLG_NO = "N";

        /**
         * 充电桩样式  01挂壁 02立柱
         */
        public static final String PILE_TYPE_WALL = "01";
        public static final String PILE_TYPE_POST = "02";

        public static final int RECEIVE_SEQ = 2;
        public static final int CONTACT_SEQ = 3;
        public static final int CHECK_SEQ = 4;
        public static final int INSTALL_SEQ = 6;


    }

    /**
     * Scrm
     */
    public class Scrm {
        public static final String PREFIX_NUMBER = "SCRM";

        public static final String CONFIG_FOLDER_NAME = "scrm";

        public static final String CASE_TYPE = "015";//工单类型'

        // //工单类型(1.欧拉销售咨询2.欧拉售后咨询3.欧拉售后投诉4.WEY销售咨询5.WEY售后咨询6.WEY售后投诉)
        public static final String CASE_TYPE_OULA_SALE_ADVICE = "01";
        public static final String CASE_TYPE_OULA_AFTER_SALE_ADVICE = "02";
        public static final String CASE_TYPE_OULA_AFTER_SALE_COMPLAIN = "03";
        public static final String CASE_TYPE_WEY_SALE_ADVICE = "04";
        public static final String CASE_TYPE_WEY_AFTER_SALE_ADVICE = "05";
        public static final String CASE_TYPE_WEY_AFTER_SALE_COMPLAIN = "06";

        public static final int CASE_TYPE_ADVICE = 1;
        public static final int CASE_TYPE_COMPLAIN = 2;
    }

    public class Crm {

        public static final int CRM_SCHEDULE = 1;

        public static final int CRM_GRADE = 2;

        public static final int CRM_FINISH = 3;

        public static final int CRM_CANCAL = 4;

        public static final int CRM_RETURN = 5;

    }

    /**
     * 咨询
     */
    public class Advice {
        public static final String PREFIX_NUMBER = "ADVICE";

        /**
         * 投诉内容 01乱收费 02态度差 03其他
         */
        public static final String CONFIG_TYPE_COMPLAINDESC = "020";

        public static final String CONFIG_FOLDER_NAME = "advice";

        public static final String WORKDAY_STARTTIME = "07:30";

        public static final String WORKDAY_ENDTIME = "17:30";

        public static final String RECEIVE_PASS = "01";
        public static final String RECEIVE_REJECT = "02";

        public static final int CONTACT_SEQ = 3;


    }

    /**
     * 咨询
     */
    public class Cancel {
        public static final String PREFIX_NUMBER = "CANCEL";




    }

    /**
     * 投诉
     */
    public class Complain {
        public static final String PREFIX_NUMBER = "COMPLAIN";

        public static final String CONFIG_FOLDER_NAME = "complain";

//        public static final String REDUCE_FIRST = "1,0,0";
//
//        public static final String REDUCE_SECOND = "0,1,0";

        /**
         * 投诉内容 01乱收费 02态度差 03其他
         */
        public static final String CONFIG_TYPE_COMPLAINDESC = "019";

        public static final int CONTACT_SEQ = 3;
    }

    /**
     * 工作流
     */
    public class WorkFollow {

        /**
         * 01 安装
         * 02 配送
         * 03 作废
         * 04 结算
         * 05 售后
         * 06 咨询
         */
        public static final String CONFIG_TYPE_WORKFOLLOW = "013";
        public static final String CONFIG_WORKFOLLOW_INSTALL = "01";
        public static final String CONFIG_WORKFOLLOW_DELIVERY = "02";
        public static final String CONFIG_WORKFOLLOW_INVALID = "03";
        public static final String CONFIG_WORKFOLLOW_SETTLE = "04";
        public static final String CONFIG_WORKFOLLOW_AFTERSELL = "05";
        public static final String CONFIG_WORKFOLLOW_ADVICE = "06";
        public static final String CONFIG_WORKFOLLOW_AFTERSELL_IN = "07";
        public static final String CONFIG_WORKFOLLOW_INVALID_IN = "08";



        /**
         * 1 安装
         * 2 配送
         * 3 作废
         * 4 结算
         * 5 售后
         * 6 咨询
         */
        public static final String WORKFOLLOW_INSTALL_NO = "1";
        public static final String WORKFOLLOW_DELIVERY_NO = "2";
        public static final String WORKFOLLOW_INVALID_NO = "3";
        public static final String WORKFOLLOW_SETTLE_NO = "4";
        public static final String WORKFOLLOW_AFTERSELL_NO = "5";
        public static final String WORKFOLLOW_ADVICE_NO = "6";
        public static final String WORKFOLLOW_IN_AFTERSELL_NO = "7";
        public static final String WORKFOLLOW_IN_INVALID_NO = "8";


        /**
         * 01 安装 签收超时
         * 02 联系客户超时
         * 03 勘察超时
         * 04 安装超时
         * 05 配送 签收超时
         * 06 配送 配送超时
         * 07 售后 联系客户超时
         * 08 咨询 联系客户超时
         */
        public static final String WORKFOLLOW_TIMEOUT_TYPE01 = "01";
        public static final String WORKFOLLOW_TIMEOUT_TYPE02 = "02";
        public static final String WORKFOLLOW_TIMEOUT_TYPE03 = "03";
        public static final String WORKFOLLOW_TIMEOUT_TYPE04 = "04";
        public static final String WORKFOLLOW_TIMEOUT_TYPE05 = "05";
        public static final String WORKFOLLOW_TIMEOUT_TYPE06 = "06";
        public static final String WORKFOLLOW_TIMEOUT_TYPE07 = "07";
        public static final String WORKFOLLOW_TIMEOUT_TYPE08 = "08";

        public static final String CONFIRM_FLG_Y = "Y";

        /**
         * 撤回
         */
        public static final String Recall_Y = "撤回";
    }

    public  class  Inspetcion{
        public static final String CONFIG_FOLDER_NAME_INSPECTION = "inspection/";
        public static final String CHECK_FLG_YES = "Y";
        public static final String CHECK_FLG_NO = "N";
        public static final int THREE_MOUTHS_MINS = 129600;
    }

    /**
     * 订单流程
     */
    public class OrderStep {

        /**
         * 是否当前步骤
         */
        public static final String CURRENT_STEP_Y = "Y";
        public static final String CURRENT_STEP_N = "N";

        /**
         * 超时状态 01超时 02未超时
         */
        public static final String CONFIG_TYPE_TIMEOUT = "017";
        public static final String CONFIG_TYPE_TIMEOUT_Y = "01";
        public static final String CONFIG_TYPE_TIMEOUT_N = "02";

        /**
         * 审核结果:014
         * 01同意
         * 02拒绝
         */
        public static final String CONFIG_TYPE_CHECK = "014";

        public static final String ROLLBACK_FLG_YES = "Y";


        public static final String VERIFY_RESULT_PASS = "01";

        public static final String VERIFY_RESULT_REJECT = "02";

        //是否需要审批

        public static final String CHECK_FLG_YES = "Y";
        public static final String CHECK_FLG_NO = "N";

        //审批结果 01 同意 02 拒绝
        public static final String CHECK_FLG_AGREE = "01";
        public static final String CHECK_FLG_REJECT = "02";


        public static final String LAST_STEP_Y = "Y";

        public static final String TIMEOUT_TYPE_WORKDAY = "01";
        public static final String TIMEOUT_TYPE_WORKDAYHOUR = "02";

        //重新生成订单类型
        public static final String REBUILD_TYPE_INSTALL = "01";
        public static final String REBUILD_TYPE_DELIVERY = "02";

    }

    /**
     * 满意度
     */
    public class orderGrade {

        public static final String PREFIX_NUMBER = "GRD";

        public static final String CONFIG_ORDER_TYPE = "013";
        public static final String CONFIG_ORDER_TYPE_INSTALL = "01";
        public static final String CONFIG_ORDER_TYPE_DELIVERY = "02";

        public static final String CONFIG_ONTIME_FLG = "007";

        public static final String CONFIG_TYPE_TIMEOUT = "017";


    }

    /**
     * 订单附件
     */
    public class orderAttach {

        public static final String PREFIX_NUMBER = "ORDATH";

        public static final String CONFIG_ATTACH_TYPE_1 = "01";
        public static final String CONFIG_ATTACH_TYPE_2 = "02";
        public static final String CONFIG_ATTACH_TYPE_3 = "03";
        public static final String CONFIG_ATTACH_TYPE_4 = "04";
        public static final String CONFIG_ATTACH_TYPE_5 = "05";
        public static final String CONFIG_ATTACH_TYPE_6 = "06";
        public static final String CONFIG_ATTACH_TYPE_7 = "07";
        public static final String CONFIG_ATTACH_TYPE_8 = "08";
        public static final String CONFIG_ATTACH_TYPE_9 = "09";
    }

    /**
     * 结算单
     */
    public class Settle {

        public static final String CONFIG_FOLDER_NAME_SIGN = "settle/file";
        public static final String PREFIX_NUMBER = "SETTLE";

        /**
         * 订单状态:012
         * 01有效
         * 02无效
         */
        public static final String SETTLE_STATUS = "012";
        public static final String SETTLE_STATUS_EFFECTIVE = "01";
        public static final String SETTLE_STATUS_INVALID = "02";

        /**
         * 审核结果:014
         * 01同意
         * 02拒绝
         */
        public static final String TRIAL = "014";
        public static final String TRIAL_PASS = "01";
        public static final String TRIAL_REFUSE = "02";

        /***
         * 导出类型
         * 01 外部
         * 02 内部
         */
        public static final String EXPORT_OUT = "01";
        public static final String EXPORT_IN = "02";
    }

    /**
     * 满意度
     */
    public class orderAuto {

        public static final String PREFIX_NUMBER = "AUTO";

    }
}

