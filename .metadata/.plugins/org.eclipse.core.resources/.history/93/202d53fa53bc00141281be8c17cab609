package uk.ac.glasgow.jagora.test;

import org.junit.After;
import org.junit.Before;

import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.impl.ContinuousOrderDrivenMarket;
import uk.ac.glasgow.jagora.impl.DefaultStock;
import uk.ac.glasgow.jagora.test.stub.StubStock;

public class DefaultMarketTest {

	Market market;
	
	@Before
	public void setUp() {
		market = new ContinuousOrderDrivenMarket(StubStock.lemons, null);
	}
	
	
	@After 
	public void tearDown() {
		market = null;
	}
	
}
