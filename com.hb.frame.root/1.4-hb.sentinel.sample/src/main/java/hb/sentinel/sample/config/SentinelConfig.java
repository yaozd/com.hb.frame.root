package hb.sentinel.sample.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.util.FilterUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SentinelConfig {
    /**
     * 自定义限流处理逻辑
     * https://blog.csdn.net/qq_36081696/article/details/86128487
     * #Spring Cloud Alibaba Sentinel 的所有配置信息
     * #https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel
     * @return
     */
    @Bean
    public UrlBlockHandler urlBlockHandler() {
        return new UrlBlockHandler() {
            @Override
            public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
                //官方提供实现逻辑：
                //FilterUtil.blockRequest(httpServletRequest, httpServletResponse);
                //客户自定义逻辑-PS:api 则返回json;html 则重定向页面
                PrintWriter out = httpServletResponse.getWriter();
                out.print("Blocked by Sentinel (flow limiting)-custom handler ");
                out.flush();
                out.close();
            }
        };
    }
}
