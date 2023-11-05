package com.hongjun.service;

import com.hongjun.model.Movie;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/4/20
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
public interface MovieService {
	int saveMovies(List<Movie> movieList);
}
