package hb.insure.app.service.remote;

import hb.insure.app.service.remoteHystrix.HelloRemoteHystrix;
import hb.order.service.inf.api.IHelloServiceInf;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by zd.yao on 2018/7/22.
 */

//如果不配置fallback，则直接抛出异常： HelloRemote#getUser(String) failed and no fallback available.
//fallback主要用于服务降级使用
//@FeignClient(name= "HB-ORDER-SERVICE-PROVIDER")
@FeignClient(name= "HB-ORDER-SERVICE-PROVIDER",fallback = HelloRemoteHystrix.class)
public interface HelloRemote extends IHelloServiceInf { }