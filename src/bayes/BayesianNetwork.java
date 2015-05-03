package bayes;

public interface BayesianNetwork {
	
	public void add(int ori,int dest);
	
	public void remove(int ori, int dest);
	
	public void reverse(int ori, int dest);
	
	public String toString();
	
	public void greedyHill();
}
