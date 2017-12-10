package com.sea.sell.controller;

import com.sea.sell.dto.OrderDTO;
import com.sea.sell.enums.ResultEnum;
import com.sea.sell.exception.SellException;
import com.sea.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "size",defaultValue = "10") Integer size, Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page-1 ,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("order/list",map);
    }

    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【取消订单】发生错误，error={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.CANCEL_ORDER_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【订单详情】发生错误，error={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail", map);
    }

    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【完结订单】发生错误，error={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg",ResultEnum.FINISH_ORDER_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
