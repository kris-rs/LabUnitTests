package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.impl.DefaultStock;

public class DefaultStockTest {

	protected Stock apples;
	
	@Before
	public void setUp(){
		apples = new DefaultStock("apples");
	}
	
	@After
	public void tearDown(){
		apples = null;
	}
	
	@Test
	public void getNameTest(){
		assertTrue(apples.getName().equals("apples"));
	}
}
