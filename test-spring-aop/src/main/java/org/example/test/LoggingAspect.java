package org.example.test;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Around("execution(* *(..)) && @annotation(trace)")
	public Object trace(ProceedingJoinPoint joinPoint, Trace trace) throws Throwable {
		long t = System.currentTimeMillis();
		LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOGGER.debug("annotation: {}", trace);
		LOGGER.debug("jointPoint: {}", joinPoint);
		LOGGER.debug("method: {}", joinPoint.getSignature().getName());
		LOGGER.debug("arguments: {}", Arrays.toString(joinPoint.getArgs()));
		
		Object o = joinPoint.proceed();
		
		LOGGER.debug("time executed: {} milliseconds", (System.currentTimeMillis() - t));
		LOGGER.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		return o;
	}
	
	//http://unpocodejava.wordpress.com/2011/11/16/trabajando-con-aspectos-y-anotaciones-con-spring/

}
