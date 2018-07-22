package hb.insure.app.service.remote;

import hb.order.service.inf.api.IHelloServiceInf;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by zd.yao on 2018/7/22.
 */
@FeignClient(name= "HB-ORDER-SERVICE-PROVIDER")
public interface HelloRemote extends IHelloServiceInf { }