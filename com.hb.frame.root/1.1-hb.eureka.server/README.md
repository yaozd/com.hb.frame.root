### Eureka服务注册和服务发现流程
```
Eureka服务注册和服务发现流程

==Eureka服务端
1.
registry是一个ConcurrentHashMap
2.(response-cache-auto-expiration-in-seconds)
readWriteCacheMap失效过期后从registry重新读取注册服务信息
3.(response-cache-update-interval-ms)
readCacheMap，把readWriteCacheMap的缓存更新到readCacheMap
(注：eureka服务器端数据增量更新的时间=清理时间【eviction-interval-timer-in-ms】+ 把readWriteCacheMap的缓存更新到readCacheMap【response-cache-update-interval-ms】)
==Eureka客户端
4.(registry-fetch-interval-seconds)
client读取的是readCacheMap，client刷新本地缓存
5.(ServerListRefreshInterval)
client刷新ribbon本地缓存
```
### eureka服务注册和服务发现时间配置
- [eureka服务注册和服务发现时间配置](https://www.2cto.com/kf/201804/739965.html)
- [深入理解Eureka缓存机制](https://blog.csdn.net/u012394095/article/details/80894140)
- [spring eureka 服务实例实现快速下线快速感知快速刷新配置解析](https://blog.csdn.net/zhxdick/article/details/78560993?from=singlemessage)
- [Spring Cloud 极端续租间隔时间的影响](https://mp.weixin.qq.com/s/6iSw2FnvdkupisDWgFUbnQ)
- [Eureka强制下线](https://blog.csdn.net/u012394095/article/details/80996172)

```
原因分析
服务提供者和服务调用者配置不够灵敏，总是服务提供者在停掉很久之后，服务调用者很长时间并没有感知到变化的原因：
EurekaServer自己的ReadWriteMap缓存失效延迟，刷新到ReadOnlyMap的延迟，服务调用者自己本地缓存刷新的延迟。

服务已经注册上去了，但是服务调用方很长时间还是调用不到，发现不了这个服务：
刷新到ReadOnlyMap的延迟，服务调用者自己本地缓存刷新的延迟

解决方案
EurekaServer修改如下配置：
==
#eureka server刷新readCacheMap的时间，注意，client读取的是readCacheMap，这个时间决定了多久会把readWriteCacheMap的缓存更新到readCacheMap上
#默认0
eureka.server.response-cache-update-interval-ms=30000
 
#eureka server缓存readWriteCacheMap失效时间，这个只有在这个时间过去后缓存才会失效，失效前不会更新，过期后从registry重新读取注册服务信息，registry是一个ConcurrentHashMap。
#由于启用了evict其实就用不太上改这个配置了
#默认180s
eureka.server.response-cache-auto-expiration-in-seconds=180
 
#启用主动失效，并且每次主动失效检测间隔为30s，默认为0
eureka.server.eviction-interval-timer-in-ms=30000
==
Eureka服务提供方修改如下配置：
#服务过期时间配置,超过这个时间没有接收到心跳EurekaServer就会将这个实例剔除
#注意，EurekaServer一定要设置eureka.server.eviction-interval-timer-in-ms否则这个配置无效，这个配置一般为服务刷新时间配置的三倍
#默认90s
eureka.instance.lease-expiration-duration-in-seconds=15
#服务刷新时间配置，每隔这个时间会主动心跳一次
#默认30s
eureka.instance.lease-renewal-interval-in-seconds=5
==
Eureka服务调用方修改如下配置：
#eureka client刷新本地缓存时间
#默认30s
eureka.client.registry-fetch-interval-seconds=5
#eureka客户端ribbon刷新时间
#默认30s
ribbon.ServerListRefreshInterval=5000
```
### [Erueka状态变更说明](https://blog.csdn.net/u012394095/article/details/80981523)
````
状态说明
STARTING ： 表示服务正在启动中

DOWN： 表示服务已经宕机，无法继续提供服务

UP ： 服务正常运行

OUT_OF_SERVICE : 不再提供服务，其他的Eureka Client将调用不到该服务，一般有人为的调用接口设置的，如：强制下线。

UNKNOWN： 未知状态

状态变更
容器启动
在容器刚刚启动，实例化instance信息的时候，默认状态为STARTING
````
### Eureka-RESTFUL接口
```
//增量
//http://peer2:8762/eureka/apps/delta
//全量
//http://peer2:8762/eureka/apps
```