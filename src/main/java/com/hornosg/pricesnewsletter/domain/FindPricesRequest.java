package com.hornosg.pricesnewsletter.domain;

import java.time.LocalDate;

public class FindPricesRequest {
    private LocalDate applicationDate;
    private Integer brandId;
    private Integer productId;

    public FindPricesRequest(LocalDate applicationDate, Integer brandId, Integer productId) {
        this.applicationDate = applicationDate;
        this.brandId = brandId;
        this.productId = productId;
    }
}
