package com.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 * @author 李嘉劲
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        /*1.声明返回类型*/
        RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();

        /*2.声明连接池*/
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        /*3.声明key的序列化类型*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        /*4.设置value的序列化方式*/
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<Object>(Object.class);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        /*5.返回redisTemplate*/
        return redisTemplate;
    }
}
