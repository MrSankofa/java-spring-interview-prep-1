package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	
	   @Autowired
		 ProductService productService;
	   
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			try {

				if(init_price > final_price) {
					return ResponseEntity.badRequest().body(new ArrayList<>());
				}

			
				return ResponseEntity.ok(productService.getFilteredProducts(init_price, final_price));

			   
			    
			} catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
				}
			
		}


		@CrossOrigin
		@GetMapping("/sort/price")
		private ResponseEntity<SortedProducts[]> sorted_books() {

			try {

				return ResponseEntity.ok(productService.getSortedProducts());

			} catch(Exception E) {
				System.out.println("Error encountered : "+E.getMessage());
				return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
			}
		}
}
