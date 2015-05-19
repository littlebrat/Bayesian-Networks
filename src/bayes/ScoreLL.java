package bayes;
/**
 * ScoreLL is a specialization of the abstract score class.
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo 
 */
public class ScoreLL extends Score{
	
	/**
	 * Constructor method that setups the scoreLL.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 */

	protected ScoreLL(Configurations cfg,int[][] tolearn) {
		super(cfg,tolearn);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor method that setups the scoreLL with a customized pseudo score.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 * @param pseudo parameter customizable to the user that serves as a way of customizing the estimate calculation.
	 */
	
	protected ScoreLL(Configurations cfg,int[][] tolearn,int pseudo) {
		super(cfg,tolearn,pseudo);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getScore(BayesGraph grp) {
		// TODO Auto-generated method stub
		double res=0;
		makeCounts(grp);
		res=getLL(grp);
		return res;
	}
	
}
