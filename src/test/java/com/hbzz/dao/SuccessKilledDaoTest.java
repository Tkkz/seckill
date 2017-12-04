package com.hbzz.dao;

import com.hbzz.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sssss on 2017/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    //注入successKilledDao
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled()throws Exception{
       /*
            第一次：Updates: 1
            第二次：Updates: 0
        */
        long id=1000L;
        long iphone=18332562205L;
        int insertCount=successKilledDao.insertSuccessKilled(id,iphone);

        System.out.println("updates="+insertCount);
    }
    @Test
    public void testQueryByIdWithSeckill(){
        long id=1000L;
        long phone=18332562205L;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}
