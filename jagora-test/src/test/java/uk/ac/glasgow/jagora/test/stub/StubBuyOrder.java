package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.impl.DefaultTrader;

public class StubBuyOrder implements BuyOrder{

	public static final BuyOrder bOrder = new StubBuyOrder();
	
	@Override
	public Trader getTrader() {
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
	public int compareTo(BuyOrder order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
