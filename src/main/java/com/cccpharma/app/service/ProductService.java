package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.ProductRepository;
import com.cccpharma.app.util.DiscountCategory;
import com.cccpharma.app.util.ProductCategory;
import com.cccpharma.app.util.ProductStatus;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private DiscountService discountService;
	
	
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		Iterable<Product> productsData = productRepository.findAll();
		
		for (Product product : productsData) {
			product.setStockData();
			product.setDiscount(getProductDiscountCategory(product));
			productList.add(product);
		}
		
		return productList;
	}
	
	public Product getProductById(Long id) {
		Optional<Product> productData = productRepository.findById(id);
		if (productData.isPresent()) {
			Product product = productData.get();
			product.setStockData();
			product.setDiscount(getProductDiscountCategory(product));
			return product;
		} else {
			return null;
		}
	}
	
	public Product getProductByBarCode(String barCode) {
		Product product = productRepository.findByBarCode(barCode);
		product.setStockData();
		product.setDiscount(getProductDiscountCategory(product));
		return product;
	}
	
	public DiscountCategory getProductDiscountCategory(Product product) {
		return discountService.getDiscountCategoryById(product.getCategory());
		
	}
	public List<Product> getProductsByCategory(ProductCategory category) {
		List<Product> productList = new ArrayList<>();
		Iterable<Product> products = productRepository.findByCategory(category);
		
		for (Product product : products) {
			product.setStockData();
			product.setDiscount(getProductDiscountCategory(product));
			productList.add(product);
		}
		return productList;
	}
	
	public List<Product> getUnavailableProducts() {
		List<Product> productList = new ArrayList<>();
		Iterable<Product> productsData = productRepository.findAll();
		for (Product product : productsData) {
			product.setStockData();
			product.setDiscount(getProductDiscountCategory(product));
			if (product.getStatus().equals(ProductStatus.UNAVAILABLE)) {
				productList.add(product);
			}
		}
		return productList;
	}
	
	public List<Product> getLowStockProducts() {
		List<Product> productList = new ArrayList<>();
		Iterable<Product> productsData = productRepository.findAll();
		for (Product product : productsData) {
			product.setStockData();
			product.setDiscount(getProductDiscountCategory(product));
			if (product.getStock() < 15 && product.getStock() > 0) {
				productList.add(product);
			}
		}
		return productList;
	}

	public Integer[] getProductStock(Long id) {
		Product product = getProductById(id);
		Integer[] stock = {0,0};
		
		for (Batch batch : product.getBatches()) { 
			if (!batch.isExpired()) {
				stock[0] += batch.getQuantity();
			} else {
				stock[1] += batch.getQuantity();
			}
		}
		return stock;
	}
	
	
	public Product update(Long id, Product newProduct) {
		Optional<Product> productData = productRepository.findById(id);
		if(productData.isPresent()) {
			Product savedProduct = productData.get();
			savedProduct.setName(newProduct.getName());
			savedProduct.setBarCode(newProduct.getBarCode());
			savedProduct.setManufacturer(newProduct.getManufacturer());
			savedProduct.setStatus(newProduct.getStatus());
			savedProduct.setCategory(newProduct.getCategory());
			savedProduct.setPrice(newProduct.getPrice());
			
			return productRepository.save(savedProduct);
		} else {
			return null;
		}
	}
	
	public Product create(Product product) {
		if (product.getStatus() == null || !product.getStatus().equals(ProductStatus.UNAVAILABLE)) {
			product.setStatus(ProductStatus.UNAVAILABLE);
		}
//		product.setStockData();
		return productRepository.save(product);
	}
	
	public boolean delete(Long id) {
		try {
			productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
