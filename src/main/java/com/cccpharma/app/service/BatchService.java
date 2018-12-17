package com.cccpharma.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.BatchRepository;
import com.cccpharma.app.repository.ProductRepository;

@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired ProductService productService;
	
	public List<Batch> getAllBatches() {
		List<Batch> list = new ArrayList<>();
		Iterable<Batch> data = batchRepository.findAll();
		for (Batch batch : data) {
			Product product = batch.getProduct();
			product.setStockData();
			product.setDiscount(productService.getProductDiscountCategory(product));
			list.add(batch);
		}
		
		return list;
	}
	
	public Batch createBatch(Long productId, int quantity, String dateString) {
		Optional<Product> productData = productRepository.findById(productId);
		if (productData.isPresent()) {
			Product product = productData.get();
			product.setStockData();
			product.setDiscount(productService.getProductDiscountCategory(product));
			Batch batch = new Batch();
			Date expirationDate;			
			try {
				expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			} catch (ParseException e) {
				expirationDate = new Date();
				e.printStackTrace();
			}
			batch.setQuantity(quantity);	
			batch.setExpirationDate(expirationDate);
			batch.setProduct(product);
		
			System.out.println("Create batch of: " + productData.get().getName() + "...");
			return batchRepository.save(batch);
		} else {
			return null;
		}
	}
	
	public Batch getBatchById(Long id) {
		Optional<Batch> batchData = batchRepository.findById(id);
		if (batchData.isPresent()) {
			Batch batch = batchData.get();
			Product product = batch.getProduct();
			product.setStockData();
			product.setDiscount(productService.getProductDiscountCategory(product));
			return batch;
		} else {
			return null;
		}
	}

	public List<Batch> getBatchesByProductId(Long id) {
		Optional<Product> productData = productRepository.findById(id);
		if (productData.isPresent()) {
			return productData.get().getBatches();
		} else {
			return null;
		}
	}

	public Batch updateBatch(Long id, Integer quantity, String dateString) {
		Optional<Batch> batchData = batchRepository.findById(id);
		if(batchData.isPresent()) {
			Batch savedBatch = batchData.get();
			if (dateString != null) {
				if (!dateString.trim().equals("")) {
					Date expirationDate;
					try {
						expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
					} catch (ParseException e) {
						expirationDate = new Date();
						e.printStackTrace();
					}
					savedBatch.setExpirationDate(expirationDate);
				}
			}
			if (quantity != null) {
				savedBatch.setQuantity(quantity);
			}
			
			return batchRepository.save(savedBatch);
		} else {
			System.out.println("PRODUCT NOT FOUND");
			return null;
		}
	}
	
	public List<Batch> getExpiringBatches() {
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, +30);
		Date month = cal.getTime();
		return batchRepository.findByNextToExpireDate(month, today);
	}
	
	public boolean deleteBatch(Long id) {
		try {
			batchRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
