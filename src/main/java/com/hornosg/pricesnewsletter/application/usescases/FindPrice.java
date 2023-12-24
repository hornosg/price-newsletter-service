package com.hornosg.pricesnewsletter.application.usescases;

import com.hornosg.pricesnewsletter.domain.dtos.PriceResponse;
import com.hornosg.pricesnewsletter.domain.dtos.FindPriceRequest;
import com.hornosg.pricesnewsletter.domain.entities.Price;
import com.hornosg.pricesnewsletter.domain.interfaces.PricesRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FindPrice {
    private final PricesRepository pricesRepository;

    public FindPrice(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public PriceResponse invoke(FindPriceRequest request) {
        validResuest(request);

        Price price = pricesRepository.findPrice(request);

        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceRateId(),
                price.getValidDateFrom(),
                price.getValidDateTo(),
                price.getPrice()
        );
    }

    private static void validResuest(FindPriceRequest request) {
        if (request.applicationDate() == null || request.applicationDate().isEmpty() || request.applicationDate().isBlank() ||
            request.brandId() == null || request.productId() == null) {

            throw new IllegalArgumentException("all parameters are required");
        }

        try {
            LocalDateTime.parse(request.applicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }catch (Exception e){
            throw new IllegalArgumentException("applicationDate cannot be parsed to expected format \"yyyy-MM-dd HH:mm:ss\"");
        }
    }
}
