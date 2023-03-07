package com.hongjun.common;

import com.hongjun.QuickStarterApplication;
import com.hongjun.common.es.index.Book;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
public class CommonEsTest {

    @Autowired
    private BaseIndexService baseIndexService;

    @Test
    void testIndex() {

        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Book.class);
        assert indexAndMapping;
    }

    @Test
    void delIndex() {
        boolean indexAndMapping = baseIndexService.delIndex(Book.class);
        assert indexAndMapping;
    }
}
