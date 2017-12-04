package com.hbzz.dto;


/**
 * service和web 数据传输层
 *
 * 暴露秒杀地址DTO
 *
 * 大部分业务部相关，是为了返回service数据的封装
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;
    //一种加密措施
    private String md5;
    //id
    private long seckillId;
    //系统当前时间（毫秒）

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    private long now;
    //秒杀开始时间
    private long start;
    //秒杀结束时间
    private long end;


    //构造方法为了不同实现对象的初始化

    /**
     * 是否开启md5
     * @param exposed
     * @param md5
     * @param seckillId
     */

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    //是否开启 当前时间和结束时间
    public Exposer(boolean exposed, long seckillId,long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId=seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }
    //是否开启seckillId
    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }
}
