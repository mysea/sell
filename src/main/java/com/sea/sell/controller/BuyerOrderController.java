package com.sea.sell.controller;

import com.sea.sell.VO.ResultVO;
import com.sea.sell.converter.OderForm2OrderDTO;
import com.sea.sell.dto.OrderDTO;
import com.sea.sell.enums.ResultEnum;
import com.sea.sell.exception.SellException;
import com.sea.sell.form.OrderForm;
import com.sea.sell.service.impl.BuyerServiceImpl;
import com.sea.sell.service.impl.OrderServiceImpl;
import com.sea.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private BuyerServiceImpl buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,BindingResult result){

        if (result.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    result.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String>  map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
        }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openid") String openid,@RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "size",defaultValue = "10") Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("【获取订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page,size);

        Page<OrderDTO> orderDTOPage = orderService.findList(openid,pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid,@RequestParam(value = "orderId") String orderId){

        OrderDTO orderDTO = buyerService.orderDetail(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam(value = "openid") String openid,@RequestParam(value = "orderId") String orderId){

//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel(orderDTO);
//        return ResultVOUtil.success();

        buyerService.orderCancel(openid, orderId);
        return ResultVOUtil.success();
    }

}
