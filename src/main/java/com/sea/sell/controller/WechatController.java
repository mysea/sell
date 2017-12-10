package com.sea.sell.controller;

import com.sea.sell.config.ProjectUrlConfig;
import com.sea.sell.enums.ResultEnum;
import com.sea.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpShakeService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    @Autowired
    private ProjectUrlConfig urlConfig;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl") String returnUrl){
        //配置
        //调用方法
        String url = urlConfig.getWechatMpAuthorize()+"/sell/wechat/userInfo";

        /**
         * oauth2buildAuthorizationUrl=
        https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        */

        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));

        /*
        * 返回的redirectUrl是：
        * http://sea.mynatapp.cc/sell/wechat/userInfo？code=**********
        * */

        log.info("【微信网页授权】获取code,result={}",redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
        try{

            /*
            *  oauth2getAccessToken=
            * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
            *
            * appId和appSercet从配置中取
            * */

            accessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】exception={}",e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }

        String openId = accessToken.getOpenId();

        /*
        * returnUrl=openidUrl="http://sea.m         ynatapp.cc/sell/wechat/authorize"
        * redirectUrl=http://sea.mynatapp.cc/sell/wechat/authorize?openId
        * */
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @RequestMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam(value = "returnUrl") String returnUrl){
        //配置
        //调用方法
        String url = urlConfig.getWechatOpenAuthorize()+"/sell/wechat/qrUserInfo";

        String redirectUrl = wxOpenService.oauth2buildAuthorizationUrl(url,WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, returnUrl);

        log.info("【微信网页授权】获取code,result={}",redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
        try{
            accessToken = wxOpenService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信开放平台授权】exception={}",e);
            throw new SellException(ResultEnum.WX_OPEN_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = accessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
