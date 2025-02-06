package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.service.ProductService;
import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = SampleController.class)
public class SampleControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean // mock won't work why
  private ProductService productService;


//  @BeforeEach
//  void setUp() {
//  }

  @Test
  public void sorted_books_success() throws Exception {
    // arrange

    SortedProducts[] mockedProducts = {
        new SortedProducts("12345678"),
        new SortedProducts("12345679")
    };

    when(productService.getAllSortedProducts()).thenReturn(mockedProducts);

    mockMvc.perform(
            get("/sort/price").contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].barCode").value("12345678"))
        .andExpect(jsonPath("$[1].barCode").value("12345679"));
  }

}
