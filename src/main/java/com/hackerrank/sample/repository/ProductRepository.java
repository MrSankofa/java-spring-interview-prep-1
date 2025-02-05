package com.hackerrank.sample.repository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepository {

  final String uri = "https://jsonmock.hackerrank.com/api/inventory";

  @Autowired
  private RestTemplate restTemplate;

  public JSONArray getAllProducts() {
    String response = restTemplate.getForObject(uri, String.class);
//    String response1 = restTemplate.getForObject(uri, String.class);
    JSONObject root = new JSONObject(response);

    return root.getJSONArray("data");
  }

}
