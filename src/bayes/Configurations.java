package bayes;

import java.util.Arrays;

/**
 * Configurations class is responsible for storing all the possible values that the variables of the graph can take.
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 */

public class Configurations {
	private int[] cfgs;
	
	/**
	 * The constructor for this class receives learning data as a 2-dimensional array, checks the highest values the variables can take and stores them on an array.
	 * @param data 		Table with all the possible integer values it can take for each variable (i.e.: column 'i' represents the values that variable 'i' takes.)
	 */
	
	protected Configurations(int[][] data){
		cfgs=new int[data[0].length];
		int max;
		for (int i = 0; i < (data[0].length); i++) {
			max=-1;
			for (int j = 0; j < data.length; j++) {
				if(max<data[j][i]) max=data[j][i];
			}
			cfgs[i]=max;
		}
	}
	
	/**
	 * This method returns the maximum value (k) the variable given in the input can take.
	 * @param var Integer valued variable from which we want to know its maximum value.
	 * @return	Returns an integer with value k.
	 */
	
	protected int getmax(int var){
		return cfgs[var];
	}
	
	/**
	 * This method returns the total number of values the variable passed as parameter can take, i.e., its possible configurations.
	 * @param var Integer valued variable from which we want to know its total number of configurations.
	 * @return Integer value with total number of configurations.
	 */
	
	protected int ri(int var){
		return cfgs[var]+1;
	}
	
	/**
	 * Method to know the total number of variables of the object.
	 * @return The size of the configurations list.
	 */
	
	protected int size(){
		return cfgs.length;
	}
	
	
	@Override
	public String toString() {
		return Arrays.toString(cfgs);
	}
}
