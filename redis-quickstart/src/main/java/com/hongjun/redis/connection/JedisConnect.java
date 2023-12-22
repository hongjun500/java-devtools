package com.hongjun.redis.connection;

import lombok.Data;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPooled;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hongjun500
 * @date 2023/12/21 17:16
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
public class JedisConnect {

    private final String host = "127.0.0.1";

    private final int port = 6379;

    private JedisPooled jedisPooled;
    private Jedis jedis;
    private JedisCluster jedisCluster;

    public JedisConnect() {
        jedisPooled = new JedisPooled(host, port);
        jedis = new Jedis(host, port);
        // Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        // jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
        // jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7380));
        // jedisCluster = new JedisCluster(jedisClusterNodes);
    }
}
