package data;

import java.util.ArrayList;

/**
 * The class TimeSample allows the user to access the events from a time-slice, considered here as time samples.
 *
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 */
public class TimeSample {
	ArrayList<Event> samples = new ArrayList<Event>();
	
	/**
	 * The method add() adds an Event to the ArrayList samples.
	 * @See Event
	 * @return samples		ArrayList with the added Event.
	 */
	protected void add(Event smp){
		this.samples.add(smp);
	}
	
	/**
	 * The method get() access a specific position of the ArrayList samples.
	 * @See Event
	 * @return Event	Event retrieved from a specific position of the ArrayList samples.
	 */
	protected Event get(int pos){		
		return this.samples.get(pos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TimeSample [samples=" + samples + "]";
	}
}
