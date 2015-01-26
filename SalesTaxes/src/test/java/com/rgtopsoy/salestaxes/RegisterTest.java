package com.rgtopsoy.salestaxes;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.salestaxes.controller.Register;
import com.rgtopsoy.salestaxes.entity.Basket;
import com.rgtopsoy.salestaxes.entity.Receipt;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class RegisterTest {
	
	@Autowired
	private Register register;
	@Autowired
	private Basket basket;

	@Test
	public void shouldBill() {
		Receipt receipt = register.bill(basket);
		assertNotNull(receipt);
	}

}
