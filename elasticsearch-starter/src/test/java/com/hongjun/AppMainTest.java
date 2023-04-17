package com.hongjun;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@Log4j2
@SpringBootTest(classes = EsAppMain.class)
public class AppMainTest {

    @Test
    void testInit() {
        log.info("AppMainTest-----init");
    }
}
