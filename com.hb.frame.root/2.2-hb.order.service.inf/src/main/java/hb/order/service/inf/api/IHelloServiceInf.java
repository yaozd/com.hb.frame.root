package hb.order.service.inf.api;

import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IHelloServiceInf {
    //解决：不要在接口类名上使用RequestMapping，虽然可以使用，
    //但同时SpringMVC会把该接口的实例当作Controller开放出去，这个可以在启动的Mapping日志中查看到
    //SERVICE_INF_PATH=接口名+"/";
    final String SERVICE_INF_PATH="IHelloServiceInf"+"/";
    //
    @RequestMapping(value = SERVICE_INF_PATH+"world", method = RequestMethod.POST)
    WorldBO world(@RequestBody WorldDTO user);
    //WorldBO world(WorldDTO user);//没用@RequestBody就无法接收数据
    //
    @RequestMapping(value = SERVICE_INF_PATH+"getUser", method = RequestMethod.GET)
    String getUser(@RequestParam("name") String name);
}
