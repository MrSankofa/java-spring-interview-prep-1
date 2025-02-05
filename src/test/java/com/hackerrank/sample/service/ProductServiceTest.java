package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Testing conversion from JSONArray to list of DTO/Models

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  // TODO: Noticing a pattern with InjectMocks being the class you are testing
  // TODO: and mock being the dependency
  @InjectMocks
  private ProductService productService;

  JSONArray mockJsonArray;

  @BeforeEach
  void setUp() throws JSONException {
    mockJsonArray = new JSONArray();

    mockJsonArray.put(new JSONObject().put("id", 1)
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



    when(productRepository.getAllProducts()).thenReturn(mockJsonArray);

  }


  @Test
  void getAllProducts() {
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

  @Test
  void getSortedProducts() throws JSONException {
    mockJsonArray.put(new JSONObject()
        .put("id", 3)
        .put("name", "Product C")
        .put("barcode", "12345678")
        .put("price", "1")
        .put("discount", "12")
        .put("available", "1")
    );

    SortedProducts[] underTestSortedProducts = productService.getSortedProducts();

    assertEquals(3, underTestSortedProducts.length);
    assertEquals("12345678", underTestSortedProducts[0].getBarCode());
    assertEquals("38472374", underTestSortedProducts[1].getBarCode());
    assertEquals("74002423", underTestSortedProducts[2].getBarCode());

    verify(productRepository, times(1)).getAllProducts();

  }

  @Test
  void testGetSortedProducts_HandleNullPrice() throws JSONException {

    mockJsonArray.put(new JSONObject().put("id", 3).put("name", "Product C").put("barcode", "1111").put("price", 30));
    mockJsonArray.put(new JSONObject().put("id", 4).put("name", "Product D").put("barcode", "2222").put("price", JSONObject.NULL)); // Null price

    when(productRepository.getAllProducts()).thenReturn(mockJsonArray);

//    Exception exception = assertThrows(NullPointerException.class, () -> productService.getSortedProducts());
//
//    assertNotNull(exception);
    assertDoesNotThrow(() -> productService.getSortedProducts());
    verify(productRepository, times(1)).getAllProducts();
  }

  @Test
  void testGetSortedProducts_HandleNonNumericPrice() throws JSONException {

    mockJsonArray.put(new JSONObject().put("id", 3).put("name", "Product C").put("barcode", "1111").put("price", "not_a_number"));
    mockJsonArray.put(new JSONObject().put("id", 4).put("name", "Product D").put("barcode", "2222").put("price", "20"));

    when(productRepository.getAllProducts()).thenReturn(mockJsonArray);

//    Exception exception = assertThrows(JSONException.class, () -> productService.getSortedProducts());
//
//    assertNotNull(exception);

    assertDoesNotThrow(() -> productService.getSortedProducts());
    verify(productRepository, times(1)).getAllProducts();
  }

  @Test
  void getFilteredProducts_success() throws JSONException {
    mockJsonArray.put(new JSONObject().put("id", 3).put("name", "Product C").put("barcode", "1111").put("price", 30));
    mockJsonArray.put(new JSONObject().put("id", 4).put("name", "Product D").put("barcode", "2222").put("price", 40)); // Null price

    when(productRepository.getAllProducts()).thenReturn(mockJsonArray);

    assertEquals(2, productService.getFilteredProducts(30, 40).size());
  }

  @Test
  void getFilteredProducts_empty() {
  }

}
