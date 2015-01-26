package com.rgtopsoy.salestaxes.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.rgtopsoy.salestaxes.logger.InjectLogger;

/**
 * Shopping basket
 * @author R. Gursoy Topsoy
 */
public class Basket {
	
	@InjectLogger
	private Logger logger;
	ArrayList<Item> items = new ArrayList<>();
	
	/**
	 * Adds an item to basket
	 * @param item to buy
	 */
	public void addItem(Item item) {
		items.add(item);
		logger.info("Item added to cart: " + item);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

}
