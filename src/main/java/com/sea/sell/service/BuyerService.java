package com.sea.sell.service;


import com.sea.sell.dto.OrderDTO;

public interface BuyerService {
    OrderDTO orderDetail(String openId,String orderId);
    OrderDTO orderCancel(String openId,String orderId);
}
