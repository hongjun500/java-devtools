package com.hongjun.redis.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hongjun500
 * @date 2023/12/25 14:05
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: RedisTemplate 常用操作
 */
@Component
@RequiredArgsConstructor
public class RedisComponent {

    private final RedisTemplate<String, Serializable> redisTemplate;

    // ----------------------------------String ----------------------------------

    public void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Duration timeout) {
        redisTemplate.expire(key, timeout);
    }

    public void set(String key, Serializable value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取并设置过期时间
     *
     * @param key     key
     * @param timeout 过期时间 使用 {@link Duration}
     * @return Serializable
     */
    public Serializable getAndExpire(String key, Duration timeout) {
        return redisTemplate.opsForValue().getAndExpire(key, timeout);
    }

    /**
     * 删除 key
     *
     * @param key key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    // ----------------------------------Hash ----------------------------------
    public void setHash(String key, Object hashKey, Serializable value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public <T> void putAllHash(String key, Map<T, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Object getHash(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取整个 hash
     *
     * @param key key
     * @return Map<Object, Object>
     */
    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取多个 hashKeys 的值
     *
     * @param key      key
     * @param hashKeys hash 中的多个 key
     * @return List
     */
    public List<Object> getHash(String key, List<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 判断 hashKey 是否存在
     *
     * @param key     key
     * @param hashKey hash 中的 key
     * @return Boolean
     */
    public Boolean hasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public Set<Object> keys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    public List<Object> values(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    public void delete(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    // ----------------------------------List ----------------------------------

    /**
     * 将元素插入列表头部
     *
     * @param key   key
     * @param value 元素
     * @return Long 列表长度
     */
    public Long leftPush(String key, Serializable value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 将多个元素插入列表头部
     *
     * @param key    key
     * @param values 多个元素
     * @return Long 列表长度
     */
    public Long leftPushAll(String key, Serializable... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 将多个元素以集合的形式插入列表头部
     *
     * @param key    key
     * @param values 多个元素组成的集合
     */
    public Long leftPushAll(String key, Collection<Serializable> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 移除并返回列表头部元素
     *
     * @param key key
     * @return Serializable
     */
    public Serializable leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 将元素插入列表尾部
     *
     * @param key   key
     * @param value 元素
     * @return Long 列表长度
     */
    public Long rightPush(String key, Serializable value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将多个元素插入列表尾部
     *
     * @param key    key
     * @param values 多个元素
     * @return Long 列表长度
     */
    public Long rightPushAll(String key, Serializable... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 将多个元素以集合的形式插入列表尾部
     *
     * @param key    key
     * @param values 多个元素组成的集合
     * @return Long 列表长度
     */
    public Long rightPushAll(String key, Collection<Serializable> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 在列表中 index 的位置设置 value 值
     *
     * @param key   key
     * @param index 位置
     * @param value 值
     */
    public void set(String key, long index, Serializable value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 获取列表指定位置的值
     *
     * @param key   key
     * @param index 位置
     * @return Serializable
     */
    public Serializable index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围的值
     *
     * @param key   key
     * @param start 开始位置
     * @param end   结束位置
     * @return List<Serializable>
     */
    public List<Serializable> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 删除列表中值为 value 的元素
     *
     * @param key   key
     * @param count 删除数量
     * @param value 值
     * @return Long 删除数量
     */
    public Long remove(String key, long count, Serializable value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 获取列表长度
     *
     * @param key key
     * @return Long 列表长度
     */
    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 返回列表中第一个匹配元素的索引
     *
     * @param key   key
     * @param value 值
     */
    public Long indexOf(String key, Serializable value) {
        return redisTemplate.opsForList().indexOf(key, value);
    }
}
