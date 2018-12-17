package com.cccpharma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cccpharma.app.model.Discount;
import com.cccpharma.app.repository.DiscountRepository;
import com.cccpharma.app.util.DiscountCategory;
import com.cccpharma.app.util.ProductCategory;

@Service
public class DiscountService {
	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> getAllDiscounts() {
		List<Discount> discountList = new ArrayList<>();
		Iterable<Discount> discountData = discountRepository.findAll();

		for (Discount discount : discountData) {
			discountList.add(discount);
		}
		
		return discountList;
	}
	
	public Discount getDiscountById(ProductCategory category) {
		Optional<Discount> discountData = discountRepository.findById(category);

		if (discountData.isPresent()) 
			return discountData.get();
			
		return new Discount(category,DiscountCategory.NO);
	}
	
	public DiscountCategory getDiscountCategoryById(ProductCategory category) {
		return getDiscountById(category).getDiscountCategory();
	}
	
	

	public Discount create(Discount discount) {
		return discountRepository.save(discount);
	}

	public boolean delete(ProductCategory productCategory) {
		try {
			discountRepository.deleteById(productCategory);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
