package com.vegan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vegan.model.Items;

public interface ItemsRepository extends CrudRepository<Items, Long> {

	@Query("select items from items_master items where item_availability='Y' order by id")
	List<Items> findAvailableItems();
	
	@Query("select items from items_master items")
	List<Items> findAll();
	
	@Modifying
	@Query("update items_master it set it.itemAvailability= ?1, it.modifiedBy = ?2 where it.id IN (?3)")
	int toggleItemStatus(String status,String modifiedBy, List<Long> ids);
}
