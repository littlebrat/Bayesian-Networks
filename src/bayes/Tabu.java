package bayes;

import java.util.LinkedList;

/**
 * 
 * 
 * @author Sofia Silva
 */

public class Tabu {
	
	/**
	 * maximum number of BayesGraph objects to be stored
	 */
	private int maxnum;
	
	/**
	 * LinkedList of BayesGraph objects
	 */
	private LinkedList<BayesGraph> tabulist = new LinkedList<BayesGraph>();
	
	/**
	 * Creates a new Tabu object initializing the maxnum parameter 
	 * 
	 * @param maxnum		maximum number of BayesGraph objects to be stored
	 */
	public Tabu(int maxnum) {
		// TODO Auto-generated constructor stub
		this.maxnum=maxnum;
	}
	
	/**
	 * Adds a BayesianGraph object to the LinkedList
	 * 
	 * @param graph			graph to be added to the list
	 */
	
	public void add(BayesGraph graph){
		if(tabulist.size()==maxnum){
			tabulist.removeFirst();
		}
		tabulist.add(graph);
	}

	/**
	 * Checks if the list contains a certain BayesianGraph object
	 * 
	 * @param graph			graph we want to check if it's contained in the list
	 * @return				true if the list contains the given graph, false otherwise
	 */
	
	public boolean contains(BayesGraph graph){
		
		for(BayesGraph bg: tabulist){
			if(graph.equals(bg))
				return true;
		}
		
		return false;
	}
}
