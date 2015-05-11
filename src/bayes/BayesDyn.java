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
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph inter = mynet;
		BayesTransitionGraph best = mynet;

		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.add(i,j);
				if(scr.getScore(intra)>scr.getScore(best)) best=intra;
			}
		}
		intra=best;
		best=mynet;
		for (int i = 0; i < nvars; i++) {
			inter=mynet;
			for (int j = 0; j < nvars; j++) {
				inter.addInter(i,j);
				if(scr.getScore(inter)>scr.getScore(best)) best=inter;
			}
		}
		inter=best;
		if(scr.getScore(inter)>scr.getScore(intra)) return inter;
		else return intra;
	}
	
	public BayesTransitionGraph bestRemove(){
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph inter = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.remove(i,j);
				if(scr.getScore(intra)>scr.getScore(best)) best=intra;
			}
		}
		intra=best;
		best=mynet;
		for (int i = 0; i < nvars; i++) {
			inter=mynet;
			for (int j = 0; j < nvars; j++) {
				inter.removeInter(i,j);
				if(scr.getScore(inter)>scr.getScore(best)) best=inter;
			}
		}
		inter=best;
		if(scr.getScore(inter)>scr.getScore(intra)) return inter;
		else return intra;
	}
	
	private BayesTransitionGraph bestReverse(){
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				if(i!=j){
					intra.reverse(i,j);
					if(scr.getScore(intra)>scr.getScore(best)) best=intra;
				}
			}
		}
		return best;
	}
	
	public void greedyHill() {
		// TODO Auto-generated method stub
		BayesTransitionGraph[] neighbours = new BayesTransitionGraph[3];
		double bestscore=Double.NEGATIVE_INFINITY;
		BayesTransitionGraph best=mynet;
		BayesTransitionGraph previous;
		boolean flag=true;
		do{
			previous = best;
			neighbours[0] = bestAdd();
			neighbours[1] = bestRemove();
			neighbours[2] = bestReverse();
			for (BayesTransitionGraph grp : neighbours) {
				if(scr.getScore(grp)>bestscore) best=grp;
			}
			if(scr.getScore(best)>scr.getScore(previous)) flag=true;
			else flag=false;
		}while(flag);
		mynet=best;
	}
	
	public static void main(String[] args) throws Exception{
		Data mydata = new DataTrain(args[0]);
		Data mytest = new DataTest(args[1]);
		int[][] learn = mydata.get();
		int[][] test = mytest.get();
		BayesDyn net = new BayesDyn(learn,test,"MDL");
		
		System.out.println(net.scr.getScore((net.mynet)));
		//net.greedyHill();
	}
}
