package com.hornosg.pricesnewsletter.domain.interfaces;

import com.hornosg.pricesnewsletter.domain.dtos.FindPriceRequest;
import com.hornosg.pricesnewsletter.domain.entities.Price;

public interface PricesRepository {
    Price findPrice(FindPriceRequest request);
}
