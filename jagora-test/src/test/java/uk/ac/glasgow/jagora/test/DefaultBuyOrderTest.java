package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.*;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubTickEvent;
import uk.ac.glasgow.jagora.test.stub.StubTrade;
import uk.ac.glasgow.jagora.test.stub.StubTrader;


public class DefaultBuyOrderTest {
	BuyOrder thisBuyOrder;
	BuyOrder testAgainstBuyOrder;
	Trader trader;
	
	TickEvent<Trade> tickEvent;
	private Stock stock = lemons;
	private Trade goodTrade;
	private Trade badTrade;
	private Integer quantity = 10;
	private Double thisPrice = 5.0;
	
	@Before
	public void setUp() {
		trader = new StubTrader();
		thisBuyOrder = new LimitBuyOrder(trader, stock, quantity, thisPrice);
	}
	
	@After 
	public void tearDown() {
		thisBuyOrder = null;
		testAgainstBuyOrder = null;
		trader = null;
		goodTrade = null;
		badTrade = null;
		tickEvent = null;
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
	
	@Test
	public void testGetTrader() {
		assertTrue("Trader is not the same as the one passed in the constructor.", thisBuyOrder.getTrader() == trader);	
	}
	
	@Test
	public void testGetStock() {
	assertTrue("Stock is not the same as the one passed in the constructor.", thisBuyOrder.getStock() == lemons);
	}
	
	@Test
	public void testRemainingQuantity() {
		assertTrue("Remaining quantity is incorrect", thisBuyOrder.getRemainingQuantity() == quantity);
	}
	
	@Test
	public void testPrice() {
		assertTrue("Stock price is incorrect", thisBuyOrder.getPrice() == thisPrice);
	}
	
	@Test
	public void testSatisfyTrade() throws Exception {
		Double oldCash = trader.getCash();
		Integer oldLemons = trader.getInventoryHolding(lemons);
		
		goodTrade = new StubTrade(3, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.satisfyTrade(tickEvent);
		
		assertTrue("Trader's cash was not reduced", trader.getCash() == oldCash - tickEvent.getEvent().getPrice());
		assertTrue("Trader's items of this stock type were not increased.", 
				oldLemons == trader.getInventoryHolding(lemons) + tickEvent.getEvent().getQuantity());
	}
	
	@Test(expected=TradeException.class)
	public void testBadSatisfyTradeQuantityNegative() throws Exception {
		badTrade = new StubTrade(-1, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.satisfyTrade(tickEvent);
	}
	
	@Test(expected=TradeException.class)
	public void testBadSatisfyTradeQuantityZero() throws Exception {
		badTrade = new StubTrade(0, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.satisfyTrade(tickEvent);
	}
	
	@Test(expected=TradeException.class)
	public void testBadSatisfyTradeCost() throws Exception{
		badTrade = new StubTrade(1, -5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.satisfyTrade(tickEvent);
	}
	
	@Test
	public void testRollBackTrade() throws Exception {
		Double newCash = trader.getCash();
		Integer newLemons = trader.getInventoryHolding(lemons);
		
		goodTrade = new StubTrade(3, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.rollBackTrade(tickEvent);
		
		assertTrue("Trader's cash was not properly restored", newCash + tickEvent.getEvent().getPrice() == trader.getCash());
		assertTrue("Trader's newly acquired stock was not removed from inventory",
				newLemons - tickEvent.getEvent().getPrice() == trader.getInventoryHolding(lemons));
	}
	
	@Test(expected=TradeException.class)
	public void testBadRollbackTradeQuantityNegative() throws Exception {
		badTrade = new StubTrade(-1, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.rollBackTrade(tickEvent);
	}
	
	@Test(expected=TradeException.class)
	public void testBadRollbackTradeQuantityZero() throws Exception {
		badTrade = new StubTrade(0, 5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.rollBackTrade(tickEvent);
	}
	
	@Test(expected=TradeException.class)
	public void testBadRollbackTradeCostNegative() throws Exception{
		badTrade = new StubTrade(1, -5.0, lemons);
		tickEvent = new StubTickEvent<Trade>(goodTrade, 0L);
		thisBuyOrder.rollBackTrade(tickEvent);
	}
}
