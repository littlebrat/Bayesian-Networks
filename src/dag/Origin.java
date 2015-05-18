package dag;

import java.util.ArrayList;

/**
 * Subclass of Node that represents a node of the graph
 * There is a directed association from the Origin to 
 * the other nodes - Destination objects (nodes to which the origin node points-directed edge)
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 * @param <T> represents type parameter
 */

public class Origin<T> extends Node<T>{
	
	/**
	 * ArrayList of Destination objects of the Origin node - Directed Association
	 */
	
	protected ArrayList<Destination<T>> destlist = new ArrayList<Destination<T>>();
	
	/**
	 * Creates new Origin object with the given element
	 * 
	 * @param element 	element of type T that is to be stored as a parameter of the node
	 */
	
	protected Origin(T element) {
		super(element);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the number of Destinations of the origin node 
	 * 
	 * @return 			size of ArrayList of Destinations
	 */
	
	protected int size() {
		return destlist.size();
	}
	
	/**
	 * Gets the representation of the Destination objects 
	 * associated to the origin node
	 * 
	 * @return ArrayList of type T of Destination objects
	 */
	
	protected ArrayList<T> getDests(){
		ArrayList<T> res = new ArrayList<T>();
		for (Destination<T> dest : destlist) {
			res.add(dest.getValue());
		}
		return res;
	}
	
	/**
	 * Checks if the origin node has any destinations nodes 
	 * 
	 * @return 			true if the node has nodes to which it points and false if otherwise
	 */
	
	protected boolean isEmpty(){
		if(destlist.size()==0)	return true;
		return false;
	}
	
	/**
	 * Checks if the origin node has a specific destination node associated
	 * 
	 * @param node 		type T parameter that represents the destination node 
	 * @return 			true if the origin node has the corresponding destination node associated or false if otherwise
	 */
	
	protected boolean contains(T node){
		return destlist.contains(new Destination<T>(node));
	}
	
	/**
	 * Adds a new destination node to the association if that association does not exist
	 * 
	 * @param node 		parameter of type T that represents the new destination
	 */
	
	protected void add(T node) {
		// TODO Auto-generated method stub
		if(!contains(node))
			destlist.add(new Destination<T>(node));
	}
	
	/**
	 * Removes a destination node from the association if it exists
	 * 
	 * @param node 		parameter of type T that represents the destination to be removed
	 */
	
	protected void remove(T node) {
		// TODO Auto-generated method stub
		destlist.remove(new Destination<T>(node));
	}
	
	/**
	 *{@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((destlist == null) ? 0 : destlist.hashCode())
				+((this.getValue() == null) ? 0 : this.getValue().hashCode());
		return result;
	}
	
	/**
	 *{@inheritDoc}
	 */


	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!(this.getValue().equals(((Origin<T>) obj).getValue())))
			return false;
		Origin<T> other = (Origin<T>) obj;
		if (destlist == null) {
			if (other.destlist != null)
				return false;
		} else if (!destlist.equals(other.destlist))
			return false;
		return true;
	}
}
