package com.vegan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vegan.dataobject.Product;
import com.vegan.dataobject.ProductForm;
import com.vegan.model.Items;
import com.vegan.service.ItemsService;

@CrossOrigin(origins = "*", maxAge = 36000)
@Controller
public class ItemsController {

	Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
	@Value("${items.rates.update.success.message}")
	private String itemRateUpdSuccessMsg;
	
	@Value("${items.rates.update.error.message}")
	private String itemRateUpdErrorMsg;
	
	@Value("${items.status.update.success.message}")
	private String itemStatusUpdSuccessMsg;
	
	@Value("${items.status.update.error.message}")
	private String itemStatusUpdErrorMsg;
	
	@Autowired
	ItemsService productsService;
	
	@ResponseBody
	@GetMapping(value="/api/items", headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Items> findAvailableItems() {
		List<Items> items = productsService.findAvailableItems();
		logger.info("Items = "+items);
		return items;
	}
	
	@GetMapping("/rate-card")
	public String findAllItems(Model model) {
		setItemModel(model, new ProductForm());
		return "rate-card";
	}
	
	@PostMapping("/toggleItemStatus")
	public String toggleItemStatus(@ModelAttribute ProductForm productForm,
			Model model,Principal principal) {
		System.out.println(productForm.getItemStatus());
		System.out.println(productForm.getProducts());
		int count = productsService.toggleItemStatus(productForm,principal.getName());
		if(count > 0) {
			ProductForm updStatusSuccess = new ProductForm(true,itemStatusUpdSuccessMsg);
			setItemModel(model,updStatusSuccess);
		}else {
			ProductForm updStatusError = new ProductForm(false,itemStatusUpdErrorMsg);
			setItemModel(model,updStatusError);
		}
		return "rate-card";
	}
	
	@PostMapping("/setItemRates")
	public String setItemRates(@ModelAttribute ProductForm productForm,
			Model model,Principal principal) {
		System.out.println("Cotr 1 ="+productForm.getProducts());
		boolean status = productsService.updateItemRates(productForm,principal.getName());
		if(status) {
			ProductForm updRateSuccess = new ProductForm(true,itemRateUpdSuccessMsg);
			setItemModel(model,updRateSuccess);
		}else {
			ProductForm updRateError = new ProductForm(false,itemRateUpdErrorMsg);
			setItemModel(model,updRateError);
		}
		return "rate-card";
	}
	
	private void setItemModel(Model model, ProductForm productForm) {
		List<Items> items = productsService.findAllItems();
		model.addAttribute("allItems", items);
		List<Product> products = new ArrayList<>(items.size());
		for(Items item : items) {
			Product product = new Product();
			product.setName(item.getName());
			product.setItemCategory(item.getItemCategory());
			product.setItemAvailability(item.getItemAvailability());
			product.setItemId(item.getId());
			product.setItemRate(item.getItemRate());
			product.setPrice(String.valueOf(item.getPrice()));
			product.setLastModifiedBy(item.getModifiedBy());
			products.add(product);
		}
		productForm.setProducts(products);
		model.addAttribute("productForm", productForm);
	}
}
