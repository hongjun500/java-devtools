package com.hongjun.web.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hongjun.enums.EnumBusinessError;
import com.hongjun.error.BusinessException;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import com.hongjun.web.constant.CustomConstants;
import com.hongjun.web.request.CustomHttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author hongjun500
 * @date 2022/12/29 17:14
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义的拦截器
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BusinessException {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		pushUserInfoToBody(request, (HandlerMethod) handler);
		return true;
	}

	// Controller执行之后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	}

	/**
	 * 将请求头中的参数放到请求体中
	 * @param request 请求
	 * @param handlerMethod 请求执行的方法
	 */
	private void pushUserInfoToBody(HttpServletRequest request, HandlerMethod handlerMethod) throws BusinessException  {
		// 请求的参数
		MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
		if (ArrayUtil.isEmpty(methodParameters)) {
			return;
		}
		for (MethodParameter methodParameter : methodParameters) {
			Class<?> parameterType = methodParameter.getParameterType();
			// 使用了@RequestBody注解
			List<Annotation> collect = Arrays.stream(methodParameter.getParameterAnnotations()).filter(obj -> obj.annotationType().equals(RequestBody.class)).toList();
			if (CollUtil.isEmpty(collect)) {
				continue;
			}
			if (request instanceof CustomHttpServletRequestWrapper customHttpServletRequestWrapper) {
				String body = customHttpServletRequestWrapper.getBody();
				// 这里断言是 post 请求并且 body 里面必须要有参数
				BusinessException.assertBusinessException(StrUtil.isBlank(body), EnumBusinessError.PARAMETER_REQUEST_ERROR);
				// 这里断言请求体中的对象必须是多个属性，不能出现整个请求体都是一个属性值
				// if (JSONUtil.isTypeJSON(body)) {
				if (JSON.isValid(body)) {
					log.info("fill userInfo to request body successful");
					// 忽略空的值

					JSONObject jsonObject  = CommonFastJsonUtil.toJsonObject(body);
					// 将需要处理的各个参数放到请求体中
					for (String headParam : CustomConstants.HEAD_PARAMS) {
						String headerParamValue = request.getHeader(headParam);
						log.info("拦截器处理放到请求体中的参数:{},对应的值:{}", headParam, headerParamValue);
						jsonObject.putIfAbsent(headParam, headerParamValue);
					}
					customHttpServletRequestWrapper.setBody(CommonFastJsonUtil.toJson(jsonObject));
				}
			}
		}
	}
}
