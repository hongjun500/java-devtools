package com.hongjun.interceptor;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.hongjun.enums.EnumBusinessError;
import com.hongjun.error.BusinessException;
import com.hongjun.req.CustomHttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hongjun.constant.CustomConstants.HEAD_PARAMS;

/**
 * @author hongjun500
 * @date 2022/12/29 17:14
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义的拦截器
 */
@Log4j2
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
	 * @param request
	 * @param handlerMethod
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
				// 这里断言是post请求并且body里面必须要有参数
				BusinessException.assertBusinessException(StrUtil.isBlank(body), EnumBusinessError.PARAMETER_REQUEST_ERROR);
				log.info("fill userInfo to request body successful");
				// 忽略空的值
				JSONObject jsonObject  = JSONUtil.parseObj(body, true);
				// 将需要处理的各个参数放到请求体中
				for (String headParam : HEAD_PARAMS) {
					String headerParamValue = request.getHeader(headParam);
					log.info("拦截器处理放到请求体中的参数:{},对应的值:{}", headParam, headerParamValue);
					jsonObject.putOpt(headParam, headerParamValue);
				}
				customHttpServletRequestWrapper.setBody(JSONUtil.toJsonStr(jsonObject));
			}
		}
	}
}
