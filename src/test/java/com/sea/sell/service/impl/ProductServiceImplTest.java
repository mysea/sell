package com.sea.sell.service.impl;

import com.sea.sell.entity.ProductInfo;
import com.sea.sell.enums.ProductStatusEnum;
import com.sea.sell.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findOne() throws Exception {
        ProductInfo info = service.findOne("1");
        Assert.assertEquals("1",info.getProductId());
    }

    @Test
    public void findOnUp() throws Exception {
        List<ProductInfo> infos = service.findOnUp();
        Assert.assertNotEquals(0,infos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> infos = service.findAll(request);
        System.out.println(infos.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo info = new ProductInfo();
        info.setProductId("2");
        info.setProductName("鳗鱼饭");
        info.setProductPrice(new BigDecimal(11.9));
        info.setProductStock(39);
        info.setProductDescription("好吃又有营养的鳗鱼饭~");
        info.setProductIcon("baidu.image.changfen");
        info.setProductStatus(0);
        info.setCategoryType(1);

        service.save(info);
    }

    @Test
    public void onSale() throws Exception{
        ProductInfo productInfo = service.onSale("1");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());
    }

    @Test
    public void offSale() throws Exception{
        ProductInfo productInfo = service.offSale("1");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}