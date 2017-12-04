package com.hbzz.dao;

import com.hbzz.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * spring和junit整合
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void testQueryById() throws Exception{
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /*
        1000元秒杀iphone6
    Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100,
    startTime=Thu Sep 21 00:00:00 CST 2017,
    endTime=Fri Sep 22 00:00:00 CST 2017,
    createTime=Thu Sep 21 16:40:20 CST 2017}
         */
    }
    @Test

    public void testQueryAll() throws Exception{
       /*
        nested exception is org.apache.ibatis.binding.BindingException:
       Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]

         List<Seckill> queryAll(int offet,int limit);
          java没有保存行参的记录：queryAll(int offet ,int limit)->queryAll(arg0,arg1)
          当超过俩个时 需要告诉他那个对应那个，这样Mybatis才能识别
        */


        List<Seckill>seckills=seckillDao.queryAll(0,100);

        for (Seckill seckill:seckills){
            System.out.println(seckill);
         }
    }

    @Test
    public void  testReduceNumber() throws Exception{
      //同上
        Date killTime=new Date();
        int updateCount= seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount="+updateCount);
    }
}

