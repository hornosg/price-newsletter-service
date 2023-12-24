package com.hornosg.pricesnewsletter.infrastructure.controllers;

import com.hornosg.pricesnewsletter.domain.dtos.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private void sendValidRequest(String paramRequest) {
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity("/v1/prices/find?" + paramRequest, PriceResponse.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    private void sendBadRequest(String paramRequest) {
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity("/v1/prices/find?" + paramRequest, PriceResponse.class);
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testFailWithoutApplicationDate() {
        sendBadRequest("brandId=1&productId=35455");
    }

    @Test
    void testFailWithInvalidApplicationDate() {
        sendBadRequest("applicationDate=2020-14-06 10:00:00&brandId=1&productId=35455");
    }

    @Test
    void testFailWithoutbraindId() {
        sendBadRequest("applicationDate=2020-06-14 10:00:00&productId=35455");
    }

    @Test
    void testFailWithoutProductId() {
        sendBadRequest("applicationDate=2020-06-14 10:00:00&brandId=1");
    }

    @Test
    void test1() {
        sendValidRequest("applicationDate=2020-06-14 10:00:00&brandId=1&productId=35455");
    }


    @Test
    void test2() {
        sendValidRequest("applicationDate=2020-06-14 16:00:00&brandId=1&productId=35455");
    }

    @Test
    void test3() {
        sendValidRequest("applicationDate=2020-06-14 21:00:00&brandId=1&productId=35455");
    }

    @Test
    void test4() {
        sendValidRequest("applicationDate=2020-06-15 10:00:00&brandId=1&productId=35455");
    }

    @Test
    void test5() {
        sendValidRequest("applicationDate=2020-06-16 21:00:00&brandId=1&productId=35455");
    }


}