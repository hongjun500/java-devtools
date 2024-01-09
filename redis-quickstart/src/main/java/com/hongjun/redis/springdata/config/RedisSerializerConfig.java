package com.hongjun.redis.springdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author hongjun500
 * @date 2023/12/28 17:24
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: RedisTemplate 序列化配置, 用于解决 RedisTemplate 默认使用 JdkSerializationRedisSerializer 序列化的问题
 * @link <a href="https://docs.spring.io/spring-data/data-redis/docs/3.0.0/reference/html/#redis:serializer">...</a>
 * 如果你需要处理的 Java 对象的类型在编译时就已知，那么 Jackson2JsonRedisSerializer 可能是一个更好的选择。
 * 如果你需要处理的 Java 对象的类型在编译时未知，或者你需要在超类或接口和具体类之间进行转换，那么 GenericJackson2JsonRedisSerializer 可能是一个更好的选择
 */
@Configuration
public class RedisSerializerConfig {

    private final RedisSerializer<String> keySerializer = new StringRedisSerializer();

    /** GenericJackson2JsonRedisSerializer 在序列化和反序列化时不需要指定具体的类，它会存储和使用类型信息 */
    private final RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer();


    /** Jackson2JsonRedisSerializer 在序列化和反序列化时需要指定具体的类，它不会存储和使用类型信息 */
    /*
    private final RedisSerializer<Object> serializer;

    public RedisSerializerConfig() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        serializer = new Jackson2JsonRedisSerializer<>(mapper, Object.class);
    }*/

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(keySerializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(serializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(serializer);
        return template;
    }
}
