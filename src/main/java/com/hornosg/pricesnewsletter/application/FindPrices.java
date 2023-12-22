package com.hornosg.pricesnewsletter.application;

import com.hornosg.pricesnewsletter.domain.FindPricesRequest;
import com.hornosg.pricesnewsletter.domain.Price;
import com.hornosg.pricesnewsletter.domain.PricesRepository;

import java.util.List;

public class FindPrices {
    private PricesRepository pricesRepository;

    public FindPrices(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public List<Price> invoke(FindPricesRequest request) {
        return pricesRepository.findPrices(request);
    }
}
