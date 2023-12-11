package com.hongjun.web.config.mvc;

import com.hongjun.web.config.mvc.converter.IntegerConverter;
import com.hongjun.web.filter.CustomFilter;
import com.hongjun.web.interceptor.CustomInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongjun500
 * @date 2022/12/30 13:43
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 接管 SpringMVC 的配置实现自定义的拦截器处理以及过滤器处理
 */
@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	private final IntegerConverter integerConverter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		CustomInterceptor customInterceptor = new CustomInterceptor();
		registry.addInterceptor(customInterceptor);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// 注册自定义的类型转换器
		registry.addConverter(integerConverter);
	}

	@Bean
	@Order
	public FilterRegistrationBean<CustomFilter> servletRegistrationBean() {
		FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>();
		bean.addUrlPatterns("/*");
		bean.setFilter(new CustomFilter());
		bean.setName("customFilter");
		return bean;
	}
}
