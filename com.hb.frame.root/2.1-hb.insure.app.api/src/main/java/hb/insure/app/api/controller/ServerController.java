package hb.insure.app.api.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerStats;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import hb.insure.app.api.utils.fastjsonExt.FastJsonUtil;
import hb.insure.app.api.utils.springExt.SpringUtil2;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * @author zd.yao
 * @description
 * @date 2018/9/16
 **/

@RestController
@RequestMapping("/api/server")
public class ServerController {

    /***
     * Eureka+Ribbon源码解析及负载均衡缓存的优化
     * https://www.jianshu.com/p/07c2e0d59dc9
     * ======
     * 自定义DiscoveryClient比较复杂.
     * 通过代码分析可以知道只需要能调到DiscoveryClient的refreshRegistry就可以实时刷新了, 但它是个private方法, 那就反射嘛, 配合Bus的refresh功能同时刷新注册信息
     * @return
     */
    @RequestMapping("refreshRegistry")
    public String getServiceList(){
        try {
            Method method = DiscoveryClient.class.getDeclaredMethod("refreshRegistry");
            method.setAccessible(true);
            method.invoke(SpringUtil2.getBean(DiscoveryClient.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }
    @RequestMapping("fun1")
    public String fun1(){
        SpringClientFactory springClientFactory = SpringUtil2.getBean(SpringClientFactory.class);
        ILoadBalancer loadBalancer = springClientFactory.getLoadBalancer("HB-ORDER-SERVICE-PROVIDER");
        DynamicServerListLoadBalancer<DiscoveryEnabledServer> dynamicServerListLoadBalancer = (DynamicServerListLoadBalancer)loadBalancer;
        loadBalancer.getAllServers();
        dynamicServerListLoadBalancer.getLoadBalancerStats().updateServerList(loadBalancer.getAllServers());
        System.out.println(dynamicServerListLoadBalancer.getLastUpdate());
        dynamicServerListLoadBalancer.enableAndInitLearnNewServersFeature();
        dynamicServerListLoadBalancer.forceQuickPing();
        dynamicServerListLoadBalancer.updateListOfServers();
        getServiceList();
        dynamicServerListLoadBalancer.updateListOfServers();
        dynamicServerListLoadBalancer.stopServerListRefreshing();
        LoadBalancerStats l=dynamicServerListLoadBalancer.getLoadBalancerStats();
        return "ok";
    }
    @RequestMapping("fun2")
    public String fun2(){
        getServiceList();
        SpringClientFactory springClientFactory = SpringUtil2.getBean(SpringClientFactory.class);
        ILoadBalancer loadBalancer = springClientFactory.getLoadBalancer("HB-ORDER-SERVICE-PROVIDER");
        DynamicServerListLoadBalancer<DiscoveryEnabledServer> dynamicServerListLoadBalancer = (DynamicServerListLoadBalancer)loadBalancer;
        dynamicServerListLoadBalancer.updateListOfServers();
        String lis=FastJsonUtil.serialize(loadBalancer.getAllServers());
        return "fun2"+lis;
    }
    @RequestMapping("fun3")
    public String fun3(){
        try {
            Method method = DiscoveryClient.class.getDeclaredMethod("refreshRegistry");
            method.setAccessible(true);
            method.invoke(SpringUtil2.getBean(DiscoveryClient.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SpringClientFactory springClientFactory = SpringUtil2.getBean(SpringClientFactory.class);
        ILoadBalancer loadBalancer = springClientFactory.getLoadBalancer("HB-ORDER-SERVICE-PROVIDER");
        DynamicServerListLoadBalancer<DiscoveryEnabledServer> dynamicServerListLoadBalancer = (DynamicServerListLoadBalancer)loadBalancer;
        dynamicServerListLoadBalancer.updateListOfServers();
        String lis=FastJsonUtil.serialize(loadBalancer.getAllServers());
        return "fun2"+lis;
    }
}
