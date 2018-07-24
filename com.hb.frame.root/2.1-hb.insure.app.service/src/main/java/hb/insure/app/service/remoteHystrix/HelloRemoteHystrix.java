package hb.insure.app.service.remoteHystrix;

import hb.insure.app.service.remote.HelloRemote;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zd.yao on 2018/7/22.
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {

/*
1. 什么情况下会触发 Fallback
    下面三种情况会导致 Hystrix 执行 Fallback
    主方法抛出异常
    主方法执行超时
    线程池拒绝
    断路器打开
参考：
Hystrix 使用指南（2）：Fallback 详解
https://www.jianshu.com/p/8a003709da22
*/

    @Override
    public WorldBO world(@RequestBody WorldDTO user) {
       throw(new IllegalStateException(this.getClass().getName()+"->world:fallback"));
    }

    @Override
    public String getUser(@RequestParam("name") String name) {
        throw(new IllegalStateException(this.getClass().getName()+"->getUser:fallback"));
        //return "getUser->fallback";
    }
}
