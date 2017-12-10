package com.sea.sell.service;

import com.sea.sell.dto.OrderDTO;

public interface PushMessageService {
    void orderStatus(OrderDTO orderDTO);
}
