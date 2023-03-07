package com.hongjun.common.es.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "library-#{@indexNamePrefix}",createIndex = false)
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

