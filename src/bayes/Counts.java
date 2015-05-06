package bayes;

import java.util.ArrayList;
import java.util.Iterator;

public class Counts {
	int[] myparents;
	int me;
	Configurations cfgs;
	int[][] countTable;
	
	public Counts(int var,ArrayList<Integer> parents,Configurations cfgs){
		// TODO Auto-generated constructor stub
		me=var;
		this.cfgs=cfgs;
		if(parents.size()>0){
			myparents=new int[parents.size()];
			for (int i = 0; i < parents.size(); i++) {
				myparents[i]=parents.get(i);
			}
			countTable=new int[cfgs.ri(me)][getq()];
		}
		else{
			countTable=new int[cfgs.ri(me)][1];
		}
	}
	
	private int getq(){
		int res=1;
		for (int j = 0; j < myparents.length; j++) {
			res=cfgs.ri(myparents[j]);
		}
		return res;
	}
	
	double getMyB(){
		return (cfgs.ri(me)-1)*getq();
	}
	
	int getGlobal(int[] localcfg){
		//isto de certeza que esta mal porque os indices comecam em 1
		int j=localcfg[0];
		for (int l = 1; l < myparents.length; l++) {
			j=j*cfgs.ri(myparents[l])+localcfg[l];
		}
		return j;
	}
	
	int[] getLocal(int global){
		//isto de certeza que esta mal porque os indices comecam em 1
		int[] localcfg=new int[myparents.length];
		int temp = global;
		for (int l = myparents.length-1; l>=1; l--) {
			localcfg[l]=temp % cfgs.ri(myparents[l]);
			temp=(temp-localcfg[l])/(cfgs.ri(myparents[l]));
		}
		localcfg[0]=(temp-localcfg[1])/(cfgs.ri(myparents[1]));
		return localcfg;
	}
	
	void addcount(int k,int[] p){
		countTable[k][getGlobal(p)]+=1;
	}
	
	int getNij(int global){
		int res=0;
		for (int k = 0; k < cfgs.ri(me); k++) {
			res+=countTable[k][global];
		}
		return res;
	}
	
	int getNijk(int global,int k){
		return countTable[k][global];
	}
	
	public static void main(String args[]){
			
	}
	
}
