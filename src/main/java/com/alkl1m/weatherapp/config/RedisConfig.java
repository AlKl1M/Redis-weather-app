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

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxWait(Duration.ofMillis(3000));
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(4);
        poolConfig.setMaxTotal(3000);

        LettucePoolingClientConfiguration poolingClientConfiguration =
                LettucePoolingClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(3000))
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
