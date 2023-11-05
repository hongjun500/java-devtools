package com.hongjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author hongjun500
 * @date 2023/2/20 15:51
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCaching
public class QuickStarterApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStarterApplication.class, args);
	}
}
