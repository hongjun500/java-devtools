package com.hongjun.redis.connection.crud;

import com.alibaba.fastjson2.TypeReference;
import com.hongjun.redis.connection.JedisConnect;
import com.hongjun.redis.springdata.util.FileResourcesUtil;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JedisCrudTest {
    private final JedisConnect jedisConnect = new JedisConnect();
    private final JedisPooled jedisPooled = jedisConnect.getJedisPooled();
    private final JedisCrud jedisCrud = new JedisCrud();
    private final List<Map<String, String>> maps = FileResourcesUtil.readCSV("csv/tmdb_5000_movies.csv");
    private final String REDIS_KEY_LIST = "tmdb_5000_movies:List";

    private final String REDIS_KEY_HASH = "tmdb_5000_movies:Hash";
    private final String REDIS_KEY_SET = "tmdb_5000_movies:Set";
    private final String REDIS_KEY_ZSET = "tmdb_5000_movies:ZSet";


    JedisCrudTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        assertEquals(4803, maps.size());
        jedisPooled.del(REDIS_KEY_LIST + ":top10");
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void string() throws InterruptedException {
        final String REDIS_KEY_STRING = "tmdb_5000_movies:String";

        jedisCrud.set(REDIS_KEY_STRING + ":1", "This is a String");
        String res1 = jedisCrud.get(REDIS_KEY_STRING + ":1");
        log.info("res1: {}", res1);
        assertEquals("This is a String", res1);
        jedisCrud.set(REDIS_KEY_STRING + ":2", "This is a String 2", 2);
        String res2 = jedisCrud.get(REDIS_KEY_STRING + ":2");
        log.info("res2: {}", res2);
        assertEquals("This is a String 2", res2);
        Thread.sleep(2000);
        String res3 = jedisCrud.get(REDIS_KEY_STRING + ":2");
        log.info("res3: {}", res3);
        assertNull(res3);
        jedisCrud.mset(REDIS_KEY_STRING + ":3", "This is a String 3", REDIS_KEY_STRING + ":4", "This is a String 4");
        List<String> mget = jedisCrud.mget(REDIS_KEY_STRING + ":3", REDIS_KEY_STRING + ":4");
        log.info("mget: {}", mget);
        // todo Java8
        assertLinesMatch(List.of("This is a String 3", "This is a String 4"), mget);
        long incr = jedisCrud.incr(REDIS_KEY_STRING + ":incr");
        log.info("incr: {}", incr);
        assertEquals(1, incr);
        String getIncr = jedisCrud.get(REDIS_KEY_STRING + ":incr");
        log.info("getIncr: {}", getIncr);
        assertEquals("1", getIncr);
        long incrBy = jedisCrud.incrBy(REDIS_KEY_STRING + ":incrBy", -176);
        log.info("incrBy: {}", incrBy);
        assertEquals(-176, incrBy);
        String getIncrBy = jedisCrud.get(REDIS_KEY_STRING + ":incrBy");
        log.info("getIncrBy: {}", getIncrBy);
        assertEquals("-176", getIncrBy);

    }

    @Test
    void list() {
        final String top10 = ":top10";
        // todo Java8
        List<Map<String, String>> sortMaps = maps.stream().sorted(Comparator.comparing(r -> r.get("vote_average"))).toList();
        List<Map<String, String>> topMaps = sortMaps.subList(4793, 4803);
        topMaps.forEach(obj ->
                jedisCrud.lpush(REDIS_KEY_LIST + top10, CommonFastJsonUtil.toJson(obj))
        );
        assertEquals(10, jedisCrud.llen(REDIS_KEY_LIST + top10));
        List<String> resAll = jedisCrud.lrange(REDIS_KEY_LIST + top10, 0, -1);
        List<Map<String, String>> resAllMaps = resAll.stream().map(obj ->
                CommonFastJsonUtil.fromJson(obj, new TypeReference<Map<String, String>>() {
                })
        ).toList();
        resAllMaps.forEach(obj ->
                log.info("resAllMaps: {}", obj)
        );
        assertEquals(10, resAllMaps.size());
        String lpop = jedisCrud.lpop(REDIS_KEY_LIST + top10);
        log.info("lpop: {}", CommonFastJsonUtil.fromJson(lpop, Map.class));
        assertEquals(9, jedisCrud.llen(REDIS_KEY_LIST + top10));
        jedisCrud.lpush(REDIS_KEY_LIST + top10, lpop);
        assertEquals(10, jedisCrud.llen(REDIS_KEY_LIST + top10));
        String rpop = jedisCrud.rpop(REDIS_KEY_LIST + top10);
        log.info("rpop: {}", CommonFastJsonUtil.fromJson(rpop, Map.class));
        assertEquals(9, jedisCrud.llen(REDIS_KEY_LIST + top10));
        jedisCrud.rpush(REDIS_KEY_LIST + top10, rpop);
        assertEquals(10, jedisCrud.llen(REDIS_KEY_LIST + top10));

        String ltrim = jedisCrud.ltrim(REDIS_KEY_LIST + top10, 0, 4);
        log.info("ltrim: {}", ltrim);
        assertEquals("OK", ltrim);
        assertEquals(5, jedisCrud.llen(REDIS_KEY_LIST + top10));

        List<String> lrange2 = jedisCrud.lrange(REDIS_KEY_LIST + top10, 3, 3);
        assertEquals(1, lrange2.size());
        Map map = CommonFastJsonUtil.fromJson(lrange2.get(0), Map.class);
        assertEquals("25000000", map.get("budget"));
    }
}