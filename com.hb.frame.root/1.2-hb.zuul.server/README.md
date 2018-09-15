##ZUUL参考：
- [史上最简单的SpringCloud教程 | 第五篇: 路由网关(zuul)(Finchley版本)](https://blog.csdn.net/forezp/article/details/81041012)

### ZUUL常见问题
- SpringCloud Feign消费Eureka服务报com.netflix.client.ClientException: Load balancer does not have available server for client: serviceprovider

HB-ORDER-SERVICE-PROVIDER所有的服务都不在线。
```
Cause:com.netflix.client.ClientException: Load balancer does not have available server for client: HB-ORDER-SERVICE-PROVIDER 
Exception:com.netflix.hystrix.exception.HystrixRuntimeException: HelloRemote#world(WorldDTO) failed and fallback failed.
```