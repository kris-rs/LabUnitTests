package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.World;
import uk.ac.glasgow.jagora.impl.DefaultTickEvent;
import uk.ac.glasgow.jagora.impl.DefaultTrade;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubWorld;

public class DefaultTradeTest {
	
	protected Trade trade;
	protected World world = StubWorld.ttTradeWorld;
	protected BuyOrder bOrder = new StubBuyOrder(null, StubStock.lemons, 10, 9.99);
	protected SellOrder sOrder = new StubSellOrder(null, StubStock.lemons, 12, 8.23);
	protected Stock stock = StubStock.lemons;
	
	@Before
	public void setUp(){
		trade = new DefaultTrade(world, bOrder, sOrder, stock, 10, 19.90);
	}
	
	@After
	public void tearDown(){
		trade = null;
		world = null;
		bOrder = null;
		sOrder = null;
		stock = null;
	}
	
	@Test
	public void testGetStock(){
		assertTrue("Trade stock getter", trade.getStock().getName().equals("lemons"));
	}
	
	@Test
	public void testGetQuantity(){
		assertTrue("Trade quantity getter", trade.getQuantity() == 10);
	}
	
	@Test
	public void testGetPrice(){
		assertTrue("Trade price getter", trade.getPrice() == 19.90);
	}
	
	@Test
	public void testExecute() throws Exception{
		assertTrue("Trade execution", trade.execute().equals(world.createTickEvent(trade)));
		assertFalse("Execute Tick event not unique", trade.execute().equals(new DefaultTickEvent<Trade>(trade, 44l)));
	}

	@Test(expected = TradeException.class)
    public void testNotMatcingPricesTrade() throws Exception{
		sOrder = new StubSellOrder(null, stock, 10, 9.99);
		bOrder = new StubBuyOrder(null, stock, 10, 8.00);
		trade = new DefaultTrade(world, bOrder, sOrder, stock, 10, 8.00);
		trade.execute();
	}
	
	@Test(expected = TradeException.class)
	public void testNotMatchingStocksTrade() throws Exception{
		sOrder = new StubSellOrder(null, stock, 10, 9.99);
		bOrder = new StubBuyOrder(null, null, 10, 11.11);
		trade = new DefaultTrade(world, bOrder, sOrder, stock, 10, 9.99);
		trade.execute();
	}
	
	@Test(expected = TradeException.class)
	public void testZeroQuantityExecution() throws Exception{
		trade = new DefaultTrade(world, bOrder, sOrder, stock, 0, 19.90);
		trade.execute();
	}
	
	@Test(expected = TradeException.class)
	public void testBadBuyOrder() throws Exception{
		trade = new DefaultTrade(world, null, sOrder, stock, 10, 19.90);
		trade.execute();
	}
	
	@Test(expected = TradeException.class)
	public void testBadSellOrder() throws Exception{
		trade = new DefaultTrade(world, bOrder, null, stock, 10, 19.90);
		trade.execute();
	}

}
