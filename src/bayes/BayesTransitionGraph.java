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
		nvars = dim/2;
		maxparents=parentmax;
		// TODO Auto-generated constructor stub
	}
	
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.add(ori+nvars,dest+nvars);
	}
	
	public void addInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getOrigins(dest+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.add(ori,dest+nvars);
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			super.remove(ori+nvars,dest+nvars);
	}
	
	public void removeInter(int ori, int dest) {
		// TODO Auto-generated method stub
		if(dest < nvars && ori < nvars)
			super.remove(ori,dest+nvars);
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(this.getDests(ori+nvars).size()<maxparents && dest < nvars && ori < nvars)
			super.reverse(ori+nvars,dest+nvars);
	}
	
	public ArrayList<Integer> getSons(int x){
		ArrayList<Integer> res = getDests(x);
		return res;
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
