package com.vegan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vegan.model.ItemOrders;

public interface ItemOrdersRepository extends CrudRepository<ItemOrders, Long>,JpaSpecificationExecutor<ItemOrders> {

	@Query(value="SELECT ORDER_ID FROM ITEM_ORDERS ORDER BY ID DESC LIMIT 1", nativeQuery = true)
	String getLastOrderId();
	
	List<ItemOrders> findByOrderId(String orderId);
	
	List<ItemOrders> findAllByOrderStatusOrderByOrderTimeDesc(String status);
	
	@Modifying
	@Query("update item_orders io set io.orderStatus= ?1, io.modifiedBy = ?2 where io.orderId IN (?3)")
	int markOrdersAsDelivered(String orderStatus, String modifiedBy, List<String> orderIds);
	
	@Modifying
	@Query("update item_orders io set io.orderTotal = io.orderTotal + ?1,  io.deliveryCharges= ?1, io.modifiedBy = ?2 "
			+ "where io.mobileNumber is not null and io.orderId IN (?3)")
	int markOrdersAsDelWithCharges(double deliveryCharges,String modifiedBy,List<String> orderIds);
	
	@Modifying
	@Query("update item_orders io set io.orderStatus= ?1 , io.cancellationReason=?2 , io.modifiedBy = ?3 where io.orderId IN (?4)")
	int markCancelled(String orderStatus, String cancellationReason,String modifiedBy,List<String> orderIds);
}
