package bayes;


/**
 * Interface that represents a Bayesian Network. In a bayesian network we 
 * can learn the network, infer probabilities from the learned network and 
 * have a certain number of restarts to escape local maximums
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 */
public interface BayesianNetwork {
	
	/**
	 * Computes a greedy hill climbing algorithm for a certain training set
	 * 
	 */
	
	void greedyHill();
	
	/**
	 * Updates the number of restarts to be made in the the GHC algorithm
	 * 
	 * @param n		number of restarts
	 */
	
	void setRestarts(int n);
	
	/**
	 * Infers the most probable value for a certain random variable 
	 * considering a test set and the learned network
	 * 
	 * @param var		random variable to get the most probable value
	 * @param testing		Integer matrix containing test data 
	 * @return				the inferred values for the given random variable
	 */
	
	int[] getPredictions(int var, int[][] testing);
	
	/**
	 * Infers the most probable value for a certain random variable 
	 * considering a test set and the learned network
	 * 
	 * @param var		random variable to get the most probable value
	 * @param testing		Integer matrix containing test data 
	 * @return				the inferred values for the given random variable
	 */

	int[][] getAllPredictions(int[][] testing);
}
