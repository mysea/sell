package com.sea.sell.repository;

import com.sea.sell.entity.ProductInfo;
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
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() throws Exception{
        ProductInfo info = new ProductInfo();
        info.setProductId("1");
        info.setProductName("肠粉");
        info.setProductPrice(new BigDecimal(3.8));
        info.setProductStock(99);
        info.setProductDescription("鸡蛋瘦肉");
        info.setProductIcon("baidu.image.changfen");
        info.setProductStatus(0);
        info.setCategoryType(1);

        ProductInfo info1 = repository.save(info);

        Assert.assertNotNull(info1);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> infos = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,infos.size());
    }

}