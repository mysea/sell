package com.sea.sell.service.impl;

import com.sea.sell.entity.SellerInfo;
import com.sea.sell.repository.SellerInfoRepository;
import com.sea.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findSellerInfoByOpenid(openid);
    }
}
