package com.rgtopsoy.salestaxes.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rgtopsoy.salestaxes.entity.Basket;
import com.rgtopsoy.salestaxes.entity.Item;
import com.rgtopsoy.salestaxes.entity.Receipt;
import com.rgtopsoy.salestaxes.logger.InjectLogger;
import com.rgtopsoy.salestaxes.util.TaxCalculator;

/**
 * Register for billing
 * @author R. Gursoy Topsoy
 */
public class Register {
	
	@InjectLogger
	private Logger logger;
	@Autowired
	private TaxCalculator taxCalculator;
	
	/**
	 * Prepares a receipt for a shopping basket
	 * @param basket
	 * @return receipt
	 */
	public Receipt bill(Basket basket) {
		BigDecimal tax;
		BigDecimal totalTax = BigDecimal.ZERO;
		Receipt receipt = new Receipt();
		for (Item item : basket.getItems()) {
			tax = taxCalculator.calculateTax(item);
			totalTax = totalTax.add(tax);
			receipt.addItem(item, item.getPrice().add(tax));
		}
		receipt.setTotalTax(totalTax);
		return receipt;
	}
	
	public void start() {
		logger.info("Register ON");
	}
	
	public void shutdown() {
		logger.info("Register OFF");
	}

}
