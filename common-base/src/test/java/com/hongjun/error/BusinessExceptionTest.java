package com.hongjun.error;

import com.hongjun.enums.EnumBusinessError;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class BusinessExceptionTest {

    @Test
    void assertBusinessException() throws BusinessException {
        log.info("test-assertBusinessException------------------");
        log.info("hello world");

        BusinessException.assertBusinessException(true, EnumBusinessError.UNKNOWN_ERROR);
    }

    @Test
    void testAssertBusinessException() {
    }
}