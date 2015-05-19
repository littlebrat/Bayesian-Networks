package bayes;

/**
 * Estimates is a class that is normally associated with a variable of a graph that has all the estimates for the present variable.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 */

public class Estimates extends TensorToArray{
	double[][] paramTable;
	/**
	 * Constructor of the class Estimates that sets up the object.
	 * @param numcfgs Integer number of configurations of the present variable.
	 * @param numjs Integer number of the total combination of configurations of the parents.
	 * @param parents Integer array with the indexes of its parents.
	 * @param cfgs Configurations of all the variables of the network.
	 */
	protected Estimates(int numcfgs,int numjs,int[] parents,Configurations cfgs){
		// TODO Auto-generated constructor stub
		this.cfgs=cfgs;
		this.myparents=parents;
		paramTable=new double[numcfgs][numjs];
	}
	
	/**
	 * Sets the estimate entry with configuration 'k' of the own variable and 'j' global configuration of its parents with a p estimate.
	 * @param p double value of its estimate.
	 * @param j global Integer configuration of parents of the variable.
	 * @param k-th value of the present variable.
	 */
	protected void setParam(double p, int j, int k){
		paramTable[k][j]=p;
	}
	
	/**
	 * Gets the estimate entry with configuration 'k' of the own variable and the configuration of its parents 'fathers'.
	 * @param fathers local configuration of the parents values.
	 * @param k configuration of the present variable.
	 * @return
	 */
	protected double getParam(int[] fathers,int k){
		if(fathers.length==0) return paramTable[k][0];
		else return paramTable[k][getGlobal(fathers)];
	}
}
