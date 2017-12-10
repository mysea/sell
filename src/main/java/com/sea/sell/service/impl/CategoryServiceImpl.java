package com.sea.sell.service.impl;

import com.sea.sell.entity.ProductCategory;
import com.sea.sell.repository.ProductCategoryRepository;
import com.sea.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryIn(List<Integer> list) {
        return repository.findByCategoryTypeIn(list);
    }
}
