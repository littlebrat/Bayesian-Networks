package bayes;

/**
 * ScoreMDL is a specialization of the abstract score class.
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo 
 */

public class ScoreMDL extends Score{
	
	/**
	 * Constructor method that setups the scoreLL.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 */

	protected ScoreMDL(Configurations cfg,int[][] tolearn) {
		super(cfg,tolearn);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor method that setups the scoreLL with a customized pseudo score.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 * @param pseudo parameter customizable to the user that serves as a way of customizing the estimate calculation.
	 */
	
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
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public double getScore(BayesGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp)-0.5*Math.log(learning.length)*getB()/Math.log(2);
		return res;
	}
	
}
