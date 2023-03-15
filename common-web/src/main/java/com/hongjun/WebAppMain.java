package com.hongjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hongjun500
 * @date 2022/9/29 11:06
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class WebAppMain {
	public static void main(String[] args) {

		SpringApplication.run(WebAppMain.class, args);
	}
}
