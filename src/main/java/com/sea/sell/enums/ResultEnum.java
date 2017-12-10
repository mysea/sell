package com.sea.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_ERROR(17,"支付状态不正确"),
    ORDER_PAY_FAIL(18,"支付失败"),

    CART_EMPTY(19,"购物车不能为空"),


    WEIXIN_OPEN_ID_ERROR(20,"微信认证错误"),
    WX_MP_ERROR(21,"微信公众号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(22,"微信通知金额核对错误"),

    CANCEL_ORDER_SUCCESS(23,"取消订单成功"),
    FINISH_ORDER_SUCCESS(23,"完结订单成功"),

    PRODUCT_STATUS_ERROR(24,"商品上下架状态错误"),
    WX_OPEN_ERROR(25,"微信开放平台错误"),

    LOGIN_FAIL(26,"登录失败，用户信息不正确"),

    LOGOUT_SUCCESS(27,"退出成功");
    ;



    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
