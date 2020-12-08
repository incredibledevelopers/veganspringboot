package com.vegan.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vegan.dataobject.Order;
import com.vegan.model.ItemOrders;

public class ItemOrdersSpecification implements Specification<ItemOrders> {

	private static final long serialVersionUID = 1L;
	
	private Order order = null;
	
	public ItemOrdersSpecification(Order order) {
		this.order = order;
	}
	
	/*@Override
	public Predicate toPredicate(Root<ItemOrders> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		if(criteriaDO.getOperation().equals(">")) {
			return builder.greaterThanOrEqualTo(root.<String> get(criteriaDO.getKey()), 
					criteriaDO.getValue().toString());
		}else if(criteriaDO.getOperation().equals("<")) {
			return builder.lessThanOrEqualTo(root.<String> get(criteriaDO.getKey()), 
					criteriaDO.getValue().toString());
		}else if(criteriaDO.getOperation().equals(":")) {
			    return builder.like(
                  root.<String>get(criteriaDO.getKey()), "%" + criteriaDO.getValue() + "%");
            } else {
                return builder.equal(root.get(criteriaDO.getKey()), criteriaDO.getValue());
            }
		}
		return null;
	}*/
	
	public Predicate toPredicate(Root<ItemOrders> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		if(isNotNullAndBlank(order.getOrderId())) {
			predicates.add(builder.like(root.get("orderId"), "%" + order.getOrderId() + "%"));
		}if(isNotNullAndBlank(order.getMobileNumber())) {
			predicates.add(builder.like(root.get("mobileNumber"), "%" + order.getMobileNumber() + "%"));
		}if(isNotNullAndBlank(order.getAddress())) {
			predicates.add(builder.like(root.get("orderAddress"), "%" + order.getAddress() + "%"));
		}if(isNotNullAndBlank(order.getFromDate())) {
			Date date = getDate(order.getFromDate());
			if(date != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("orderTime"), date));
			}
		}if(isNotNullAndBlank(order.getToDate())) {
			Date date = getDate(order.getToDate());
			if(date != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("orderTime"), date));
			}
		}
		return query.where(builder.and(predicates.toArray(new Predicate[0]))).getRestriction();
	}

	private static boolean isNotNullAndBlank(String string) {
		return string != null && string.trim().length() > 0;
	}
	
	private static Date getDate(String dateStr) {
		Date date = null; 
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
