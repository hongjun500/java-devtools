package com.hongjun.redis.connection;

import com.hongjun.redis.connection.crud.JedisCrud;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.SetParams;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JedisConnectTest {

    private final JedisConnect jedisConnect = new JedisConnect();

    private final JedisPooled jedisPooled = jedisConnect.getJedisPooled();
    private final Jedis jedis = jedisConnect.getJedis();

    private final JedisCrud jedisCrud = new JedisCrud();

    @Test
    void testJedisConnect() {
        // 执行命令();
        // Object command = jedisPooled.sendCommand(Protocol.Command.PING);
        // log.info("command: {}", command.toString());
        String ping = jedis.ping();
        log.info("ping: {}", ping);
    }

    @Test
    void String() throws InterruptedException {
        jedisCrud.set(jedisPooled, "name:1", "hongjun");

        String res1 = jedisCrud.get(jedisPooled, "name:1");
        log.info("res1: {}", res1);

        jedisCrud.set(jedisPooled, "name", "hongjun", 2);
        boolean setnx1 = jedisCrud.setnx(jedisPooled, "name", "hongjun");
        log.info("setnx1: {}", setnx1);
        assertTrue(setnx1);
        Thread.sleep(2000);
        boolean setnx2 = jedisCrud.setnx(jedisPooled, "name", "hongjun");
        log.info("setnx2: {}", setnx2);
        assertFalse(setnx2);

        jedisCrud.mset(jedisPooled, "name:2", "hongjun2", "name:3", "hongjun3");
        List<String> mget = jedisCrud.mget(jedisPooled, "name:2", "name:3");
        log.info("mget: {}", mget);
        // todo Java8
        assertLinesMatch(List.of("hongjun2", "hongjun3"), mget);

        long incr = jedisCrud.incr(jedisPooled, "incr");
        log.info("incr: {}", incr);
        assertEquals(1, incr);
        String getIncr = jedisCrud.get(jedisPooled, "incr");
        log.info("getIncr: {}", getIncr);
        assertEquals("1", getIncr);

        long incrBy = jedisCrud.incrBy(jedisPooled, "incrBy", -176);
        log.info("incrBy: {}", incrBy);
        assertEquals(-176, incrBy);
        String getIncrBy = jedisCrud.get(jedisPooled, "incrBy");
        log.info("getIncrBy: {}", getIncrBy);
        assertEquals("-176", getIncrBy);

    }


    @Test
    @Disabled
    // @AfterEach
    void afterAll() {
        jedis.flushAll();
    }
}