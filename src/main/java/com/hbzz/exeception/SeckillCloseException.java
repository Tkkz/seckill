package com.hbzz.exeception;

/**
 * 秒杀关闭异常（时间到了，或者 库存没有了）
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
