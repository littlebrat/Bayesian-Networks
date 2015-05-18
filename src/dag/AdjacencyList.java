package dag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Class that implements the interface Graph as an adjacency list
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 */

public class AdjacencyList implements Graph{
	
	/**
	 * Array of Origin objects
	 */
	
	protected Origin<Integer>[] originlist;
	
	/**
	 * Creates a new AdjacencyList object which creates an array of new Origins with the given dimension
	 * 
	 * @param dim		number of nodes of the adjacency list
	 */
	
	@SuppressWarnings("unchecked")
	public AdjacencyList(int dim){
		originlist = (Origin<Integer>[]) new Origin[dim];
		for (int i = 0; i < originlist.length; i++) {
			originlist[i]=new Origin<Integer>(i);
		}
	}
	
	/**
	 *{@inheritDoc}.
	 *
	 *If the adding of the edge results in a graph that is not a DAG the edge is not added 
	 */
	
	@Override
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest)
			if(reachable(dest)[ori]==0){
				originlist[ori].add(dest);
			}
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest)
			originlist[ori].remove(dest);
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (Origin<Integer> origin : originlist) {
			if(origin.size()!=0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest && originlist[ori].contains(dest)==true){
			originlist[ori].remove(dest);
			if(reachable(ori)[dest]==0)
				originlist[dest].add(ori);
			else originlist[ori].add(dest);
		}
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public String toString() {
		String r = new String();
		for (int i = 0; i < originlist.length; i++) {
			for (int j = 0; j < originlist.length; j++) {
				if(originlist[j].contains(i)){
					r=r+' '+'1';
				}
				else{
					r=r+' '+'0';
				}
			}
			r=r+'\n';
		}
		return r;
	}
	
	/**
	 * Gets the all the destinations of a certain node (nodes to which this a certain nodes points)
	 * 
	 * @param x 		Integer representation of Node to get destinations from
	 * @return 			ArrayList with the integer representation of the destinations of node x 
	 */

	public ArrayList<Integer> getDests(int x) {
		// TODO Auto-generated method stub
		return originlist[x].getDests();
	}
	
	/**
	 * Gets the all the origins of a certain node (nodes which point to that certain node-it's parents)
	 * 
	 * @param x 		Integer representation of Node to get origins from
	 * @return 			ArrayList with the integer representation of the origins of node x 
	 */
	
	public ArrayList<Integer> getOrigins(int x) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ori = new ArrayList<Integer>();
		for (Origin<Integer> origin : originlist) {
			if(origin.contains(x))
				ori.add(origin.getValue());
		}
		return ori;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return originlist.length;
	}
	
	/**
	 * Gets an array of Integer representing the nodes that can be reached 
	 * from a certain node by implementation of a DFS algorithm
	 * 
	 * @param node		Node from which we want to know what nodes can be reached from it
	 * @return			array of type Integer representing the nodes that can be reached from the given node
	 */
	
	
	private int[] reachable(int node){
		
		Stack<Integer> stack = new Stack<Integer>();
		int[] visited = new int[size()];
		stack.push(node);
		int v;
		while(!stack.isEmpty()){
			v=stack.pop();
			if(visited[v]!=1){
				visited[v]=1;
				for (Integer w : getDests(v)) {
					stack.push(w);
				}
			}
		}	
		return visited;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(originlist);
		return result;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdjacencyList other = (AdjacencyList) obj;
		if (!Arrays.equals(originlist, other.originlist))
			return false;
		return true;
	}
	
	/**
	 *{@inheritDoc}
	 */
	
	@Override
	public AdjacencyList clone(){
		// TODO Auto-generated method stub
		AdjacencyList nova = new AdjacencyList(originlist.length);
		for (Origin<Integer> origin : originlist) {
			for (int i = 0; i < originlist.length; i++) {
				if(origin.contains(i)) nova.add(origin.getValue(),i);
			}
		}
		return nova;
	}
	
	/**
	 *{@inheritDoc}
	 */

	@Override
	public boolean contains(int ori,int dest) {
		return originlist[ori].contains(dest);
	}
	
}
