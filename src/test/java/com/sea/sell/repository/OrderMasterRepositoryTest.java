package com.sea.sell.repository;

import com.sea.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID="sea";

    @Test
    public void save() throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2");
        orderMaster.setBuyerName("sea");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("广东金融学院");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(15.8));

        OrderMaster master = repository.save(orderMaster);
        Assert.assertNotNull(master);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(1, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);
//        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}