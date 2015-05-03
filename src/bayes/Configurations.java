package bayes;

import java.util.Arrays;

public class Configurations {
	private int[] cfgs;
	private int len;
	
	protected Configurations(int[][] data){
		cfgs=new int[data[0].length/2];
		int max;
		for (int i = 0; i < (data[0].length)/2; i++) {
			max=-1;
			for (int j = 0; j < data.length; j++) {
				if(max<data[j][i]) max=data[j][i];
			}
			cfgs[i]=max;
		}
		len=data[0].length/2;
	}
	
	protected int get(int pos){
		return cfgs[pos];
	}
	
	protected int size(){
		return len;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(cfgs);
	}
	
}
