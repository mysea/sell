package com.sea.sell.handler;

import com.sea.sell.VO.ResultVO;
import com.sea.sell.config.ProjectUrlConfig;
import com.sea.sell.exception.SellException;
import com.sea.sell.exception.SellerAuthorizeException;
import com.sea.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /*
    * 如果没登录（也就是没有cookie），就会抛出一个异常
    * 然后捕获这个异常，跳转到微信登录页面
    * */

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login")
        );
//        .concat(projectUrlConfig.getWechatOpenAuthorize()
//                .concat("/sell/wechat/qrAuthorize")
//                .concat("?returnUrl=")
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handleSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
