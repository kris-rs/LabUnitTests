package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.World;
import uk.ac.glasgow.jagora.impl.ContinuousOrderDrivenMarket;
import uk.ac.glasgow.jagora.test.stub.StubStock;



public class ContinuousOrderDrivenMarketTest {
	
	Market market;
	Stock stubLemons = StubStock.lemons;
	World stubWorld;
	
	@Before
	public void setUp() {
		market = new ContinuousOrderDrivenMarket(stubLemons, stubWorld);
	}
	
	@After
	public void tearDown() { 
		market = null;
		stubLemons = null;
		stubWorld = null;
	}
	
	@Test
	public void testGetStock() {
		assertTrue("Stock of this market incorrect.", market.getStock().equals(stubLemons));
	}
	
	@Test
	public void testPlaceBuyOrder() {
		
	}
}
