package hb.order.service.provider.impl;

import hb.order.service.inf.api.IHelloServiceInf;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloServiceImpl implements IHelloServiceInf {
    @Override
    public WorldBO world(WorldDTO user) {
        return null;
    }
}
