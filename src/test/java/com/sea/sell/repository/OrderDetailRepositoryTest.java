package com.sea.sell.repository;

import com.sea.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save () throws Exception{
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("1");
        orderDetail.setDetailId("121");
        orderDetail.setProductIcon("/rous/1.jpg");
        orderDetail.setProductId("1212321");
        orderDetail.setProductName("黑米粉");
        orderDetail.setProductPrice(new BigDecimal(15.8));
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }

    @Test
    public void findByOrOrderId() throws Exception {
        List<OrderDetail> orderDetails = repository.findByOrderId("1");
        Assert.assertNotEquals(0,orderDetails.size());
    }

}