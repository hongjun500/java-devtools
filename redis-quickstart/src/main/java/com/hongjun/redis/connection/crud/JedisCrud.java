package com.hongjun.redis.connection.crud;

import redis.clients.jedis.JedisPooled;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/12/22 15:13
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

public class JedisCrud {

    // ---------------------------------- String ----------------------------------
    // By default, a single Redis string can be a maximum of 512 MB.

    public void set(JedisPooled jedisPooled, String key, String value) {
        jedisPooled.set(key, value);
    }

    public void set(JedisPooled jedisPooled, String key, String value, long seconds) {
        jedisPooled.setex(key, seconds, value);
    }

    /**
     * 判断对应的 key:value 是否存在
     *
     * @param jedisPooled
     * @param key
     * @param value
     * @return 0 (because key already exists)
     */
    public boolean setnx(JedisPooled jedisPooled, String key, String value) {
        return jedisPooled.setnx(key, value) == 0; // 0 (because key already exists)
    }

    public String get(JedisPooled jedisPooled, String key) {
        return jedisPooled.get(key);
    }

    /**
     * 设置多个 key:value
     *
     * @param jedisPooled
     * @param keysvalues
     */
    public void mset(JedisPooled jedisPooled, String... keysvalues) {
        jedisPooled.mset(keysvalues);
    }

    /**
     * 获取多个 key:value
     *
     * @param jedisPooled
     * @param keys
     * @return 返回值数组
     */
    public List<String> mget(JedisPooled jedisPooled, String... keys) {
        return jedisPooled.mget(keys);
    }

    /**
     * 解析字符串的值为整数并使其值递增 1, 如果传递负数，则递减
     * 如果键不存在，则在执行操作之前将其设置为 0。
     * <p>
     * INCR 命令将字符串值解析为整数，将其递增一，最终将得到的值设置为新值。还有类似的命令，如 INCRBY、DECR 和 DECRBY。在内部，它们都是同一个命令，只是以稍微不同的方式执行。
     * 所谓 INCR 具有原子性是指，即使有多个客户端同时对相同的键执行 INCR 命令，它们也不会进入竞态条件。例如，永远不会发生这样的情况：客户端 1 读取值为 "10"，客户端 2 同时读取值也为 "10"，两者都将值递增到 11，然后将新值设置为 11。最终的值将始终为 12，而读-递增-设置的操作是在其他所有客户端未执行命令的情况下完成的。
     * 简而言之，INCR 命令的原子性确保了在多个并发客户端的情况下，递增操作是以不可分割的方式执行的。这意味着不会发生并发冲突，保障了数据的一致性。
     * @param jedisPooled
     * @param key
     */
    public long incr(JedisPooled jedisPooled, String key) {
        return jedisPooled.incr(key);
    }

    public long incrBy(JedisPooled jedisPooled, String key, long increment) {
        return jedisPooled.incrBy(key, increment);
    }

    // ---------------------------------- Lists ----------------------------------
}
