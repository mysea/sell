package com.sea.sell.service;

import com.sea.sell.entity.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findAll();
    ProductCategory findOne(Integer categoryId);
    ProductCategory save(ProductCategory productCategory);
    List<ProductCategory> findByCategoryIn(List<Integer> list);
}
