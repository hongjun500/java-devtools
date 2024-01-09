package com.hongjun.redis.springdata.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hongjun.redis.springdata.RedisConfiguration;
import com.hongjun.redis.springdata.config.RedisSerializerConfig;
import com.hongjun.redis.springdata.util.FileResourcesUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataRedisTest
@Import(value = {RedisComponent.class, RedisConfiguration.class, RedisSerializerConfig.class})
class RedisComponentTest {

    private final List<Map<String, String>> maps = FileResourcesUtil.readCSV("csv/tmdb_5000_movies.csv");
    private final String REDIS_TEMPLATE_STRING = "REDIS_TEMPLATE:STRING";
    private final String REDIS_TEMPLATE_HASH = "REDIS_TEMPLATE:HASH";
    public final String REDIS_TEMPLATE_LIST = "REDIS_TEMPLATE:LIST";

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

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


    @Test
    void hash() {

        maps.forEach(obj -> {
            redisComponent.setHash(REDIS_TEMPLATE_HASH, obj.get("id"), obj.get("original_title"));
        });
        Map<Object, Object> hash = redisComponent.getHash(REDIS_TEMPLATE_HASH);

        maps.forEach(obj ->
                Assertions.assertEquals(hash.get(obj.get("id")), obj.get("original_title"))
        );
        assertTrue(redisComponent.hasKey(REDIS_TEMPLATE_HASH, maps.get(0).get("id")));

        assertEquals(redisComponent.getHash(REDIS_TEMPLATE_HASH, "5"), "Four Rooms");
        List<Object> list = redisComponent.getHash(REDIS_TEMPLATE_HASH, Lists.newArrayList("5", "79", "146"));
        assertEquals(3, list.size());

        redisComponent.delete(REDIS_TEMPLATE_HASH, "5");

        Set<Object> keys = redisComponent.keys(REDIS_TEMPLATE_HASH);
        assertEquals(4802, keys.size());
    }

    @Test
    void list() {
       /* maps.forEach(obj -> {
            HashMap<Object, Object> map = new HashMap<>(obj);
            redisComponent.leftPush(REDIS_TEMPLATE_LIST + ":v1", map);
        });*/
        List<Serializable> list = maps.stream()
                .map(map -> (Serializable) map)
                // todo Java 8
                .toList();

        Long count = redisComponent.leftPushAll(REDIS_TEMPLATE_LIST, list);
        assertEquals(4803, count);
        Serializable first = redisComponent.index(REDIS_TEMPLATE_LIST, 0);
        Long removed = redisComponent.remove(REDIS_TEMPLATE_LIST, 1, first);
        assertEquals(1, removed);
        assertEquals(4802, redisComponent.size(REDIS_TEMPLATE_LIST));
        redisComponent.set(REDIS_TEMPLATE_LIST, 0, first);
        Serializable leftedPop = redisComponent.leftPop(REDIS_TEMPLATE_LIST);
        assertEquals(first, leftedPop);
        List<Serializable> range = redisComponent.range(REDIS_TEMPLATE_LIST, 0, 10);
        assertEquals(11, range.size());
    }

    @AfterEach
    void afterAll() {
        redisComponent.delete(REDIS_TEMPLATE_STRING);
        redisComponent.delete(REDIS_TEMPLATE_HASH);
        redisComponent.delete(REDIS_TEMPLATE_LIST);
    }
}