package com.hongjun.controller;

import com.hongjun.enums.EnumBusinessError;
import com.hongjun.error.BusinessException;
import com.hongjun.response.CommonReturnType;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2022/12/30 17:19
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: restController类的增强处理
 */
@Log4j2
@RestControllerAdvice(annotations = RestController.class)
public class BaseController implements ResponseBodyAdvice<Object> {

	/**
	 * 方法返回 true 后，会对所有的响应进行处理
	 * @param returnType the return type
	 * @param converterType the selected converter type
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType) || StringHttpMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// 处理空值
		if (body == null && StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
			return null;
		}



		//if true, need to translate
		/*if (returnType.hasMethodAnnotation(I18n.class)) {
			I18n i18n = returnType.getMethodAnnotation(I18n.class);
			body = translate(body, i18n.value());
		}*/

		if (!(body instanceof CommonReturnType<?>)) {
			if (body instanceof String) {
				return CommonFastJsonUtil.toJson(CommonReturnType.create(body));
			}
			// return CommonReturnType.create(body);
		}
		return body;
	}


	/**
	 * 定义exceptionHandler解决未被controller层吸收的exception
	 * @return obj
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object handlerException(Exception e){
		Map<String, Object> responseData = new HashMap<>();
		if (e instanceof BusinessException businessException){
			responseData.put("errCode", businessException.getErrCode());
			responseData.put("errMsg", businessException.getErrMsg());
		} else {
			responseData.put("errCode", EnumBusinessError.UNKNOWN_ERROR.getErrCode());
			responseData.put("errMsg", EnumBusinessError.UNKNOWN_ERROR.getErrMsg());
		}
		log.debug(e);
		log.warn("------错误信息----------{}----------",responseData.get("errMsg"));
		return CommonReturnType.create(responseData,"fail");
	}

}
