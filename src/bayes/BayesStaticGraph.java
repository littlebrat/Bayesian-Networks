package bayes;

import java.util.ArrayList;

import dag.AdjacencyList;

public class BayesStaticGraph extends AdjacencyList implements BayesGraph{
	private static int maxparents=3;
	private int nvars;
	
	public BayesStaticGraph(int dim) {
		super(dim);
		nvars=dim;
		// TODO Auto-generated constructor stub
	}
	
	public BayesStaticGraph(int dim,int parentmax) {
		super(dim);
		nvars=dim;
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
