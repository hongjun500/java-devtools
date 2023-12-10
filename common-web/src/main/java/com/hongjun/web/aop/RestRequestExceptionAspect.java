package com.hongjun.web.aop;

import com.hongjun.error.BusinessException;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @author hongjun500
 * @date 2023/2/1 15:05
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: rest 请求异常时的参数和响应
 */

@Slf4j
@Aspect
@Order(value = 2)
@Component
@RequiredArgsConstructor
public class RestRequestExceptionAspect {

	/**
	 * 环绕通知
	 */
	@Around(value = "com.hongjun.web.aop.CommonPointcuts.restController()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		Object[] reqArgs = pjp.getArgs();
		log.debug("请求的地址:{},\n请求的参数{}", request.getRequestURI(), Arrays.toString(reqArgs));
		try {
			Object proceed = pjp.proceed(reqArgs);
			log.debug("响应结果:{}", CommonFastJsonUtil.toJson(proceed));
			return proceed;
		} catch (BusinessException businessException) {
			String errMsg = businessException.getErrMsg();
			log.error("业务异常{}\t exception:{}", parseMethodInfo(pjp), errMsg);
			BusinessException.assertBusinessException(true, businessException);
		}
		return null;
	}

	private String parseMethodInfo(ProceedingJoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		String[] params = methodSignature.getParameterNames();
		StringBuilder builder = new StringBuilder(joinPoint.getSignature().toString() + "参数: ");
		try {
			for (int i = 0; i < args.length; i++) {
				builder.append(",").append(params[i]).append(":").append(CommonFastJsonUtil.toJson(args[i]));
			}
		} catch (Throwable ex) {
			// ignore
		}
		return builder.toString();
	}
}
