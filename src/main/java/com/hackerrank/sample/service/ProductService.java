package com.hackerrank.sample.service;


import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
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

  public SortedProducts[] getAllSortedProductsAscending() {
    return getAllProducts().stream()
        .sorted(Comparator.comparingInt(Product::getPrice))
        .map( product -> new SortedProducts(product.getBarcode()))
        .toArray(SortedProducts[]::new);
  }

  public ArrayList<FilteredProducts> getFilteredProducts(int minPrice, int maxPrice) {
    ArrayList<FilteredProducts> filteredProducts = new ArrayList<>();

    return new ArrayList<>(
        getAllProducts().stream()
            .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
            .map( product -> {
              return new FilteredProducts(product.getBarcode(), product.getPrice());
            })
            .collect(Collectors.toList())
    );

  }


}
