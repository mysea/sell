package com.sea.sell.controller;

import com.sea.sell.entity.ProductCategory;
import com.sea.sell.exception.SellException;
import com.sea.sell.form.CategoryForm;
import com.sea.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categories = categoryService.findAll();
        map.put("categories",categories);
        return new ModelAndView("category/list",map);
    }


    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId, Map<String,Object> map){

        if (categoryId != null){
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category",category);
        }
        return new ModelAndView("category/index",map);
    }
    /*
    * 新增或修改
    * */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult result, Map<String, Object> map){
        if (result.hasErrors()){
            map.put("msg",result.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }

        ProductCategory category = new ProductCategory();
        try {
            //如果为空，说明是新增，否则为更新
            if (form.getCategoryId() != null){
                category = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
