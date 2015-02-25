package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.impl.DefaultTickEvent;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubTrade;

public class DefaultTickEventTest {
	
	protected TickEvent<Trade> tEvent;
	protected TickEvent<Trade> earlyTEvent;
	protected TickEvent<Trade> lateTEvent;
	protected Trade trade = new StubTrade(10, 9.99, StubStock.lemons);

	@Before
	public void setUp(){
		tEvent = new DefaultTickEvent<Trade>(trade, 33l);
		earlyTEvent = new DefaultTickEvent<Trade>(trade, 1l);
		lateTEvent = new DefaultTickEvent<Trade>(trade, 99l);
	}
	
	@After
	public void tearDown(){
		tEvent = null;
		trade = null;
	}
	
	@Test
	public void getTickTest(){
		assertTrue("Tick getter from tick event object", tEvent.getTick() == 33l);
	}
	
	@Test
	public void getEventTest(){
		assertTrue("Event getter from tick event object", tEvent.getEvent().equals(trade));
	}
	
	@Test
	public void compareToTest(){
		assertTrue("Comparison of tick events", tEvent.compareTo(earlyTEvent) > 0);
		assertTrue("Comparison of tick events", tEvent.compareTo(lateTEvent) < 0);
		assertTrue("Equality comparison of tick events", tEvent.compareTo(new DefaultTickEvent<Trade>(trade, 33l)) == 0);
	}
	
}
