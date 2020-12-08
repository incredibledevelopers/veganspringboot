package com.vegan.repository;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.vegan.dataobject.Product;
import com.vegan.dataobject.ProductForm;

@Repository
public class ItemRatesRepository {
	
	Logger logger = LoggerFactory.getLogger(ItemRatesRepository.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate template;
	
	public boolean updateItemRates(ProductForm productForm,String modifiedBy) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		return template.execute( new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					int rows = updateItemRates(productForm.getProducts(),modifiedBy);
					if (rows > 0)
						return true;
	
				} catch (Exception exception) {
					logger.error("Error while updating Item Rates", exception);
					status.setRollbackOnly();
				}
				return false;
			}
		});
	}
	
	private int updateItemRates(List<Product> products,String modifiedBy) {
		String sql = "update items_master set item_price=?, modified_by=? where item_id=?";
		int count = 0;
		for(Product product : products) {
			logger.debug(" Updating Rates for :"+product);
			Long itemId = product.getItemId();
			if(itemId != null) {
				//count = template.update(sql, new Object[] {product.getPrice(), modifiedBy, itemId});
				count = template.update(sql, new Object[] {Double.valueOf(product.getPrice()), modifiedBy, itemId});
			}
		}
		return count;
	}

}
