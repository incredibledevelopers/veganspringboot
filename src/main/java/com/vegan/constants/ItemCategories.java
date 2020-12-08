package com.vegan.constants;

import java.util.HashMap;
import java.util.Map;

public enum ItemCategories {

	RV("Regular"),
	LV("Leafy"),
	CV("Cut"),
	EX("Exotic"),
	FR("Fruits"),
	DL("Dal");
	
	private String itemCategory;
	
	ItemCategories(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public String toString() {
		return this.itemCategory;
	}
	
	public static Map<String, String> map(){
		Map<String, String> map = new HashMap<>();
		for(ItemCategories itemCategory : ItemCategories.values()) {
			map.put(itemCategory.name(), itemCategory.toString());
		}
		return map;
	}
}
