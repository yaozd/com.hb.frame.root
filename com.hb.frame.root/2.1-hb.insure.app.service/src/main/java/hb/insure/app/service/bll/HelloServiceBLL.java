package hb.insure.app.service.bll;

import hb.insure.app.service.remote.HelloRemote;
import hb.order.service.inf.bo.hello.WorldBO;
import hb.order.service.inf.dto.hello.WorldDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zd.yao on 2018/7/22.
 */
@Service
public class HelloServiceBLL {
    @Autowired
    HelloRemote helloRemote;

    /***
     *
     * @param user
     * @return
     */
    public WorldBO world(WorldDTO user){
        return helloRemote.world(user);
    }

    /***
     *
     * @param name
     * @return
     */
    public String getUser(String name){
        return helloRemote.getUser(name);
    }
}
