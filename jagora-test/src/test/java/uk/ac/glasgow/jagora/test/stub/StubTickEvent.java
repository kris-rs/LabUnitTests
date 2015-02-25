package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.TickEvent;

public class StubTickEvent<T> implements TickEvent<T>{
	
	private T object;
	Long tickTimestamp;
	
	public StubTickEvent(T object) {
		this(object, null);
	}
	
	public StubTickEvent(T object, Long tickTimestamp) {
		this.object = object;
		this.tickTimestamp = tickTimestamp;
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
		return tickTimestamp;
	}
	

}
