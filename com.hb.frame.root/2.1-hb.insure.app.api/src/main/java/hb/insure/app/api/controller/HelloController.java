package hb.insure.app.api.controller;


import hb.insure.app.service.bll.HelloServiceBLL;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @Autowired
    HelloServiceBLL helloServiceBLL;
    @RequestMapping("world")
    public String world() {
        WorldDTO worldDTO=new WorldDTO();
        worldDTO.setName("yzd");
        worldDTO.setPassword("123456");
        WorldBO worldBO=helloServiceBLL.world(worldDTO);
        return "=====WorldBO.getValue()="+worldBO.getValue();
    }
    @RequestMapping("getUser")
    public String getUser() {
        String name="yzd";
        String value=helloServiceBLL.getUser(name);
        return value;
    }
}
