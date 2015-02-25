package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.World;
import uk.ac.glasgow.jagora.impl.ContinuousOrderDrivenMarket;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubStockBananas;
import uk.ac.glasgow.jagora.test.stub.StubTickEvent;
import uk.ac.glasgow.jagora.test.stub.StubTrade;
import uk.ac.glasgow.jagora.test.stub.StubTrader;



public class ContinuousOrderDrivenMarketTest {
	
	Market market;

	Stock bananas = StubStockBananas.bananas;
	World stubWorld;
		
	BuyOrder buyOrder;
	SellOrder sellOrder;
	
	BuyOrder bestBid;
	SellOrder bestOffer;
	
	Trader trader = StubTrader.trader;
	Stock lemons = StubStock.lemons;
	
	Trader buyer = StubTrader.buyer;
	Trader seller = StubTrader.seller;
	
	@Before
	public void setUp() {
		market = new ContinuousOrderDrivenMarket(lemons, stubWorld);
	}
	
	@After
	public void tearDown() { 
		market = null;
		lemons = null;
		stubWorld = null;
		buyOrder = null;
		sellOrder = null;
		bestBid = null;
		bestOffer = null;
		trader = null;
		lemons = null;
		buyer = null;
		seller = null;
		bananas = null;
	}
	
	@Test
	public void testGetStock() {
		assertTrue("Stock of this market incorrect.", market.getStock() == lemons);
	}
	
	@Test
	public void testGetBestBid() {
		buyOrder = new StubBuyOrder(trader, lemons, 3, 9.0);
		market.placeBuyOrder(buyOrder);
		buyOrder = new StubBuyOrder(trader, lemons, 4, 16.0);
		bestBid = buyOrder;
		market.placeBuyOrder(buyOrder);
		buyOrder = new StubBuyOrder(trader, lemons, 10, 12.0);
		market.placeBuyOrder(buyOrder);
		assertTrue("Best bid incorrect", bestBid.getPrice() == market.getBestBid());
	}
	
	@Test
	public void testGetBestBidNull() {
		assertTrue("Best bid should be null", market.getBestBid() == null);
	}
	
	@Test
	public void testGetBestOffer() {
		sellOrder = new StubSellOrder(trader, lemons, 8, 13.0);
		market.placeSellOrder(sellOrder);
		sellOrder = new StubSellOrder(trader, lemons, 6, 3.0);
		bestOffer = sellOrder;
		market.placeSellOrder(sellOrder);
		sellOrder = new StubSellOrder(trader, lemons, 8, 17.0);
		market.placeSellOrder(sellOrder);
		assertTrue("Best offer incorrect", bestOffer.getPrice() == market.getBestBid());
	}
	
	@Test
	public void testGetBestOfferNull() {
		assertTrue("Best offer should be null", market.getBestOffer() == null);
	}
	
	@Test
	public void testPlaceBuyOrder() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		buyOrder = new StubBuyOrder(trader, lemons, 2, 15.0);
		market.placeBuyOrder(buyOrder);
		assertTrue("Buy order was not placed inside the buy order order book", market.getBestBid() == buyOrder.getPrice());
	}
	
	@Test
	public void testPlaceWrongStockBuyOrder() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		buyOrder = new StubBuyOrder(trader, bananas, 2, 15.0);
		market.placeBuyOrder(buyOrder);
		assertTrue("Buy order was not placed inside the buy order order book", market.getBestBid() == null);
	}
	
	@Test
	public void testPlaceSellOrder() {
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		sellOrder = new StubSellOrder(trader, lemons, 2, 15.0);
		market.placeSellOrder(sellOrder);
		assertTrue("Sell order wasn ot placed inside the sell order book.", market.getBestOffer() == sellOrder.getPrice());
	}
	
	@Test
	public void testPlaceWrongStockSellOrder() {
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		sellOrder = new StubSellOrder(trader, bananas, 2, 15.0);
		market.placeSellOrder(sellOrder);
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
	}
	
	@Test
	public void testCancelBuyOrder() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		buyOrder = new StubBuyOrder(trader, lemons, 2, 15.0);
		market.placeBuyOrder(buyOrder);
		assertTrue("Buy order was not placed inside the buy order book", market.getBestBid() == buyOrder.getPrice());
		market.cancelBuyOrder(buyOrder);
		assertTrue("Buy order was not removed from the buy order book", market.getBestBid() == null);
	}
	
	@Test 
	public void testCancelSellOrder() {
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		sellOrder = new StubSellOrder(trader, lemons, 2, 15.0);
		market.placeSellOrder(sellOrder);
		assertTrue("Sell order was not placed inside the sell order book", market.getBestOffer() == sellOrder.getPrice());
		market.cancelSellOrder(sellOrder);
		assertTrue("Sell order was not removed from the buy order book.", market.getBestOffer() == null);
	}
	
	@Test
	public void testClearingPossibleTradesExist() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		
		buyOrder = new StubBuyOrder(buyer, lemons, 3, 5.35);
		market.placeBuyOrder(buyOrder);
		buyOrder = new StubBuyOrder(buyer, lemons, 5, 6.0);
		market.placeBuyOrder(buyOrder);

		
		sellOrder = new StubSellOrder(seller, lemons, 3, 4.0);
		market.placeSellOrder(sellOrder);
		sellOrder = new StubSellOrder(seller, lemons, 3, 5.0);
		market.placeSellOrder(sellOrder);
		sellOrder = new StubSellOrder(seller, lemons, 4, 9.0);
		market.placeSellOrder(sellOrder);
		
		List<TickEvent<Trade>> returnResult = market.doClearing();
		List<TickEvent<Trade>> expectedResult = Arrays.asList(
				new StubTickEvent<Trade>(new StubTrade(3, 12.0, lemons), 0L),
				new StubTickEvent<Trade>(new StubTrade(2, 10.0, lemons), 0L),
				new StubTickEvent<Trade>(new StubTrade(1, 5.35, lemons), 0L));
		
		assertTrue("Returned tick events are incorrect", returnResult.equals(expectedResult)); 
		
	}
	
	@Test
	public void testClearingNoPossibleTrades() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		
		buyOrder = new StubBuyOrder(buyer, lemons, 3, 8.0);
		market.placeBuyOrder(buyOrder);
		sellOrder = new StubSellOrder(seller, lemons, 3, 9.0);
		market.placeSellOrder(sellOrder);
		
		assertTrue("No clearing should take place", market.doClearing().isEmpty());
	}
	
	@Test
	public void testFullTradeClearing() {
		assertTrue("Buy order book should be empty at this point", market.getBestBid() == null);
		assertTrue("Sell order book should be empty at this point", market.getBestOffer() == null);
		
		buyOrder = new StubBuyOrder(buyer, lemons, 3, 9.0);
		market.placeBuyOrder(buyOrder);
		sellOrder = new StubSellOrder(seller, lemons, 3, 9.0);
		market.placeSellOrder(sellOrder);
		
		List<TickEvent<Trade>> returnResult = market.doClearing();
		List<TickEvent<Trade>> expectedResult = Arrays.asList(
				new StubTickEvent<Trade>(new StubTrade(3, 27.0, lemons), 0L));
		assertTrue("Returned tick events are incorrect", returnResult.equals(expectedResult));
	}
	

	
}
