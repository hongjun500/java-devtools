package com.hongjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest(classes = EsAppMain.class)
public class AppMainTest {

    @Test
    void testInit() {
        log.info("AppMainTest-----init");
    }
}
