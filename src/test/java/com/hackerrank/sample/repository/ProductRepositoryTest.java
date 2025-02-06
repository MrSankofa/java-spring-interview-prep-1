package com.hackerrank.sample.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// remembering Extend,
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

  @Mock
  RestTemplate restTemplate;

  @InjectMocks
  ProductRepository productRepository;

  @Test
  void findAllProducts() throws JSONException {
    String mockApiResponse = "{ \"page\": 1, \"per_page\": 10, \"total\": 2, \"total_pages\": 1, \"data\": " +
        "[ { \"id\": 1, \"item\": \"Product A\" }, { \"id\": 2, \"item\": \"Product B\" } ] }";

    when(restTemplate.getForObject(productRepository.uri, String.class)).thenReturn(mockApiResponse);

    JSONArray underTestResponse = productRepository.findAllProducts();

    assertNotNull(underTestResponse);
    assertEquals("Product A", underTestResponse.getJSONObject(0).getString("item"));
  }
}
