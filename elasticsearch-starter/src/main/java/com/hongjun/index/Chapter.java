package com.hongjun.index;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author hongjun500
 * @date 2023/2/24 10:42
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 章节
 */
@Data
public class Chapter {
	@Field(type = FieldType.Integer)
	private Integer number;
	@Field(type = FieldType.Text)
	private String title;
}
