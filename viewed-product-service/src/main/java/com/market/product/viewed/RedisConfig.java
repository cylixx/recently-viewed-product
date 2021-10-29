package com.market.product.viewed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {
    
	@Value("${application.redis.host}")
	private String redisHostName;
	
	@Value("${application.redis.port}")
	private int redisPort;
	
	
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHostName);
        configuration.setPort(redisPort);
        return new JedisConnectionFactory(configuration);
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }
    
}
