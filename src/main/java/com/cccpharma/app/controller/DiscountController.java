package com.cccpharma.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cccpharma.app.model.Discount;
import com.cccpharma.app.service.DiscountService;
import com.cccpharma.app.util.DiscountCategory;
import com.cccpharma.app.util.ProductCategory;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "https://cccpharma.herokuapp.com")
public class DiscountController {
	@Autowired
	private DiscountService discountService;
	
	
	@GetMapping("/discounts")
	public List<Discount> getAllDiscounts() {
		System.out.println("GETTING ALL DISCOUNTS...");
		List<Discount> discounts = discountService.getAllDiscounts();
		return discounts;
	}
	

	@PostMapping("/discount/create")
	public Discount createProduct(@Valid @RequestBody Discount discount) {
		
		System.out.println("Create discount: " + discount.getProductCategory().getProductCategory() + 
				" : " + discount.getDiscountCategory().getDiscountCategory());
		
		Discount oldDiscount = discountService.getDiscountById(discount.getProductCategory());
		
		if (oldDiscount != null)
			deleteDiscount(discount.getProductCategory());
		
		return discountService.create(discount);
	}

	@GetMapping("/discount/get/{category}")
	public Discount getById(@PathVariable("category") ProductCategory category) {
		return discountService.getDiscountById(category);
	}
	
	@GetMapping("/discount/category/{category}")
	public DiscountCategory getCategoryById(@PathVariable("category") ProductCategory category) {
		return discountService.getDiscountCategoryById(category);
	}

	@DeleteMapping("/discount/{category}")
	public ResponseEntity<String> deleteDiscount(@PathVariable("category") ProductCategory category) {
		System.out.println("DELETE DISCOUNT " + category.getProductCategory());
		
		if (discountService.delete(category)) {
			return new ResponseEntity<String>("Discount has been deleted.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed to delete.", HttpStatus.EXPECTATION_FAILED);
		}
	}
}
