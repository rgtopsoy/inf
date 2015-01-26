package com.rgtopsoy.marsrovers.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rgtopsoy.marsrovers.logger.InjectLogger;

/**
 * Represents a mars rover which is deployed on Mars by Deployer
 * @author R. Gursoy Topsoy
 */
public class Rover {
	
	@InjectLogger
	private Logger logger;
	@Autowired
	private Plateau plateau;
	private Direction heading;
	private Coordinates position;
	private String instructions;
	ArrayList<Character> photos = new ArrayList<>();
	
	/**
	 * Makes the rover explore the plateau, abiding by the instructions
	 */
	public String explore() {
		for (char instruction : instructions.toCharArray()) {
			switch (instruction) {
				case 'M': move(); break;
				case 'L': left(); break;
				case 'R': right(); break;
				default : logger.warn("Invalid instruction. The rover's position is remained.");
			}
		}
		logger.info("Mission accomplished. Position: " + position + " " + heading);
		report();
		return this.toString();
	}
	
	/**
	 * Makes the rover move one grid forward
	 */
	private void move() {
		position.add(heading.getStep() / 10, heading.getStep() % 10);
		takePhoto();
		logger.info("    Moved forward: " + position + " " + heading);
	}
	
	/**
	 * Makes the rover turn left maintaining its current position
	 */
	private void left() {
		heading = heading.left();
		logger.info("    Turned left  : " + position + " " + heading);
	}
	
	/**
	 * Makes the rover turn right maintaining its current position
	 */
	private void right() {
		heading = heading.right();
		logger.info("    Turned right : " + position + " " + heading);
	}
	
	private void takePhoto() {
		photos.add(plateau.getPhoto(position));
	}
	
	/**
	 * Reports the exploration.
	 * Prints the taken photos of Mars' surface
	 * and examine them if there is water on Mars
	 */
	private void report() {
		StringBuilder builder = new StringBuilder();
		for (char photo : photos) {
			builder.append(photo).append(" ");
		}
		String report = builder.toString();
		logger.info("Taken photos: " + report);
		if (report.contains("W A T E R")) {
			logger.info("*** WE FOUND WATER ON MARS! ***");
		}
	}
	
	/**
	 * Loads required data for the rover to explore
	 * @param position of rover
	 * @param heading of rover
	 * @param instructions tell the rover how to explore
	 */
	public void loadData(Coordinates position, Direction heading, String instructions) {
		this.position = position;
		this.heading = heading;
		this.instructions = instructions;
		logger.info("The rover is deployed on Mars. Position: " + position + " " + heading);
	}
	
	@Override
	public String toString() {
		return new StringBuilder(position.toString())
		.append(" ").append(heading).toString();
	}
	
	public Coordinates getPosition() {
		return position;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}
	
	public Direction getHeading() {
		return heading;
	}

	public void setHeading(Direction heading) {
		this.heading = heading;
	}

	public String getInstructions() {
		return instructions;
	}
	
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
