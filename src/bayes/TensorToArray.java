package bayes;

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
	
	int[] getLocal(int global){
		//corrigido, mas nao sei se ja funciona!
		int[] localcfg=new int[myparents.length];
		if(myparents.length==1){
			localcfg[0]=global;
		}
		else{
			int temp = global;
			for (int l = myparents.length-1; l>=1; l--) {
				localcfg[l]=temp % cfgs.ri(myparents[l]);
				temp=(temp-localcfg[l])/(cfgs.ri(myparents[l]));
			}
			localcfg[0]=temp;
		}
		return localcfg;
	}
}
