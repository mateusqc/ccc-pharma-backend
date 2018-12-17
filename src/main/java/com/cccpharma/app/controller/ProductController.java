package com.cccpharma.app.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.service.ProductService;
import com.cccpharma.app.util.ProductCategory;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "https://cccpharma.herokuapp.com")
public class ProductController {
	@Autowired
	private ProductService productService;

	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		System.out.println("GETTING ALL PRODUCTS...");
		List<Product> products = productService.getAllProducts();
		return products;
	}
	
	@PostMapping("/products/create")
	public Product createProduct(@Valid @RequestBody Product product) {
		System.out.println("Create product: " + product.getName() + "...");
		return productService.create(product);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		System.out.println("GET A PRODUCT BY ID...");
		Product product = productService.getProductById(id);
		
		return product != null?
				new ResponseEntity<Product>(product, HttpStatus.OK):
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/stock/{id}")
	public ResponseEntity<Integer[]> getProductStock(@PathVariable("id") Long id) {
		System.out.println("GET stock  from Batches and product ID...");
		Integer stock[] = productService.getProductStock(id);
	
		HttpHeaders headers = new HttpHeaders();
		headers.add("type", "no-cors");
		
		//returns Integer[stock,expiredstock]
		return new ResponseEntity<Integer[]>(stock, headers, HttpStatus.OK);
	}
	

	@GetMapping("/products/barcode/{code}")
	public Product getAllProducts(@PathVariable("code") String barCode) {
		System.out.println("GETTING PRODUCT BY BAR CODE...");
		return productService.getProductByBarCode(barCode);
	}
	
	@GetMapping("/products/category/{category}")
	public List<Product> getProductsCategory(@PathVariable("category") ProductCategory category) {
		System.out.println("GET ALL PRODUCTS FROM A CATHEGORY...");
		return productService.getProductsByCategory(category);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		System.out.println("UPDATING PRODUCT " + id + "...");
				
		Product updatedProduct = productService.update(id, product);
		if(updatedProduct != null) {
			return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		System.out.println("DELETE PRODUCT " + id + "...");
		
		if (productService.delete(id)) {
			return new ResponseEntity<String>("Product has been deleted.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/products/unavailable")
	public List<Product> getUnavailableProducts() {
		System.out.println("GET ALL UNAVAILABLE PRODUCTS...");
		return productService.getUnavailableProducts();
	}
	
	@GetMapping("/products/lowStock")
	public List<Product> getLowStockProducts() {
		System.out.println("GET ALL LOW STOCK PRODUCTS...");
		return productService.getLowStockProducts();
	}
}
