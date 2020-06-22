package cn.backend.controller.report.export;

import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author james
 * @date 2019/8/5
 **/
@Component
@ConfigurationProperties(value = "cn.backend.excel.master.settlereport", exceptionIfInvalid = true)
public class SettleReportMasterExcelExportConfig extends AbsMasterExcelExportConfig {
}
