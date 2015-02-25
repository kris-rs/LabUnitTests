package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;
import uk.ac.glasgow.jagora.impl.DefaultTickEvent;

public class StubWorld implements World{
	
	public static final World ttTradeWorld = new StubWorld();

	@SuppressWarnings("hiding")
	@Override
	public <Trade> TickEvent<Trade> createTickEvent(Trade event) {
		return new DefaultTickEvent<Trade>(event,33l);
	}

}
