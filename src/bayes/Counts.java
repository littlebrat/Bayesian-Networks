package bayes;

public class Counts extends TensorToArray{
	int me;
	int[][] countTable;
	
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
	
	double getMyB(){
		return (cfgs.ri(me)-1)*getq();
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
