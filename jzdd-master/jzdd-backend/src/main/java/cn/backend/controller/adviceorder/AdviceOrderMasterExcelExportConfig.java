package cn.backend.controller.adviceorder;

import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author james
 * @date 2019/7/31
 **/
@Component
@ConfigurationProperties(value = "cn.backend.excel.master.adviceorder", exceptionIfInvalid = true)
public class AdviceOrderMasterExcelExportConfig extends AbsMasterExcelExportConfig {

}
