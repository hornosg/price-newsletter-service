package com.hornosg.pricesnewsletter.infrastructure;

import com.hornosg.pricesnewsletter.application.FindPrices;
import com.hornosg.pricesnewsletter.domain.FindPricesRequest;
import com.hornosg.pricesnewsletter.domain.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/prices")
public class PricesController {
    private final FindPrices findPrices;

    public PricesController() {
        H2PricesRepository repository = new H2PricesRepository();
        this.findPrices = new FindPrices(repository);
    }

    @GetMapping("/list")
    public List<Price> listPrices(FindPricesRequest request) {
        return findPrices.invoke(request);
    }
}
