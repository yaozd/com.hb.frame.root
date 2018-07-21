package hb.insure.app.api.remote;

import hb.order.service.inf.api.IHelloServiceInf;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name= "HB-ORDER-SERVICE-PROVIDER")
public interface HelloRemote extends IHelloServiceInf{ }