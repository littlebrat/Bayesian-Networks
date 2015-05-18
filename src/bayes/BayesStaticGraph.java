package bayes;

import java.util.ArrayList;

import dag.AdjacencyList;

/**
 * Represents a graph in a form of an adjacency list (extends AdjacencyList class) and also implements a BayesGraph
 * Particularly it represents a static bayesian network which describes dependencies between random variables 
 * at a certain time interval
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 */

public class BayesStaticGraph extends AdjacencyList implements BayesGraph{
	
	/**
	 * Integer parameter that represents the maximum number of parents a node can have.
	 * By default that number is 3
	 */
	private static int maxparents=3;
	/**
	 * Integer parameter that represents the number of random variables represented in the graph,
	 * which will be the same as the number of nodes of the graph
	 */
	private int nvars;
	
	/**
	 * Creates a new BayesStaticGraph object with a certain number of random variables representing 
	 * it, and uses the default number of maximum parents
	 * 
	 * @param dim  represents the number of nodes of the graph to be created
	 */
	public BayesStaticGraph(int dim) {
		super(dim);
		nvars=dim;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Creates a new BayesStaticGraph object with a certain number of random variables representing
	 *  it and a new number of maximum parents, other than the default 
	 * 
	 * @param dim		represents the number of nodes of the graph to be created
	 * @param parentmax		represents the maximum number of parents a node can have
	 */
	
	public BayesStaticGraph(int dim,int parentmax) {
		super(dim);
		nvars=dim;
		maxparents=parentmax;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest).size()<maxparents)
			super.add(ori,dest);
	}
	/**
	 * {@inheritDoc}
	 */
	
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(ori).size()<maxparents && dest < nvars && ori < nvars)
			super.reverse(ori,dest);
	}
	/**
	 * {@inheritDoc}
	 */
	
	public ArrayList<Integer> getSons(int x){
		return getDests(x);
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	public int[] getParents(int x){//adicionar exceptioon
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
	public BayesStaticGraph clone() {
		// TODO Auto-generated method stub
		BayesStaticGraph cln = new BayesStaticGraph(this.size());
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				if(contains(i,j)){
					cln.add(i,j);
				}
			}
		}
		return cln;
	}
	
}
