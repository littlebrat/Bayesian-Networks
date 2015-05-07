package bayes;

import java.io.IOException;
import java.util.Arrays;
import dag.AdjacencyList;
import dag.Graph;

public class BayesDyn implements BayesianNetwork{
	protected BayesTransitionGraph mynet;
	protected Score scr;
	private int nvars;
	
	public BayesDyn(Data d,String s){
		int [][] learning = d.getTraining();
		int [][] testing = d.getTesting();
		Configurations cfgs = new Configurations(learning);
		nvars=learning[0].length/2;
		mynet = new BayesTransitionGraph(learning[0].length);
		
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning,testing);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning,testing);
		else throw new IOException("The chosen type of score is neither of the availables scores.");
	}

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
		//Nao faz muito sentido criares copias do bayesnet, era melhor copias do grafo?
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
		//Nao faz muito sentido criares copias do bayesnet, era melhor copias do grafo?
		BayesTransitionGraph intra = mynet;
		BayesTransitionGraph best = mynet;
		
		for (int i = 0; i < nvars; i++) {
			intra=mynet;
			for (int j = 0; j < nvars; j++) {
				intra.add(i,j);
				if(scr.getScore(intra)>scr.getScore(best)) best=intra;
			}
		}
		return best;
	}
	
	public void greedyHill() {
		// TODO Auto-generated method stub
		/*Graph res = grp;
		Graph aux = res;
		Graph neighbour;
		//precisamos de ver se a aresta foi mesmo adicionada, caso contrario, nao e necessario calcular o score
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
