package com.hackerrank.sample.repository;

import com.hackerrank.sample.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties") // Ensures properties are loaded
public class ProductRepositoryIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ProductRepository productRepository;

  Product product = new Product();

  @BeforeEach
  public void setUp() {
    product.setPrice(200);
    product.setItem("Product F");
    product.setCategory("who knows");
    product.setBarcode("123456789");
    product.setDiscount(7);
    product.setAvailable(10);
  }

  @Test
  void createProduct_Success() throws Exception {



    String uri = productRepository.crud_uri + productRepository.apiKey + "/Product";
    ResponseEntity<Product> responseEntity = restTemplate.postForEntity(uri, product, Product.class);

    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals("Product F", responseEntity.getBody().getItem());
    assertEquals("123456789", responseEntity.getBody().getBarcode());
    assertEquals("who knows", responseEntity.getBody().getCategory());
    assertEquals(7, responseEntity.getBody().getDiscount());
    assertEquals(10, responseEntity.getBody().getAvailable());
    assertEquals(200, responseEntity.getBody().getPrice());
  }

  @Test
  void createProduct_showHaveAResourceURIResponse() throws Exception {

  }
}
