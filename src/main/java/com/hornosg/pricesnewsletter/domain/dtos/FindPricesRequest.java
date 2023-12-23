package com.hornosg.pricesnewsletter.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FindPricesRequest {
    private LocalDateTime applicationDate;
    private Integer brandId;
    private Integer productId;

    public FindPricesRequest(LocalDateTime applicationDate, Integer brandId, Integer productId) {
        this.applicationDate = applicationDate;
        this.brandId = brandId;
        this.productId = productId;
    }
}
