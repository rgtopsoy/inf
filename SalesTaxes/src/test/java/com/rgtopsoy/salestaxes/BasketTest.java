package com.rgtopsoy.salestaxes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.salestaxes.entity.Basket;
import com.rgtopsoy.salestaxes.entity.NormalItem;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class BasketTest {
	
	@Autowired
	private Basket basket;

	@Test
	public void shouldAddItemToBasket() {
		assertNotNull(basket);
		assertNotNull(basket.getItems());
		basket.addItem(new NormalItem());
		assertEquals(1, basket.getItems().size());
	}

}
