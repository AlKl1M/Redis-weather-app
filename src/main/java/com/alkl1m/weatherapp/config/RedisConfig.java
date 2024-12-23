package com.alkl1m.weatherapp.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author AlKl1M
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${redis.pool.max-wait:3000}")
    private long maxWait;

    @Value("${redis.pool.max-idle:8}")
    private int maxIdle;

    @Value("${redis.pool.min-idle:4}")
    private int minIdle;

    @Value("${redis.pool.max-total:3000}")
    private int maxTotal;

    @Value("${redis.command.timeout:3000}")
    private long commandTimeout;

    /**
     * Конфигурирует соединение с Redis с использованием Lettuce ConnectionFactory.
     *
     * @return настроенное подключение к Redis с пулом подключений
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(password);
        config.setDatabase(0);

        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxWait(Duration.ofMillis(maxWait));
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxTotal);

        LettucePoolingClientConfiguration poolingClientConfiguration =
                LettucePoolingClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(commandTimeout))
                        .poolConfig(poolConfig)
                        .build();

        return new LettuceConnectionFactory(config, poolingClientConfiguration);
    }

    /**
     * Конфигурирует RedisTemplate для работы с Redis, используя Lettuce.
     * Устанавливает сериализаторы для ключей и значений как StringRedisSerializer.
     *
     * @return настроенный RedisTemplate для работы с Redis
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}