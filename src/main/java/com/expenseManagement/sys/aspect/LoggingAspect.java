package com.expenseManagement.sys.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * Logs method execution time for any method annotated with @LogExecutionTime
	 */
	@Around("@annotation(com.smartexpense.aspect.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed(); // execute the actual method
		long end = System.currentTimeMillis();

		logger.info("Execution time of {}.{}: {} ms", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), (end - start));

		return result;
	}

	/**
	 * Logs entry and exit of all service methods (optional)
	 */
	@Before("execution(* com.smartexpense.service.*.*(..))")
	public void logBeforeServiceCall(org.aspectj.lang.JoinPoint joinPoint) {
		logger.info("Entering Service Method: {}", joinPoint.getSignature());
	}

	@AfterReturning(pointcut = "execution(* com.smartexpense.service.*.*(..))", returning = "result")
	public void logAfterServiceCall(org.aspectj.lang.JoinPoint joinPoint, Object result) {
		logger.info("Exiting Service Method: {} | Returned: {}", joinPoint.getSignature(), result);
	}
}
