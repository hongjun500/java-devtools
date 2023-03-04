package com.hongjun.index.service;

import com.hongjun.AppMain;
import com.hongjun.index.base.BaseIndexService;
import com.hongjun.index.document.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AppMain.class)
class BaseIndexServiceTest {

    @Autowired
    BaseIndexService baseIndexService;

    @Test
    void initIndexAndMapping() {
        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Movie.class);
        assert indexAndMapping;
    }


}