package com.hbzz.entity;

import java.util.Date;

/**
 * Created by sssss on 2017/9/21.
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short start;
    private Date createTime;

    //变通 多对一
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", start=" + start +
                ", createTime=" + createTime +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getStart() {
        return start;
    }

    public void setStart(short start) {
        this.start = start;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
