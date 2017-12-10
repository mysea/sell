package com.sea.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sea.sell.enums.OrderStatusEnum;
import com.sea.sell.enums.ProductStatusEnum;
import com.sea.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable{
    private static final long serialVersionUID = 5020895002743288053L;
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /** 0正常，1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
