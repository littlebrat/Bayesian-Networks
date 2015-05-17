package bayes;

import java.util.Random;

import data.Data;//depois apagar isto
import data.DataTest;
import data.DataTrain;

public class BayesDyn implements BayesianNetwork{
	protected BayesTransitionGraph mynet;
	protected Score scr;
	private int nvars;
	private int randomrst=0;
	private String[] names;
	Tabu tabu;
	private int[][] learning;
	private int[][] testing;
	private Configurations cfgs;
	
	public BayesDyn(int[][] learning,int[][] testing,String s,String[] namevar, int ntabu){
		
		tabu=new Tabu(ntabu);
		this.learning=learning;
		this.testing=testing;
		cfgs = new Configurations(learning);
		nvars=learning[0].length/2;
		mynet = new BayesTransitionGraph(learning[0].length);
		names=namevar;
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning,testing);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning,testing);
	}

	public String toString() {
		String s= new String();
		s="===Inter-slice connectivity\n";
		for(int i=nvars; i<2*nvars;i++){
			s+=names[i-nvars]+" : ";
			for(int j=0; j<mynet.getParents(i).length;j++){
				if(mynet.getParents(i)[j]<=nvars-1){
					s+= names[mynet.getParents(i)[j]]+",";
				}
			}
			s=s.substring(0,s.length()-1);
			s+="\n";
		}
		s+="===Intra-slice connectivitiy\n";
		for(int i=nvars; i<2*nvars;i++){
			s+=names[i-nvars]+" : ";
			for(int j=0; j<mynet.getParents(i).length;j++){
				if(mynet.getParents(i)[j]>=nvars){
					s+= names[mynet.getParents(i)[j]-nvars]+",";
				}
			}
			s=s.substring(0,s.length()-1);
			s+="\n";
		}
		s+="===Scores\n";
		Score scrLL = new ScoreLL(cfgs,learning,testing);
		Score scrMDL = new ScoreMDL(cfgs,learning,testing);
		s+="LL score: "+scrLL.getScore(mynet)+"\n";
		s+="MDL score:  "+scrMDL.getScore(mynet)+"\n"; 
		return s;
	}
	
	public void setRestarts(int n){
		randomrst=n;
	}
	
	private BayesTransitionGraph bestAdd(){
		BayesTransitionGraph intra;
		BayesTransitionGraph inter;
		BayesTransitionGraph best = mynet.clone();
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				if(i!=j){
					intra=mynet.clone();
					intra.add(i,j);
					if(!(tabu.contains(intra))){
						if(scr.getScore(intra)>scr.getScore(best)) best=intra;
					}
				}
			}
		}
		intra=best;
		best=mynet.clone();
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				inter=mynet.clone();
				inter.addInter(i,j);
				if(!(tabu.contains(inter))){
					if(scr.getScore(inter)>scr.getScore(best)) best=inter;
				}
			}
		}
		inter=best;
		if(scr.getScore(inter)>scr.getScore(intra)) return inter;
		else return intra;
	}
	
	public BayesTransitionGraph bestRemove(){
		if(!mynet.isEmpty()){
			BayesTransitionGraph intra;
			BayesTransitionGraph inter;
			BayesTransitionGraph best = mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					if(i!=j){
						intra=mynet.clone();
						intra.remove(i,j);
						if(!(tabu.contains(intra))){
							if(scr.getScore(intra)>scr.getScore(best)) best=intra;
						}
					}
				}
			}
			intra=best;
			best=mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					inter=mynet.clone();
					inter.removeInter(i,j);
					if(!(tabu.contains(inter))){
						if(scr.getScore(inter)>scr.getScore(best)) 
							best=inter;
					}
				}
			}
			inter=best;
			if(scr.getScore(inter)>scr.getScore(intra)) return inter;
			else return intra;
		}
		return mynet;
	}
	
	private BayesTransitionGraph bestReverse(){
		if(!mynet.isEmpty()){
			BayesTransitionGraph intra;
			BayesTransitionGraph best = mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					if(i!=j){
						intra= mynet.clone();
						intra.reverse(i,j);
						if(!(tabu.contains(intra))){
							if(scr.getScore(intra)>scr.getScore(best)) best=intra;
						}
					}
				}
			}
			return best;
		}
		return mynet;
	}
	
	private BayesTransitionGraph makeRandomOP() {
		// TODO Auto-generated method stub
		BayesTransitionGraph grp=mynet.clone();
		Random rd = new Random();
		if(mynet.isEmpty()){
			if(rd.nextInt(2)==0) grp.add(rd.nextInt(nvars),rd.nextInt(nvars));
			else grp.addInter(rd.nextInt(nvars),rd.nextInt(nvars));
			
		}else{
			int op=rd.nextInt(3);
			switch(op){
			 case 0:
				 if(rd.nextInt(2)==0) grp.add(rd.nextInt(nvars),rd.nextInt(nvars));
				 else grp.addInter(rd.nextInt(nvars),rd.nextInt(nvars));
				 break;
			 case 1:
				 if(rd.nextInt(2)==0) grp.remove(rd.nextInt(nvars),rd.nextInt(nvars));
				 else grp.addInter(rd.nextInt(nvars),rd.nextInt(nvars));
			     break;
			 case 2:
				 grp.reverse(rd.nextInt(nvars), rd.nextInt(nvars));
				 break;   
			}
		}
		return grp;
	}
	
	public void greedyHill() {
		// TODO Auto-generated method stub
		BayesTransitionGraph aux;
		int restarts=0;
		Random rd = new Random();
		BayesTransitionGraph[] neighbours = new BayesTransitionGraph[3];
		double bestscore=Double.NEGATIVE_INFINITY;
		BayesTransitionGraph best=mynet.clone();
		BayesTransitionGraph previous;
		boolean flag=true;
		do{
			previous = best;
			neighbours[0] = bestAdd();
			neighbours[1] = bestRemove();
			neighbours[2] = bestReverse();
			for (BayesTransitionGraph grp : neighbours) {
				if(scr.getScore(grp)>bestscore) 
				{
					best=grp;
					bestscore=scr.getScore(grp);
				}
			}
			if(scr.getScore(best)>scr.getScore(previous)){
				mynet=best;
				aux=mynet.clone();
				tabu.add(aux);
			}
			else if(scr.getScore(best)==scr.getScore(previous) && restarts<randomrst){
				int ops=(rd.nextInt(nvars)+1);
				for(int i=0; i<ops;i++){
					mynet=makeRandomOP();
				}
				restarts++;
			}
			else flag=false;
		}while(flag);
		scr.makeEstimates();
	}

	public int[] getPredictions(int var){
		return scr.getVarFromTests(var);
	}
	
	public static void main(String[] args) throws Exception{
		Data mydata = new DataTrain(args[0]);
		Data mytest = new DataTest(args[1]);
		int[][] learn = mydata.get();
		int[][] test = mytest.get();
		double timetobuild = System.currentTimeMillis();
		String[] nomesranhosos = mydata.getNames(args[0]);
		BayesDyn mamen = new BayesDyn(learn,test,"LL",nomesranhosos,30);
		mamen.setRestarts(10);
		mamen.greedyHill();
		timetobuild = System.currentTimeMillis()-timetobuild;
		System.out.println(timetobuild/1000+" seconds");
		System.out.println(mamen);
		int[] pila = mamen.getPredictions(10);
		for (int i = 0; i < test.length; i++) {
			System.out.println(pila[i]);
		}
		
	}
}
