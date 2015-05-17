package bayes;

import java.util.Queue;
import java.util.Random;

public class BayesStatic implements BayesianNetwork{
	
	protected BayesStaticGraph mynet;
	protected Score scr;
	private int nvars;
	private int randomrst=0;
	private String[] names;
	private int[][] learning;
	private int[][] testing;
	private Configurations cfgs;
	
	public BayesStatic(int[][] learning,int[][] testing,String s,String[] namevar){
		this.testing=testing;
		int[][] learn = new int[learning.length][learning[0].length/2];
		for (int i = 0; i < learn.length; i++) {
			for (int j = 0; j < learning[0].length/2; j++) {
				learn[i][j]=learning[i][j];
			}			
		}
		this.learning=learn;
		cfgs = new Configurations(learning);
		nvars=learning[0].length;
		mynet = new BayesStaticGraph(learning[0].length);
		names=namevar;
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning,testing);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning,testing);
	}

	public void setRestarts(int n){
		randomrst=n;
	}
	
	public String toString() {
		String s= new String();
		s="===Structure connectivity\n";
		for(int i=0; i<nvars;i++){
			s+="node "+names[i]+" at t+1: ";
			for(int j=0; j<mynet.getParents(i).length;j++){
				if(mynet.getParents(i)[j]<=nvars-1){
					s+= names[mynet.getParents(i)[j]]+" ";
				}
			}
			s+="at time-slice t\n";
		}
		s+="===Scores\n";
		Score scrLL = new ScoreLL(cfgs,learning,testing);
		Score scrMDL = new ScoreMDL(cfgs,learning,testing);
		s+="LL score: "+scrLL.getScore(mynet)+"\n";
		s+="MDL score:  "+scrMDL.getScore(mynet)+"\n"; 
		return s;
	}
	
	private BayesStaticGraph bestAdd(){
		BayesStaticGraph intra;
		BayesStaticGraph best = mynet.clone();
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				if(i!=j){
					intra=mynet.clone();
					intra.add(i,j);
					if(scr.getScore(intra)>scr.getScore(best)) best=intra;
				}
			}
		}
		return best;
	}
	
	public BayesStaticGraph bestRemove(){
		if(!mynet.isEmpty()){
			BayesStaticGraph intra;
			BayesStaticGraph best = mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					if(i!=j){
						intra=mynet.clone();
						intra.remove(i,j);
						if(scr.getScore(intra)>scr.getScore(best)) best=intra;
					}
				}
			}
		}
		return mynet;
	}

	private BayesStaticGraph bestReverse(){
		if(!mynet.isEmpty()){
			BayesStaticGraph intra;
			BayesStaticGraph best = mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					if(i!=j){
						intra= mynet.clone();
						intra.reverse(i,j);
						if(scr.getScore(intra)>scr.getScore(best)) best=intra;
					}
				}
			}
			return best;
		}
		return mynet;
	}
	
	private BayesStaticGraph makeRandomOP() {
		// TODO Auto-generated method stub
		BayesStaticGraph grp=mynet.clone();
		Random rd = new Random();
		if(mynet.isEmpty()){
			grp.add(rd.nextInt(nvars),rd.nextInt(nvars));
		}
		else{
			int op=rd.nextInt(3);
			switch(op){
			 case 0:
				 grp.add(rd.nextInt(nvars),rd.nextInt(nvars));
				 break;
			 case 1:
				 grp.remove(rd.nextInt(nvars),rd.nextInt(nvars));
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
		int restarts=0;
		Random rd = new Random();
		BayesStaticGraph[] neighbours = new BayesStaticGraph[3];
		double bestscore=Double.NEGATIVE_INFINITY;
		BayesStaticGraph best=mynet.clone();
		BayesStaticGraph previous;
		boolean flag=true;
		do{
			previous = best;
			neighbours[0] = bestAdd();
			neighbours[1] = bestRemove();
			neighbours[2] = bestReverse();
			for (BayesStaticGraph grp : neighbours) {
				if(scr.getScore(grp)>bestscore) 
				{
					best=grp;
					bestscore=scr.getScore(grp);
				}
			}
			if(scr.getScore(best)>scr.getScore(previous)) mynet=best;
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

}
