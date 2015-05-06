package bayes;

import java.util.Arrays;

public class Configurations {
	private int[] cfgs;
	
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
	
	protected int getmax(int var){
		return cfgs[var];
	}
	
	protected int ri(int var){
		return cfgs[var]+1;
	}
	
	protected int size(){
		return cfgs.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(cfgs);
	}
}
