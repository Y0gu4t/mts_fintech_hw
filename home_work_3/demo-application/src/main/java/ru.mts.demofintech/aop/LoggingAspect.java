package ru.mts.demofintech.aop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.annotations.Logging;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {
    private static final String ENTER = "|--> {} {}";
    private static final String EXIT = "{} <--| {}";

    @Pointcut("execution(public * ru.mts..*(..))")
    public void publicMethods() {
    }

    @Around("@annotation(logging) && publicMethods()")
    public Object logMethods(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        Logger logger = LogManager.getLogger(fetchLogger(joinPoint));
        String annotationValue = fetchAnnotationValue(joinPoint, logging);
        if (logging.entering()) {
            logger
                    .atLevel(Level.getLevel(logging.level()))
                    .log(ENTER, annotationValue, logging.args() ? "args: " + Arrays.toString(joinPoint.getArgs()) : "");
        }

        Object result = joinPoint.proceed();

        if (logging.exiting()) {
            logger
                    .atLevel(Level.getLevel(logging.level()))
                    .log(EXIT, annotationValue, logging.exiting() ? "res: " + result : "");
        }
        return result;
    }

    private String fetchAnnotationValue(ProceedingJoinPoint joinPoint, Logging logging) {
        return Optional.of(logging)
                .map(Logging::value)
                .filter(v -> !v.isBlank())
                .orElse(joinPoint.getSignature().getName());
    }

    private Object fetchLogger(ProceedingJoinPoint joinPoint) {
        return Optional.of(joinPoint)
                .map(JoinPoint::getTarget)
                .orElseGet(joinPoint::getThis);
    }
}
