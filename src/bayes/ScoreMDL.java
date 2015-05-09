package bayes;

public class ScoreMDL extends Score{

	protected ScoreMDL(Configurations cfg,int[][] tolearn,int[][] totest) {
		super(cfg,tolearn,totest);
		// TODO Auto-generated constructor stub
	}
	
	protected ScoreMDL(Configurations cfg,int[][] tolearn,int[][] totest,int pseudo) {
		super(cfg,tolearn,totest,pseudo);
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
	public double getScore(BayesTransitionGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp)-0.5*Math.log(learning.length)*getB();
		return res;
	}
	
}
