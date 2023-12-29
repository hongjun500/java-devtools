package com.hongjun.redis.connection;

import com.hongjun.redis.crud.JedisOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;

@Slf4j
class JedisConnectTest {

    private final JedisConnect jedisConnect = new JedisConnect();

    private final JedisPooled jedisPooled = jedisConnect.getJedisPooled();
    private final Jedis jedis = jedisConnect.getJedis();

    private final JedisOperation jedisCrud = new JedisOperation();

    @Test
    void testJedisConnect() {
        // 执行命令();
        // Object command = jedisPooled.sendCommand(Protocol.Command.PING);
        // log.info("command: {}", command.toString());
        String ping = jedis.ping();
        log.info("ping: {}", ping);
    }
}