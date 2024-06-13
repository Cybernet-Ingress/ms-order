package com.java.msorder.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class MonitoredAspect {

    @Before("execution(public * *(..)) && @within(Monitored)")
    public void beforeAdvice(JoinPoint joinPoint) {
        StringBuilder logString = new StringBuilder();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();
        List<String> paramNames = Arrays.asList(methodSignature.getParameterNames());
        Object[] arguments = joinPoint.getArgs();

        logString.append("STARTED ")
                .append("Class: ").append(className)
                .append(", Method: ").append(methodName)
                .append(", Params: [");

        StringBuilder params = new StringBuilder();
        for (int index = 0; index < arguments.length; index++) {
            String paramName = paramNames.get(index);
            String paramValue = arguments[index] != null ? arguments[index].toString() : "null";

            params.append("{name: ").append(paramName)
                    .append(", value: ").append(paramValue)
                    .append("}");

            if (index < arguments.length - 1) {
                params.append(", ");
            }
        }
        logString.append(params).append("]");
        log.info(logString.toString());
    }

    @AfterReturning(pointcut = "execution(public * *(..)) && @within(Monitored)", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        StringBuilder logString = new StringBuilder();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();

        logString.append("END ")
                .append("Class: ").append(className)
                .append(", Method: ").append(methodName)
                .append(", Returned: ");

        if (result != null) {
            String returnedStr = result.toString();
            logString.append("{name: RETURN")
                    .append(", value: ").append(returnedStr, 0, Math.min(returnedStr.length(), 100))
                    .append(returnedStr.length() < 100 ? "" : "...")
                    .append("}");
        } else {
            logString.append("null");
        }

        log.info(logString.toString());
    }

}