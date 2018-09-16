package hb.insure.app.api;

import hb.insure.app.api.utils.springExt.SpringUtil2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author zd.yao
 */
@SpringBootApplication
@EnableDiscoveryClient //开启eureka服务
@EnableHystrix//开启Hystrix:使用EnableCircuitBreaker或者 EnableHystrix 表明Spring boot工程启用hystrix,两个注解是等价的
@EnableFeignClients("hb.insure.app.service.remote")//开启feigin注解
@ComponentScan("hb.insure.app.api,hb.insure.app.service")
public class ApplicationForInsureAppApi {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApplicationForInsureAppApi.class, args);
        SpringUtil2.setApplicationContext(ctx);
        //BaseLoadBalancer listLoadBalancer=SpringUtil2.getBean(BaseLoadBalancer.class);
        //listLoadBalancer.getLoadBalancerStats()
        //System.out.println(listLoadBalancer);
        /*SpringClientFactory springClientFactory = ctx.getBean(SpringClientFactory.class);
        ILoadBalancer loadBalancer = springClientFactory.getLoadBalancer("[服务名]");
        DynamicServerListLoadBalancer<DiscoveryEnabledServer> dynamicServerListLoadBalancer = (DynamicServerListLoadBalancer)loadBalancer;
        LoadBalancerStats l=dynamicServerListLoadBalancer.getLoadBalancerStats();
        l.getServerStats().clear();*/

    }
}