package com.cccpharma.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.BatchRepository;
import com.cccpharma.app.repository.ProductRepository;
import com.cccpharma.app.util.ProductStatus;

@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Batch> getAllBatches() {
		List<Batch> list = new ArrayList<>();
		Iterable<Batch> data = batchRepository.findAll();
		data.forEach(list::add);
		
		return list;
	}
	
	public Batch createBatch(Long productId, int quantity, String dateString) {
		Optional<Product> productData = productRepository.findById(productId);
		if (productData.isPresent()) {
			Batch batch = new Batch();
			batch.setProductId(productId);
			Date expirationDate;			
			try {
				expirationDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
			} catch (ParseException e) {
				expirationDate = new Date();
				e.printStackTrace();
			}
			batch.setQuantity(quantity);	
			batch.setExpirationDate(expirationDate);
			
			if (quantity > 0 && expirationDate.compareTo(new Date()) <= 0) {
				Product product = productData.get();
				if (product.getStatus().equals(ProductStatus.UNAVAILABLE)) {
						product.setStatus(ProductStatus.AVAILABLE);
						productRepository.save(product);
				}
			}
		
			System.out.println("Create batch of: " + productData.get().getName() + "...");
			return batchRepository.save(batch);
		} else {
			return null;
		}
	}
	
	public Batch getBatchById(Long id) {
		Optional<Batch> batchData = batchRepository.findById(id);
		return batchData.isPresent()? batchData.get() : null;
	}

	public List<Batch> getBatchesByProductId(Long id) {
		List<Batch> list = getAllBatches();
		List<Batch> ret = new ArrayList<Batch>();
		for (Batch batch : list) {
			if (batch.getProductId() == id) {
				ret.add(batch);
			}
		}
		return ret;
	}

	public Batch updateBatch(Long id, Batch batch) {
		Optional<Batch> batchData = batchRepository.findById(id);
		if(batchData.isPresent()) {
			Batch savedBatch = batchData.get();
			if (batch.getProductId() != savedBatch.getProductId()) {
				return null;
			}
			savedBatch.setQuantity(batch.getQuantity());
			savedBatch.setExpirationDate(batch.getExpirationDate());
			
			return batchRepository.save(savedBatch);
		} else {
			return null;
		}
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
