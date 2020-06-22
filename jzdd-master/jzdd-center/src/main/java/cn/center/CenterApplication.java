package cn.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务中心
 * 关闭mongodb启动
 */
@EnableEurekaServer
@SpringBootApplication
public class CenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(CenterApplication.class, args);
    }
}
