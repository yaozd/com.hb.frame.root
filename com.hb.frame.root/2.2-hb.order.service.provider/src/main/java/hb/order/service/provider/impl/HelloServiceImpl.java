package hb.order.service.provider.impl;

import cn.hutool.core.thread.ThreadUtil;
import hb.order.service.inf.api.IHelloServiceInf;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloServiceImpl implements IHelloServiceInf {
    @Value("${server.port}")
    String port;
    @Override
    public WorldBO world(@RequestBody WorldDTO user) {
        //模拟超时
        ThreadUtil.sleep(10, TimeUnit.SECONDS);
        //
        String value="端口：" + port+ ";hello，this is first messge="+user.getName();
        WorldBO worldBO=new WorldBO();
        worldBO.setValue(value);
        return worldBO;
    }

    @Override
    public String getUser(@RequestParam("name") String name) {
        //模拟异常
        throwException();
        //模拟超时
        //ThreadUtil.sleep(10, TimeUnit.SECONDS);
        //
        return "hello->"+name;
    }

    private void throwException() {
        Integer t1=1;
        if(t1==1){
            throw new IllegalStateException("模拟异常");
        }
    }
}
