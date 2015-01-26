package com.rgtopsoy.salestaxes;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.salestaxes.controller.Register;
import com.rgtopsoy.salestaxes.entity.Basket;
import com.rgtopsoy.salestaxes.entity.NormalItem;
import com.rgtopsoy.salestaxes.entity.Receipt;
import com.rgtopsoy.salestaxes.entity.TaxfreeItem;
import com.rgtopsoy.salestaxes.logger.InjectLogger;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class SalesTaxesTest {

	@InjectLogger private Logger logger;
	
	@Autowired private Register register;
	
	// Input 1
	@Autowired private Basket basket1;
	@Autowired private TaxfreeItem item11;
	@Autowired private NormalItem item12;
	@Autowired private TaxfreeItem item13;
	
	// Input 2
	@Autowired private Basket basket2;
	@Autowired private TaxfreeItem item21;
	@Autowired private NormalItem item22;
	
	// Input 3
	@Autowired private Basket basket3;
	@Autowired private NormalItem item31;
	@Autowired private NormalItem item32;
	@Autowired private TaxfreeItem item33;
	@Autowired private TaxfreeItem item34;
	
	private static String output1;
	private static String output2;
	private static String output3;
	
	@BeforeClass
	public static void setup() {
		output1 = new StringBuilder()
				.append("1 book: 12.49\n")
				.append("1 music CD: 16.49\n")
				.append("1 chocolate bar: 0.85\n")
				.append("Sales Taxes: 1.50\n")
				.append("Total: 29.83").toString();
		output2 = new StringBuilder()
				.append("1 imported box of chocolates: 10.50\n")
				.append("1 imported bottle of perfume: 54.65\n")
				.append("Sales Taxes: 7.65\n")
				.append("Total: 65.15").toString();
		output3 = new StringBuilder()
				.append("1 imported bottle of perfume: 32.19\n")
				.append("1 bottle of perfume: 20.89\n")
				.append("1 packet of headache pills: 9.75\n")
				.append("1 imported box of chocolates: 11.85\n")
				.append("Sales Taxes: 6.70\n")
				.append("Total: 74.68").toString();
	}
	
	@Test
	public void testInput1() {
		logger.info("##### Processing Input 1 ...");
		basket1.addItem(item11);
		basket1.addItem(item12);
		basket1.addItem(item13);
		Receipt receipt = register.bill(basket1);
		logger.info("##### Output 1:\n" + receipt);
		assertEquals(output1, receipt.toString());
	}
	
	@Test
	public void testInput2() {
		logger.info("##### Processing Input 2 ...");
		basket2.addItem(item21);
		basket2.addItem(item22);
		Receipt receipt = register.bill(basket2);
		logger.info("##### Output 2:\n" + receipt);
		assertEquals(output2, receipt.toString());
	}
	
	@Test
	public void testInput3() {
		logger.info("##### Processing Input 3 ...");
		basket3.addItem(item31);
		basket3.addItem(item32);
		basket3.addItem(item33);
		basket3.addItem(item34);
		Receipt receipt = register.bill(basket3);
		logger.info("##### Output 3:\n" + receipt);
		assertEquals(output3, receipt.toString());
	}

}
