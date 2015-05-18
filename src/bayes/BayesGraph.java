package bayes;

import java.util.ArrayList;

import dag.Graph;

public interface BayesGraph extends Graph {
	
	
	ArrayList<Integer> getSons(int x);
	
	int[] getParents(int x);
}
