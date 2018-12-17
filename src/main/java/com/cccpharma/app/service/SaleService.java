package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Cart;
import com.cccpharma.app.model.Product;

@Service
public class SaleService {
	
	private Cart cart;
	
	@Autowired
	private ProductService productService;
	
	public boolean addProductsToCart(String userToken, Map<Long, Integer> productsMap) {
		for (Long productId : productsMap.keySet()) {
			Product product = productService.getProductById(productId);
			if (product.getStock() < productsMap.get(productId)) {
				return false;
			}
		}
		
		if (cart == null) {
			cart = new Cart(userToken, productsMap);
		} else {
			cart.setUserToken(userToken);
			cart.setProducts(productsMap);
		}
		
		return true;
	}
	
	public Map<Long, Integer> getCartMap(String userToken) {
		if (cart.getUserToken() == userToken) {
			return cart.getProducts();
		} else {
			return null;
		}
	}
	
	public List<Product> getCartProducts() {
		List<Product> productList = new ArrayList<>();
		if (cart != null) {
			for (Long productId : cart.getProducts().keySet()) {
				productList.add(productService.getProductById(productId));
			}
			
		}
		return productList;
		
	}
	
	public void cleanCart() {
		cart.getProducts().clear();
	}
}
