package bayes;

import java.util.ArrayList;
import dag.AdjacencyList;

/**
 * Represents a graph in a form of an adjacency list (extends AdjacencyList class) and also implements a BayesGraph.
 * Particularly it represents a transition bayesian network which describes dependencies between random variables 
 * at two different time instants t and t+1 (future random variables depend on current random variables but not the 
 * other way around) and dependencies between random variables at time instant t+1.
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 */

public class BayesTransitionGraph extends AdjacencyList implements BayesGraph{
	
	/**
	 * Integer parameter that represents the maximum number of parents a certain node can have. By default that number is 3
	 */
	private static int maxparents=3;
	/**
	 * Integer parameter that represents the number of random variables represented in the graph,
	 * which will be half the number of nodes of the graph
	 */
	private int nvars;
	
	/**
	 * Creates a new BayesStaticGraph object with a certain number of random variables representing 
	 * it, and uses the default number of maximum parents
	 * 
	 * @param dim  represents the number of nodes of the graph to be created
	 */
	
	public BayesTransitionGraph(int dim) {
		super(dim);
		nvars = dim/2;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Creates a new BayesStaticGraph object with a certain number of random variables representing
	 *   it and a new number of maximum parents, other than the default.
	 * 
	 * @param dim		represents the number of nodes of the graph to be created
	 * @param parentmax		represents the maximum number of parents a node can have
	 */
	
	public BayesTransitionGraph(int dim,int parentmax) {
		super(dim);
		nvars = dim/2;
		maxparents=parentmax;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * {@inheritDoc}.
	 * Adding an edge only between random variables referring to time slice t+1 
	 */
	
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.add(ori+nvars,dest+nvars);
	}
	
	/**
	 * Adding an edge between a node representing a random variable from time slice
	 * t and one from time slice t+1
	 * 
	 * @param ori		origin node of the desired new edge
	 * @param dest		destination node of the desired new edge
	 */
	
	public void addInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.add(ori,dest+nvars);
	}
	
	/**
	 * {@inheritDoc}.
	 * Removing an edge only between random variables referring to time slice t+1 
	 */

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			super.remove(ori+nvars,dest+nvars);
	}
	
	/**
	 * Removing an edge between a node representing a random variable from time slice
	 * t and one from time slice t+1
	 * 
	 * @param ori		origin node of the desired edge to remove
	 * @param dest		destination node of the desired edge to remove
	 */
	
	public void removeInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			super.remove(ori,dest+nvars);
	}
	
	/**
	 * {@inheritDoc}.
	 * Checking if there is an edge only between random variables referring to time slice t+1
	 */

	@Override
	public boolean contains(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			return super.contains(ori+nvars, dest+nvars);
		else return false;
	}
	
	/**
	 * Checking if there is an edge between a node representing a random variable from time slice
	 * t and one from time slice t+1
	 * 
	 * @param ori		origin node of the desired edge to be checked
	 * @param dest		destination node of the desired edge to be checked
	 */
	
	public boolean containsInter(int ori, int dest) {
		if(dest < nvars && ori < nvars)
			return super.contains(ori, dest+nvars);
		else return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(ori+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.reverse(ori+nvars,dest+nvars);
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	public ArrayList<Integer> getSons(int x){
		ArrayList<Integer> res = getDests(x);
		return res;
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	public int[] getParents(int x){
		int[] res;
		ArrayList<Integer> temp = getOrigins(x);
		res = new int[temp.size()];
		int i=0;
		for (int val : temp) {
			res[i]=val;
			i++;
		}
		return res;
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + nvars;
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof BayesTransitionGraph))
			return false;
		BayesTransitionGraph other = (BayesTransitionGraph) obj;
		if (nvars != other.nvars)
			return false;
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public BayesTransitionGraph clone() {
		// TODO Auto-generated method stub
		BayesTransitionGraph cln = new BayesTransitionGraph(this.size());
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				if(contains(i,j)){
					cln.add(i,j);
				}
				if(containsInter(i,j)){
					cln.addInter(i,j);
				}
			}
		}
		return cln;
	}
}
