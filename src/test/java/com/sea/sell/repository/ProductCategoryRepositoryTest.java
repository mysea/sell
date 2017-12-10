package com.sea.sell.repository;

import com.sea.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        //查询数据
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
//    @Transactional
    public void saveProductCategory(){
        //保存数据
        ProductCategory productCategory = new ProductCategory("英伦范儿",3);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateProductCategory(){
        //更新数据
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryName("女神的新衣");
        ProductCategory p = productCategoryRepository.save(productCategory);
        Assert.assertNotEquals(null,p);
    }

    @Test
    public void findByList(){
        //根据条件查询
        List<Integer> list = Arrays.asList(1,2,3,4);

        List<ProductCategory> categories = productCategoryRepository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(null,categories);
    }

}
