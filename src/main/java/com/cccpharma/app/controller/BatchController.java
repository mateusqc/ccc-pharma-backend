package com.cccpharma.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Batch;
import com.cccpharma.app.service.BatchService;

@RestController
@CrossOrigin(origins = "https://cccpharma.herokuapp.com")
public class BatchController {
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
	
	@GetMapping("/batches/product/{id}")
	public ResponseEntity<List<Batch>> updateBatch(@PathVariable("id") Long id) {
		System.out.println("GET BATCHES OF PRODUCT " + id + "...");
		
		List<Batch> batchList = batchService.getBatchesByProductId(id);
		if(batchList != null) {
			return new ResponseEntity<List<Batch>>(batchList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateBatch(@PathVariable("id") Long id, @RequestParam(value="quantity") Integer quantity,
			@RequestParam(value="expirationDate") String expirationDate) {
		System.out.println("UPDATING BATCH " + id + "...");
		
		Batch updatedBatch = batchService.updateBatch(id, quantity, expirationDate);
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
	
	@GetMapping("/batches/toExpire")
	public List<Batch> getToExpireBatches(){
		System.out.println("GET TO EXPIRE BATCHES...");
		return batchService.getExpiringBatches();
	}
}
