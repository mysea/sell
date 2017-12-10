package com.sea.sell.entity.mapper;

import com.sea.sell.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Test
    public void addProductCategoryByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","客家风味");
        map.put("categoryType",520);

        int result = categoryMapper.addProductCategoryByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void addProductCategoryByObject() throws Exception{
        ProductCategory category = new ProductCategory();
        category.setCategoryName("烧腊");
        category.setCategoryType(120);

        int result = categoryMapper.addProductCategoryByObject(category);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType()throws Exception{
        ProductCategory category = categoryMapper.findByCategoryType(2);
        Assert.assertNotNull(category);
    }

    @Test
    public void findByCategoryName()throws Exception{
        ProductCategory category = categoryMapper.findByCategoryName("女神专享");
        Assert.assertNotNull(category);
    }

    @Test
    public void updateByCategoryTye()throws Exception{
        int result= categoryMapper.updateByCategoryTye("好粥道",5);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void updateByObject()throws Exception{
        ProductCategory category = new ProductCategory();
        category.setCategoryName("北京烤鸭");
        category.setCategoryType(120);

        int result= categoryMapper.updateByObject(category);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void deleteByCategoryType()throws Exception{
        int result = categoryMapper.deleteByCategoryType(7);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void selectByCategoryTye()throws Exception{
        ProductCategory productCategory = categoryMapper.selectByCategoryTye(520);
        Assert.assertNotNull(productCategory);
    }
}