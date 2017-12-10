package com.sea.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sea.sell.entity.OrderDetail;
import com.sea.sell.enums.OrderStatusEnum;
import com.sea.sell.enums.PayStatusEnum;
import com.sea.sell.utils.EnumUtil;
import com.sea.sell.utils.serialzer.Date2Long;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家手机号 */
    private String buyerPhone;

    /** 订单状态，默认新下单 */
    private Integer orderStatus;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 支付状态，默认未支付 */
    private Integer payStatus;

    @JsonSerialize(using = Date2Long.class)
    private Date createTime;

    @JsonSerialize(using = Date2Long.class)
    private Date updateTime;

    List<OrderDetail> orderDetails = new ArrayList<>();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
