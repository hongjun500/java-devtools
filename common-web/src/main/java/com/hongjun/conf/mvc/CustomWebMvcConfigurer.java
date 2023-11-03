package com.hongjun.conf.mvc;

import com.hongjun.conf.mvc.converter.IntegerConverter;
import com.hongjun.filter.CustomFilter;
import com.hongjun.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongjun500
 * @date 2022/12/30 13:43
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 实现自定义的拦截器处理以及过滤器
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {


	@Autowired
	private IntegerConverter integerConverter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		CustomInterceptor customInterceptor = new CustomInterceptor();
		registry.addInterceptor(customInterceptor);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// WebMvcConfigurer.super.addFormatters(registry);
		// registry.addConverter(new IntegerConverter()
		// 注册自定义的类型转换器
		registry.addConverter(integerConverter);

	}

	@Bean
	public FilterRegistrationBean servletRegistrationBean() {
		CustomFilter customFilter = new CustomFilter();
		FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(customFilter);
		bean.setName("customFilter");
		bean.addUrlPatterns("/*");
		bean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return bean;
	}
}
