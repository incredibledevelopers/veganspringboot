package com.vegan.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vegan.constants.OrderStatus;
import com.vegan.constants.RateUnits;
import com.vegan.dataobject.Order;
import com.vegan.dataobject.ProductOrders;
import com.vegan.model.ItemOrders;
import com.vegan.repository.ItemOrdersRepository;
import com.vegan.repository.ItemOrdersSpecification;

@Service
public class ItemOrdersService {
	
	//Logger analytis = LoggerFactory.getLogger("analytics");
	Logger logger = LoggerFactory.getLogger(ItemOrdersService.class);
	
	@Value("${orders.prefix}")
	String ordersPrefix;
	
	
	@Autowired
	ItemOrdersRepository ordersRepository;
	
	public List<ItemOrders> getPendingOrders(){
		return ordersRepository.findAllByOrderStatusOrderByOrderTimeDesc(OrderStatus.P.toString());
	}
	
	public List<ItemOrders> getOrderDetails(String orderId) {
		System.out.println(" Order Id ==> "+orderId);
		return ordersRepository.findByOrderId(orderId);
	}
	
	public List<ItemOrders> searchOrders(Order order){
		ItemOrdersSpecification itemOrdersSpecification = new ItemOrdersSpecification(order);
		return ordersRepository.findAll(itemOrdersSpecification);
	}
	
	@Transactional
	public int markOrdersDelivered(List<String> orderIds,String modifiedBy){
		return ordersRepository.markOrdersAsDelivered(OrderStatus.D.toString(),modifiedBy, orderIds);
	}
	
	@Transactional
	public int markOrdersAsDelWithCharges(List<String> orderIds,String modifiedBy,double deliveryCharges){
		int total = 0;
		total += ordersRepository.markOrdersAsDelivered(OrderStatus.D.toString(),modifiedBy,orderIds);
		total += ordersRepository.markOrdersAsDelWithCharges(deliveryCharges,modifiedBy,orderIds);
		return total;
	}
	
	@Transactional
	public int markCancelled(List<String> orderIds,String cancellationReason,String modifiedBy){
		return ordersRepository.markCancelled(OrderStatus.C.toString(),cancellationReason,modifiedBy,orderIds);
	}
	
	public boolean placeOrder(Order order) {
		boolean status = false;
		System.out.println(" Order ="+order);
		double orderTotal = 0;
		String lastOrderId = ordersRepository.getLastOrderId();
		long orderId = Long.parseLong(lastOrderId.substring(lastOrderId.indexOf(ordersPrefix)+ordersPrefix.length(), lastOrderId.length()));
		System.out.println(" Last Order Id ="+lastOrderId+" Int = "+orderId);
		List<ItemOrders> itemOrders = new ArrayList<>();
		ProductOrders[] productOrders = order.getProductOrders();
		for(int i = 0; i < productOrders.length; i++) {
			ProductOrders orders = productOrders[i];
			ItemOrders itemOrder = new ItemOrders();
			long nextOrderId = orderId + 1;
			itemOrder.setOrderId(ordersPrefix+nextOrderId);
			if(i == 0) {
				itemOrder.setMobileNumber(order.getMobileNumber());
				itemOrder.setOrderAddress(order.getAddress());
				itemOrder.setCustomerName(order.getCustomerName());
			}
			itemOrder.setItemName(orders.getProduct().getName());
			itemOrder.setOrderStatus(OrderStatus.P.toString());
			int itemQuantity = Integer.parseInt(orders.getQuantity());
			if(RateUnits.gms.toString().equals(orders.getProduct().getItemRate())){
				itemOrder.setItemQuantity(itemQuantity* 250);
			}else {
				itemOrder.setItemQuantity(itemQuantity);
			}
			double itemTotal = itemQuantity*Double.valueOf(orders.getProduct().getPrice());
			itemOrder.setItemTotalPrice(itemTotal);
			orderTotal += itemTotal;
			itemOrders.add(itemOrder);
		}
		ItemOrders itemOrder = itemOrders.get(0);
		itemOrder.setOrderTotal(orderTotal);
		System.out.println(itemOrders);
		ordersRepository.saveAll(itemOrders);
		status = true;
		return status;
	}
	
	/*
	
	private static final String SEPARATOR = " ";
	 @Value("${orders.files.location}")
	String ordersFileLocation;
	
	@Value("${support.contact.numbers}")
	String contactNumber;
	 
	 public boolean placeOrder(Order order) {
		boolean written = false;
		Path dir = Paths.get(ordersFileLocation);
		String location = dir.toAbsolutePath()+File.separator+order.getMobileNumber()+"_"+Instant.now().toEpochMilli();
		System.out.println("location = "+location);
		Path path = Paths.get(location);
		try (BufferedWriter writer = Files.newBufferedWriter(path))
		{
		    writer.write("*********************************\n");
		    writer.write("   "+order.getMobileNumber()+"   \n");
		    writer.write("                                 \n");
		    writer.write("   "+order.getAddress()+"   \n");
		    writer.write("                                 \n");
		    writer.write("   "+LocalDateTime.now()+"   \n");
		    writer.write("*********************************\n");
		    ProductOrders[] productOrders = order.getProductOrders();
		    int index = 1;
		    for(ProductOrders orders : productOrders) {
		    	String details = String.join(SEPARATOR,orders.getProduct().getName(),
		    			orders.getQuantity(),orders.getProduct().getPrice(),
		    			(Double.valueOf(orders.getQuantity())*Double.valueOf(orders.getProduct().getPrice()))+"");
		    	String item = index + " "+details;
		    	writer.write(item+"\n");
		    	index++;
		    	analytis.debug(order.getMobileNumber()+" "+details);
		    }
		    writer.write("*********************************");
		    logger.info("File Written for the order by "+order.getMobileNumber());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("An error occurred while writing the order file",e);
			throw new OrderException("An error occurred while placing the order. Please contact support at :"+contactNumber);
		}
		written = true;
		return written;
	}*/
}
