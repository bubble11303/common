package config;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import annotation.SysLog;
import constant.HeaderConstant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import utils.HttpRequestUtil;

@Order(99)
@Aspect
@Component
@Slf4j
// @EnableAsync(proxyTargetClass = true)
public class SysLogAspect {

    private static final String lineSeparator = System.lineSeparator();

    @Around("@annotation(annotation.SysLog)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();

        String traceId = MDC.get(HeaderConstant.traceId);

        // url
        String url = Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).map(ServletRequestAttributes::getRequest).map(HttpServletRequest::getRequestURI).orElse("");

        // 类名
        String className = point.getTarget().getClass().getName();
        // 方法名
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        SysLog annotation = method.getAnnotation(SysLog.class);
        String methodName = StringUtils.isBlank(annotation.value()) ? point.getSignature().getName() : annotation.value();
        // 请求IP地址
        String ip = HttpRequestUtil.getRealIpAddress();

        // 请求头
        Map<String, String> header = HttpRequestUtil.getHeader();

        // 请求参数
        String params = JSONUtil.toJsonStr(point.getArgs());

        // 返回值
        Object obj;

        String errorType = null;
        String errorMsg = null;
        try {
            obj = point.proceed();
        } catch (Exception e) {
            // 设置异常信息
            errorType = Arrays.toString(e.getStackTrace());
            errorMsg = e.getMessage();
            throw e;
        } finally {
            log.info("============================= start =============================");
            log.info("请求类名:【{}】", className);
            log.info("TraceID:【{}】", traceId);
            log.info("请求地址:【{}】", url);
            log.info("请求方法:【{}】", methodName);

            log.info("请求IP:【{}】", ip);
            log.info("请求头:【{}】", header);
            log.info("请求参数:【{}】", params);
            log.info("错误类型:【{}】", errorType);
            log.info("错误信息:【{}】", errorMsg);
            log.info("请求耗时:【{}】ms", System.currentTimeMillis() - startTime);
            log.info("============================= end ============================= {}", lineSeparator);
        }
        return obj;
    }


}
