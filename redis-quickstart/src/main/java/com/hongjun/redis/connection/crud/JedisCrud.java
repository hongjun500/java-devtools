package com.hongjun.redis.connection.crud;

import com.hongjun.redis.connection.JedisConnect;
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
    
    private final JedisConnect jedisConnect = new JedisConnect();
    private final JedisPooled jedisPooled = jedisConnect.getJedisPooled();
    // ---------------------------------- String ----------------------------------

    public void set(String key, String value) {
        jedisPooled.set(key, value);
    }

    public void set(String key, String value, long seconds) {
        jedisPooled.setex(key, seconds, value);
    }

    /**
     * 判断对应的 key:value 是否存在
     *
     * @param key
     * @param value
     * @return 0 (because key already exists)
     */
    public boolean setnx(String key, String value) {
        return jedisPooled.setnx(key, value) == 0; // 0 (because key already exists)
    }

    public String get(String key) {
        return jedisPooled.get(key);
    }

    /**
     * 设置多个 key:value
     * @param keysvalues
     */
    public void mset(String... keysvalues) {
        jedisPooled.mset(keysvalues);
    }

    /**
     * 获取多个 key:value
     *
     * @param keys
     * @return 返回值数组
     */
    public List<String> mget(String... keys) {
        return jedisPooled.mget(keys);
    }

    /**
     * 解析字符串的值为整数并使其值递增 1, 如果传递负数，则递减
     * 如果键不存在，则在执行操作之前将其设置为 0。
     * <p>
     * INCR 命令将字符串值解析为整数，将其递增一，最终将得到的值设置为新值。还有类似的命令，如 INCRBY、DECR 和 DECRBY。在内部，它们都是同一个命令，只是以稍微不同的方式执行。
     * 所谓 INCR 具有原子性是指，即使有多个客户端同时对相同的键执行 INCR 命令，它们也不会进入竞态条件。例如，永远不会发生这样的情况：客户端 1 读取值为 "10"，客户端 2 同时读取值也为 "10"，两者都将值递增到 11，然后将新值设置为 11。最终的值将始终为 12，而读-递增-设置的操作是在其他所有客户端未执行命令的情况下完成的。
     * 简而言之，INCR 命令的原子性确保了在多个并发客户端的情况下，递增操作是以不可分割的方式执行的。这意味着不会发生并发冲突，保障了数据的一致性。
     * @param key
     */
    public long incr(String key) {
        return jedisPooled.incr(key);
    }

    public long incrBy(String key, long increment) {
        return jedisPooled.incrBy(key, increment);
    }

    // ---------------------------------- Lists ----------------------------------

    /**
     * 将一个或多个值插入到列表头部
     * @param key
     * @param values
     */
    public void lpush(String key, String... values) {
        jedisPooled.lpush(key, values);
    }

    /**
     * 将一个或多个值插入到列表尾部
     * @param key
     * @param values
     */
    public void rpush(String key, String... values) {
        jedisPooled.rpush(key, values);
    }

    /**
     * 移除并返回列表的头元素
     * @param key koc
     * @return
     */
    public String lpop(String key) {
        return jedisPooled.lpop(key);
    }

    /**
     * 移除并返回列表的尾元素
     * @param key
     * @return
     */
    public String rpop(String key) {
        return jedisPooled.rpop(key);
    }

    /**
     * 返回列表的长度
     * @param key
     * @return
     */
    public long llen(String key) {
        return jedisPooled.llen(key);
    }

    /**
     * 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
     * <p> end 为 -1 表示最后一个元素，-2 表示倒数第二个元素，以此类推。
     */
    public List<String> lrange(String key, long start, long end) {
        return jedisPooled.lrange(key, start, end);
    }

    /**
     * 保留列表中指定范围内的元素，超出范围的元素会被删除。
     */
    public String ltrim(String key, long start, long end) {
        return jedisPooled.ltrim(key, start, end);
    }
}
