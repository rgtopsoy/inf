package com.rgtopsoy.marsrovers.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.rgtopsoy.marsrovers.logger.InjectLogger;
import com.rgtopsoy.marsrovers.model.*;
import com.rgtopsoy.marsrovers.util.InputLoader;

/**
 * Determine the plateau to be explore and deploy the Rovers
 * @author R. Gursoy Topsoy
 */
public class Deployer implements ApplicationContextAware {
	
	@InjectLogger
	private Logger logger;
	@Autowired
	private InputLoader loader;
	private ApplicationContext context;
	
	/**
	 * Parses the input which is read from file
	 */
	public String run(ArrayList<String> instructions) {
		instructions = loader.loadInput();
		String size = instructions.get(0);
		settlePlateau(size);
		Iterator<String> iterator = instructions.subList(1, instructions.size()).iterator();
		StringBuilder aftermath = new StringBuilder();
		while (iterator.hasNext()) {
			String roverPosition = iterator.next();
			String roverInstructions = iterator.next();
			String report = deployRover(roverPosition, roverInstructions);
			aftermath.append(report).append("\n");
		}
		logger.info("Last positions of rovers:\n" + aftermath.toString().trim());
		return aftermath.toString().trim();
	}
	
	/**
	 * Determines the plateau to be explored
	 * @param size a String for specifying the size of the plateau
	 */
	private void settlePlateau(String size) {
		String[] limits = size.split(" ");
		char[][] grids = new char[Integer.parseInt(limits[0]) + 1][Integer.parseInt(limits[1]) + 1];
		context.getBean(Plateau.class).setGrids(grids);
	}
	
	/**
	 * Gives rover the required data for exploration
	 * and deployes it on Mars  
	 * @param position of rover on plateau
	 * @param instructions tell the rover how to explore the plateau
	 */
	private String deployRover(String position, String instructions) {
		Rover rover = (Rover) context.getBean("rover");
		logger.info("--------------------------------------------------");
		logger.info("Preparing the rover...");
		String[] posData = position.split(" ");
		Coordinates coords = new Coordinates(Integer.parseInt(posData[0]), Integer.parseInt(posData[1]));
		rover.loadData(coords, Direction.valueOf(posData[2]), instructions);
		return rover.explore();
	}
	
	public void start() {
		logger.info("Deployer ON");
	}

	public void shutdown() {
		logger.info("Deployer OFF");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

}
