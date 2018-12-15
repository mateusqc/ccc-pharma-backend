package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.ProductRepository;
import com.cccpharma.app.util.ProductCategory;
import com.cccpharma.app.util.ProductStatus;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BatchService batchService;
	
	
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		Iterable<Product> productsData = productRepository.findAll();
		
		productsData.forEach(list::add);
		return list;
	}
	
	public Product getProductById(Long id) {
		Optional<Product> productData = productRepository.findById(id);
		return productData.isPresent()? productData.get() : null;
	}
	
	public List<Product> getProductsByCategory(ProductCategory category) {
		List<Product> list = new ArrayList<>();
		Iterable<Product> products = productRepository.findByCategory(category);
		
		products.forEach(list::add);
		return list;
	}
	
	public Integer getProductStock(Long id) {
		List<Batch> batches = batchService.getBatchesByProductId(id);
		Integer stock = 0;
		
		for (Batch batch : batches) 
			if (!batch.isExpired())
				stock += batch.getQuantity();
		
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
