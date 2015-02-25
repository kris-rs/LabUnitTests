package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.Stock;

public class StubStockBananas implements Stock{
	public static final Stock bananas = new StubStock ();
	
	@Override
	public String getName() {
		return "bananas";
	}
}
