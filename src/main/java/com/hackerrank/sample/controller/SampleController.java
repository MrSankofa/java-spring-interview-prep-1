package com.hackerrank.sample.controller;

import java.util.ArrayList;
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
		 ProductService productService;
	   
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			try {

				if( init_price > final_price) {
					return new ResponseEntity<ArrayList<FilteredProducts>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
				}



				// comment 2:
				// how do you see the data so you know how to convert it from jsonObjects to our models
				// sys out the data and run a corresponding test.
					System.out.println("Sample data: " + data);
					ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();

				List<Product> products = productService.getProductList(data);



				products.stream().filter( p -> p.getPrice() >= init_price && p.getPrice() <= final_price).forEach( product -> {
					books.add(new FilteredProducts(product.getBarcode()));
				});



				if(books.isEmpty()) {
					return new ResponseEntity<ArrayList<FilteredProducts>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
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

				// comment 3:
				// start with the easiest route and build from there

				// we need to be able to query the api to get the data (done)
				// convert the jsonObjects to Products
				// sort those products in asc order by price
				// map those results to sorted products


				// create a service that has a function that takes in a jsonObject and returns
				// a Product

				List<Product> products = productService.getProductList(data);

				products = products.stream().sorted(Comparator.comparingInt(Product::getPrice)).collect(Collectors.toList());

//				SortedProducts[] sortedProducts = products.toArray(
//						new SortedProducts[products.size()]
//				);
//				SortedProducts[] sortedProducts = new SortedProducts[products.size()];

				SortedProducts[] sortedProducts = products.stream().map( p -> new SortedProducts(p.getBarcode()))
						.toArray(SortedProducts[]::new);

				return ResponseEntity.ok(sortedProducts);
			    
			} catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
				}
			
		}


}
