package com.rgtopsoy.marsrovers.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.rgtopsoy.marsrovers.logger.InjectLogger;
import com.rgtopsoy.marsrovers.util.InputLoader;

/**
 * Exploration area on Mars
 * @author R. Gursoy Topsoy
 */
public class Plateau {
	
	@InjectLogger
	private Logger logger;
	@Autowired
	private InputLoader loader;
	private char[][] grids;
	
	/**
	 * Returns the photo of a specified grid 
	 * @param coord
	 * @return photo of the surface
	 */
	public char getPhoto(Coordinates coord) {
		try {
			return grids[coord.getX()][coord.getY()];
		} catch (Exception e) {
			logger.warn("Out of plateau!");
			return '#';
		}
	}
	
	private void extractPlateauFromSurface() {
		loader.setInputFile(new ClassPathResource("META-INF/input/marssurface.txt"));
		ArrayList<String> surface = loader.loadInput();
		char[] visualGrids = surface.get(0).toCharArray();
		for (int i = 0; i < grids.length; i++) {
			for (int j = 0; j < grids[i].length; j++) {
				grids[i][j] = visualGrids[(i * grids.length) + j];
			}
		}
	}
	
	public void setGrids(char[][] grids) {
		this.grids = grids;
		extractPlateauFromSurface();
		logger.info("The plateau to be explored:\n" + toString());
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("    ");
		for (char[] rows : grids) {
			for (char grid : rows) {
				builder.append(grid).append(" ");
			}
			builder.delete(4, builder.length());
		}
		return builder.toString();
	}
	
	public char[][] getGrids() {
		return grids;
	}
	
	public void setLoader(InputLoader loader) {
		this.loader = loader;
	}
	
}
