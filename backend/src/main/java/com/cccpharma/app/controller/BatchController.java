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
import com.cccpharma.app.service.BatchService;

@RestController
public class BatchController {
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BatchService batchService;

	@GetMapping("/batches")
	public List<Batch> getAllBatches(){
		System.out.println("GETTING ALL BATCHES");
		
		return batchService.getAllBatches();
	}
	
	@PostMapping("/batches/create")
	public ResponseEntity<Batch> createProduct(@RequestParam(value="productId") Long productId, @RequestParam(value="quantity") int quantity,
			@RequestParam(value="expirationDate") String dateString) {
		Batch newBatch = batchService.createBatch(productId, quantity, dateString);
		if (newBatch != null) {
			return new ResponseEntity<Batch>(newBatch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/batches/{id}")
	public ResponseEntity<Batch> getBatch(@PathVariable("id") Long id) {
		System.out.println("GET A BATCH BY ID...");
		Batch batch = batchService.getBatchById(id);
		return batch != null ?
				new ResponseEntity<Batch>(batch, HttpStatus.OK):
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateBatch(@PathVariable("id") Long id, @RequestBody Batch batch) {
		System.out.println("UPDATING BATCH " + id + "...");
		
		Batch updatedBatch = batchService.updateBatch(id, batch);
		if(updatedBatch != null) {
			return new ResponseEntity<Batch>(updatedBatch, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/batches/delete/{id}")
	public ResponseEntity<String> deleteBatch(@PathVariable long id) {
		System.out.println("DELETING BATCH " + id + "...");
		
		try {
			batchService.deleteBatch(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>("Batch has been deleted.", HttpStatus.OK);
	}
}
