package com.sea.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {
    /*
    * 微信公众平台授权url
    * */
    private String wechatMpAuthorize;
    /*
    * 微信开放平台授权url
    * */
    private String wechatOpenAuthorize;
    /*
    * 点餐系统
    * */
    private String sell;
}
