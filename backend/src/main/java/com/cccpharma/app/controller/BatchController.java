package com.cccpharma.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Batch> createProduct(@RequestParam(value="productId") Long productId, @RequestParam(value="quantity") int quantity,
			@RequestParam(value="expirationDate") String dateString) {
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
		
			System.out.println("Create batch of: " + productData.get().getName() + "...");
			batchRepository.save(batch);
			return new ResponseEntity<Batch>(batch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/batches/{id}")
	public ResponseEntity<Batch> getBatch(@PathVariable("id") Long id) {
		System.out.println("GET A BATCH BY ID...");
		
		Optional<Batch> batchData = batchRepository.findById(id);
		return batchData.isPresent()?
				new ResponseEntity<Batch>(batchData.get(), HttpStatus.OK):
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateBatch(@PathVariable("id") Long id, @RequestBody Batch batch) {
		System.out.println("UPDATING BATCH " + id + "...");
		
		Optional<Batch> batchData = batchRepository.findById(id);
		if(batchData.isPresent()) {
			Batch savedBatch = batchData.get();
//			savedBatch.setProductId(batch.getProductId());
			if (batch.getProductId() != savedBatch.getProductId()) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			savedBatch.setQuantity(batch.getQuantity());
			savedBatch.setExpirationDate(batch.getExpirationDate());
			
			Batch updatedBatch = batchRepository.save(savedBatch);
			return new ResponseEntity<Batch>(updatedBatch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/batches/delete/{id}")
	public ResponseEntity<String> deleteBatch(@PathVariable long id) {
		System.out.println("DELETING BATCH " + id + "...");
		
		try {
			batchRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>("Batch has been deleted.", HttpStatus.OK);
	}
}
