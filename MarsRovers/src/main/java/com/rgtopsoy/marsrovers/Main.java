package com.rgtopsoy.marsrovers;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.rgtopsoy.marsrovers.controller.Deployer;
import com.rgtopsoy.marsrovers.util.InputLoader;

/**
 * @author R. Gursoy Topsoy
 */
public class Main {
	
	private static ApplicationContext context;

	public static void main(String[] args) {	
		context = new GenericXmlApplicationContext("META-INF/beans.xml");
		InputLoader loader = (InputLoader) context.getBean("loader");
		ArrayList<String> instructions = loader.loadInput();
		Deployer deployer = (Deployer) context.getBean("deployer");
		deployer.run(instructions);
		((ConfigurableApplicationContext) context).registerShutdownHook();
	}

}
