package com.hornosg.pricesnewsletter.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record FindPriceRequest(
        String applicationDate,
        Integer brandId,
        Integer productId
) {
}