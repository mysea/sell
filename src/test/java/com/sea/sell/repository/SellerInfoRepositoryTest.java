package com.sea.sell.repository;

import com.sea.sell.entity.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() throws Exception{
        SellerInfo seller = new SellerInfo();
        seller.setSellerId("1");
        seller.setUsername("admin");
        seller.setPassword("admin");
        seller.setOpenid("123");

        sellerInfoRepository.save(seller);
    }

    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo result = sellerInfoRepository.findSellerInfoByOpenid("123");
        Assert.assertEquals("123",result.getOpenid());
    }
}