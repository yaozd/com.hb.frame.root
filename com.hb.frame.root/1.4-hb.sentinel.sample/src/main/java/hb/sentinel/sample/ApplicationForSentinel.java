package hb.sentinel.sample;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"hb.sentinel.sample"})
public class ApplicationForSentinel {

    public static void main(String[] args) {

        SpringApplication.run(ApplicationForSentinel.class, args);
        initFlowQpsRule();
    }

    /**
     * 初始化限流规则
     * 可以通过gitlab做配置管理
     */
    private static void initFlowQpsRule() {
        String KEY="/api/foo/world";
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(KEY);
        // set limit qps to 20
        rule1.setCount(2);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
}