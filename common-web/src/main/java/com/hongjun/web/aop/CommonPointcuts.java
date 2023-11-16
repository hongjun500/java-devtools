package com.hongjun.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hongjun500
 * @date 2023/3/14 17:05
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:共享命名切入点
 */
@Aspect
@Component
public class CommonPointcuts {

	/**
	 * 带有PostMapping注释的方法切入点
	 */
	@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void inWebLayer() {}

	/**
	 * 带有PostMapping注释的方法
	 */
	@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postMapping(){}

	/**
	 * 带有PostMapping注释的方法
	 */
	@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getMapping(){}
}
