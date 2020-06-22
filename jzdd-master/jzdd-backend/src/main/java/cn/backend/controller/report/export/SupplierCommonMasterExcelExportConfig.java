package cn.backend.controller.report.export;

import cn.zdwl.common.excel.AbsMasterExcelExportConfig;
import io.swagger.annotations.Api;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author james
 * @date 2019/8/5
 **/
@Api(description = "服务商报表")
@Component
@ConfigurationProperties(value = "cn.backend.excel.master.common", exceptionIfInvalid = true)
public class SupplierCommonMasterExcelExportConfig extends AbsMasterExcelExportConfig {

}
