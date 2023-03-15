package com.hongjun.es;

import com.hongjun.es.document.Movie;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hongjun500
 * @date 2023/3/9
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
// @Repository
public interface MovieRepository extends ListCrudRepository<Movie, String> {
}
