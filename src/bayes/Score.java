package bayes;

public abstract class Score {
	protected Counts[] varcounts;
	protected static double pseudo = 0.5;
	protected Configurations cfg;
	protected double myB;
	private int[][] learning;
	private int[][] testing;
	
	protected Score(Configurations cfg,int[][] tolearn,int[][] totest){
		learning=tolearn;
		testing=totest;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
	}
	
	protected Score(Configurations cfg,int[][] tolearn,int[][] totest,int pseudo){
		learning=tolearn;
		testing=totest;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
		this.pseudo=pseudo;
	}
	
	protected int getNijk(int i, int j, int k){
		return;
	}
	
	protected int getNij(int i, int j){
		
		return k;
	}
	
	protected double getLL(BayesTransitionGraph grp){
		
		return 0;
	}
	
	public abstract double getScore(BayesTransitionGraph grp);
	
}
