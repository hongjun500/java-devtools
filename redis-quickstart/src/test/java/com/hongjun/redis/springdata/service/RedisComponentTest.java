package com.hongjun.redis.springdata.service;

import com.hongjun.redis.springdata.RedisConfiguration;
import com.hongjun.redis.springdata.config.RedisSerializerConfig;
import com.hongjun.redis.springdata.util.FileResourcesUtil;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DataRedisTest
@Import(value = {RedisComponent.class, RedisConfiguration.class, RedisSerializerConfig.class})
class RedisComponentTest {

    private final List<Map<String, String>> maps = FileResourcesUtil.readCSV("csv/tmdb_5000_movies.csv");
    private final String REDIS_TEMPLATE_STRING = "REDIS_TEMPLATE:STRING";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisComponent redisComponent;

    RedisComponentTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        Assertions.assertAll(() -> {
            assertNotNull(maps);
            assertNotNull(redisTemplate);
            // assertNotNull(redisTemplate2);
            assertNotNull(redisComponent);
        });
    }

    @Test
    void string() {
        maps.forEach(obj ->
            redisComponent.set(REDIS_TEMPLATE_STRING + ":" + obj.get("id"), obj.get("original_title"))
        );
        maps.forEach(obj -> {
            Assertions.assertEquals(obj.get("original_title"), redisComponent.get(REDIS_TEMPLATE_STRING + ":" + obj.get("id")));
            redisComponent.set(REDIS_TEMPLATE_STRING + ":" + obj.get("id"), Duration.ofSeconds(10));
        });
        Long expire = redisComponent.getExpire(REDIS_TEMPLATE_STRING + ":" + maps.get(0).get("id"));
        log.info("expire: {}", expire);
        Long expire1 = redisComponent.getExpire(REDIS_TEMPLATE_STRING + ":" + maps.get(0).get("id"), TimeUnit.MILLISECONDS);
        log.info("expire1: {}", expire1);
        Object andExpire = redisComponent.getAndExpire(REDIS_TEMPLATE_STRING + ":" + maps.get(0).get("id"), Duration.ofSeconds(10));
        log.info("andExpire: {}", andExpire);
    }



}