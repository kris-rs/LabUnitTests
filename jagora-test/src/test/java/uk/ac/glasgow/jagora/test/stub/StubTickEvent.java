package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.TickEvent;

public class StubTickEvent<T> implements TickEvent<T>{
	
	private T object;
	
	public StubTickEvent(T object) {
		this.object = object;
	}
	
	@Override
	public int compareTo(TickEvent<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getEvent() {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public Long getTick() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
