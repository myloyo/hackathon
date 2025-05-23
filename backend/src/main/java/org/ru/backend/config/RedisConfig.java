package org.ru.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128); // Set max connections (optional)
        poolConfig.setMaxIdle(128);  // Set max idle connections (optional)
        poolConfig.setMinIdle(16);   // Set min idle connections (optional)

        // Disable JMX for Jedis Pool
        poolConfig.setJmxEnabled(false);

        // You can specify host and port for your Redis instance, by default, it's localhost and port 6379
        return new JedisPool(poolConfig, redisHost, redisPort);
    }
}
