package com.cccpharma.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.model.Sale;
import com.cccpharma.app.service.SaleService;

@RestController
@CrossOrigin(origins = "https://cccpharma.herokuapp.com")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping("/sale/cart")
	public ResponseEntity<List<Product>> getCartProducts() {
		List<Product> products = saleService.getCartProducts();
		if (products != null) {
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/sale/cart")
	public ResponseEntity<String> setCartProducts(@RequestBody Map<Long,Integer> productMap,
			@RequestHeader(value = "Authorization") String token){
		return saleService.addProductsToCart(token, productMap)?
				new ResponseEntity<String>("Products added with success!", HttpStatus.OK):
				new ResponseEntity<String>("Cart was not created", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/sale/cart")
	public ResponseEntity<String> cleanCart() {
		saleService.cleanCart();
		return new ResponseEntity<String>("Cart was Cleaned.", HttpStatus.OK);
	}
	
	@GetMapping("/sale")
	public List<Sale> getAllSales() {
		System.out.println("GET ALL SALES...");
		return saleService.getAllSales();
	}
	
	@DeleteMapping("/sale/{id}")
	public ResponseEntity<String> deleteSale(@PathVariable("id") Long id) {
		if (saleService.deleteSaleById(id)) {
			return new ResponseEntity<String>("Product has been deleted.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
