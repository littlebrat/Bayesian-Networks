package bayes;

import java.util.ArrayList;

import dag.AdjacencyList;

public class BayesStaticGraph extends AdjacencyList{
	private static int maxparents=3;
	private int nvars;
	
	public BayesStaticGraph(int dim) {
		super(dim);
		// TODO Auto-generated constructor stub
	}
	
	public BayesStaticGraph(int dim,int parentmax) {
		super(dim);
		nvars = dim/2;
		maxparents=parentmax;
		// TODO Auto-generated constructor stub
	}
	
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest).size()<maxparents)
			super.add(ori,dest);
	}
	
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(ori).size()<maxparents && dest < nvars && ori < nvars)
			super.reverse(ori,dest);
	}
	
	public ArrayList<Integer> getSons(int x){
		return getDests(x);
	}
	
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
	
}
