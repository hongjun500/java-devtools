package com.hongjun.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hongjun500
 * @date 2023/3/14 17:05
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:共享命名切入点
 */
@Aspect
public class CommonPointcuts {

	/**
	 * 带有 @RequestMapping 注释的类
	 */
	@Pointcut(value = "@within(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}

	/**
	 * 带有 @RestController 注释的类
	 */
	@Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController)")
	public void restController() {
	}

	/**
	 * 带有 @PostMapping 注释的方法
	 */
	@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postMapping() {
	}
}
