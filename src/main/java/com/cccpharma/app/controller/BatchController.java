package com.cccpharma.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.model.Product;
import com.cccpharma.app.repository.BatchRepository;
import com.cccpharma.app.repository.ProductRepository;

@RestController
public class BatchController {
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/batches")
	public List<Batch> getAllBatches(){
		System.out.println("GETTING ALL BATCHES");
		
		List<Batch> list = new ArrayList<>();
		Iterable<Batch> data = batchRepository.findAll();
		data.forEach(list::add);
		
		return list;
	}
	
	@PostMapping("/batches/create")
	public ResponseEntity<Batch> createProduct(@RequestParam(value="productId") Long productId, @RequestParam(value="quantity") int quantity) {
		Batch batch = new Batch();
//		Date expiration = new Date(expirationDate);
		batch.setQuantity(quantity);	
		batch.setShelfLife(null);
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			batch.setProduct(product.get());
			System.out.println("Create batch of: " + batch.getProduct().getName() + "...");
			return new ResponseEntity<Batch>(batchRepository.save(batch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
