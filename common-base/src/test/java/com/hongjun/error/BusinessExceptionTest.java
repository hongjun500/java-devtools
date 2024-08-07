package com.hongjun.error;

import com.hongjun.enums.EnumBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Slf4j
class BusinessExceptionTest {

    @Test
    @Disabled
    void assertBusinessException() throws BusinessException {
        log.info("test-assertBusinessException------------------");
        log.info("hello world");

        BusinessException.assertBusinessException(true, EnumBusinessError.UNKNOWN_ERROR);
    }

    @Test
    @Disabled
    void testAssertBusinessException() throws BusinessException {
        BusinessException.assertBusinessException(true, EnumBusinessError.UNKNOWN_ERROR, "custom error message");
    }
}