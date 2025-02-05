package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.repository.ProductRepository;
import com.hackerrank.sample.service.ProductService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ProductRepository productRepository;

  @MockBean
  private ProductService productService;

  @Autowired
  MockMvc mockMvc;


  @Test
  void testCreateProduct_ReturnsLocationHeader() throws Exception {
    Product mockProduct = new Product();
    mockProduct.setPrice(200);
    mockProduct.setItem("Product F");
    mockProduct.setCategory("who knows");
    mockProduct.setBarcode("123456789");
    mockProduct.setDiscount(7);
    mockProduct.setAvailable(10);
    mockProduct.setLocationURI(new URI("http://localhost/product/123456789")); // Simulated generated URI


    when(productService.createProduct(any(Product.class))).thenReturn(mockProduct);


    String productJson = "{"
        + "\"price\": 200,"
        + "\"item\": \"Product F\","
        + "\"category\": \"who knows\","
        + "\"barcode\": \"123456789\","
        + "\"discount\": 7,"
        + "\"available\": 10"
        + "}";

    mockMvc.perform(
            post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson)
        )
        .andExpect(status().isCreated())
        .andExpect(header().exists("location"))
        .andExpect(header().string("location", "http://localhost/product/123456789"));
  }
}
