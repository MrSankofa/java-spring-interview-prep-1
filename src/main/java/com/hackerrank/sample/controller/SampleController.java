package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hackerrank.sample.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

				ArrayList<FilteredProducts> results = productService.getFilteredProducts(init_price, final_price);
				if(results.isEmpty()) {
					return ResponseEntity.notFound().build();
				}
			
				return ResponseEntity.ok(results);

			   
			    
			} catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
				}
			
		}

		@CrossOrigin
		@PostMapping("/product")
		public ResponseEntity<?> createProduct(@RequestBody Product product) {
			try {
				Product product1 = productService.createProduct(product);
				product1.generateLocationURI();
					return ResponseEntity.created(product1.getLocationURI()).body(product1);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
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

		@CrossOrigin
		@DeleteMapping("/product/{id}")
		private ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
			// see if the product exists
			Optional<Product> product = productService.getProduct(id);

			if(product.isPresent()) {
				// delete the product
				productService.deleteProduct(id);
			} else {
				// if not return not found
				return ResponseEntity.notFound().build();
			}



			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		@CrossOrigin
		@GetMapping("/product/{id}")
		private ResponseEntity<?> getProduct(@PathVariable("id") String id) {
			try {
				Optional<Product> product = productService.getProduct(id);
				if(product.isPresent()) {
					return ResponseEntity.ok(product.get());
				} else {
					return ResponseEntity.notFound().build();
				}

			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}

		@CrossOrigin
		@GetMapping("/products")
		private ResponseEntity<?> getProducts(@RequestParam(required = false) int minPrice) {
			try {
				List<Product> products = productService.getMinPriceProducts(minPrice);
				if(products.isEmpty()) {
					return ResponseEntity.notFound().build();
				} else {
					return ResponseEntity.ok(products);
				}
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}

}
