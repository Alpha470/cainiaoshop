/**
  * Copyright 2020 bejson.com 
  */
package com.alpha.cainiaoshop.product.vo;
import com.alpha.cainiaoshop.product.entity.SkuImagesEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2020-06-18 22:39:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<SkuImagesEntity> images;
    private List<String> descar;
    private Long fullCount;
    private Long discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Long priceStatus;
    private List<MemberPrice> memberPrice;

}