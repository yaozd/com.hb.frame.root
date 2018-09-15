package hb.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zd.yao
 * @description
 * @date 2018/9/15
 **/

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ApplicationForZuulServer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationForZuulServer.class, args);
    }
}
