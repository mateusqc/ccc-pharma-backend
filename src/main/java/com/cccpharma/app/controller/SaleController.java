package com.cccpharma.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Product;
import com.cccpharma.app.service.SaleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping("/sale/cart")
	public ResponseEntity<List<Product>> getCartProducts() {
		List<Product> products = saleService.getCartProducts();
		if (products != null) {
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/sale/cart")
	public ResponseEntity<String> setCartProducts(@RequestBody Map<Long,Integer> productMap,
			@RequestHeader(value = "Authorization") String token){
		return saleService.addProductsToCart(token, productMap)?
				new ResponseEntity<String>("Products added with success!", HttpStatus.OK):
				new ResponseEntity<String>("Cart was not created", HttpStatus.NOT_FOUND);
	}
}
