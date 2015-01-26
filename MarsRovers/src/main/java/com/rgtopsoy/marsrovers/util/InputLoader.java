package com.rgtopsoy.marsrovers.util;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import com.rgtopsoy.marsrovers.logger.InjectLogger;

/**
 * Helper class to read input from file
 * @author R. Gursoy Topsoy
 */
public class InputLoader {

	@InjectLogger
	private Logger logger;
	private Resource inputFile;

	/**
	 * Reads input from file
	 * @return input lines as an ArrayList
	 */
	public ArrayList<String> loadInput() {
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.getInputStream()));) {
			String line = null;
			while (true) {
				line = reader.readLine();
				if (line == null) {
					break;
				}
				list.add(line);
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return list;
	}
	
	public void setInputFile(Resource inputFile) {
		this.inputFile = inputFile;
	}
	
}
