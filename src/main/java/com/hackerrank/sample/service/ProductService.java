package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


  public Product convertToProduct(JSONObject jsonObject) {
    Product product = new Product();

    product.setItem(jsonObject.optString("item"));
    product.setPrice(jsonObject.optInt("price"));
    product.setDiscount(jsonObject.optInt("discount"));
    product.setCategory(jsonObject.optString("category"));
    product.setBarcode(jsonObject.optString("barcode"));
    product.setAvailable(jsonObject.optInt("available"));
    return product;
  }

  public List<Product> getProductList(JSONArray data) {
    List<Product> products = new ArrayList<>();

    for(int i = 0; i < data.length(); i++) {
      JSONObject jsonObject = data.getJSONObject(i);

      products.add(convertToProduct(jsonObject));
    }
    return products;
  }
}
