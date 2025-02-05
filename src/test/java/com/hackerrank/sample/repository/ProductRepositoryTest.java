package com.hackerrank.sample.repository;

import com.hackerrank.sample.dto.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// Testing the conversion from string to JSONArray
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ProductRepository productRepositorySpy;

  @Test
  @DisplayName("Confirms that JSON array is not null, converts string response into JSONArray and has all of its contents")
  void getAllProducts() throws JSONException {

    // Mock API response
    String mockApiResponse = "{ \"page\": 1, \"per_page\": 10, \"total\": 2, \"total_pages\": 1, \"data\": " +
        "[ { \"id\": 1, \"name\": \"Product A\" }, { \"id\": 2, \"name\": \"Product B\" } ] }";

    when(restTemplate.getForObject("https://jsonmock.hackerrank.com/api/inventory", String.class))
    .thenReturn(mockApiResponse);

    JSONArray result = productRepositorySpy.getAllProducts();

    assertNotNull(result);
    assertEquals(2, result.length());
    assertTrue(result.getJSONObject(0) instanceof JSONObject);
    assertEquals("Product A", result.getJSONObject(0).getString("name"));
    assertEquals("Product B", result.getJSONObject(1).getString("name"));

    verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
  }
}
