package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = SampleController.class)
@ExtendWith(MockitoExtension.class)
class SampleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  // TODO: why do we use MockBean instead of Mock?
  @MockBean
  private ProductService productService;

  @InjectMocks
  private SampleController sampleController;


//  @BeforeEach
//  void setUp() {
//  }

  @Test
  void testSortedBooks_Success() throws Exception {
    // should return 200 status
    SortedProducts[] mockedProducts = {
        new SortedProducts("12345678"),
        new SortedProducts("12345679")
    };

    when(productService.getSortedProducts()).thenReturn(mockedProducts);

    mockMvc.perform(
        get("/sort/price").contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].barCode").value("12345678"))
        .andExpect(jsonPath("$[1].barCode").value("12345679"));
  }

  // this test is dependent on the productService being used
  @Test
  void testSortedBooks_Empty() throws Exception {

    SortedProducts[] mockedProducts = {};

    when(productService.getSortedProducts()).thenReturn(mockedProducts);

    mockMvc.perform(
            get("/sort/price").contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.length()").value(0));
  }

  /*
  * Adding the Origin header simulates a real CORS request,
  * which triggers Spring Boot to include CORS response headers.
  * Without it, the request is considered a same-origin request,
  * and Spring Boot does not add CORS headers.
  * */
  @Test
  void testSortedBooks_HandleCORS() throws Exception {
    mockMvc.perform(
          get("/sort/price")
          .contentType(MediaType.APPLICATION_JSON)
              .header("Origin", "https://example.com")
        )
        .andExpect(header().exists("Access-Control-Allow-Origin"));
  }

  // this test is dependent on the productService being used
  @Test
  void testSortedBooks_Handle404() throws Exception {

    when(productService.getSortedProducts()).thenThrow(new RuntimeException());

    mockMvc.perform(
        get("/sort/price").contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isNotFound());
  }
}
