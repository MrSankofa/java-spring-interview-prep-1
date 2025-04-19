package com.hackerrank.sample.service;


import com.hackerrank.sample.dto.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ProductService {

  public ArrayList<Product> getProducts(JSONArray jsonArray) {
    ArrayList<Product> products = new ArrayList<>();

    for(int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);

      Product product = new Product();

      product.setItem(jsonObject.optString("item"));
      product.setBarcode(jsonObject.optString("barcode"));
      product.setAvailable(jsonObject.optInt("available"));
      product.setDiscount(jsonObject.optInt("discount"));
      product.setPrice(jsonObject.optInt("price"));
      product.setCategory(jsonObject.optString("category"));

      products.add(product);
    }

    return products;
  }
}
