package com.hbzz.exeception;

/**
 * 所有的秒杀相关业务异常
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
