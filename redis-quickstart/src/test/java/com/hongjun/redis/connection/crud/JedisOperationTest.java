package com.hongjun.redis.connection.crud;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Maps;
import com.hongjun.redis.connection.JedisConnect;
import com.hongjun.redis.springdata.util.FileResourcesUtil;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JedisOperationTest {
    private final JedisConnect jedisConnect = new JedisConnect();
    private final JedisPooled jedisPooled = jedisConnect.getJedisPooled();
    private final JedisOperation jedisOperation = new JedisOperation();
    private final List<Map<String, String>> maps = FileResourcesUtil.readCSV("csv/tmdb_5000_movies.csv");
    private final String REDIS_KEY_LIST = "tmdb_5000_movies:List";

    private final String REDIS_KEY_HASH = "tmdb_5000_movies:Hash";
    private final String REDIS_KEY_SET = "tmdb_5000_movies:Set";
    private final String REDIS_KEY_ZSET = "tmdb_5000_movies:ZSet";


    JedisOperationTest() throws IOException {
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
    void strings() throws InterruptedException {
        final String REDIS_KEY_STRING = "tmdb_5000_movies:String";

        jedisOperation.set(REDIS_KEY_STRING + ":1", "This is a String");
        String res1 = jedisOperation.get(REDIS_KEY_STRING + ":1");
        log.info("res1: {}", res1);
        assertEquals("This is a String", res1);
        jedisOperation.set(REDIS_KEY_STRING + ":2", "This is a String 2", 2);
        String res2 = jedisOperation.get(REDIS_KEY_STRING + ":2");
        log.info("res2: {}", res2);
        assertEquals("This is a String 2", res2);
        Thread.sleep(2000);
        String res3 = jedisOperation.get(REDIS_KEY_STRING + ":2");
        log.info("res3: {}", res3);
        assertNull(res3);
        jedisOperation.mset(REDIS_KEY_STRING + ":3", "This is a String 3", REDIS_KEY_STRING + ":4", "This is a String 4");
        List<String> mget = jedisOperation.mget(REDIS_KEY_STRING + ":3", REDIS_KEY_STRING + ":4");
        log.info("mget: {}", mget);
        // todo Java8
        assertLinesMatch(List.of("This is a String 3", "This is a String 4"), mget);
        long incr = jedisOperation.incr(REDIS_KEY_STRING + ":incr");
        log.info("incr: {}", incr);
        assertEquals(1, incr);
        String getIncr = jedisOperation.get(REDIS_KEY_STRING + ":incr");
        log.info("getIncr: {}", getIncr);
        assertEquals("1", getIncr);
        long incrBy = jedisOperation.incrBy(REDIS_KEY_STRING + ":incrBy", -176);
        log.info("incrBy: {}", incrBy);
        assertEquals(-176, incrBy);
        String getIncrBy = jedisOperation.get(REDIS_KEY_STRING + ":incrBy");
        log.info("getIncrBy: {}", getIncrBy);
        assertEquals("-176", getIncrBy);

    }

    @Test
    void lists() {
        final String top10 = ":top10";
        // todo Java8
        List<Map<String, String>> sortMaps = maps.stream().sorted(Comparator.comparing(r -> r.get("vote_average"))).toList();
        List<Map<String, String>> topMaps = sortMaps.subList(4793, 4803);
        topMaps.forEach(obj ->
                jedisOperation.lpush(REDIS_KEY_LIST + top10, CommonFastJsonUtil.toJson(obj))
        );
        assertEquals(10, jedisOperation.llen(REDIS_KEY_LIST + top10));
        List<String> resAll = jedisOperation.lrange(REDIS_KEY_LIST + top10, 0, -1);
        List<Map<String, String>> resAllMaps = resAll.stream().map(obj ->
                CommonFastJsonUtil.fromJson(obj, new TypeReference<Map<String, String>>() {
                })
        ).toList();
        resAllMaps.forEach(obj ->
                log.info("resAllMaps: {}", obj)
        );
        assertEquals(10, resAllMaps.size());
        String lpop = jedisOperation.lpop(REDIS_KEY_LIST + top10);
        log.info("lpop: {}", CommonFastJsonUtil.fromJson(lpop, Map.class));
        assertEquals(9, jedisOperation.llen(REDIS_KEY_LIST + top10));
        jedisOperation.lpush(REDIS_KEY_LIST + top10, lpop);
        assertEquals(10, jedisOperation.llen(REDIS_KEY_LIST + top10));
        String rpop = jedisOperation.rpop(REDIS_KEY_LIST + top10);
        log.info("rpop: {}", CommonFastJsonUtil.fromJson(rpop, Map.class));
        assertEquals(9, jedisOperation.llen(REDIS_KEY_LIST + top10));
        jedisOperation.rpush(REDIS_KEY_LIST + top10, rpop);
        assertEquals(10, jedisOperation.llen(REDIS_KEY_LIST + top10));

        String ltrim = jedisOperation.ltrim(REDIS_KEY_LIST + top10, 0, 4);
        log.info("ltrim: {}", ltrim);
        assertEquals("OK", ltrim);
        assertEquals(5, jedisOperation.llen(REDIS_KEY_LIST + top10));

        List<String> lrange2 = jedisOperation.lrange(REDIS_KEY_LIST + top10, 3, 3);
        assertEquals(1, lrange2.size());
        Map map = CommonFastJsonUtil.fromJson(lrange2.get(0), Map.class);
        assertEquals("25000000", map.get("budget"));
    }

    @Test
    void sets() {
        final String genre = ":genre";
        final String keyword = ":keyword";
        // todo Java8
        List<Map<String, String>> sortMaps = maps.stream().filter(obj -> obj.get("original_language").equals("zh")).sorted(Comparator.comparing(v -> v.get("vote_average"))).toList();
        sortMaps.forEach(obj -> {
            String id = obj.get("id");
            List<JSONObject> genres = CommonFastJsonUtil.fromJson(JSONObject.class, obj.get("genres"));
            List<JSONObject> keywords = CommonFastJsonUtil.fromJson(JSONObject.class, obj.get("keywords"));
            if (!CollectionUtils.isEmpty(genres)) {
                jedisOperation.sadd(REDIS_KEY_SET + ":" + id + genre, genres.stream().map(o -> o.getString("name")).toArray(String[]::new));
            }
            if (!CollectionUtils.isEmpty(keywords)) {
                jedisOperation.sadd(REDIS_KEY_SET + ":" + id + keyword, keywords.stream().map(o -> o.getString("name")).toArray(String[]::new));
            }
        });

        // Set<String> smembers1 = jedisOperation.smembers(REDIS_KEY_SET + ":79" + genre);
        // assertEquals(4, smembers1.size());

        jedisOperation.sscan(REDIS_KEY_SET + ":79" + genre, "0").forEach(obj ->
            log.info("sscan: {}", obj)
        );

        boolean drama = jedisOperation.sismember(REDIS_KEY_SET + ":79" + genre, "Drama");
        assertTrue(drama);
        jedisOperation.srem(REDIS_KEY_SET + ":79" + genre, "Drama");
        Set<String> sinter = jedisOperation.sinter(REDIS_KEY_SET + ":79" + genre, REDIS_KEY_SET + ":146" + genre);
        assertIterableEquals(Set.of("Adventure","Action"), sinter);

        Set<String> sunion = jedisOperation.sunion(REDIS_KEY_SET + ":79" + keyword, REDIS_KEY_SET + ":146" + keyword);
        sunion.forEach(obj ->
            log.info("sunion: {}", obj)
        );
        assertEquals(12, sunion.size());
        long scard = jedisOperation.scard(REDIS_KEY_SET + ":79" + keyword);
        assertEquals(4, scard);
    }

    @Test
    void hash() {
        List<Map<String, String>> sortMaps = maps.stream().filter(obj -> obj.get("original_language").equals("zh")).sorted(Comparator.comparing(v -> v.get("vote_average"))).toList();
        sortMaps.forEach(obj -> {
            String id = obj.get("id");
            jedisOperation.hset(REDIS_KEY_HASH + ":" + id, obj);
        });

        Map<String, String> hgetAll = jedisOperation.hgetAll(REDIS_KEY_HASH + ":79");
        log.info("hgetAll: {}", hgetAll);

        String originalTitle = jedisOperation.hget(REDIS_KEY_HASH + ":79", "original_title");
        assertEquals("英雄", originalTitle);
        jedisOperation.hdel(REDIS_KEY_HASH + ":79", "original_title");
        jedisOperation.hset(REDIS_KEY_HASH + ":79", "original_title", "英雄2");
        List<String> hmget = jedisOperation.hmget(REDIS_KEY_HASH + ":79", "original_title", "title");
        hmget.forEach(obj -> log.info("hmget: {}", obj));

        Set<String> hkeys = jedisOperation.hkeys(REDIS_KEY_HASH + ":79");
        hkeys.forEach(obj -> log.info("hkeys: {}", obj));
        List<String> hvals = jedisOperation.hvals(REDIS_KEY_HASH + ":79");
        hvals.forEach(obj -> log.info("hvals: {}", obj));

        Map<String, String> hscan = jedisOperation.hscan(REDIS_KEY_HASH + ":79", "0", 20);

        log.info("hscan: {}", hscan);

        assertEquals(20, jedisOperation.hlen(REDIS_KEY_HASH + ":79"));

    }

    @Test
    void zset() {
        final String originalTitle = ":original_title";
        final String title = ":title";
        List<Map<String, String>> sortMaps = maps.stream().filter(obj -> obj.get("original_language").equals("zh")).sorted(Comparator.comparing(v -> v.get("vote_average"))).toList();
        sortMaps.forEach(obj -> {
            // jedisOperation.zadd(REDIS_KEY_ZSET, Double.valueOf(obj.get("vote_average")), CommonFastJsonUtil.toJson(obj));
            jedisOperation.zadd(REDIS_KEY_ZSET + title, Double.parseDouble(obj.get("vote_average")), obj.get("title"));
        });
        Map<String, Double> map = Maps.newHashMap();
        sortMaps.forEach(obj -> {
            map.put(obj.get("original_title"), Double.valueOf(obj.get("vote_average")));
        });
        jedisOperation.zadd(REDIS_KEY_ZSET + originalTitle, map);
        List<String> zrange = jedisOperation.zrange(REDIS_KEY_ZSET + originalTitle, 0, -1);
        zrange.forEach(obj -> log.info("zrange: {}", obj));




        List<String> zrevrange = jedisOperation.zrevrange(REDIS_KEY_ZSET + originalTitle, 0, -1);
        zrevrange.forEach(obj -> log.info("zrevrange: {}", obj));

        List<Double> zrangeByScore = jedisOperation.zrangeByScore(REDIS_KEY_ZSET + originalTitle, 0, 3);
        zrangeByScore.forEach(obj -> log.info("zrangeByScore: {}", obj));

        List<Double> zrevrangeByScore = jedisOperation.zrevrangeByScore(REDIS_KEY_ZSET + originalTitle, 0, 6);
        zrevrangeByScore.forEach(obj -> log.info("zrevrangeByScore: {}", obj));

        List<String> zrangeByScore1 = jedisOperation.zrangeByScore(REDIS_KEY_ZSET + originalTitle, 3D, 4D);
        zrangeByScore1.forEach(obj -> log.info("zrangeByScore1: {}", obj));
        assertLinesMatch(List.of("兔侠传奇"), zrangeByScore1);
        List<String> zrevrangeByScore1 = jedisOperation.zrevrangeByScore(REDIS_KEY_ZSET + originalTitle, 8D, 7.5D);
        zrevrangeByScore1.forEach(obj -> log.info("zrevrangeByScore1: {}", obj));
        assertLinesMatch(List.of("南京!南京!"), zrevrangeByScore1);

        long zrem = jedisOperation.zrem(REDIS_KEY_ZSET + originalTitle, "兔侠传奇");
        assertEquals(1, zrem);

        long zremrangeByRank = jedisOperation.zremrangeByScore(REDIS_KEY_ZSET + originalTitle, 4.0D, 4.9D);
        assertEquals(2, zremrangeByRank);

        Long zrank = jedisOperation.zrank(REDIS_KEY_ZSET + originalTitle, "一代宗師");
        assertEquals(5, zrank);

        Long zrevrank = jedisOperation.zrevrank(REDIS_KEY_ZSET + originalTitle, "英雄");
        assertEquals(2, zrevrank);

        List<String> zrangeByLex = jedisOperation.zrangeByLex(REDIS_KEY_ZSET + title, "[H", "[T");
        zrangeByLex.forEach(obj -> log.info("zrangeByLex: {}", obj));

        Map<String, Double> racer_scores = Maps.newHashMap();
        racer_scores.put("Norem", 0D);
        racer_scores.put("Sam-Bodden", 0D);
        racer_scores.put("Royce", 1D);
        racer_scores.put("Castilla", 0D);
        racer_scores.put("Prickett", 0D);
        racer_scores.put("Ford", 0D);
        jedisOperation.zadd(REDIS_KEY_ZSET + ":racer_scores", racer_scores);
        List<String> zrangeByLex1 = jedisOperation.zrangeByLex(REDIS_KEY_ZSET + ":racer_scores", "[C", "[N");
        zrangeByLex1.forEach(obj -> log.info("zrangeByLex1: {}", obj));

        double zincrBy = jedisOperation.zincrBy(REDIS_KEY_ZSET + ":racer_scores", 1.0D, "Norem");
        assertEquals(1.0D, zincrBy);
    }

}