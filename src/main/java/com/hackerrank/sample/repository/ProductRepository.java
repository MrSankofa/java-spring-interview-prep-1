package com.hackerrank.sample.repository;

import com.hackerrank.sample.dto.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepository {

  final String uri = "https://jsonmock.hackerrank.com/api/inventory";
  final String crud_uri = "https://crudcrud.com/api/";

  @Value("${api_key}")
  public String apiKey;

  @Autowired
  private RestTemplate restTemplate;



  public JSONArray getAllProducts() {
    String response = restTemplate.getForObject(uri, String.class);
//    String response1 = restTemplate.getForObject(uri, String.class);
    JSONObject root = new JSONObject(response);

    return root.getJSONArray("data");
  }

  public JSONObject createProduct(Product product) {
    String response = restTemplate.postForObject(crud_uri + apiKey, product, String.class);

    JSONObject root = new JSONObject(response);

    System.out.println("deserialized json response: " + root);

    return root;
  }

  public JSONObject getProduct(String id) {
    String response = restTemplate.getForObject(crud_uri + apiKey + "/Product/" + id, String.class);

    JSONObject root = new JSONObject(response);

    return root;
  }

  public void deleteProduct(String id) {
    restTemplate.delete(crud_uri + apiKey + "/Product/" + id);
  }

}
