package cn.backend.controller.settle;

import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author james
 * @date 2019/7/31
 **/
@Component
@ConfigurationProperties(value = "cn.backend.excel.master.settle", exceptionIfInvalid = true)
public class SettleMasterExcelExportConfig extends AbsMasterExcelExportConfig {

}
