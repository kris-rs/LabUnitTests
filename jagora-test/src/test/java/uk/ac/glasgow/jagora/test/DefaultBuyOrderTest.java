package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.*;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;

public class DefaultBuyOrderTest {
	
	
	BuyOrder thisBuyOrder;
	BuyOrder testAgainstBuyOrder;
	
	private Stock stock = lemons;
	private Integer quantity = 1;
	private Double thisPrice = 5.0;
	
	@Before
	public void setUp() {
		thisBuyOrder = new LimitBuyOrder(null, stock, quantity, thisPrice);
		
	}
	
	@After 
	public void tearDown() {
		thisBuyOrder = null;
		testAgainstBuyOrder = null;
	}
	
	@Test
	public void testEquals() {
		testAgainstBuyOrder = new LimitBuyOrder(null, stock, quantity, 5.0);
		assertTrue("BuyOrder prices should return 0.", thisBuyOrder.compareTo(testAgainstBuyOrder) == 0);
	}
	
	@Test
	public void testBuyOderPriceMoreThanArgument() {
		testAgainstBuyOrder = new LimitBuyOrder(null, stock, quantity, 4.0);
		assertTrue("BuyOrder prices should return a negative number.", thisBuyOrder.compareTo(testAgainstBuyOrder) < 0);
	}
	
	@Test
	public void testBuyOrderPriceLessThanArgument() {
		testAgainstBuyOrder = new LimitBuyOrder(null, stock, quantity, 6.0);
		assertTrue("BuyOrder prices should return a negative number.", thisBuyOrder.compareTo(testAgainstBuyOrder) > 0);
	}
	
	
	
}
