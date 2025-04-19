package com.hackerrank.sample.controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.hackerrank.sample.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.dto.Product;

@RestController
public class SampleController {

	
	   final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	   RestTemplate restTemplate = new RestTemplate();
	   String result = restTemplate.getForObject(uri, String.class);			
	   JSONObject root = new JSONObject(result);
	   
	   JSONArray data = root.getJSONArray("data");


		 @Autowired
		 private ProductService productService;


  @CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			try {

					if(init_price > final_price) {
						return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
					}


				
			
					ArrayList<FilteredProducts> books = new ArrayList<>(productService.getProducts(data).stream().filter(
							product -> product.getPrice() >= init_price && product.getPrice() <= final_price
					).map( p -> {
						return new FilteredProducts(p.getBarcode());
					}).collect(Collectors.toList()));

					if(books.isEmpty()) {
						return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
					}
			
				    return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);

			   
			    
			}catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
		@CrossOrigin
		@GetMapping("/sort/price")  
		private ResponseEntity<SortedProducts[]> sorted_books()   
		{  
			
			try {

				ArrayList<Product> products = productService.getProducts(data);

				ArrayList<SortedProducts> sortedProducts = new ArrayList<>(products.stream()
						.sorted(Comparator.comparingInt(Product::getPrice))
						.map(p -> new SortedProducts(p.getBarcode()))
						.collect(Collectors.toList()));

				SortedProducts[] sortedProductsArray = sortedProducts.toArray(new SortedProducts[sortedProducts.size()]);



				return ResponseEntity.ok(sortedProductsArray);
			    
			}catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
	
}
