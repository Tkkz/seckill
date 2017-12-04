package com.hbzz.service;

import com.hbzz.dto.Exposer;
import com.hbzz.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sssss on 2017/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
                        ,"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void testGetSeckillList() throws Exception{
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void testGetById() throws Exception {
        long id=1000;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportGetSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
        //Exposer{exposed=false,
        // md5='null',
        // seckillId=1000,
        // now=1506160129636,
        // start=1505923200000,
        // end=1506009600000}
    }

    @Test
    public void testExportGetSeckill() throws Exception {

    }
}
