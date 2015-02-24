package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.*;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.impl.DefaultStock;
import uk.ac.glasgow.jagora.test.stub.StubStock;

public class DefaultStockTest {
	
	private Stock stock;
	
	@Before
	public void setUp() {
		stock = new DefaultStock("lemons");
	}
	
	
	@After 
	public void tearDown() {
		stock = null;
	}
	
	@Test
	public void testName() {
		assertEquals("Expecting lemons.", "lemons", stock.getName());
	}
}
