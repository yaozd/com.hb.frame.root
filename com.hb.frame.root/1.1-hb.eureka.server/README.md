### Eureka服务注册和服务发现流程
```
Eureka服务注册和服务发现流程

==Eureka服务端
0.
清理时间【eviction-interval-timer-in-ms】
把registry中的数据，刷新到readWriteCacheMap中
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
拉取增量信息更新到client的本地缓存
5.(ServerListRefreshInterval)
client刷新ribbon本地缓存
把client本地缓存中的增量信息更新到ribbon中
```
### eureka服务注册和服务发现时间配置
- [eureka服务注册和服务发现时间配置](https://www.2cto.com/kf/201804/739965.html)
- [深入理解Eureka缓存机制](https://blog.csdn.net/u012394095/article/details/80894140)
- [spring eureka 服务实例实现快速下线快速感知快速刷新配置解析-byArvin推荐参考](https://blog.csdn.net/zhxdick/article/details/78560993?from=singlemessage)
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

OUT_OF_SERVICE : 不再提供服务，但其服务依然正常运行，其他的Eureka Client将调用不到该服务，一般有人为的调用接口设置的，如：强制下线。

UNKNOWN： 未知状态

状态变更
容器启动
在容器刚刚启动，实例化instance信息的时候，默认状态为STARTING
````
### Eureka-RESTFUL接口
- [增量-http://peer2:8762/eureka/apps/delta](http://peer2:8762/eureka/apps/delta)
- [全量-http://peer2:8762/eureka/apps](http://peer2:8762/eureka/apps)
```
//增量
//http://peer2:8762/eureka/apps/delta
//全量
//http://peer2:8762/eureka/apps
```
### Eureka强制下线-线上项目发版时使用
- [Eureka强制下线](https://blog.csdn.net/u012394095/article/details/80996172)

```
eg:
postman put http://peer2:8762/eureka/apps/HB-INSURE-APP-API/192.168.0.229:9001/status?value=OUT_OF_SERVICE
postman put http://peer2:8762/eureka/apps/HB-ORDER-SERVICE-PROVIDER/192.168.0.229:9000/status?value=OUT_OF_SERVICE
===
实现方式
调用接口：/eureka/apps/appID/instanceID/status?value=OUT_OF_SERVICE
调用示例：http://101.37.33.252:8083/eureka/apps/EUREKA-1/10.28.144.127:17101/status?value=OUT_OF_SERVICE
调用方式：PUT
```
### Eureka线上发版流程（此功能需要开发相应管理界面）
```
一：Eureka服务端操作
1.服务强制下线
OUT_OF_SERVICE : 不再提供服务，但其服务依然正常运行
postman put http://peer2:8762/eureka/apps/HB-ORDER-SERVICE-PROVIDER/192.168.0.229:9000/status?value=OUT_OF_SERVICE
2.查看全量与增量信息中是否已经更新完成
//增量
//http://peer2:8762/eureka/apps/delta
//全量
//http://peer2:8762/eureka/apps
===
二：Eureka客户端操作
1.手工拉取增量信息，快速刷新服务列表
http://localhost:9001/api/server/fun2
2.验证（此验证只是在单服务下）
http://localhost:9001/v1/api/hello/world
3.关闭程序（不要使用kill 9关闭。）
```
