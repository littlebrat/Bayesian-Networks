package bayes;

import java.util.Queue;

public class BayesStatic implements BayesianNetwork{
	
	protected BayesStaticGraph mynet;
	protected Score scr;
	private int nvars;
	private int randomrst=0;
	private String[] names;

	public BayesStatic(int[][] learning,int[][] testing,String s,String[] namevar){
		
		int[][] learn = new int[learning.length][learning[0].length];
		
		Configurations cfgs = new Configurations(learn);
		nvars=learn.length;
		mynet = new BayesStaticGraph(learn.length);
		names=namevar;
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning,testing);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning,testing);
	}

	@Override
	public void greedyHill() {
		// TODO Auto-generated method stub
		
	}

}
