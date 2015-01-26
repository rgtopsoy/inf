package com.rgtopsoy.salestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgtopsoy.salestaxes.entity.NormalItem;
import com.rgtopsoy.salestaxes.entity.TaxfreeItem;
import com.rgtopsoy.salestaxes.util.TaxCalculator;

/**
 * @author R. Gursoy Topsoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/test-beans.xml")
public class TaxCalculatorTest {

	@Autowired
	private TaxCalculator taxCalculator;
	@Autowired
	private NormalItem localNormalItem;
	@Autowired
	private NormalItem importedNormalItem;
	@Autowired
	private TaxfreeItem localTaxfreeItem;
	@Autowired
	private TaxfreeItem importedTaxfreeItem;
	
	private BigDecimal tax;

	@Test
	public void shouldCalculateTaxForItem() {
		tax = taxCalculator.calculateTax(localNormalItem);
		assertEquals(1.5f, tax.floatValue(), 0);
		tax = taxCalculator.calculateTax(importedNormalItem);
		assertEquals(7.15f, tax.floatValue(), 0);
		tax = taxCalculator.calculateTax(localTaxfreeItem);
		assertEquals(0.0f, tax.floatValue(), 0);
		tax = taxCalculator.calculateTax(importedTaxfreeItem);
		assertEquals(0.6f, tax.floatValue(), 0);
	}

}
