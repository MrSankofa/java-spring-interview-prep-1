package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  // TODO: Noticing a pattern with InjectMocks being the class you are testing
  // TODO: and mock being the dependency
  @InjectMocks
  private ProductService productService;

  @Test
  void getAllProducts() throws JSONException {
    JSONArray mockJsonArray = new JSONArray();
    mockJsonArray.put(new JSONObject()
        .put("id", 1)
        .put("name", "Product A")
        .put("barcode", "38472374")
        .put("price", "909")
        .put("discount", "7")
        .put("available", "1")
    );
    mockJsonArray.put(new JSONObject()
        .put("id", 2)
        .put("name", "Product B")
        .put("barcode", "74002423")
        .put("price", "3548")
        .put("discount", "12")
        .put("available", "1")
    );

    when(productRepository.getAllProducts())
        .thenReturn(mockJsonArray);


    List<Product> underTestProducts = productService.getAllProducts();

    assertEquals(2, underTestProducts.size());

    assertEquals("Product A", underTestProducts.get(0).getItem());
    assertEquals("38472374", underTestProducts.get(0).getBarcode());
    assertEquals(909, underTestProducts.get(0).getPrice());
    assertEquals(7, underTestProducts.get(0).getDiscount());
    assertEquals(1, underTestProducts.get(0).getAvailable());

    assertEquals("Product B", underTestProducts.get(1).getItem());
    assertEquals("74002423", underTestProducts.get(1).getBarcode());
    assertEquals(3548, underTestProducts.get(1).getPrice());
    assertEquals(12, underTestProducts.get(1).getDiscount());
    assertEquals(1, underTestProducts.get(1).getAvailable());

    verify(productRepository, times(1)).getAllProducts();
  }
}
