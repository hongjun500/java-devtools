package com.hongjun.conf.mvc.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * @author hongjun500
 * @date 2023/8/25 16:38
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: Integer 转换
 */
@Configuration
public class IntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		try {
			return Integer.parseInt(source.trim());
		} catch (Exception exp) {
			return -1;
		}
	}
}
