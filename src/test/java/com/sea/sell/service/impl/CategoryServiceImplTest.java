package com.sea.sell.service.impl;

import com.sea.sell.entity.ProductCategory;
import com.sea.sell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> categories = repository.findAll();
        Assert.assertNotEquals(0,categories.size());
    }

    @Test
    public void findOne() throws Exception {
        ProductCategory category = repository.findOne(1);
        Assert.assertEquals(new Integer(1),category.getCategoryId());
    }

    @Test
    public void save() throws Exception {
        ProductCategory category = new ProductCategory("冬日上新",4);
        ProductCategory category1 = repository.save(category);
        Assert.assertNotNull(category1);
    }

    @Test
    public void findByCategoryIn() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> categories = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,categories.size());
    }

}