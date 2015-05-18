package bayes;

public interface BayesianNetwork {
	
	void greedyHill();
	
	void setRestarts(int n);
	
	int[] getPredictions(int var, int[][] testing);

	int[][] getAllPredictions(int[][] testing);
}
