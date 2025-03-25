package com.hongjun.mongodb.springdata.dao;

import java.util.List;

import com.hongjun.mongodb.common.conf.MongoConfig;
import com.hongjun.mongodb.springdata.document.NetflixTitles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hongjun500
 * @date 2025-03-25 21:50
 * @tool ThinkPadX1隐士
 * Created with 2024.1.6.IntelliJ IDEA
 * Description:
 */
@DataMongoTest
@ImportAutoConfiguration(MongoConfig.class)
class NetflixTitlesDaoTest {

    @Autowired
    private NetflixTitlesDao netflixTitlesDao;

    @Test
    void context() {
        assert netflixTitlesDao != null;
    }

    @Test
    void daoTest() {
        List<NetflixTitles> all = netflixTitlesDao.findAll();
        assert !all.isEmpty();
    }
}