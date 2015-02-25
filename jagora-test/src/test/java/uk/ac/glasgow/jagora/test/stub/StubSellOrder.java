package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

public class StubSellOrder implements SellOrder{

	public static final SellOrder sOrder = new StubSellOrder();
	
	@Override
	public Trader getTrader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock getStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRemainingQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void satisfyTrade(TickEvent<Trade> tradeEvent) throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollBackTrade(TickEvent<Trade> tradeEvent)
			throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(SellOrder order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
