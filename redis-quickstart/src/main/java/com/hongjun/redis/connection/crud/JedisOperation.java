package com.hongjun.redis.connection.crud;

import com.google.common.collect.Maps;
import com.hongjun.redis.connection.JedisConnect;
import org.checkerframework.checker.nullness.qual.Nullable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hongjun500
 * @date 2023/12/22 15:13
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

public class JedisOperation {

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
     *
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
     *
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
     *
     * @param key
     * @param values
     */
    public void lpush(String key, String... values) {
        jedisPooled.lpush(key, values);
    }

    /**
     * 将一个或多个值插入到列表尾部
     *
     * @param key
     * @param values
     */
    public void rpush(String key, String... values) {
        jedisPooled.rpush(key, values);
    }

    /**
     * 移除并返回列表的头元素
     *
     * @param key koc
     * @return
     */
    public String lpop(String key) {
        return jedisPooled.lpop(key);
    }

    /**
     * 移除并返回列表的尾元素
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        return jedisPooled.rpop(key);
    }

    /**
     * 返回列表的长度
     *
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

    // ---------------------------------- Sets ----------------------------------

    /**
     * 将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略。
     *
     * @param key
     * @param members
     * @return
     */
    public long sadd(String key, String... members) {
        return jedisPooled.sadd(key, members);
    }

    /**
     * 删除集合中的一个或多个成员元素，不存在的成员元素会被忽略。
     */
    public long srem(String key, String... members) {
        return jedisPooled.srem(key, members);
    }

    /**
     * 返回集合中的所有成员。不存在的集合 key 被视为空集合。
     * O(N)， N 为集合的基数。使用 SSCAN 命令可以实现分批遍历。
     */
    public Set<String> smembers(String key) {
        return jedisPooled.smembers(key);
    }

    /**
     * 迭代集合中的元素。
     */
    public List<String> sscan(String key, String cursor) {
        ScanResult<String> sscan = jedisPooled.sscan(key, cursor);
        return sscan.getResult();
    }

    /**
     * 判断 member 元素是否是集合 key 的成员。
     */
    public boolean sismember(String key, String member) {
        return jedisPooled.sismember(key, member);
    }

    /**
     * 返回多个集合共有的成员，即交集。
     */
    public Set<String> sinter(String... keys) {
        return jedisPooled.sinter(keys);
    }

    /**
     * 返回多个集合的并集。
     */
    public Set<String> sunion(String... keys) {
        return jedisPooled.sunion(keys);
    }

    /**
     * 返回集合中元素的数量。也称集合的基数。
     */
    public long scard(String key) {
        return jedisPooled.scard(key);
    }

    // ---------------------------------- Hashes ----------------------------------

    /**
     * 设置哈希表
     */
    public long hset(String key, Map<String, String> hash) {
        return jedisPooled.hset(key, hash);
    }

    /**
     * 设置哈希表 key 中的字段 field 的值为 value 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hset(String key, String field, String value) {
        return jedisPooled.hset(key, field, value);
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return jedisPooled.hget(key, field);
    }

    /**
     * 返回哈希表 key 中一个或多个给定域的值。
     *
     * @param key
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        return jedisPooled.hmget(key, fields);
    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        return jedisPooled.hgetAll(key);
    }

    /**
     * 返回哈希表 key 中的所有域。
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        return jedisPooled.hkeys(key);
    }

    /**
     * 返回哈希表 key 中所有域的值。
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        return jedisPooled.hvals(key);
    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     *
     * @param key
     * @param cursor 游标
     * @return
     * @count 一次返回的数量
     */
    public Map<String, String> hscan(String key, String cursor, Integer count) {
        ScanParams scanParams = new ScanParams();
        scanParams.count(count);
        int i;
        Map<String, String> map = Maps.newHashMap();
        do {
            ScanResult<Map.Entry<String, String>> hscan = jedisPooled.hscan(key, cursor, scanParams);
            List<Map.Entry<String, String>> result = hscan.getResult();
            map.putAll(result.stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll));
            i = Integer.parseInt(hscan.getCursor());
        } while (i > 0);
        return map;
    }

    /**
     * 返回哈希表 key 中域的数量。
     *
     * @param key
     * @return
     */
    public long hlen(String key) {
        return jedisPooled.hlen(key);
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     * @return
     */
    public long hdel(String key, String... fields) {
        return jedisPooled.hdel(key, fields);
    }

    // ---------------------------------- Sorted Sets ----------------------------------

    /**
     * 将一个 member 元素及其 score 值加入到有序集 key 当中。
     *
     * @param key
     * @param score  分数
     * @param member 成员
     */
    public long zadd(String key, double score, String member) {
        return jedisPooled.zadd(key, score, member);
    }

    /**
     * 将多个 member 元素及其 score 值加入到有序集 key 当中。
     *
     * @param key
     * @param scoreMembers member:score
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public long zadd(String key, Map<String, Double> scoreMembers) {
        return jedisPooled.zadd(key, scoreMembers);
    }

    /**
     * 从低到高返回有序集 key 中，指定区间内的成员。
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     */
    public List<String> zrange(String key, long start, long end) {
        return jedisPooled.zrange(key, start, end);
    }

    /**
     * 根据字典区间返回有序集 key 中的所有的成员。
     * 前提是具有相同分数的成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public List<String> zrangeByLex(String key, String min, String max) {
        return jedisPooled.zrangeByLex(key, min, max);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员分数
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     */
    public List<Double> zrangeByScore(String key, long start, long end) {
        List<Tuple> tuples = jedisPooled.zrangeWithScores(key, start, end);
        return tuples.stream().map(Tuple::getScore).toList();
    }

    /**
     * 从高到低返回有序集 key 中，指定区间内的成员。
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     */
    public List<String> zrevrange(String key, long start, long end) {
        return jedisPooled.zrevrange(key, start, end);
    }

    /**
     * 从高到低返回有序集 key 中，指定区间内的成员分数
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     */
    public List<Double> zrevrangeByScore(String key, long start, long end) {
        List<Tuple> tuples = jedisPooled.zrevrangeWithScores(key, start, end);
        return tuples.stream().map(Tuple::getScore).toList();
    }

    /**
     * 从低到高返回有序集 key 中，指定 score 区间内的成员。
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     */
    public List<String> zrangeByScore(String key, double min, double max) {
        return jedisPooled.zrangeByScore(key, min, max);
    }

    /**
     * 从高到低返回有序集 key 中，指定 score 区间内的成员。
     *
     * @param key
     * @param max 最大值
     * @param min 最小值
     */
    public List<String> zrevrangeByScore(String key, double max, double min) {
        return jedisPooled.zrevrangeByScore(key, max, min);
    }

    /**
     * 删除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     */
    public long zrem(String key, String... members) {
        return jedisPooled.zrem(key, members);
    }

    /**
     * 根据分数范围删除
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     * @return 删除的数量
     */
    public long zremrangeByScore(String key, double min, double max) {
        return jedisPooled.zremrangeByScore(key, min, max);
    }

    /**
     * 返回有序集 key 中，成员 member 的排名(指索引)，其中有序集成员按 score 值从小到大排列。
     *
     * @param key
     * @param member 成员
     */
    public Long zrank(String key, String member) {
        return jedisPooled.zrank(key, member);
    }

    /**
     * 返回有序集 key 中，成员 member 的排名(指索引)，其中有序集成员按 score 值从大到小排列。
     *
     * @param key
     * @param member 成员
     * @return
     */
    public Long zrevrank(String key, String member) {
        return jedisPooled.zrevrank(key, member);
    }

    /**
     * 递增有序集 key 中成员 member 的 score 值。
     * @param key
     * @param score
     * @param member
     */
    public double zincrBy(String key, double score, String member) {
        return jedisPooled.zincrby(key, score, member);
    }
}
