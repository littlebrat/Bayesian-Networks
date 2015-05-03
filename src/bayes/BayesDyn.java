package bayes;

import java.util.Arrays;

import dag.AdjacencyList;
import dag.Graph;

public class BayesDyn implements BayesianNetwork{
	protected Graph grp;
	protected Score scr;
	protected int[][] learning;
	protected int[][] testing;
	protected Configurations cfgs;
	
	public BayesDyn(Data d,String s){
		if(s.equals("MDL"))	scr = new ScoreMDL();
		else if(s.equals("LL")) scr = new ScoreLL();
		
		learning = d.getTraining();
		testing = d.getTesting();
		
		cfgs = new Configurations(learning);
		grp = new AdjacencyList(learning[0].length);
		
		/*Faltam coisas*/
	}
	
	@Override
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		int d = learning[0].length/2;
		grp.add(ori+d,dest+d);
	}
	
	public void addInter(int ori, int dest) {
		// TODO Auto-generated method stub
		grp.add(ori,dest+(learning[0].length)/2);
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		int d = learning[0].length/2;
		grp.remove(ori+d,dest+d);
	}
	
	public void removeInter(int ori, int dest) {
		// TODO Auto-generated method stub
		grp.remove(ori,dest+(learning[0].length)/2);
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		int d = learning[0].length/2;
		grp.reverse(ori+d,dest+d);
	}

	@Override
	public String toString() {
		String r = new String();
		r="Graph: \n"+grp.toString();
		r=r+"\n" +"Score: "+ scr.toString();
		return r;
	}

	@Override
	public void greedyHill() {
		// TODO Auto-generated method stub
		
	}
	
}
