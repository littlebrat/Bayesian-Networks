package data;

import java.util.Arrays;

public class Event {
	private int[] occurrences;
	public Event(int size){
		occurrences=new int[size];	
	}
	protected void add(int[] vect){
		this.occurrences=vect;
	}
	protected int get(int pos){
		return this.occurrences[pos];
	}
	public int size(){
		return this.occurrences.length;
	}
	@Override
	public String toString() {
		return "Event [occurrences=" + Arrays.toString(occurrences) + "]";
	}
	
}
