package com.hongjun;

import com.hongjun.index.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/2/24 10:46
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findBooksById(Long id);
}
