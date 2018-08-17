package hb.insure.app.api.config;

import cn.hutool.crypto.SecureUtil;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import hb.insure.app.api.utils.exceptionExt.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zd.yao on 2018/7/24.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Exception ex) {
        HttpStatus status = getStatus(request);
        //未知异常信息
        //对于未知异常信息，可以把异常堆栈信息MD5，
        //然后通过钉钉发送MD5值预警或把MD5值发送到后台，让开发人员及时处理。
        //通过MD5值来追踪信息
        String errorMessage = getErrorMessageForInner(ex);
        String md5Val= SecureUtil.md5(errorMessage);
        logger.error(String.format("handleControllerException-[%s]-异常详细信息:",md5Val),ex);
        return ResponseEntity.status(status).body(errorMessage);
    }

    /***
     * 针对应用程序内部的异常-打印堆栈信息
     * @param ex
     * @return
     */
    private String getErrorMessageForInner(Exception ex) {
        return ExceptionUtil.exceptionToString(ex);
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerExceptionForHystrix(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        String errorMessage = getErrorMessageForHystrix(ex);
        logger.error("handleControllerExceptionForHystrix-异常详细信息:",errorMessage);
        return ResponseEntity.status(status).body(errorMessage);
        //logger.error("HystrixRuntimeException:",ex);
        //return ResponseEntity.status(status).body(ex);
    }
    /***
     * 针对feign远程调用与hystrix的异常
     */
    private String getErrorMessageForHystrix(Throwable ex) {
        String cause="";
        if(ex.getCause()!=null){
            cause=ex.getCause().getMessage();
        }
        StringBuilder sb=new StringBuilder();
        sb.append("Cause:").append(cause)
                .append(System.lineSeparator())
                .append("Exception:").append(ex.toString())
                .append(System.lineSeparator());
        return sb.toString();
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
