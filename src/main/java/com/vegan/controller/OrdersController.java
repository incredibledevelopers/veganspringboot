package com.vegan.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vegan.dataobject.Order;
import com.vegan.dataobject.Response;
import com.vegan.model.ItemOrders;
import com.vegan.service.ItemOrdersService;

@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@Controller
public class OrdersController {
	
	Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	@Autowired
	ItemOrdersService ordersService;
	
	@Value("${order.placed.success.message}")
	private String orderPlacedSuccessMsg;
	
	@Value("${order.placed.failure.message}")
	private String orderPlacedErroMsg;
	
	@Value("${orders.delivered.success.message}")
	private String orderDeliveredUpdSuccessMsg;
	
	@Value("${orders.delivered.error.message}")
	private String orderDeliveredUpdErrorMsg;
	
	@Value("${orders.cancelled.success.message}")
	private String orderCancelledUpdSuccessMsg;
	
	@Value("${orders.cancelled.error.message}")
	private String orderCancelledUpdErrorMsg;
	
	@ResponseBody
	@PostMapping(value="/api/placeOrder", headers="Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> placeOrder(@RequestBody Order order) {
		System.out.println("Order = "+order);
		boolean result = ordersService.placeOrder(order);
		if(result) {
			Response status = new Response(result,orderPlacedSuccessMsg);
			logger.info("Order placed successfully by "+order.getMobileNumber());
			return new ResponseEntity<Response>(status,HttpStatus.OK);
		}
		return getErrorResponse();
	}
	
	@GetMapping("/orders")
	public String getPendingOrders(Model model){
		Order order = new Order();
		order.setDeliveryCharges(0);
		model.addAttribute("order", order);
		setPendingOrdersDetails(model);
		return "orders";
	}
	
	@GetMapping("/get-search-orders")
	public String getSearchOrdersPage(Model model){
		model.addAttribute("searchOrder", new Order());
		return "search-orders";
	}
	
	@GetMapping("/showOrder")
	public String getOrderDetails(@RequestParam("d") String orderId, Model model){
		System.out.println(" Order ID = "+orderId);
		List<ItemOrders> itemOrders = ordersService.getOrderDetails(orderId);
		System.out.println(" Item Orders = "+itemOrders);
		model.addAttribute("orderDetails", itemOrders);
		return "order-details";
	}
	
	@PostMapping("/search-orders")
	public String searchOrders(@ModelAttribute Order order,Model model) {
		System.out.println("Order ="+order);
		System.out.println("Order ="+order.getFromDate()+" "+order.getToDate());
		model.addAttribute("searchOrder", order);
		List<ItemOrders> searchedOrders = ordersService.searchOrders(order);
		model.addAttribute("searchOrders", searchedOrders);
		return "search-orders";
	}
	
	@PostMapping("/markDelivered")
	public String markOrdersAsDelivered(@ModelAttribute Order order,Model model,Principal principal) {
		logger.info(" Order = "+order.getOrderIds());
		int count = ordersService.markOrdersDelivered(order.getOrderIds(),principal.getName());
		logger.info("Mark Order As Delivered Count = "+count);
		if(count > 0) {
			model.addAttribute("order", new Order(true, orderDeliveredUpdSuccessMsg));
		}else {
			model.addAttribute("order", new Order(false, orderDeliveredUpdErrorMsg));
		}
		
		setPendingOrdersDetails(model);
		return "orders";
	}
	
	@PostMapping("/markDeliveredWithCharges")
	public String markOrdersAsDelWithCharges(@ModelAttribute Order order,Model model,Principal principal) {
		logger.info(" Order = "+order.getOrderIds());
		System.out.println("Delivery Charges = "+order.getDeliveryCharges());
		int count = ordersService.markOrdersAsDelWithCharges(order.getOrderIds(), 
				principal.getName(),order.getDeliveryCharges());
		logger.info("Mark Order As Delivered With Charges Count = "+count);
		if(count > 0) {
			model.addAttribute("order", new Order(true, orderDeliveredUpdSuccessMsg));
		}else {
			model.addAttribute("order", new Order(false, orderDeliveredUpdErrorMsg));
		}
		
		setPendingOrdersDetails(model);
		return "orders";
	}
	
	@PostMapping("/markCancelled")
	public String markCancelled(@ModelAttribute Order order,Model model,Principal principal) {
		logger.info(" Order = "+order.getOrderIds());
		System.out.println("Cancellation Reason = "+order.getCancellationReason());
		int count = ordersService.markCancelled(order.getOrderIds(),order.getCancellationReason(),principal.getName());
		logger.info("Mark Order As Cancelled Count = "+count);
		if(count > 0) {
			model.addAttribute("order", new Order(true, orderCancelledUpdSuccessMsg));
		}else {
			model.addAttribute("order", new Order(false, orderCancelledUpdErrorMsg));
		}
		
		setPendingOrdersDetails(model);
		return "orders";
	}
	
	private void setPendingOrdersDetails(Model model) {
		List<ItemOrders> pendingOrders = ordersService.getPendingOrders();
		model.addAttribute("pendingOrders", pendingOrders);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> orderPlacementException(Exception e){
		System.out.println("In EXception Handler.....");
		logger.error("An error occurred while placing the order.",e);
		return getErrorResponse();
	}
	
	private ResponseEntity<Response> getErrorResponse(){
		Response status = new Response(false,orderPlacedErroMsg);
		return new ResponseEntity<Response>(status,HttpStatus.OK);
	}
}
