package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Cart;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.model.Sale;
import com.cccpharma.app.repository.SaleRepository;

@Service
public class SaleService {
	
	private Cart cart;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleRepository saleRepository;
	
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
	
	public List<Sale> getAllSales() {
		List<Sale> saleList = new ArrayList<>();
		Iterable<Sale> saleData = saleRepository.findAll();
		for (Sale sale : saleData) {
			saleList.add(sale);
		}
		return saleList;
	}
	
	public Sale getSaleById(Long id) {
		Optional<Sale> saleData = saleRepository.findById(id);
		if(saleData.isPresent()) {
			return saleData.get();
		} else {
			return null;
		}
	}
	
	public List<Sale> getSaleByProduct(Product product) {
		List<Sale> saleList = saleRepository.findByProduct(product);
		if (saleList != null) {
			return saleList;
		} else {
			return new ArrayList<>();
		}	
	}
	
	public boolean deleteSaleById(Long id) {
		try {
			saleRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
}
