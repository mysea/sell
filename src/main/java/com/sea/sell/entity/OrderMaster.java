package com.sea.sell.entity;

import com.sea.sell.enums.OrderStatusEnum;
import com.sea.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /** 买家微信openid */
    private String buyerOpenid;

    /** 支付状态，默认未支付 */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
