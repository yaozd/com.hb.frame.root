package hb.insure.app.api.remote;

import hb.order.service.inf.api.IHelloServiceInf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "HB-ORDER-SERVICE-PROVIDER")
public interface HelloRemote extends IHelloServiceInf{ }