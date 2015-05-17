package bayes;

import java.util.ArrayList;

public interface BayesGraph {
	
	void add(int ori, int dest);
	
	void remove(int ori, int dest);
	
	boolean contains(int ori, int dest);
	
	void reverse(int ori, int dest);
	
	ArrayList<Integer> getSons(int x);
	
	int[] getParents(int x);
	
	boolean isEmpty();
	
	int size();
}
