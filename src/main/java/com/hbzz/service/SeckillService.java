package com.hbzz.service;

import com.hbzz.dto.SeckillExecution;
import com.hbzz.entity.Seckill;
import com.hbzz.dto.Exposer;
import com.hbzz.exeception.RepeatKillException;
import com.hbzz.exeception.SeckillCloseException;
import com.hbzz.exeception.SeckillException;

import java.util.List;

/**
 *业务接口：站在"使用者"角度设计接口;
 *    三个方面：1 方法定义粒度（非常明确） 2 参数（简练，直接） 3返回类型（return 类型/异常  （友好））
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    //行为接口

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    //很多业务无关的数据 当web调用这个接口时 会返回dto封装的数据
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀接口
     * 用于验证用户，如果md5出现串改，就不让用户进行秒杀
     * 输出各种状态的异常
      * @param seckillId
     * @param userPhone
     * @param md5
     */
    //throws 目的是告诉接口使用方 抛出的异常
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws  SeckillCloseException, SeckillException, RepeatKillException;

}
