package hb.order.service.inf.api;

import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("IHelloServiceInf")
public interface IHelloServiceInf {
    @RequestMapping(value = "world", method = RequestMethod.POST)
    WorldBO world(@RequestBody WorldDTO user);
}
