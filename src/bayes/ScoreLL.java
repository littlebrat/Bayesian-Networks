package bayes;

public class ScoreLL extends Score{

	protected ScoreLL(Configurations cfg,int[][] tolearn) {
		super(cfg,tolearn);
		// TODO Auto-generated constructor stub
	}
	
	protected ScoreLL(Configurations cfg,int[][] tolearn,int pseudo) {
		super(cfg,tolearn,pseudo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getScore(BayesGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp);
		return res;
	}
	
}
