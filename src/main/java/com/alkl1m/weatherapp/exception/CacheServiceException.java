package com.alkl1m.weatherapp.exception;

/**
 * Исключение, возникающее при ошибках кеширования.
 */
public class CacheServiceException extends RuntimeException {

    /**
     * Конструктор исключения с сообщением.
     *
     * @param message сообщение об ошибке
     */
    public CacheServiceException(String message) {
        super(message);
    }

    /**
     * Конструктор исключения с сообщением и причиной.
     *
     * @param message сообщение об ошибке
     * @param cause   причина ошибки
     */
    public CacheServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
