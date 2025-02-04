package com.hackerrank.sample.repository;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepository {

  @Autowired
  private RestTemplate restTemplate;

  final String uri = "https://jsonmock.hackerrank.com/api/inventory";

  public JSONArray getData() {
    String result = restTemplate.getForObject(uri, String.class);
    JSONObject root = new JSONObject(result);
    return root.getJSONArray("data");
  }
}
