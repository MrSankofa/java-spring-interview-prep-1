package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.List;

import com.hackerrank.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.dto.Product;

@RestController
public class SampleController {


	@Autowired
	private ProductService productService;
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			try {
				
			
					ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			
					return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);
			    
			} catch(Exception E)
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

				// TODO: How do you take data from the restTemplate and convert it into your models?
				// TODO: What is a JSONObject and how do you use it?
				// TODO: What is a JSONArray and how do you use it?
					// you need to loop through the length the JSONArray and use the getJSONObject method to
				  // get the element
				  // then you can use the model's constructor to convert the JSONObject props to the model
				// convert JSONArray data into sortedProducts
//				System.out.println("what is the data: " + data);

				// MOve to service


				// TODO: how do you use the Comparator function
				// TODO: how do you sort in java on array lists
				;

				// TODO: map a list of Products into Sorted Products




				List<Product> products = productService.getProductsSortedByPrice();
				return ResponseEntity.ok(
						products.stream()
								.map( product -> new SortedProducts(product.getBarcode()))
								.toArray( SortedProducts[]::new)
				);
			}catch(Exception E) {
				System.out.println("Error encountered : "+E.getMessage());
				return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
	
}
