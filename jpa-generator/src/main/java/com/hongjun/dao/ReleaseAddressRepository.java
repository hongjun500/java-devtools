package com.hongjun.dao;

import com.hongjun.model.ReleaseAddress;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hongjun500
 * @date 2023/4/20 14:11
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Repository
public interface ReleaseAddressRepository extends ListCrudRepository<ReleaseAddress, Integer> {
}
