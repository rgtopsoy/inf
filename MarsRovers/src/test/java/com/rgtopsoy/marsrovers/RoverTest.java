package com.rgtopsoy.marsrovers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.marsrovers.model.Direction;
import com.rgtopsoy.marsrovers.model.Rover;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class RoverTest {
	
	@Autowired
	Rover testRover;
	
	@Test
	public void testExplore() {
		assertEquals(Direction.N, testRover.getHeading());
		assertEquals(1, testRover.getPosition().getX());
		assertEquals(2, testRover.getPosition().getY());
		testRover.explore();
		assertEquals(Direction.N, testRover.getHeading());
		assertEquals(1, testRover.getPosition().getX());
		assertEquals(3, testRover.getPosition().getY());
	}

}
