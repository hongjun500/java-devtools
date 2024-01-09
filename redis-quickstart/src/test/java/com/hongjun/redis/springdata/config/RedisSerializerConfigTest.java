package com.hongjun.redis.springdata.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(RedisSerializerConfig.class)
class RedisSerializerConfigTest {

    @Test
    void contextLoads() {
    }

}