package com.cccpharma.app.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.ProductRepository;
import com.cccpharma.app.util.ProductCategory;

@RestController
//@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		System.out.println("GETTING ALL PRODUCTS...");
		
		List<Product> list = new ArrayList<>();
		Iterable<Product> customers = productRepository.findAll();
		
		customers.forEach(list::add);
		return list;
	}
	
	@PostMapping("/products/create")
	public Product createProduct(@Valid @RequestBody Product product) {
		System.out.println("Create product: " + product.getName() + "...");
		return productRepository.save(product);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		System.out.println("GET A PRODUCT BY ID...");
		
		Optional<Product> productData = productRepository.findById(id);
		return productData.isPresent()?
				new ResponseEntity<Product>(productData.get(), HttpStatus.OK):
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products/category/{category}")
	public List<Product> getProductsCategory(@PathVariable("category") ProductCategory category) {
		System.out.println("GET ALL PRODUCTS FROM A CATHEGORY...");
		
		List<Product> list = new ArrayList<>();
		Iterable<Product> products = productRepository.findByCategory(category);
		
		products.forEach(list::add);
		return list;
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		System.out.println("UPDATING PRODUCT " + id + "...");
		
		Optional<Product> productData = productRepository.findById(id);
		if(productData.isPresent()) {
			Product savedProduct = productData.get();
			savedProduct.setName(product.getName());
			savedProduct.setBarCode(product.getBarCode());
			savedProduct.setManufacturer(product.getManufacturer());
			savedProduct.setStatus(product.getStatus());
			savedProduct.setCategory(product.getCategory());
			savedProduct.setPrice(product.getPrice());
			
			Product updatedProduct = productRepository.save(savedProduct);
			return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		System.out.println("DELETE PRODUCT " + id + "...");
		
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<String>("Product has been deleted.", HttpStatus.OK);
	}
}
