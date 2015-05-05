package data;

import java.util.ArrayList;

public class TimeSample {
	ArrayList<Event> samples = new ArrayList<Event>();
	
	
	protected void add(Event smp){
		this.samples.add(smp);
	}
	
	protected Event get(int pos){		
		return this.samples.get(pos);
	}

	@Override
	public String toString() {
		return "TimeSample [samples=" + samples + "]";
	}


	
	
}
