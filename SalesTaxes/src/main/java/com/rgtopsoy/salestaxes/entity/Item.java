package com.rgtopsoy.salestaxes.entity;

import java.math.BigDecimal;

/**
 * Item superclass represents a product
 * @author R. Gursoy Topsoy
 */
public abstract class Item {
	
	private String name;
	private BigDecimal price;
	private boolean imported;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder((imported) ? "imported " : "");
		return builder.append(name).toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

}
