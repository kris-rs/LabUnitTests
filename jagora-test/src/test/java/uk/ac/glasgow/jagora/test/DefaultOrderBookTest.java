package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.OrderBook;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.impl.DefaultOrderBook;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubTickEvent;
import uk.ac.glasgow.jagora.test.stub.StubWorld;


public class DefaultOrderBookTest {


	protected OrderBook<SellOrder> sellOrderBook;
	protected OrderBook<BuyOrder> buyOrderBook;
	
	protected StubWorld world;
	protected Stock stock = StubStock.lemons;
	
	protected StubBuyOrder stBuyOrder = new StubBuyOrder(null, stock, 10, 7.50);
	protected StubSellOrder stSellOrder = new StubSellOrder(null, stock, 10, 8.00);
	
	protected StubBuyOrder smallBuyOrder = new StubBuyOrder(null, stock, 10, 5.00);
	protected StubSellOrder smallSellOrder = new StubSellOrder(null, stock, 10, 6.50);
	

	@Before
	public void setUp(){
		sellOrderBook = new DefaultOrderBook<SellOrder>(world);
		buyOrderBook = new DefaultOrderBook<BuyOrder>(world);
	}
	
	@After
	public void tearDown(){
		world = null;
		stBuyOrder = null;
		stSellOrder = null;
		sellOrderBook = null;
		buyOrderBook = null;
	}
	
	@Test
	public void testEmptyBook(){
		assertTrue("Empty sell book does not return null", sellOrderBook.getBestOrder() == null);
		assertTrue("Empty buy book does not return null", buyOrderBook.getBestOrder() == null);
	}
	
	@Test
	public void testOrderBooksRecording(){
		sellOrderBook.recordOrder(stSellOrder);
		buyOrderBook.recordOrder(stBuyOrder);
		
		assertTrue("Sell order book does not record correctly", sellOrderBook.getBestOrder().compareTo(stSellOrder) == 0);
		assertTrue("Buy order book does not record correctly", buyOrderBook.getBestOrder().compareTo(stBuyOrder) == 0);
	}
	
	@Test
	public void testOrderBooksCancelling(){
		sellOrderBook.recordOrder(stSellOrder);
		buyOrderBook.recordOrder(stBuyOrder);
		
		sellOrderBook.cancelOrder(stSellOrder);
		buyOrderBook.cancelOrder(stBuyOrder);
		
		assertTrue("Cannot cancel sell order", sellOrderBook.getBestOrder() == null);
		assertTrue("Cannot cancel buy order", buyOrderBook.getBestOrder() == null);
	}
	
	
	@Test
	public void testBestSellOrder(){
		sellOrderBook.recordOrder(stSellOrder);
		sellOrderBook.recordOrder(smallSellOrder);
		
		assertTrue("Not returning 'best' sell order", sellOrderBook.getBestOrder() == smallSellOrder);
	}
	
	@Test
	public void testBestBuyOrder(){
		buyOrderBook.recordOrder(stBuyOrder);
		buyOrderBook.recordOrder(smallBuyOrder);
		
		assertTrue("Not returning 'best' buy order", buyOrderBook.getBestOrder() == stBuyOrder);
	}

	@Test
	public void testOrdersAsList(){
		assertTrue("Empty sell order book should return empty list", sellOrderBook.getOrdersAsList().isEmpty());
		assertTrue("Empty buy order book should return empty list", buyOrderBook.getOrdersAsList().isEmpty());
		
		sellOrderBook.recordOrder(stSellOrder);
		buyOrderBook.recordOrder(stBuyOrder);
		
		assertTrue("Sell order list incorrect", sellOrderBook.getOrdersAsList().get(0).compareTo(new StubTickEvent<SellOrder>(stSellOrder)) == 0);
		assertTrue("Buy order list incorrect", buyOrderBook.getOrdersAsList().get(0).compareTo(new StubTickEvent<BuyOrder>(stBuyOrder)) == 0);
		
		sellOrderBook.recordOrder(smallSellOrder);
		buyOrderBook.recordOrder(smallBuyOrder);
		
		assertTrue("Sell order list incorrect", sellOrderBook.getOrdersAsList().get(1).compareTo(new StubTickEvent<SellOrder>(smallSellOrder)) == 0);
		assertTrue("Buy order list incorrect", buyOrderBook.getOrdersAsList().get(1).compareTo(new StubTickEvent<BuyOrder>(smallBuyOrder)) == 0);
		
	}

	
}
