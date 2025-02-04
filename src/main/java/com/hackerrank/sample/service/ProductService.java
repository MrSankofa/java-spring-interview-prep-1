package com.hackerrank.sample.service;


import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    JSONArray rawProducts =  productRepository.getAllProducts();

    List<Product> products = new ArrayList<>();

    for (int i = 0; i < rawProducts.length(); i++) {
      JSONObject rawProduct = rawProducts.getJSONObject(i);

      Product product = new Product();

      product.setBarcode(rawProduct.optString("barcode"));
      product.setPrice(rawProduct.optInt("price"));

      products.add(product);
    }

    return products;
  }


}
