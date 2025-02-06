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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  ProductRepository productRepository;

  @InjectMocks
  ProductService productService;

  @Test
  void getAllProducts() throws JSONException {
    JSONArray mockJsonArray = new JSONArray();
    mockJsonArray.put(new JSONObject().put("id", 1)
        .put("item", "Product A")
        .put("barcode", "38472374")
        .put("price", "909")
        .put("discount", "7")
        .put("available", "1")
    );
    mockJsonArray.put(new JSONObject()
        .put("id", 2)
        .put("item", "Product B")
        .put("barcode", "74002423")
        .put("price", "3548")
        .put("discount", "12")
        .put("available", "1")
    );

    when(productRepository.findAllProducts()).thenReturn(mockJsonArray);

    List<Product> underTestProducts = productService.getAllProducts();

    assertNotNull(underTestProducts);
    assertEquals(2, underTestProducts.size());
    assertEquals("Product A", underTestProducts.get(0).getItem());
    assertEquals(909, underTestProducts.get(0).getPrice());
    assertEquals("38472374", underTestProducts.get(0).getBarcode());

    assertEquals("Product B", underTestProducts.get(1).getItem());
    assertEquals(3548, underTestProducts.get(1).getPrice());
    assertEquals("74002423", underTestProducts.get(1).getBarcode());
  }
}
