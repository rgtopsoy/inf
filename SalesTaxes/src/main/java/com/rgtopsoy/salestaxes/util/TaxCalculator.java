package com.rgtopsoy.salestaxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

import com.rgtopsoy.salestaxes.entity.Item;
import com.rgtopsoy.salestaxes.entity.NormalItem;
import com.rgtopsoy.salestaxes.logger.InjectLogger;

public class TaxCalculator {
	
	@InjectLogger
	private Logger logger;
	
	private final BigDecimal taxRate = BigDecimal.valueOf(0.1);
	private final BigDecimal importDutyRate = BigDecimal.valueOf(0.05);
	
	/**
	 * Calculates total tax of the item
	 * @param item to be calculated
	 * @return total tax
	 */
	public BigDecimal calculateTax(Item item) {
		logger.info("Calculating tax for the item...");
		if (item instanceof NormalItem) {
			BigDecimal tax = calculateBasicSalesTax(item).add(calculateImportDuty(item));
			logger.info("Total tax: " + tax);
			return tax;
		} else {
			BigDecimal tax = calculateImportDuty(item);
			logger.info("Total tax: " + tax);
			return tax;
		}
	}
	
	/**
	 * Calculates the basic sales tax of the item
	 * @param item to be calculated
	 * @return basic sales tax
	 */
	private BigDecimal calculateBasicSalesTax(Item item) {
		logger.info("Price  : " + item.getPrice());
		BigDecimal tax = taxRate.multiply(item.getPrice());
		logger.info("Tax    : " + tax);
		tax = round(tax);
		logger.info("Rounded: " + tax);
		return tax;
	}
	
	/**
	 * Calculates the import duty of the item
	 * @param item to be calculated
	 * @return import duty
	 */
	private BigDecimal calculateImportDuty(Item item) {
		if (item.isImported()) {
			logger.info("Price      : " + item.getPrice());
			BigDecimal tax = importDutyRate.multiply(item.getPrice());
			logger.info("Import Duty: " + tax);
			tax = round(tax);
			logger.info("Rounded    : " + tax);
			return tax;
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * Rounds the number up to the nearest 0.05
	 * @param number to round
	 * @return rounded number
	 */
	private BigDecimal round(BigDecimal number) {
	    number = number.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP).setScale(2);
	    number = number.divide(BigDecimal.valueOf(20), RoundingMode.UP);
	    return number;
	}

}
