package com.alpha.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuReduceTo {

    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Long priceStatus;
    private List<MemberPrice> memberPrice;
}
