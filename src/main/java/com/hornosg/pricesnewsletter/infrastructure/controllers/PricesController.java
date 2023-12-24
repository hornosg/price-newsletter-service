package com.hornosg.pricesnewsletter.infrastructure.controllers;

import com.hornosg.pricesnewsletter.application.usescases.FindPrice;
import com.hornosg.pricesnewsletter.domain.dtos.FindPriceRequest;
import com.hornosg.pricesnewsletter.infrastructure.repository.H2PricesRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/prices")
public class PricesController {
    private final FindPrice findPrice;

    @Autowired
    public PricesController(H2PricesRepository repository) {
        this.findPrice = new FindPrice(repository);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getPrice(@ModelAttribute FindPriceRequest request) {
        return new ResponseEntity<>(findPrice.invoke(request), org.springframework.http.HttpStatus.OK);
    }
}
