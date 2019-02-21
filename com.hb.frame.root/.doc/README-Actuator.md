## Spring boot actuator 监控模块-actuator是监控系统健康情况的工具。
### [SpringBoot随笔（一）： spring-boot-starter-actuator 模块详解](https://blog.csdn.net/love3765/article/details/79291584)
```
五、其他端点介绍：
HTTP方法	路径	描述	鉴权
GET	/autoconfig	查看自动配置的使用情况	true
GET	/configprops	查看配置属性，包括默认配置	true
GET	/beans	查看bean及其关系列表	true
GET	/dump	打印线程栈	true
GET	/env	查看所有环境变量	true
GET	/env/{name}	查看具体变量值	true
GET	/health	查看应用健康指标	false
GET	/info	查看应用信息（需要自己在application.properties里头添加信息，比如info.contact.email=easonjim@163.com）	false
GET	/mappings	查看所有url映射	true
GET	/metrics	查看应用基本指标	true
GET	/metrics/{name}	查看具体指标	true
POST	/shutdown	关闭应用（要真正生效，得配置文件开启endpoints.shutdown.enabled: true）	true
GET	/trace	查看基本追踪信息	true
--------------------- 
原文：https://blog.csdn.net/love3765/article/details/79291584 
```
### [spring-boot-starter-actuator监控接口详解](https://www.jianshu.com/p/481134c3fab7)