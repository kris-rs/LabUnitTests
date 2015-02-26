package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.World;
import uk.ac.glasgow.jagora.impl.DefaultStockExchange;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubMarket;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubTrader;
import uk.ac.glasgow.jagora.test.stub.StubWorld;

public class DefaultStockExchangeTest {
	
	BuyOrder buyOrder;
	SellOrder sellOrder;
	Trader trader = StubTrader.trader;
	Trader buyer = StubTrader.buyer;
	Trader seller = StubTrader.seller;
	World world = StubWorld.ttTradeWorld;
	Stock lemons = StubStock.lemons;
	StockExchange stockExchange;
	Market lemonMarket = StubMarket.lemonMarket;
	
	@Before
	public void setUp() {
		stockExchange = new DefaultStockExchange(world);
	}
	
	@After
	public void tearDown() {
		buyOrder = null;
		stockExchange = null;
		world = null;
		trader = null;
		lemons = null;
		sellOrder = null;
		buyer = null;
		lemonMarket = null;
		seller = null;
	}
	
	@Test
	public void testPlaceBuyOrder() {
		buyOrder = new StubBuyOrder(trader, lemons, 5, 20.0);
		stockExchange.placeBuyOrder(buyOrder);
		assertTrue("Buy order was not placed in the appropriate market.", stockExchange.getBestBid(lemons) == buyOrder.getPrice());
	}
	
	@Test
	public void testPlaceSellOrder() {
		sellOrder = new StubSellOrder(trader, lemons, 5, 20.0);
		stockExchange.placeSellOrder(sellOrder);
		assertTrue("Sell order was not placed in the appropriate market", stockExchange.getBestOffer(lemons) == buyOrder.getPrice());
	}
	
	@Test
	public void testCancelBuyOrder() {
		buyOrder = new StubBuyOrder(trader, lemons, 2, 2.2);
		stockExchange.placeBuyOrder(buyOrder);
		assertTrue("Buy order was not placed in the appropriate market.", stockExchange.getBestBid(lemons) == buyOrder.getPrice());
		stockExchange.cancelBuyOrder(buyOrder);
		assertTrue("Buy order was not removed from the appropriate market.", stockExchange.getBestBid(lemons) == null);
	}
	
	@Test
	public void testCancelSellOrder() {
		sellOrder = new StubSellOrder(trader, lemons, 2, 2.2);
		stockExchange.placeSellOrder(sellOrder);
		assertTrue("Buy order was not placed in the appropriate market.", stockExchange.getBestOffer(lemons) == buyOrder.getPrice());
		stockExchange.cancelSellOrder(sellOrder);
		assertTrue("Buy order was not removed from the appropriate market.", stockExchange.getBestOffer(lemons) == null);
	}
	
	@Test
	public void testGetBestBid() {
		buyOrder = new StubBuyOrder(trader, lemons, 3, 10.0);
		stockExchange.placeBuyOrder(buyOrder);
		assertTrue("Incorrect best bid", stockExchange.getBestBid(lemons) == buyOrder.getPrice());
	}
	
	@Test
	public void testGetBestOffer() {
		sellOrder = new StubSellOrder(trader, lemons, 3, 10.0);
		stockExchange.placeSellOrder(sellOrder);
		assertTrue("Incorrect best offer", stockExchange.getBestOffer(lemons) == sellOrder.getPrice());
	}
	
	@Test
	public void testGetTradeHistory() {
		buyOrder = new StubBuyOrder(buyer, lemons, 2, 10.0);
		stockExchange.placeBuyOrder(buyOrder);
		
		sellOrder = new StubSellOrder(seller, lemons, 2, 10.0);
		stockExchange.placeSellOrder(sellOrder);
		
		stockExchange.doClearing();
		List<TickEvent<Trade>> actualResult = stockExchange.getTradeHistory(lemons);
		assertTrue("Trade was not added to history of lemons", actualResult.equals(lemonMarket.doClearing()));
		
	}
}
