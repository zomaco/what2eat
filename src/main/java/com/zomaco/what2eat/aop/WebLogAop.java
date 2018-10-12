package com.zomaco.what2eat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAop {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAop.class);

    private Long startTime;

    @Pointcut("execution(public * com.zomaco.what2eat.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime = System.currentTimeMillis();

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL          : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD  : " + request.getMethod());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("PARAMETER    : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE     : " + ret);
        logger.info("SPEND TIME   : " + (System.currentTimeMillis() - startTime));
    }

    @Pointcut("execution(public * com.zomaco.what2eat.controller..*.*(..))")
    public void serviceLog() {
    }

    @Before("serviceLog()")
    public void serviceStart(JoinPoint joinPoint) throws Throwable {
        logger.info("METHOD_START > {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("PARAMETER    > {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void serviceReturn(JoinPoint joinPoint, Object ret) throws Throwable {
        logger.info("RETURN       > {}", ret);
        logger.info("METHOD_END   > {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
