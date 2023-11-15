package com.hongjun.error;

import com.hongjun.enums.EnumBusinessError;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class BusinessExceptionTest {

    @Test
    // @Disabled
    void assertBusinessException() throws BusinessException {
        log.info("test-assertBusinessException------------------");
        log.info("hello world");

        BusinessException.assertBusinessException(true, EnumBusinessError.UNKNOWN_ERROR);
    }

    @Test
    // @Disabled
    void testAssertBusinessException() throws BusinessException {
        BusinessException.assertBusinessException(true, EnumBusinessError.UNKNOWN_ERROR, "custom error message");
    }
}