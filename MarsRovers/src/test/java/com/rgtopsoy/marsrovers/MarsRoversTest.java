package com.rgtopsoy.marsrovers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.marsrovers.controller.Deployer;
import com.rgtopsoy.marsrovers.model.Plateau;
import com.rgtopsoy.marsrovers.util.InputLoader;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class MarsRoversTest {

	@Autowired
	private Deployer deployer;
	@Autowired
	private InputLoader loader;
	@Autowired
	Plateau plateau;
	private static String output;
	
	@BeforeClass
	public static void setup() {
		output = "1 3 N\n5 1 E";
	}
	
	@Test
	public void testRun() {
		ArrayList<String> instructions = loader.loadInput();
		String aftermath = deployer.run(instructions);
		assertEquals(6, plateau.getGrids().length);
		assertEquals(6, plateau.getGrids()[0].length);
		assertEquals(output, aftermath);
	}

}
