package com.hbzz.dao;

import com.hbzz.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * dao层
 */
/*
    遵循四个原则
       1,接口方法名=Users.xml中id
       2,返回值类型 与 Mapper.xml文件中的值一致
       3,方法的入参类型与Mapper.xml中入参的类型要一致
       4,命名空间绑定此接口
 */

public interface SeckillDao {

    /**
     * 减库存，减库存id，执行减库存的时间
     * @param seckillId
     * @param killTime
     * @return int类型 如果影响行数>1，表示更新的记录行数
     */

    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);


    /**
     *根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offet 偏移量
     * @param limit 限制，取多少条数据
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offet,@Param("limit") int limit);
}
