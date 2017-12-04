package com.hbzz.dao;

import com.hbzz.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sssss on 2017/9/21.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复（联合唯一主键，可过滤重复）
     * @param seckillid
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillid,@Param("userPhone") long userPhone);


    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实例
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);


}
