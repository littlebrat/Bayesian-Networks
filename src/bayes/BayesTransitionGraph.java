package bayes;

import java.util.ArrayList;

import dag.AdjacencyList;

public class BayesTransitionGraph extends AdjacencyList{
	private static int maxparents=3;
	private int nvars;
	
	public BayesTransitionGraph(int dim) {
		super(dim);
		nvars = dim/2;
		// TODO Auto-generated constructor stub
	}
	
	public BayesTransitionGraph(int dim,int parentmax) {
		super(dim);
		nvars = this.size()/2;
		maxparents=parentmax;
		// TODO Auto-generated constructor stub
	}
	
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getDests(dest).size()<maxparents && dest < nvars && ori < nvars)
			add(ori+nvars,dest+nvars);
	}
	
	public void addInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getDests(dest).size()<maxparents && dest < nvars && ori < nvars)
			add(ori,dest+nvars);
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			remove(ori+nvars,dest+nvars);
	}
	
	public void removeInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			remove(ori,dest+nvars);
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getDests(ori).size()<maxparents && dest < nvars && ori < nvars)
			reverse(ori+nvars,dest+nvars);
	}

	@Override
	public String toString() {
		 return toString();
	}
	
	public int size(){
		return nvars;
	}
	
	public ArrayList<Integer> getSons(int x){
		return getDests(x);
	}
	
	public ArrayList<Integer> getParents(int x){
		return getOrigins(x);
	}

}
