package bayes;

import java.util.ArrayList;

import dag.Graph;

/**
 * Interface which extends the Graph interface and represents a bayesian graph. 
 * A bayesian network has random variables for nodes. It has the particularity 
 * of refering to the directed edges corresponding nodes as sons and parents, 
 * where the sons are the destinations of the edges, and the parents the origins.
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo 
 * @author Nuno Mendes 
 *
 */
public interface BayesGraph extends Graph {
	
	/**
	 * Gets the sons of a certain node of the bayesian graph (to which nodes does it point)
	 * 
	 * @param x 		Integer representation of Node to get sons from
	 * @return 			ArrayList with the integer representation of the sons of node x 
	 */
	
	ArrayList<Integer> getSons(int x);
	
	/**
	 * Gets the parents of a certain node of the bayesian graph (which nodes are pointing to it)
	 * 
	 * @param x 		Integer representation of Node to get parents from
	 * @return 			array with the integer representation of the parents of node x 
	 */
	
	int[] getParents(int x);
}
