package cn.backend.timer;

import cn.backend.service.inspection.IInspectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 项目启动后启动
 * @author xsj
 * @date 2019/04/16
 */
@Component
public class TimerTask implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);

    @Autowired
    private IInspectionService iInspectionService;


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    /**
     * 巡检处理
     */
//    @Scheduled(cron = "${task.schedule.evaluation}")
//    @Transactional(rollbackFor = Exception.class)
    public void evaluation() {
        logger.info("处理巡检开始");
        iInspectionService.timer();
        logger.info("处理巡检结束");
    }

}
