package hb.sentinel.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("limit")
public class LimitController {
    @GetMapping("flow")
    public String world() {
        return "=====limit.flow="+System.currentTimeMillis();
    }
}
