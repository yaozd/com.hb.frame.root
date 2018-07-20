package hb.order.service.provider.impl;

import hb.order.service.inf.api.IHelloServiceInf;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloServiceImpl implements IHelloServiceInf {
    @Value("${server.port}")
    String port;
    @Override
    public WorldBO world(WorldDTO user) {
        String value="端口：" + port+ ";hello，this is first messge";
        WorldBO worldBO=new WorldBO();
        worldBO.setValue(value);
        return worldBO;
    }
}
