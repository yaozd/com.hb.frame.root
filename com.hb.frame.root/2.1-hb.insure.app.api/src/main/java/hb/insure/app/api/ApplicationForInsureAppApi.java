package hb.insure.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient //开启eureka服务
@EnableHystrix//开启Hystrix:使用EnableCircuitBreaker或者 EnableHystrix 表明Spring boot工程启用hystrix,两个注解是等价的
@EnableFeignClients("hb.insure.app.service.remote")//开启feigin注解
@ComponentScan("hb.insure.app.api,hb.insure.app.service")
public class ApplicationForInsureAppApi {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationForInsureAppApi.class, args);
    }
}