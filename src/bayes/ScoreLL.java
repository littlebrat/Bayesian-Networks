package bayes;

public class ScoreLL extends Score{

	protected ScoreLL(Configurations cfg,int[][] tolearn,int[][] totest) {
		super(cfg,tolearn,totest);
		// TODO Auto-generated constructor stub
	}
	
	protected ScoreLL(Configurations cfg,int[][] tolearn,int[][] totest,int pseudo) {
		super(cfg,tolearn,totest,pseudo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getScore(BayesTransitionGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp);
		return res;
	}
	
	
	
}
