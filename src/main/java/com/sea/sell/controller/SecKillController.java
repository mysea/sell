package com.sea.sell.controller;

import com.sea.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;
    /*
    * 查询秒杀特价商品的信息
    * */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId)throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }
    /*
    * 秒杀，没有抢到获得“矮油，不错哦”，抢到了返回剩余的库存量
    * */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request,productId:"+productId);
        secKillService.orderProductMockDiffUser(productId);

        return secKillService.querySecKillProductInfo(productId);
    }
}

