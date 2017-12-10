package com.sea.sell.service.impl;

import com.sea.sell.dto.OrderDTO;
import com.sea.sell.enums.ResultEnum;
import com.sea.sell.exception.SellException;
import com.sea.sell.service.BuyerService;
import com.sea.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO orderDetail(String openId, String orderId) {
        return auth(openId, orderId);
    }

    @Override
    public OrderDTO orderCancel(String openId, String orderId) {
        OrderDTO orderDTO = auth(openId, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查询不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO auth(String openId, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equals(openId)){
            log.error("【查询订单】openId不一致，orderId={},openId={}",orderId,openId);
            throw new SellException(ResultEnum.WEIXIN_OPEN_ID_ERROR);
        }
        return orderDTO;
    }
}
