package com.hackerrank.sample.repository;

import com.hackerrank.sample.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: When do you need MockMvc, when should you load the entire application
// TODO: when should try to just load the class

// TODO: When using rest template you will need to use the SpringBootTest

@SpringBootTest
class ProductRepositoryTest {



  @Test
  void getProducts() {
    ProductRepository repository = new ProductRepository();

    List<Product> products = repository.getProducts();

    assertNotNull(products);
    assertTrue(products.size() > 0);
  }
}
