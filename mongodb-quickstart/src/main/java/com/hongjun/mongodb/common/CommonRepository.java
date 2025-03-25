package com.hongjun.mongodb.common;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author hongjun500
 * @date 2025-03-25 21:24
 * @tool ThinkPadX1隐士
 * Created with 2024.1.6.IntelliJ IDEA
 * Description:
 */
@NoRepositoryBean
public interface CommonRepository<T, ID> extends
        ListPagingAndSortingRepository<T, ID>,
        ListCrudRepository<T, ID> {

    Class<T> getTClass();

    MongoTemplate getMt();
}
