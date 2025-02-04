package com.hackerrank.sample.service;


import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getProducts() {
    JSONArray array = this.productRepository.getData();

    List<Product> productList = new ArrayList<Product>() {};

    for(int i = 0; i < array.length(); i++) {
      JSONObject result = array.getJSONObject(i);
      Product product = new Product();
      product.setPrice(result.getInt("price"));
      product.setDiscount(result.getInt("discount"));
      product.setItem(result.getString("name"));
      product.setBarcode(result.getString("barcode"));
      productList.add(product);
    }

    return productList;
  }

  public List<Product> getProductsSortedByPrice() {
    List<Product> products = getProducts();

    return products.stream()
        .sorted((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()))
        .collect(Collectors.toList());
  }
}
