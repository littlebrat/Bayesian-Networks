package bayes;

import java.util.Arrays;

import dag.AdjacencyList;
import dag.Graph;

public class BayesDyn implements BayesianNetwork{
	protected BayesTransitionGraph mynet;
	protected Score scr;
	protected int[][] learning;
	protected int[][] testing;
	protected Configurations cfgs;
	private int nvars;
	
	public BayesDyn(Data d,String s){
		if(s.equals("MDL"))	scr = new ScoreMDL();
		else if(s.equals("LL")) scr = new ScoreLL();
		
		learning = d.getTraining();
		testing = d.getTesting();
		
		cfgs = new Configurations(learning);
		mynet = new BayesTransitionGraph(learning[0].length);
		
		/*Faltam coisas*/
	}

	
	
	@Override
	public String toString() {
		String r = new String();
		r="Network: \n"+mynet.toString();
		r=r+"\n" +"Score: "+ scr.toString();
		return r;
	}
	
	private BayesTransitionGraph bestAdd(){
		//Nao faz muito sentido criares copias do bayesnet, era melhor copias do grafo?
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph inter = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.add(i,j);
				if(score(intra)>score(best)) best=intra;
			}
		}
		intra=best;
		best=mynet;
		for (int i = 0; i < nvars; i++) {
			inter=mynet;
			for (int j = 0; j < nvars; j++) {
				inter.addInter(i,j);
				if(score(inter)>score(best)) best=inter;
			}
		}
		inter=best;
		if(score(inter)>score(intra)) return inter;
		else return intra;
	}
	
	public BayesTransitionGraph bestRemove(){
		//Nao faz muito sentido criares copias do bayesnet, era melhor copias do grafo?
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph inter = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.remove(i,j);
				if(score(intra)>score(best)) best=intra;
			}
		}
		intra=best;
		best=mynet;
		for (int i = 0; i < nvars; i++) {
			inter=mynet;
			for (int j = 0; j < nvars; j++) {
				inter.removeInter(i,j);
				if(score(inter)>score(best)) best=inter;
			}
		}
		inter=best;
		if(score(inter)>score(intra)) return inter;
		else return intra;
	}
	
	private BayesTransitionGraph bestReverse(){
		//Nao faz muito sentido criares copias do bayesnet, era melhor copias do grafo?
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.add(i,j);
				if(score(intra)>score(best)) best=intra;
			}
		}
		return best;
	}
	
	
	@Override
	public void greedyHill() {
		// TODO Auto-generated method stub
		/*Graph res = grp;
		Graph aux = res;
		Graph neighbour;
		
		while(score(aux)<score(neighbour)){
			if(score(res))
		}
		*/
	}
	
	public static void main(String[] args){
		//BayesianNetwork lol = new BayesDyn(data,"MDL");
		String principal = new String("Hello!");
		String aux = principal;
		aux=aux+"e";
		System.out.println(principal);
		System.out.println(aux);
	}
}
