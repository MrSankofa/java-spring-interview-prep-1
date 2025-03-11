package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.Product;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.repository.ProductRepository;
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
    return productRepository.getProducts();
  }

  public SortedProducts[] getSortedProducts() {
    // should take the products and sort them in ascending order
    // then return them in an array of SortedProducts

    return getAllProducts().stream()
        .sorted(Comparator.comparingInt(Product::getPrice))
        .map(product -> new SortedProducts(product.getBarcode()))
        .toArray(SortedProducts[]::new);
  }

  public ArrayList<FilteredProducts> getFilteredProducts(int init_price, int final_price) {

    List<FilteredProducts> filteredProducts = productRepository.getProducts().stream()
        .filter( product -> product.getPrice() >= init_price && product.getPrice() <= final_price)
        .map( product -> new FilteredProducts(product.getBarcode()))
        .collect(Collectors.toList());

    ArrayList<FilteredProducts> filteredProductsArray = new ArrayList<>();

    filteredProductsArray.addAll(filteredProducts);

    return filteredProductsArray;


  }

}
