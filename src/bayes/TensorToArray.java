package bayes;

import java.util.List;
/**
 * Abstract class that cannot be instantiated that provides two methods for dealing with tensor data.
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 */

public abstract class TensorToArray {
	Configurations cfgs;
	int[] myparents;
	
	/**
	 * Receives an array of values and generates a mapping to a linear index.
	 * @param localcfg Integer combination of values of an array.
	 * @return Integer linear index.
	 */
	public int getGlobal(int[] localcfg){
		int j=localcfg[0];
		for (int l = 1; l < myparents.length; l++) {
			j=j*cfgs.ri(myparents[l])+localcfg[l];
		}
		return j;
	}
	
	/**
	 * Static method that receives a linear index and a list of the number of different values an index can have. 
	 * @param global Integer mapped index.
	 * @param js list of total possible values for each index.
	 * @return mapping from linear index to a combination of values.
	 */
	public static int[] getLocal(int global,List<Integer> js){
		int[] localcfg=new int[js.size()];
		if(js.size()==1){
			localcfg[0]=global;
		}
		else{
			int temp = global;
			for (int l = js.size()-1; l>=1; l--) {
				localcfg[l]=temp % js.get(l);
				temp=(temp-localcfg[l])/js.get(l);
			}
			localcfg[0]=temp;
		}
		return localcfg;
	}
}
