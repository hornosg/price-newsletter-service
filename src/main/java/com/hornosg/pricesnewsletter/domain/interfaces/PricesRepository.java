package com.hornosg.pricesnewsletter.domain;

import java.util.List;

public interface PricesRepository {
    List<Price> findPrices(FindPricesRequest request);
}
