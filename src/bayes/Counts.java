package bayes;

/**
 * Class Counts is a specialization of the class TensorToArray that deals with the counting task for each variable of the graph.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 */

public class Counts extends TensorToArray{
	int me;
	int[][] countTable;
	
	/**
	 * Constructor method that produces a Count object.
	 * @param var Index of the variable responsible for this object.
	 * @param parents Array of Integers with the index' of each parent.
	 * @param cfgs Object with all the configurations of the network.
	 */
	
	protected Counts(int var,int[] parents,Configurations cfgs){
		// TODO Auto-generated constructor stub
		me=var;
		this.cfgs=cfgs;
		if(parents.length>0){
			myparents=parents;
			countTable=new int[cfgs.ri(me)][getq()];
		}
		else{
			countTable=new int[cfgs.ri(me)][1];
			myparents=new int[0];
		}
	}
	
	/**
	 * Calculates the number of total combined configurations that parents of this node take.
	 * @return Integer value of the total number of combined configurations.
	 */
	
	int getq(){
		if(myparents.length>0){
			int res=1;
			for (int j = 0; j < myparents.length; j++) {
				res*=cfgs.ri(myparents[j]);
			}
			return res;
		}
		else return 0;
	}
	
	/**
	 * Gets the i'th 'B' value used when calculating the MDL score.
	 * @return double number with B score for this variable.
	 */
	
	double getMyB(){
		return (cfgs.ri(me)-1)*getq();
	}
	
	/**
	 * Adds one count to the object, corresponding to entry with k for its configuration and p for the configuration of its parents.
	 * @param k Integer for its configuration.
	 * @param p Array with the configuration values of its parents.
	 */
	
	void addcount(int k,int[] p){
		if(myparents.length>0){
			countTable[k][getGlobal(p)]+=1;
		}
		else{
			countTable[k][0]+=1;
		}
	}
	
	/**
	 * It calculates the number of times the configuration of parents with 'global' configuration appears on the learning set for any configuration of the present variable.
	 * @param global Integer global configuration of parents of the variable.
	 * @return Integer with the number of counts.
	 */
	
	int getNij(int global){
		int res=0;
		for (int k = 0; k < cfgs.ri(me); k++) {
			res+=getNijk(global,k);
		}
		return res;
	}
	
	/**
	 * It calculates the number of times the configuration of parents with 'global' configuration appears on the learning set for a configuration 'k' of the present variable.
	 * @param global Integer global configuration of parents of the variable.
	 * @param k Integer for its configuration.
	 * @return Integer with the number of counts.
	 */
	
	int getNijk(int global,int k){
		if(myparents.length>0)
			return countTable[k][global];
		else return countTable[k][0];
	}
	
	/**
	 * Returns the total number of configurations of the parents of the variable.
	 * @return number of different combinations of configurations of the parents.
	 */
	
	int getLengthJ(){
		return countTable[0].length;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = new String();
		for (int i = 0; i < countTable.length; i++) {
			for (int j = 0; j < countTable[0].length; j++) {
				s=s+' '+countTable[i][j];
			}
			s=s+'\n';
		}
		return s;
	}
	
}
