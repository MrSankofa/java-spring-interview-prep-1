package com.hackerrank.sample.service;


import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// TODO: recall what is a service, repository, configuration, controller, restController annotation? What does it do? Why do we use it?
// TODO: @SpringBootTest, @ExtendWIth(MockitoExtension.class), @mock, @InjectMocks
@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    JSONArray result = productRepository.getAllProducts();

    List<Product> products = new ArrayList<>();
    for(int i = 0; i < result.length(); i++) {
      JSONObject jsonObject = result.getJSONObject(i);

      Product product = new Product();
      product.setItem(jsonObject.optString("name"));
      product.setPrice(jsonObject.getInt("price"));
      product.setBarcode(jsonObject.optString("barcode"));
      product.setDiscount(jsonObject.getInt("discount"));
      product.setAvailable(jsonObject.getInt("available"));
      products.add(product);

    }

    return products;
  }

  public SortedProducts[] getSortedProducts() {
    return new SortedProducts[]{};
  }


}
