package com.rgtopsoy.salestaxes.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Shopping receipt
 * @author R. Gursoy Topsoy
 */
public class Receipt {
	
	private BigDecimal totalTax;
	private ArrayList<Item> items = new ArrayList<>();
	private ArrayList<BigDecimal> prices = new ArrayList<>();;
	
	/**
	 * Enter an item from shopping basket for billing
	 * @param item
	 * @param price
	 */
	public void addItem(Item item, BigDecimal price) {
		items.add(item);
		prices.add(price);
	}
	
	@Override
	public String toString() {
		Item item = null;
		BigDecimal totalPrice = BigDecimal.ZERO;
		StringBuilder receipt = new StringBuilder();
		for (int i = 0; i < items.size(); i++) {
			totalPrice = totalPrice.add(prices.get(i));
			item = items.get(i);
			receipt.append("1 ").append((item.isImported() ? "imported " : ""))
				   .append(items.get(i).getName()).append(": ")
				   .append(prices.get(i)).append("\n");
		}
		receipt.append("Sales Taxes: ").append(totalTax).append("\n")
			   .append("Total: ").append(totalPrice);
		return receipt.toString();
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

}
