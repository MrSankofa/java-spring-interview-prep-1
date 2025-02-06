package com.hackerrank.sample.repository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepository {

  @Autowired
  RestTemplate restTemplate;

  final String uri = "https://jsonmock.hackerrank.com/api/inventory";

  public JSONArray findAllProducts() {
    String response = restTemplate.getForObject(uri, String.class);

    JSONObject root = new JSONObject(response);

    JSONArray jsonArray = root.getJSONArray("data");

    return jsonArray;
  }
}
