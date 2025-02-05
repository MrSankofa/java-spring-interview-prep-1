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
import java.util.Optional;
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
      products.add(convertToProduct(new Product(), jsonObject));

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

  public ArrayList<FilteredProducts> getFilteredProducts(int init_price, int final_price) {
    // int initial and final price, need to get all products from the API
    // return is an ArrayList of FilteredProducts
    // given some JSONArray with JSONObjects convert those to a List of Products
    //

    // getAllProducts
    // filterProducts based on initial and final price
    // map List of Products to List of FilteredProducts
    // convert List to ArrayList

    List<Product> products = getAllProducts().stream()
        .filter( product -> product.getPrice() >= init_price && product.getPrice() <= final_price)
        .collect(Collectors.toList()
    );

    List<FilteredProducts> filteredProducts = products.stream().map( product ->
        new FilteredProducts(product.getBarcode(), product.getPrice()))
        .collect(Collectors.toList()
    );

    ArrayList<FilteredProducts> filteredProductsArray = new ArrayList<>(filteredProducts);
    return filteredProductsArray;
  }

  public Product createProduct(Product product) {
    JSONObject result = productRepository.createProduct(product);

    return convertToProduct(product, result);
  }

  private static Product convertToProduct(Product product, JSONObject result) {

    product.setId(result.optString("_id"));
    product.setItem(result.optString("item"));
    product.setPrice(result.optInt("price"));
    product.setBarcode(result.optString("barcode"));
    product.setDiscount(result.optInt("discount"));
    product.setAvailable(result.optInt("available"));
    product.setCategory(result.optString("category"));
    product.generateLocationURI();
    return product;
  }

  public Optional<Product> getProduct(String id) {
    JSONObject target = productRepository.getProduct(id);

    if(target == null) {
      return Optional.empty();
    } else {
      return Optional.of(convertToProduct(new Product(), target));
    }
  }

  public void deleteProduct(String id) {
    productRepository.deleteProduct(id);
  }

  public List<Product> getMinPriceProducts(int minPrice) {
    List<Product> result = getAllProducts();

    return result.stream().filter( product -> product.getPrice() >= minPrice).collect(Collectors.toList());
  }


}
