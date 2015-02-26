package uk.ac.glasgow.jagora.test.stub;

import java.util.Arrays;
import java.util.List;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

public class StubMarket implements Market{
	
	public static final Market lemonMarket = new StubMarket();
	
	
	@Override
	public Stock getStock() {
		// TODO Auto-generated method stub
		return StubStock.lemons;
	}

	@Override
	public List<TickEvent<Trade>> doClearing() {
		// TODO Auto-generated method stub
		return Arrays.asList(new StubTickEvent<Trade>(new StubTrade(2, 20.0, StubStock.lemons)));
	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getBestBid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getBestOffer() {
		// TODO Auto-generated method stub
		return null;
	}

}
