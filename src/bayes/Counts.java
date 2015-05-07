package bayes;

public class Counts {
	int[] myparents;
	int me;
	static Configurations cfgs;
	int[][] countTable;
	
	protected Counts(int var,int[] parents){
		// TODO Auto-generated constructor stub
		me=var;
		if(parents.length>0){
			myparents=parents;
			countTable=new int[cfgs.ri(me)][getq()];
		}
		else{
			countTable=new int[cfgs.ri(me)][1];
		}
	}
	
	protected void setupConfig(Configurations c){
		cfgs=c;
	}
	
	int getq(){
		if(myparents.length>0){
			int res=1;
			for (int j = 0; j < myparents.length; j++) {
				res=cfgs.ri(myparents[j]);
			}
			return res;
		}
		else return 0;
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
		if(myparents.length>0){
			countTable[k][getGlobal(p)]+=1;
		}
		else{
			countTable[k][0]+=1;
		}
	}
	
	int getNij(int global){
		int res=0;
		for (int k = 0; k < cfgs.ri(me); k++) {
			res+=getNijk(global,k);
		}
		return res;
	}
	
	int getNijk(int global,int k){
		if(myparents.length>0)
			return countTable[k][global];
		else return countTable[k][0];
	}
	
	public static void main(String args[]){
			int[] hello =new int[0];
			
			System.out.println(hello.length);
	}
	
}
