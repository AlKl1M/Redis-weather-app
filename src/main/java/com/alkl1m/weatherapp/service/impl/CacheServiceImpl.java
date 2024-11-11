package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.exception.CacheServiceException;
import com.alkl1m.weatherapp.service.CacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;

/**
 * Реализация сервиса кэширования
 *
 * @author AlKl1M
 */
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Получает объект WeatherDto из кеша по имени кеша и ключу.
     *
     * @param cacheName имя кеша
     * @param key       ключ для получения данных
     * @return объект WeatherDto из кеша или null, если данные не найдены
     */
    @Override
    public WeatherDto getFromCache(String cacheName, String key) {
        String value = redisTemplate.opsForValue().get(getCacheKey(cacheName, key));
        if (value != null) {
            try {
                return objectMapper.readValue(value, WeatherDto.class);
            } catch (JsonProcessingException e) {
                throw new CacheServiceException("Ошибка десериализации WeatherDto из кеша", e);
            }
        }
        return null;
    }

    /**
     * Сохраняет объект WeatherDto в кеш с указанным временем жизни.
     *
     * @param cacheName имя кеша
     * @param key       ключ для сохранения данных
     * @param value     объект WeatherDto для сохранения
     * @param ttl       время жизни объекта в кеше
     */
    @Override
    public void putInCache(String cacheName, String key, WeatherDto value, Duration ttl) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(getCacheKey(cacheName, key), jsonValue, ttl);
        } catch (JsonProcessingException e) {
            throw new CacheServiceException("Ошибка сериализации WeatherDto в кеш", e);
        }
    }

    /**
     * Удаляет объект из кеша по имени кеша и ключу.
     *
     * @param cacheName имя кеша
     * @param key       ключ объекта для удаления
     */
    @Override
    public void evictFromCache(String cacheName, String key) {
        redisTemplate.delete(getCacheKey(cacheName, key));
    }

    /**
     * Удаляет все объекты из кеша по имени кеша.
     *
     * @param cacheName имя кеша
     */
    @Override
    public void evictCacheCategory(String cacheName) {
        Set<String> keys = redisTemplate.keys(cacheName + ":*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * Формирует ключ для кеша, комбинируя имя кеша и ключ.
     *
     * @param cacheName имя кеша
     * @param key       ключ
     * @return строка, представляющая ключ кеша
     */
    private String getCacheKey(String cacheName, String key) {
        return cacheName + ":" + key;
    }

}
