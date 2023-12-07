package com.hongjun.index.service;

import com.hongjun.index.Book;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Log4j2
class BaseIndexServiceTest {

    @Autowired
    BaseIndexService baseIndexService;


    @Test
    void initIndexAndMapping() {
        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Book.class);
        assert indexAndMapping;
    }



    @Test
    void delIndex() {
        boolean delIndex = baseIndexService.delIndex(Book.class);
        assert delIndex;
    }
}