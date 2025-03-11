package com.hackerrank.sample.repository;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import com.hackerrank.sample.dto.Product;

@Repository
public class ProductRepository {

  @Autowired
  public RestTemplate restTemplate;

  final String uri = "https://jsonmock.hackerrank.com/api/inventory";

  public List<Product> getProducts() {
    String result = restTemplate.getForObject(uri, String.class);

    JSONObject root = new JSONObject(result);

    JSONArray products = root.getJSONArray("data");

    List<Product> productList = new ArrayList<>();

    for(int i = 0; i < products.length(); i++) {
      JSONObject rawProduct = products.getJSONObject(i);
      Product product = new Product();

      product.setAvailable(rawProduct.optInt("available"));
      product.setBarcode(rawProduct.optString("barcode"));
      product.setCategory(rawProduct.optString("category"));
      product.setDiscount(rawProduct.optInt("discount"));
      product.setItem(rawProduct.optString("item"));
      product.setPrice(rawProduct.optInt("price"));

      productList.add(product);
    }

    return  productList;

  }

}
