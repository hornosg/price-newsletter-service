package com.hornosg.pricesnewsletter.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(
        Integer productId,
        Integer brandId,
        Integer priceRateId,
        LocalDateTime validDateFrom,
        LocalDateTime validDateTo,
        BigDecimal price
) {
}
