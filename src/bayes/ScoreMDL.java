package bayes;

public class ScoreMDL extends Score{

	protected ScoreMDL(Configurations cfg,int[][] tolearn) {
		super(cfg,tolearn);
		// TODO Auto-generated constructor stub
	}
	
	protected ScoreMDL(Configurations cfg,int[][] tolearn,int pseudo) {
		super(cfg,tolearn,pseudo);
		// TODO Auto-generated constructor stub
	}

	private double getB(){
		double res=0;
		for (Counts count : varcounts) {
			res+=count.getMyB();
		}
		return res;
	}
	
	@Override
	public double getScore(BayesGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp)-0.5*Math.log(learning.length)*getB()/Math.log(2);
		return res;
	}
	
}
