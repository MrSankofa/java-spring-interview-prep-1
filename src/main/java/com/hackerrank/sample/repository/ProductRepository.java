package com.hackerrank.sample.repository;

import com.hackerrank.sample.dto.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


// TODO: Forgot the Respository annotation

@Repository
public class ProductRepository {

  String url = "https://jsonmock.hackerrank.com/api/inventory";

  // TODO: how to set up a custom repository

  @Autowired
  private RestTemplate restTemplate;


  public List<Product> getProducts() {
    String response = restTemplate.getForObject(url, String.class);

    JSONObject jsonObject = new JSONObject(response);

    JSONArray products = jsonObject.getJSONArray("data");

    List<Product> productList = new ArrayList<>();

    for (int i = 0; i < products.length(); i++) {
      productList.add((Product) products.get(i));
    }

    return productList;
  }
}
