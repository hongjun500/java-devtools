package com.hongjun.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/2/24 10:25
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 书籍
 */
@Data
@Document(indexName = "library",createIndex = false)
public class Book {
	@Id
	// @Field(type = FieldType.Long)
	private Long id;

	@Field(type = FieldType.Keyword)
	private String title;

	@Field(type = FieldType.Text)
	private String originalTitle;

	@Field(type = FieldType.Keyword)
	private String kew;

	/*@Field(type = FieldType.Object)
	private Publisher publisher;


	@Field(type = FieldType.Nested)
	private List<Chapter> chapters;*/
}
