package bayes;

import data.Data;
import data.DataTest;
import data.DataTrain;

public class BayesDyn implements BayesianNetwork{
	protected BayesTransitionGraph mynet;
	protected Score scr;
	private int nvars;
	
	public BayesDyn(int[][] learning,int[][] testing,String s) throws Exception{
		
		Configurations cfgs = new Configurations(learning);
		nvars=learning[0].length/2;
		mynet = new BayesTransitionGraph(learning[0].length);
		
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning,testing);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning,testing);
		else throw new Exception("The chosen type of score is neither of the availables scores.");
	}

	public String toString() {
		String r = new String();
		r="Network: \n"+mynet.toString();
		r=r+"\n" +"Score: "+ scr.getScore(mynet);
		return r;
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
					if(scr.getScore(intra)>scr.getScore(best)) best=intra;
				}
			}
		}
		intra=best;
		best=mynet.clone();
		for (int i = 0; i < nvars; i++) {
			for (int j = 0; j < nvars; j++) {
				inter=mynet.clone();
				inter.addInter(i,j);
				if(scr.getScore(inter)>scr.getScore(best)) best=inter;
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
						if(scr.getScore(intra)>scr.getScore(best)) best=intra;
					}
				}
			}
			intra=best;
			best=mynet.clone();
			for (int i = 0; i < nvars; i++) {
				for (int j = 0; j < nvars; j++) {
					inter=mynet.clone();
					inter.removeInter(i,j);
					if(scr.getScore(inter)>scr.getScore(best)) best=inter;
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
						if(scr.getScore(intra)>scr.getScore(best)) best=intra;
					}
				}
			}
			return best;
		}
		return mynet;
	}
	
	public void greedyHill() {
		// TODO Auto-generated method stub
		double timetobuild = System.currentTimeMillis();
		BayesTransitionGraph[] neighbours = new BayesTransitionGraph[3];
		double bestscore=scr.getScore(mynet);
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
			if(scr.getScore(best)>scr.getScore(previous)) mynet=best;
			else flag=false;
		}while(flag);
		timetobuild = System.currentTimeMillis()-timetobuild;
		System.out.println(timetobuild/1000+" seconds");
		scr.makeEstimates();
	}
	
	public static void main(String[] args) throws Exception{
		Data mydata = new DataTrain(args[0]);
		Data mytest = new DataTest(args[1]);
		int[][] learn = mydata.get();
		int[][] test = mytest.get();
		BayesDyn mamen = new BayesDyn(learn,test,"LL");
		mamen.greedyHill();
		System.out.println(mamen);
		
	}
}
