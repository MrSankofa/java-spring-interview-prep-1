package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    JSONArray response = productRepository.findAllProducts();

    List<Product> products = new ArrayList<>();

    for (int i = 0; i < response.length(); i++) {
      Product product = new Product();

      product.setItem(response.getJSONObject(i).optString("item"));
      product.setCategory(response.getJSONObject(i).optString("category"));
      product.setAvailable(response.getJSONObject(i).optInt("available"));
      product.setBarcode(response.getJSONObject(i).optString("barcode"));
      product.setDiscount(response.getJSONObject(i).optInt("discount"));
      product.setPrice(response.getJSONObject(i).optInt("price"));

      products.add(product);
    }

    return products;

  }

  public SortedProducts[] getAllSortedProducts() {
    List<Product> products = getAllProducts();

    // we want to sort the products
    // map the products to sortedProducts
    // convert to SortedProducts array

    List<SortedProducts> spList = products.stream()
        .sorted(Comparator.comparingInt(Product::getPrice))
        .map( product -> new SortedProducts(product.getBarcode()))
        .collect(Collectors.toList());


    return spList.toArray(new SortedProducts[]{});
  }


}
