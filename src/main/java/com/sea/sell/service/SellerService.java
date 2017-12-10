package com.sea.sell.service;

import com.sea.sell.entity.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
