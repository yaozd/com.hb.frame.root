
#### Eureka
~~~
两个启动项都设置一下Program arguments，就是--spring.profiles.active分别设置为server1和server2，代表分别以两个不同的配置来启动项目。
然后把两个启动项都启动起来，分别访问各自的端口
eg:
--spring.profiles.active=peer1
===
java -Xmx100m  -jar hb.eureka.server-1.0-SNAPSHOT.jar --spring.profiles.active=peer1
java -Xmx100m  -jar hb.eureka.server-1.0-SNAPSHOT.jar --spring.profiles.active=peer2

~~~
#### Eureka单点配置
~~~
#
spring:
  application:
    name: HB_EUREKA_SERVER
#
server:
  port: 3333
#
eureka:
  environment: dev
  instance:
    hostname: localhost
    preferIpAddress: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}:${spring.application.name}
  client:
    #不要向注册中心注册自己
    register-with-eureka: false
    #禁止检索服务
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
  server:
    #过期实例应该启动并运行的时间间隔，单位为毫秒，默认为60 * 1000
    eviction-interval-timer-in-ms: 4000
    #打开保护机制
    enable-self-preservation: true
    #在Eureka服务器获取不到集群里对等服务器上的实例时，需要等待的时间，单位为毫秒，默认为1000 * 60 * 5
    waitTimeInMsWhenSyncEmpty: 0
~~~
#### Feign配置遇到的bean not found问题
~~~
spring cloud配置遇到的bean not found问题
https://www.jianshu.com/p/551c7c251f91
@EnableFeignClients({"cn.tearn.demobsdk.restful"})
~~~