## 参考:
#### [ELK学习笔记-LogStash读取json日志分类型建立索引](https://www.cnblogs.com/lchb/articles/5731838.html)
#### [Spring Cloud Sleuth (2)-与ELK集成](https://blog.csdn.net/yejingtao703/article/details/78318234) 
#### [logback配置示例-包含生成json日志，与ELK整合](https://blog.csdn.net/KingBoyWorld/article/details/78917865) 
#### [spring cloud 微服务日志跟踪 sleuth logback elk 整合](https://www.cnblogs.com/zhyg/p/9167425.html) 
```
<springProperty scope="context" name="appName" source="spring.application.name" /><br>
<springProperty scope="context" name="ip" source="spring.cloud.client.ipAddress" />
```
完整配置
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <contextName>${HOSTNAME}</contextName>
  <property name="LOG_PATH" value="phantom-log" />
  <springProperty scope="context" name="appName" source="spring.application.name" />
  <springProperty scope="context" name="ip" source="spring.cloud.client.ipAddress" />
  <property name="CONSOLE_LOG_PATTERN"
            value="[%d{yyyy-MM-dd HH:mm:ss.SSS} ${ip} ${appName} %highlight(%-5level) %yellow(%X{X-B3-TraceId}),%green(%X{X-B3-SpanId}),%blue(%X{X-B3-ParentSpanId}) %yellow(%thread) %green(%logger) %msg%n"/>

  <appender name="FILEERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>../${LOG_PATH}/${appName}/${appName}-error.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>../${LOG_PATH}/${appName}/${appName}-error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>2MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <append>true</append>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>${CONSOLE_LOG_PATTERN}</pattern>
      <charset>utf-8</charset>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>error</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>

  <appender name="FILEWARN" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>../${LOG_PATH}/${appName}/${appName}-warn.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>../${LOG_PATH}/${appName}/${appName}-warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>2MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <append>true</append>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>${CONSOLE_LOG_PATTERN}</pattern>
      <charset>utf-8</charset>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>warn</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>

  <appender name="FILEINFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>../${LOG_PATH}/${appName}/${appName}-info.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>../${LOG_PATH}/${appName}/${appName}-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>2MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <append>true</append>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>${CONSOLE_LOG_PATTERN}</pattern>
      <charset>utf-8</charset>
    </encoder>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>info</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>

  <appender name="logstash" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>../${LOG_PATH}/${appName}/${appName}.json</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>../${LOG_PATH}/${appName}/${appName}-%d{yyyy-MM-dd}.json</fileNamePattern>
      <maxHistory>7</maxHistory>
    </rollingPolicy>
    <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
      <providers>
        <timestamp>
          <timeZone>UTC</timeZone>
        </timestamp>
        <pattern>
          <pattern>
            {
            "ip": "${ip}",
            "app": "${appName}",
            "level": "%level",
            "trace": "%X{X-B3-TraceId:-}",
            "span": "%X{X-B3-SpanId:-}",
            "parent": "%X{X-B3-ParentSpanId:-}",
            "thread": "%thread",
            "class": "%logger{40}",
            "message": "%message",
            "stack_trace": "%exception{10}"
            }
          </pattern>
        </pattern>
      </providers>
    </encoder>
  </appender>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>${CONSOLE_LOG_PATTERN}</pattern>
      <charset>utf-8</charset>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>debug</level>
    </filter>
  </appender>

  <logger name="org.springframework" level="INFO" />
  <logger name="org.hibernate" level="INFO" />
  <logger name="com.kingboy.repository" level="DEBUG" />

  <root level="INFO">
    <appender-ref ref="FILEERROR" />
    <appender-ref ref="FILEWARN" />
    <appender-ref ref="FILEINFO" />
    <appender-ref ref="logstash" />
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
--------------------- 
```