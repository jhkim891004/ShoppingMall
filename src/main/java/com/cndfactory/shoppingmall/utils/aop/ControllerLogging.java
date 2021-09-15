package com.cndfactory.shoppingmall.utils.aop;

import com.cndfactory.shoppingmall.utils.exception.BusinessException;
import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class ControllerLogging {
	@Pointcut("execution(public * com.cndfactory.shoppingmall.web.controller..*Controller.*(..))")
	public void controller() {}

	@Around("controller()")
	public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		ObjectMapper mapper = new ObjectMapper();
		Object requestParams = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getParam(joinPoint));

		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = CustomUserDetails.setAnonymousUser();
		if(authentication.getName() != null && !authentication.getName().equals("anonymousUser")) {
			userDetails = (CustomUserDetails) authentication.getPrincipal();
		}

		try {
			return joinPoint.proceed(joinPoint.getArgs());
		} catch(BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		} finally {
			long end = System.currentTimeMillis();
			log.info("\n" +
							"[{}] - {}\n" +
							"Login Id: {} - {}\n" +
							"Running time: {}ms\n" +
							"Request method: {}\n" +
							"Request body: {}\n" +
							"Response body: {}",
					request.getMethod(), request.getRequestURI(),
					userDetails.getUsername(), userDetails.getAuthorities().stream().collect(Collectors.toList()),
					(end-start),
					joinPoint.getSignature().toShortString(),
					requestParams,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(joinPoint.proceed(joinPoint.getArgs())));
		}
	}

	private Map getParam(JoinPoint joinPoint) {
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] parameterNames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		Map<String, Object> params = new HashMap<>();

		for(int i=0; i<parameterNames.length; i++) {
			params.put(parameterNames[i], args[i]);
		}
		return params;
	}
}
