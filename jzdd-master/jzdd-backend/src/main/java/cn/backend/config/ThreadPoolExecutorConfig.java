package cn.backend.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class ThreadPoolExecutorConfig {

    //线程池
    private static volatile ThreadPoolExecutor threadPoolExecutor;

    /**
     * 提交执行任务
     * @param task
     */
    public static void submit(Runnable task){
        if(threadPoolExecutor == null){
            synchronized (ThreadPoolExecutorConfig.class) {
                if(threadPoolExecutor == null){
                    threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
                }
            }
        }
        threadPoolExecutor.submit(task);
    }
}
