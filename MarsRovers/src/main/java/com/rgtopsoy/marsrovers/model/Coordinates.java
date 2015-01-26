package com.rgtopsoy.marsrovers.model;

/**
 * Shows position 
 * @author R. Gursoy Topsoy
 */
public class Coordinates {
	
	private int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Add the values to the current position
	 * @param x axis
	 * @param y axis
	 */
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
