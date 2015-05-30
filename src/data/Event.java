package data;

import java.util.Arrays;

/**
 * The class Event includes the values of each random variable into an Array of integers.
 *
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */

public class Event {
	int[] occurrences;
	
	/**
	 * Creates an element of type Event.
	 * @param	size	Size of the vector of occurrences
	 */
	public Event(int size){
		occurrences=new int[size];	
	}
	
	/**
	 * The method add() adds a vector of the values of the random variables to the Event.
	 * 
	 *  @return Event 	An Event with the values taken by the random variables.
	 */
	protected void add(int[] vect){
		this.occurrences=vect;
	}
	
	/**
	 * The method get() gets the element of a specific position of the array of integers.
	 * 
	 *  @return number 	The value of the random variable.
	 */
	protected int get(int pos){
		return this.occurrences[pos];
	}
	
	/**
	 * The method size() returns the length of the array with the values of the random variables.
	 * 
	 *  @return size 	The length of the array.
	 */
	public int size(){
		return this.occurrences.length;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Event [occurrences=" + Arrays.toString(occurrences) + "]";
	}
	
}
