package com.hbzz.service.impl;

import com.hbzz.dao.SeckillDao;
import com.hbzz.dao.SuccessKilledDao;
import com.hbzz.dto.SeckillExecution;
import com.hbzz.entity.Seckill;
import com.hbzz.entity.SuccessKilled;
import com.hbzz.dto.Exposer;
import com.hbzz.enums.SeckillStatEnum;
import com.hbzz.service.SeckillService;
import com.hbzz.exeception.RepeatKillException;
import com.hbzz.exeception.SeckillCloseException;
import com.hbzz.exeception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by sssss on 2017/9/23.
 */
//@Component(所有组件) @Service @Dao @Conroller
@Service
public class SeckillServiceImpl implements SeckillService {
    //统一的日子API
    private  Logger logger= LoggerFactory.getLogger(this.getClass());

    //需要DAO的配合 注入SERVICE
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    //md5研制 字符串，用于混淆md5
    private final String slat="dasjhcnjkadn%$kadsa&&dadadsmcz";

    //查询秒杀记录所有的商品
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }
    //查询单个秒杀记录
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    //秒杀开启时输出秒杀接口地址
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill=seckillDao.queryById(seckillId);
        //查不到的话
        if (seckill == null){
            return new Exposer(false,seckillId);
        }
        //不为空 拿到开始时间和结束时间
        Date startTime=seckill.getStartTime();
        Date endTime=seckill.getEndTime();
        Date nowTime=new Date();
        if (nowTime.getTime()< startTime.getTime()|| nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),
                    startTime.getTime(),endTime.getTime());
        }

        //转换特点字符串的过程，不可逆
      String md5=getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    private String getMd5(long seckillId){
        String base=seckillId+"/"+slat;
        //生成MD5 二进制
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    /**
     * 使用注解控制事务方法的优点：（事务时一个很小心的操作，一旦操作不好会造成数据库延迟，阻塞等）
     * 1：开发团队达成一致约定，明确标注事务方法的编程风格。
     * 2：保证事务方法的执行时间尽可能的短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
     * 3：不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillCloseException, SeckillException, RepeatKillException {
        if(md5 == null || md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存+记录购买行为
        //系统当前时间，如果秒杀时间不在开始和结束之际，就不进行减库存
        Date nowTime=new Date();
        try {
            //减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            //没有更新到记录，秒杀结束
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //唯一验证：seckill,userPhone ignor进行的验证
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        //对不同的异常类型做catch 方便指定那里出错了
        }catch (SeckillCloseException e1){
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e){
        //异常，包括数据库连接超时，数据库没有连接上等异常
            logger.error(e.getMessage(),e);
            //所有编译异常转换成运行期异常
            throw new SeckillException("seckill inner error"+e.getMessage());
        }

    }
}
