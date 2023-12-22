package com.hornosg.pricesnewsletter.domain;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Price {
    private String id;
    private Integer brandId;
    private Integer productId;
    private String validDateFrom;
    private String validDateTo;
    private Integer priceRateId;
    private Integer priority;
    private BigDecimal price;
    private String currencyId;

    public Price(String id, Integer brandId, Integer productId, String validDateFrom, String validDateTo,
                 Integer priceRateId, Integer priority, BigDecimal price, String currencyId) {
        this.id = id;
        this.brandId = brandId;
        this.productId = productId;
        this.validDateFrom = validDateFrom;
        this.validDateTo = validDateTo;
        this.priceRateId = priceRateId;
        this.priority = priority;
        this.price = price;
        this.currencyId = currencyId;
    }
}
