package com.hongjun;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongjun500
 * @date 2023/4/14 15:27
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

public class GeneratorApp {
	public static void main(String[] args) {
		System.out.println("hello generator");
		generator();
	}

	public static void generator() {
		List<String> warnings = new ArrayList<>();
		// 是否应许覆盖文件
		boolean overwrite = true;
		try {
			InputStream resourceAsStream = MyBatisGenerator.class.getResourceAsStream("/mybatis-dynamicsql-generator.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(resourceAsStream);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);

			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

			myBatisGenerator.generate(null);
			warnings.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
