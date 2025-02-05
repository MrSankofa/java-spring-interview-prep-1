package com.hackerrank.sample.service;


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


// TODO: recall what is a service, repository, configuration, controller, restController annotation? What does it do? Why do we use it?
// TODO: @SpringBootTest, @ExtendWIth(MockitoExtension.class), @mock, @InjectMocks
@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    JSONArray result = productRepository.getAllProducts();

    List<Product> products = new ArrayList<>();
    for(int i = 0; i < result.length(); i++) {
      JSONObject jsonObject = result.getJSONObject(i);

      Product product = new Product();
      product.setItem(jsonObject.optString("name"));
      product.setPrice(jsonObject.optInt("price"));
      product.setBarcode(jsonObject.optString("barcode"));
      product.setDiscount(jsonObject.optInt("discount"));
      product.setAvailable(jsonObject.optInt("available"));
      products.add(product);

    }

    return products;
  }

  public SortedProducts[] getSortedProducts() {
    // no explicit parameters, need to get all the products from the API, previous test make sure this matches the model/DTOs we expect
    // Return requires a change from a list of Products to an array of Sorted Products
    // so give ArrayList<Products> mapped to SortedProducts[] (only has barcode in its properties)

    // first getAllProducts
    // sort them in ascending order
    // map those sorted Products to SortedProducts
    // convert the sorted list of Products to an array of SortedProducts[]

    List<Product> products = getAllProducts().stream()
        .sorted( Comparator.comparingInt(Product::getPrice))
        .collect(Collectors.toList());

    List<SortedProducts> sortedProducts = products.stream()
        .map( product -> new SortedProducts( product.getBarcode()))
        .collect(Collectors.toList());

    SortedProducts[] sortedProductsArray = sortedProducts.toArray(new SortedProducts[sortedProducts.size()]);

    return sortedProductsArray;
  }


}
