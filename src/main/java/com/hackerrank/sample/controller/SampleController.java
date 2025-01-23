package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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
	   
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			try {
				
			
					ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			
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

				List<Product> productList = new ArrayList<>();

				// Parse JSON data into Product objects
				for (int i = 0; i < data.length(); i++) {
					JSONObject jsonObject = data.getJSONObject(i);

					Product product = new Product();
					product.setBarcode(jsonObject.optString("barcode"));
					product.setCategory(jsonObject.optString("category"));
					product.setPrice(jsonObject.getInt("price"));
					product.setDiscount(jsonObject.getInt("discount"));
					product.setAvailable(jsonObject.getInt("available"));

					productList.add(product);
				}

				// Sort products by price in ascending order
				productList.sort((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()));

				// Map sorted products to SortedProducts
				List<SortedProducts> sortedList = new ArrayList<>();
				for (Product product : productList) {
					sortedList.add(new SortedProducts(product.getBarcode()));
				}

				// Convert list to array
				SortedProducts[] sortedArray = sortedList.toArray(new SortedProducts[0]);

				return new ResponseEntity<>(sortedArray, HttpStatus.OK);
			    
			}catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
	
}
