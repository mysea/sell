package com.sea.sell.repository;

import com.sea.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{
    SellerInfo findSellerInfoByOpenid(String openid);
}
