package hb.insure.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("hb.insure.app.service.remote")
@ComponentScan("hb.insure.app.api,hb.insure.app.service")
public class ApplicationForInsureAppApi {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationForInsureAppApi.class, args);
    }
}