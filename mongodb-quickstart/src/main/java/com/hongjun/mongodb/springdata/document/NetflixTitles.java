package com.hongjun.mongodb.springdata.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author hongjun500
 * @date 2023/12/11 15:22
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: NetflixTitles document
 */
@Data
@Document(value = "netflix_titles")
public class NetflixTitles {

	@Id
	private String id;

	@Field(value = "show_id")
	private String showId;

	private String type;

	private String title;

	private String director;

	private String cast;

	private String country;

	@Field(value = "date_added")
	private String dateAdded;

	@Field(value = "release_year")
	private String releaseYear;

	private String rating;

	private String duration;

	@Field(value = "listed_in")
	private String listedIn;

	private String description;
}
