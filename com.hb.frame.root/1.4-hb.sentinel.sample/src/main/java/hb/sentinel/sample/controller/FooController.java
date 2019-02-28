package hb.sentinel.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/foo")
public class FooController {
    @GetMapping("hello")
    public String hello() {
        return "=====api.foo.hello="+System.currentTimeMillis();
    }
    @GetMapping("world")
    public String world() {
        return "=====api.foo.world="+System.currentTimeMillis();
    }

}
