package hb.insure.app.api.controller;

import hb.insure.app.api.remote.HelloRemote;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @Autowired
    HelloRemote helloRemote;
    @RequestMapping("world")
    public String world() {
        WorldDTO worldDTO=new WorldDTO();
        worldDTO.setName("yzd");
        worldDTO.setPassword("123456");
        WorldBO worldBO=helloRemote.world(worldDTO);
        return "=====WorldBO.getValue()="+worldBO.getValue();
    }
}
