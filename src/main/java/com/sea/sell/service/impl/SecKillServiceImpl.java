package com.sea.sell.service.impl;

import com.sea.sell.exception.SellException;
import com.sea.sell.service.RedisLock;
import com.sea.sell.service.SecKillService;
import com.sea.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {

    private final static Integer TIMEOUT = 2*1000;

    @Autowired
    private RedisLock redisLock;

    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        products = new HashMap<>();
        products.put("123456",100000);

        stock = new HashMap<>();
        stock.put("123456",100000);

        orders = new HashMap<>();
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return "活动特价，限量 "
                +products.get(productId)
                +" 份，还剩："+stock.get(productId)+" 份"
                +" 该商品成功下单用户数目："+orders.size()+"人";
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        /*
        * apache ab压测命令：
        * g:\Apache24\bin>ab -n 1000 -c 100 127.0.0.1/sell/order/123456
        * */

        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101, "哎呀，差一点就抢到了");
        }


        //1.查询商品数目，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单（模拟不同用户，openid不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3.减库存
            stockNum = stockNum - 1;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }


        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}
