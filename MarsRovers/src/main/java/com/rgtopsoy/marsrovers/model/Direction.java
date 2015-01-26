package com.rgtopsoy.marsrovers.model;

/**
 * Cardinal compass points
 * @author RGT
 */
public enum Direction {
	
	W(-10), N(01), E(10), S(-01);

	private static Direction[] values = values();
	private int step;
	
	private Direction(int step) {
		this.step = step;
	}
	
	/**
	 * @return next counterclockwise direction
	 */
	public Direction left() {
		return values[ordinal() == 0 ? 3 : (ordinal() - 1)];
	}
	
	/**
	 * @return next clockwise direction
	 */
	public Direction right() {
		return values[(ordinal() + 1) % 4]; 
	}
	
	/**
	 * @return a special number for calculating the next grid 
	 * to move for the {@link Rover}
	 */
	public int getStep() {
		return step;
	}

}
