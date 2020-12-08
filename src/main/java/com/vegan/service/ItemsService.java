package com.vegan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegan.constants.ItemCategories;
import com.vegan.dataobject.Product;
import com.vegan.dataobject.ProductForm;
import com.vegan.model.Items;
import com.vegan.repository.ItemRatesRepository;
import com.vegan.repository.ItemsRepository;

@Service
public class ItemsService {

	Logger logger = LoggerFactory.getLogger(ItemsService.class);
	/*@Value("${vegetables.file.json}")
	String vegetablesFilePath;
	
	public Vegetables[] getVegtables() {
		ObjectMapper objectMapper = new ObjectMapper();
		Vegetables[] vegetables = null;
		try {
			System.out.println("File Path = "+vegetablesFilePath+" present = "+new File(vegetablesFilePath).exists());
			vegetables = objectMapper.readValue(new File(vegetablesFilePath), Vegetables[].class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Vegetables = "+vegetables);
		return filterActive(vegetables);
	}
	
	private Vegetables[]  filterActive(Vegetables[] vegetables) {
		if(vegetables != null) {
			return Arrays.stream(vegetables)
				.filter(vegetable -> vegetable.getAvailable().equalsIgnoreCase("Y")).toArray(Vegetables[]::new);
		}
		return new Vegetables[0];
	}*/
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	ItemRatesRepository itemsRateUpRepository;
	
	public List<Items> findAvailableItems(){
		List<Items> items = new ArrayList<>();
		Iterable<Items> itemsIterable =  itemsRepository.findAvailableItems();
		itemsIterable.forEach(items::add);
		logger.debug("Available Items "+items);
		return items;
	}
	
	public List<Items> findAllItems(){
		List<Items> items = new ArrayList<>();
		Iterable<Items> itemsIterable =  itemsRepository.findAll();
		Map<String,String> categoryToNameMap = ItemCategories.map();
		itemsIterable.forEach(item ->{
			String itemCategory = item.getItemCategory();
			if(categoryToNameMap.containsKey(itemCategory)) {
				item.setItemCategory(categoryToNameMap.get(itemCategory));
			}else {
				item.setItemCategory("");
			}
			items.add(item);
		});
		return items;
	}
	
	@Transactional
	public int toggleItemStatus(ProductForm productForm, String modifiedBy) {
		List<Product> products = productForm.getProducts();
		List<Long> itemIds = products.stream().filter(p -> p.getItemId() != null).map(p -> Long.valueOf(p.getItemId())).collect(Collectors.toList());
		logger.info("Item Status ="+productForm.getItemStatus()+" Item Ids = "+itemIds);
		int count = itemsRepository.toggleItemStatus(productForm.getItemStatus(), modifiedBy, itemIds);
		return count;
	}
	
	public boolean updateItemRates(ProductForm productForm, String modifiedBy) {
		return itemsRateUpRepository.updateItemRates(productForm,modifiedBy);
	}

}
