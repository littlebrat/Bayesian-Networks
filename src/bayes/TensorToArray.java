package bayes;

import java.util.List;

public abstract class TensorToArray {
	Configurations cfgs;
	int[] myparents;
	
	public int getGlobal(int[] localcfg){
		int j=localcfg[0];
		for (int l = 1; l < myparents.length; l++) {
			j=j*cfgs.ri(myparents[l])+localcfg[l];
		}
		return j;
	}
	
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
