package cn.mongo.config.constant;

/**
 * @author : du
 * @data : 2019/6/11
 * @description :
 */
public class SysConstant {

    public static final String IS_DEL_N = "N";

    public static final String ASC_SORT_MARK = "+";
    public static final String DESC_SORT_MARK = "-";
    public static final String TIME_SORT_MARK = "When";
    public static final String DEFAULT_SORT = "-lastModifiedWhen";

    public static final String CREATEDNAME = "OTAServer";

    public class MongoCollection {

        public final static String TNML_REAL_INFO_COLLECTION = "tnml_real_info_collection";
        public final static String TNML_WARN_INFO_COLLECTION = "tnml_warn_info_collection";
        public final static String OTA_COMMOND_COLLECTION = "ota_commond_collection";
        public final static String OTA_RECORD_COLLECTION = "ota_record_collection";

    }

    public class MongoIndex {

        /**
         * 索引名称
         * */
        public final static String CREATED_TIME_IDX = "createdtime_idx";
        public final static String LASTMODIFIED_TIME_IDX = "lastmodifiedtime_idx";
        public final static String CORPNO_IDX = "corpno_idx";
        public final static String TMNLCODE_IDX = "tmnlcode_idx";

    }

}
