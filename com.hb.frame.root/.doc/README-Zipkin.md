## 参考文章：
- [Spring Cloud 分布式链路跟踪 Sleuth + Zipkin + Elasticsearch【Finchley 版】](https://segmentfault.com/a/1190000015697673)
- [https://github.com/jarvisqi/spring-cloud-microservice](https://github.com/jarvisqi/spring-cloud-microservice)
- [spring boot 2.0.3+spring cloud （Finchley）7、服务链路追踪Spring Cloud Sleuth](https://www.cnblogs.com/cralor/p/9246582.html)
- [https://github.com/cralor7/springcloud](https://github.com/cralor7/springcloud)
- [Spring Cloud（十二）：分布式链路跟踪 Sleuth 与 Zipkin【Finchley 版】](https://windmt.com/2018/04/24/spring-cloud-12-sleuth-zipkin/)
- [https://github.com/zhaoyibo/spring-cloud-study](https://github.com/zhaoyibo/spring-cloud-study)
- []()

### [Zipkin 服务端](https://www.cnblogs.com/cralor/p/9246582.html)-官方就不推荐自行定制编译了
```
关于 Zipkin 的服务端，在使用 Spring Boot 2.x 版本后，官方就不推荐自行定制编译了，反而是直接提供了编译好的 jar 包来给我们使用，详情请看 upgrade to Spring Boot 2.0 NoClassDefFoundError UndertowEmbeddedServletContainerFactory · Issue #1962 · openzipkin/zipkin · GitHub

并且以前的@EnableZipkinServer也已经被打上了@Deprecated

If you decide to make a custom server, you accept responsibility for troubleshooting your build or configuration problems, even if such problems are a reaction to a change made by the OpenZipkin maintainers. In other words, custom servers are possible, but not supported.

EnableZipkinServer.java - github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/java/zipkin/server/EnableZipkinServer.java
 
简而言之就是：私自改包，后果自负。

所以官方提供了一键脚本（Windows下需要安装curl，不过如果你安装了Git客户端，可以直接在Git Bash中使用）

curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
如果用 Docker 的话，直接

docker run -d -p 9411:9411 openzipkin/zipkin
```
### Zipkin在windows下的安装与配置
```
当前Zipkin版：2.1.3.RELEASE
1.Git Bash中执行下面脚本
一键脚本（Windows下需要安装curl，不过如果你安装了Git客户端，可以直接在Git Bash中使用）
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
-------------------
2.启动命令 （http方式）
java -jar zipkin.jar
2.启动命令 （rabbitmq方式）
java -jar zipkin.jar --RABBIT_ADDRESSES=localhost
-------------------
```
### [zipkin.jar的yml配置文件内容zipkin-server-shared.yml](https://github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/resources/zipkin-server-shared.yml)
```
参考：
spring boot 2.0.3+spring cloud （Finchley）7、服务链路追踪Spring Cloud Sleuth
https://www.cnblogs.com/cralor/p/9246582.html
------------
通过这种方式可以启动zipkin然后使用rabbitmq进行链路追踪。另外在zipkin中配置的rabbitmq的用户名和密码是guest、guest如果你的rabbitmq用户名密码不是这个也要修改配置启动。

zipkin.jar的yml配置文件内容可在此处查看：https://github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/resources/zipkin-server-shared.yml

 这是配置文件的截图
```
### Zipkin使用RabbitMQ时，可以使用Rabbit的虚拟路径（virtual-host）
```
virtual-host:相当于为每一个独立的RabbitMQ
spring:
        application:
          name: user-service
        sleuth:
          web:
            client:
              enabled: true
          sampler:
            probability: 1.0
        zipkin:
          base-url: http://192.168.10.100:9411/
          enabled: true
          sender:
            type: RABBIT
        rabbitmq:
          addresses: 192.168.10.100
          port: 5672
          username: admin
          password: 12345
          virtual-host: sleuth
```

### [消息总线 RabbitMQ-可配置的环境变量](https://windmt.com/2018/04/24/spring-cloud-12-sleuth-zipkin/)
```
zipkin.collector.rabbitmq.concurrency	RABBIT_CONCURRENCY	并发消费者数量，默认为1
zipkin.collector.rabbitmq.connection-timeout	RABBIT_CONNECTION_TIMEOUT	建立连接时的超时时间，默认为 60000毫秒，即 1 分钟
zipkin.collector.rabbitmq.queue	RABBIT_QUEUE	从中获取 span 信息的队列，默认为 zipkin
zipkin.collector.rabbitmq.uri	RABBIT_URI	符合 RabbitMQ URI 规范 的 URI，例如amqp://user:pass@host:10000/vhost
如果设置了 URI，则以下属性将被忽略。

属性	环境变量	描述
zipkin.collector.rabbitmq.addresses	RABBIT_ADDRESSES	用逗号分隔的 RabbitMQ 地址列表，例如localhost:5672,localhost:5673
zipkin.collector.rabbitmq.password	RABBIT_PASSWORD	连接到 RabbitMQ 时使用的密码，默认为 guest
zipkin.collector.rabbitmq.username	RABBIT_USER	连接到 RabbitMQ 时使用的用户名，默认为guest
zipkin.collector.rabbitmq.virtual-host	RABBIT_VIRTUAL_HOST	使用的 RabbitMQ virtual host，默认为 /
zipkin.collector.rabbitmq.use-ssl	RABBIT_USE_SSL	设置为true则用 SSL 的方式与 RabbitMQ 建立链接
```