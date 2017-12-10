package com.sea.sell.controller;

import com.sea.sell.VO.ProductInfoVO;
import com.sea.sell.VO.ProductVO;
import com.sea.sell.VO.ResultVO;
import com.sea.sell.entity.ProductCategory;
import com.sea.sell.entity.ProductInfo;
import com.sea.sell.service.CategoryService;
import com.sea.sell.service.ProductService;
import com.sea.sell.utils.ResultVOUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
//    @Cacheable(cacheNames = "product", key = "123")
    /*
    * 当seller不同时，第一次需要重新缓存
    * 当sellerId大于2时才缓存
    * */
//    @Cacheable(cacheNames = "product", key = "#sellerId",condition = "#sellerId.length()>3",unless = "#result.getCode() !=0")
    public ResultVO list(@RequestParam("sellerId") String sellerId){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findOnUp();

        //2.查询类目
        //查询所有上架商品的商品类型
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        //根据商品类型查询已上架商品类目信息
        List<ProductCategory> categories = categoryService.findByCategoryIn(categoryTypeList);

        //数据拼装
        //遍历商品类目信息，赋给productVO
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categories ) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            //遍历上架商品信息，根据类目赋给对应的productInfoVO
            for (ProductInfo info: productInfoList) {
                if (info.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info,productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
                productVO.setProductInfo(productInfoVOList);
            }
            productVOList.add(productVO);
        }

//        ResultVO resultVo = new ResultVO();
//        ProductVO productVO = new ProductVO();
//        ProductInfoVO productInfoVO = new ProductInfoVO();

//        productVO.setProductInfo(Arrays.asList(productInfoVO));
//        resultVo.setData(Arrays.asList(productVO));

//        resultVo.setData(productVOList);
//        resultVo.setCode(0);
//        resultVo.setMsg("success");
//        return resultVo;

        return ResultVOUtil.success(productVOList);
//        return ResultVOUtil.error(10,"测试缓存条件");
    }
}
