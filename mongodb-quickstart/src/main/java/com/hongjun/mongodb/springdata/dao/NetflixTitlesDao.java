package com.hongjun.mongodb.springdata.dao;

import com.hongjun.mongodb.common.CommonRepository;
import com.hongjun.mongodb.springdata.document.NetflixTitles;
import org.springframework.stereotype.Repository;

/**
 * @author hongjun500
 * @date 2025-03-25 21:34
 * @tool ThinkPadX1隐士
 * Created with 2024.1.6.IntelliJ IDEA
 * Description:
 */
@Repository
public interface NetflixTitlesDao extends CommonRepository<NetflixTitles, String> {

}
