package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

public class StubTickEventForWorld implements TickEvent<Trade> {
	
	public static final TickEvent<Trade> tradeEvent = new StubTickEventForWorld();

	@Override
	public int compareTo(TickEvent<Trade> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Trade getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTick() {
		// TODO Auto-generated method stub
		return null;
	}



}
