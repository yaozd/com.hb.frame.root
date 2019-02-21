
> github仓库
- [https://github.com/yaozd/spring-cloud-study-1](https://github.com/yaozd/spring-cloud-study-1)
- [https://github.com/zhaoyibo/spring-cloud-study](https://github.com/zhaoyibo/spring-cloud-study)
- []()

> 参考
- [Spring Cloud（七）：配置中心（Git 版与动态刷新）【Finchley 版】](https://windmt.com/2018/04/19/spring-cloud-7-config-sample/)
- [Spring Cloud（八）：配置中心（服务化与高可用）【Finchley 版】](https://windmt.com/2018/04/19/spring-cloud-8-config-with-eureka/)
- [Config Server——使用Spring Cloud Bus自动刷新配置](http://www.itmuch.com/spring-cloud/spring-cloud-bus-auto-refresh-configuration/)
- [Spring Cloud（九）：配置中心（消息总线）【Finchley 版】](https://windmt.com/2018/04/19/spring-cloud-9-config-eureka-bus/)
- []()

> 配置中心-[Spring Cloud生态的配置服务器最全对比贴](http://www.itmuch.com/spring-cloud-sum/spring-cloud-config-serer-compare/)
- [Spring Cloud Config](https://github.com/spring-cloud/spring-cloud-config)
- [Apollo](https://github.com/ctripcorp/apollo)
- [Nacos](http://www.itmuch.com/spring-cloud-sum/spring-cloud-config-serer-compare/)
- [Disconf](https://github.com/knightliao/disconf )
- []()

> 注意事项
- [Spring Cloud（七）：配置中心（Git 版与动态刷新）【Finchley 版】-推荐参考-byArvin](https://windmt.com/2018/04/19/spring-cloud-7-config-sample/)
```
1.开启更新机制
需要给加载变量的类上面加载@RefreshScope，在客户端执行/actuator/refresh的时候就会更新此类下面的变量值。
2.实现 refresh 的功能
spring-boot-starter-actuator是一套监控的功能，可以监控程序在运行时状态，其中就包括/actuator/refresh的功能。
/actuator/refresh
```

> 参考代码
- [https://github.com/yaozd/spring-cloud-study-1](https://github.com/yaozd/spring-cloud-study-1)-[config-git]-Git 版与动态刷新
```
更新-必须POST
http://localhost:13000/actuator/refresh
[
    "config.client.version",
    "info.profile"
]
----------------------
验证
http://localhost:13000/info

```