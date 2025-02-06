package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SampleController.class)
class SampleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Test
  @DisplayName("GET /sort/price - Success with two products")
  void testSortedBooksSuccess() throws Exception {
    SortedProducts[] mockedProducts = {
        new SortedProducts("12345678"),
        new SortedProducts("12345679")
    };

    when(productService.getSortedProducts()).thenReturn(mockedProducts);

    mockMvc.perform(get("/sort/price").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].barCode").value("12345678"))
        .andExpect(jsonPath("$[1].barCode").value("12345679"));
  }

  @Test
  @DisplayName("GET /sort/price - Success with empty result")
  void testSortedBooksEmpty() throws Exception {
    SortedProducts[] mockedProducts = {};
    when(productService.getSortedProducts()).thenReturn(mockedProducts);

    mockMvc.perform(get("/sort/price").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
  }

  @Test
  @DisplayName("GET /sort/price - CORS headers present")
  void testSortedBooksHandleCORS() throws Exception {
    mockMvc.perform(get("/sort/price")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Origin", "https://example.com"))
        .andExpect(header().exists("Access-Control-Allow-Origin"));
  }

  @Test
  @DisplayName("GET /sort/price - Returns 404 when exception occurs")
  void testSortedBooksHandle404() throws Exception {
    when(productService.getSortedProducts()).thenThrow(new RuntimeException("Error"));

    mockMvc.perform(get("/sort/price").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("GET /filter/price/100/200 - CORS headers present")
  void testFilteredBooksHandleCORS() throws Exception {
    mockMvc.perform(get("/filter/price/100/200")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Origin", "https://example.com"))
        .andExpect(header().exists("Access-Control-Allow-Origin"));
  }

  @Test
  @DisplayName("GET /filter/price/100/200 - Success with two filtered products")
  void testFilteredBooksSuccess() throws Exception {
    ArrayList<FilteredProducts> filteredProducts = new ArrayList<>();
    filteredProducts.add(new FilteredProducts("1231239", 100));
    filteredProducts.add(new FilteredProducts("12312310", 200));

    when(productService.getFilteredProducts(100, 200)).thenReturn(filteredProducts);

    mockMvc.perform(get("/filter/price/100/200")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].barCode").value("1231239"))
        .andExpect(jsonPath("$[1].barCode").value("12312310"));
  }

  @Test
  @DisplayName("GET /filter/price/100/99 - Bad Request")
  void testFilteredBooksBadRequest() throws Exception {
    mockMvc.perform(get("/filter/price/100/99"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("GET /filter/price/3/4 - Not Found for empty filtered products")
  void testFilteredBooksEmpty() throws Exception {
    // Simulate no products found for the range 3 to 4.
    when(productService.getFilteredProducts(3, 4)).thenReturn(new ArrayList<>());

    mockMvc.perform(get("/filter/price/3/4").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
