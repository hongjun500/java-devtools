package com.hongjun.es;

import com.hongjun.es.document.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author hongjun500
 * @date 2023/3/20
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
public interface PageMovieRepository extends PagingAndSortingRepository<Movie, String> {
}
