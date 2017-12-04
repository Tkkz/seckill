package com.hbzz.web;

import com.hbzz.dto.Exposer;
import com.hbzz.dto.SeckillExecution;
import com.hbzz.dto.SeckillResult;
import com.hbzz.entity.Seckill;
import com.hbzz.exeception.RepeatKillException;
import com.hbzz.exeception.SeckillCloseException;
import com.hbzz.exeception.SeckillException;
import com.hbzz.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller               //   @Service @Component
@RequestMapping("/seckill")  //url:/模块/资源/{id}/细分
public class SeckillController {
    private final Logger longger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    //获取所有秒杀数据列表。model 存放所有的渲染list.jsp+model(数据)=ModeAndView
    @RequestMapping(name = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
       List<Seckill>list= seckillService.getSeckillList();
       model.addAttribute("list",list);

        return "list";
    }
    //详情页
    @RequestMapping(value = "/{seckillId}/detial",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){

        if (seckillId == null){
            return "redirect:/seckill/list";
        }

        Seckill seckill=seckillService.getById(seckillId);
        if (seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId){
        SeckillResult<Exposer> result;
        try{
            Exposer exposer=seckillService.exportSeckillUrl(seckillId);

            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            longger.error(e.getMessage(),e);
            return new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8" } )
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPone",required = false)Long phone){
        if (phone == null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;
        try{
            SeckillExecution execution=seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e1){
            SeckillExecution execution=seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(false,execution);

        }catch (RepeatKillException e2){
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(false, execution);

        } catch (Exception e) {
            longger.error(e.getMessage(), e);
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(false, execution);
            // return new SeckillResult<Exposer>(false, e.getMessage());
        }
    }
    //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time(){
        Date now =new Date();
        return new SeckillResult(true,now.getTime());
    }
}
