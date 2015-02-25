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
import uk.ac.glasgow.jagora.impl.DefaultTrade;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubWorld;

public class DefaultTradeTest {
	
	Trade trade;
	World world = StubWorld.ttTradeWorld;
	BuyOrder bOrder = StubBuyOrder.bOrder;
	SellOrder sOrder = StubSellOrder.sOrder;
	Stock stock = StubStock.lemons;
	Integer quantity = 10;
	Double price = 19.90;
	
	@Before
	public void setUp(){
		trade = new DefaultTrade(world, bOrder, sOrder, stock, quantity, price);
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
	public void testExecute(){
		try {
			assertTrue("Trade execution", trade.execute().equals(world.createTickEvent(trade)));
		} catch (TradeException e) {
			// TODO Auto-generated catch block
		}
	}

	

}
