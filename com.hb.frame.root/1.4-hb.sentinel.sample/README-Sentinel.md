
> [Sentinel 控制台-帮助文档](https://github.com/alibaba/Sentinel/wiki/控制台)
```
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar

java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.4.2.jar

java -jar  -Dserver.port=8080 -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard .\sentinel-dashboard-1.4.2.jar
----------------
sentinel-dashboard-1.4.2.jar=》百度云=》软件开发-JAVA=》J-S-Sentinel-流量控制=》sentinel-dashboard-1.4.2-bak-2019-02-27-1156.zip
```

> [sentinel控制台监控数据持久化【InfluxDB】-代码实现-github-地址](https://github.com/yaozd/Sentinel/tree/dev-yzd)
```
分支：dev-yzd
https://github.com/yaozd/Sentinel/tree/dev-yzd
https://github.com/yaozd/Sentinel/tree/dev-yzd
参考：
sentinel控制台监控数据持久化【InfluxDB】-推荐参考byArvin
https://www.cnblogs.com/cdfive2018/p/9914838.html
```
InfluxDB-按照时间查询
```
select * from sentinel_metric where time >='2019-11-01T10:00:00Z' 
select count(*) from sentinel_metric where time >='2019-11-01T10:00:00Z' 
```

> 
[Sentinel-Wiki-中文](https://github.com/alibaba/Sentinel/wiki/主流框架的适配)<br>
[Sentinel-如何使用](https://github.com/alibaba/Sentinel/wiki/如何使用)<br>
[Spring Cloud Alibaba Sentinel 的所有配置信息](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel)<br>
[Sentinel-动态规则扩展](https://github.com/alibaba/Sentinel/wiki/动态规则扩展)<br>
[Sentinel-主流框架的适配](https://github.com/alibaba/Sentinel/wiki/主流框架的适配)

> 示例-01
```
按照 Sentinel 控制台文档 启动控制台
PS:例如控制台的启动
java -jar -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard .\sentinel-dashboard-1.4.2.jar
--------------------

```

>
[自定义限流处理逻辑](https://blog.csdn.net/qq_36081696/article/details/86128487)<br>
[利用Spring Cloud Aalibaba Sentinel完成 Spring Cloud 应用的限流管理示例](https://blog.csdn.net/qq_36081696/article/details/86128487)<br>
[Dubbo使用Sentinel来对服务进行降级与限流-推荐参考byArvin](https://blog.csdn.net/pwh19920920/article/details/85252203)<br>
[sentinel-dubbo](https://github.com/pwh19920920/sentinel-dubbo)<br>
[sentinel-dubbo-备份yzd](https://github.com/yaozd/sentinel-dubbo)<br>
[sentinel控制台监控数据持久化【InfluxDB】-推荐参考byArvin](https://www.cnblogs.com/cdfive2018/p/9914838.html)<br>
[cdfive/Sentinel-数据持久化InfluxDB](https://github.com/cdfive/Sentinel/tree/winxuan_develop/sentinel-dashboard)<br>
[cdfive/Sentinel-数据持久化InfluxDB-备份yzd](https://github.com/yaozd/Sentinel)<br>

