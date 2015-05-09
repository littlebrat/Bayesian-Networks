package bayes;

public class Counts {
	int[] myparents;
	int me;
	Configurations cfgs;
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
		if(myparents.length==1){
			localcfg[0]=global;
		}
		else{
			int temp = global;
			for (int l = myparents.length-1; l>=1; l--) {
				localcfg[l]=temp % cfgs.ri(myparents[l]);
				temp=(temp-localcfg[l])/(cfgs.ri(myparents[l]));
			}
			localcfg[0]=(temp-localcfg[1])/(cfgs.ri(myparents[1]));
		}
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
	
	public static void main(String args[]){
		int[][] tabela = {{0,3,2,0},{1,4,1,0},{0,2,2,0},{1,1,1,0}};
		Configurations cfgs = new Configurations(tabela);
		BayesTransitionGraph grp = new BayesTransitionGraph(4);
		grp.add(0,1);
		
		
		Counts[] con = new Counts[4];
		for (int i = 0; i < con.length; i++) {
			con[i]= new Counts(i,grp.getParents(i),cfgs);
		}
		con[0].addcount(0,new int[0]);
		con[0].addcount(1,new int[0]);
		con[0].addcount(0,new int[0]);
		con[0].addcount(1,new int[0]);
		
		con[1].addcount(3,new int[0]);
		con[1].addcount(4,new int[0]);
		con[1].addcount(2,new int[0]);
		con[1].addcount(1,new int[0]);
		
		con[2].addcount(2,new int[0]);
		con[2].addcount(1,new int[0]);
		con[2].addcount(2,new int[0]);
		con[2].addcount(1,new int[0]);
		
		int[] caso_a = {2}; 
		int[] caso_b = {1}; 
		int[] caso_c = {2};
		int[] caso_d = {1};
		con[3].addcount(0,caso_a);
		con[3].addcount(0,caso_b);
		con[3].addcount(0,caso_c);
		con[3].addcount(0,caso_d);
		
		for (Counts counts : con) {
			System.out.println(counts);
		}
		System.out.println(con[3].getLocal(0)[0]);
	}
	
}
