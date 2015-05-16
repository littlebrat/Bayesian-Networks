package dag;

import java.util.ArrayList;

/**
 * Interface that represents a graph
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 */

public interface Graph {
	
	/**
	 * Adds a directed edge to the graph
	 * 
	 * @param ori 		origin node of the directed edge 
	 * @param dest 		destination node of the directed edge
	 */
	
	public void add(int ori,int dest);
	
	/**
	 * Removes an edge form the graph
	 * 
	 * @param ori 		Integer representation of origin node of the edge
	 * @param dest 		Integer representation of destination node of the edge
	 */
	
	public void remove(int ori,int dest);
	
	/**
	 * Checks if the graph has any edges
	 * 
	 * @return 			true if it's empty or false if it's not
	 */
	
	public boolean isEmpty();
	
	/**
	 * Reverses an edge from the graph
	 * 
	 * @param ori 		Integer representation of origin node of the edge
	 * @param dest 		Integer representation of destination node of the edge
	 */
	
	public void reverse(int ori,int dest);
	
	/**
	 * Gets the all the destinations of a certain node
	 * 
	 * @param x 		Integer representation of Node to get destinations from
	 * @return 			ArrayList with the integer representation of the destinations of node x 
	 */
	
	public ArrayList<Integer> getDests(int x);
	
	/**
	 * Gets the all the origins of a certain node
	 * 
	 * @param x 		Integer representation of Node to get origins from
	 * @return 			ArrayList with the integer representation of the origins of node x 
	 */
	
	public ArrayList<Integer> getOrigins(int x);
	
	/**
	 * Gets the number of nodes of the graph
	 * 
	 * @return 			number of nodes of the graph
	 */
	
	public int size();
	
	/**
	 * Clones the graph
	 * 
	 * @return 			new graph cloned from the graph
	 */
	
	public Graph clone();
	
	/**
	 * Checks if there is a directed edge from a certain node to another
	 * 
	 * @param ori 		Integer representation of origin node of the edge
	 * @param dest 		Integer representation of destination node of the edge
	 * @return 			true if there is a directed edge from ori to dest or false if it's not
	 */
	
	public boolean contains(int ori,int dest);
	
}
