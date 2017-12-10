package com.sea.sell.service.impl;

import com.sea.sell.dto.OrderDTO;
import com.sea.sell.entity.OrderDetail;
import com.sea.sell.enums.OrderStatusEnum;
import com.sea.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    private static final String ORDER_ID="1511507526595152587";

    @Autowired
    private OrderServiceImpl service;

    private final static String BUYER_OPENID = "sea";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("sea");
        orderDTO.setBuyerAddress("gduf");
        orderDTO.setBuyerPhone("13035778969");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();

//        OrderDetail o1 = new OrderDetail();
//        o1.setProductId("1");
//        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(1);

        OrderDetail o3 = new OrderDetail();
        o3.setProductId("3");
        o3.setProductQuantity(1);

//        orderDetails.add(o1);
        orderDetails.add(o2);
        orderDetails.add(o3);

        orderDTO.setOrderDetails(orderDetails);

        OrderDTO result =service.create(orderDTO);

        log.info("【创建订单】 result={}",result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = service.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = service.findList(BUYER_OPENID,pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void findAllList() throws Exception{
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = service.findList(pageRequest);
        Assert.assertTrue("返回订单列表",orderDTOPage.getTotalElements() > 0);
    }
}