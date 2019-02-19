
## Feign传递请求头信息-通过拦截器-RequestInterceptor
- [springcloud fegin获取request header解决方案](https://blog.csdn.net/dounine/article/details/79976747)
- [myth-开源分布式事务-spring cloud插件](https://github.com/yu199195/myth.git)
```
org.dromara.myth.springcloud.configuration.MythRestTemplateConfiguration
@Configuration
public class MythRestTemplateConfiguration {
    /**
     * Request interceptor request interceptor.
     *
     * @return the request interceptor
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new MythFeignInterceptor();
    }
}
------------------
@Configuration
public class MythFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        final MythTransactionContext mythTransactionContext = TransactionContextLocal.getInstance().get();
        requestTemplate.header(CommonConstant.MYTH_TRANSACTION_CONTEXT,
                GsonUtils.getInstance().toJson(mythTransactionContext));
    }

}

```