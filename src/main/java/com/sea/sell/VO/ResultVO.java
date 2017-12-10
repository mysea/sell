package com.sea.sell.VO;

import lombok.Data;

import java.io.Serializable;

/*
* http请求返回的最外层的格式
* */
@Data
public class ResultVO<T> implements Serializable{
    private static final long serialVersionUID = 804604923869044620L;
    /** 错误码 */
    private Integer code;
    private String msg;
    private T data;
}
