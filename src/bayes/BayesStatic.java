package bayes;

import java.util.Random;

/**
 * Represents a Simple Bayesian Network (implements the BayesianNetwork interface)
 * to be learned using Bayes Static Graphs, meaning learning dependences in the same time slice.  
 * To escape local maximum, besides the random restarts, a TABU list is used to prevent 
 * the revisiting of a recently seen graph.The GHC algorithm uses as stopping criteria
 * reaching a local maximum.
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 */

public class BayesStatic implements BayesianNetwork{
	
	protected BayesStaticGraph mynet;
	protected Score scr;
	private int nvars;
	private int randomrst=0;
	private String[] names;
	Tabu tabu;
	private int[][] learning;
	private Configurations cfgs;
	
	/**
	 * Creates a new BayesDyn object which initializes a TABU list, the training data to be used
	 * the configurations of the random variables given in the training set, the number of random variables,
	 * a new BayesTransitionNetwork object, the names of the random variables and a new Score object
	 *  
	 *   
	 * @param learning		the training data
	 * @param s				the type of score to be computed
	 * @param namevar		names of the random variables
	 * @param ntabu			maximum size of the TABU list
	 */
	
	public BayesStatic(int[][] learning,String s,String[] namevar,int ntabu){
		
		this.learning=learning;
		tabu=new Tabu(ntabu);
		cfgs = new Configurations(learning);
		nvars=learning[0].length;
		mynet = new BayesStaticGraph(nvars);
		names=namevar;
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public void setRestarts(int n){
		randomrst=n;
	}
	
	/**
	 * String representation of the BayesDyn in terms of dependences between 
	 * nodes - parents of each node (random variable)
	 */
	
	public String toString() {
		String s= new String();
		s="===Structure connectivity\n";
		for(int i=0; i<nvars;i++){
			s+=names[i]+": ";
			for(int j=0; j<mynet.getParents(i).length;j++){
				if(mynet.getParents(i)[j]<=nvars-1){
					s+= names[mynet.getParents(i)[j]]+",";
				}
			}
			s=s.substring(0,s.length()-1);
			s+="\n";
		}
		s+="\n===Scores\n";
		Score scrLL = new ScoreLL(cfgs,learning);
		Score scrMDL = new ScoreMDL(cfgs,learning);
		s+="LL score: "+scrLL.getScore(mynet)+"\n";
		s+="MDL score:  "+scrMDL.getScore(mynet)+"\n"; 
		return s;
	}
	
	/**
	 * Computes all graphs obtained by adding an edge to the previous 
	 * graph and stores the one with the best score. If the resulting graph is 
	 * in the TABU list it is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */
	
	private BayesStaticGraph bestAdd(){
		BayesStaticGraph intra;
		BayesStaticGraph best = mynet.clone();
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
		return best;
	}
	
	/**
	 * Computes all graphs obtained by removing an edge to the previous 
	 * graph and stores the one with the best score. If the resulting graph is 
	 * in the TABU list it is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */
	
	public BayesStaticGraph bestRemove(){
		if(!mynet.isEmpty()){
			BayesStaticGraph intra;
			BayesStaticGraph best = mynet.clone();
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
		}
		return mynet;
	}
	
	/**
	 * Computes all graphs obtained by reversing an edge to the previous 
	 * graph and stores the one with the best score. If the resulting graph is 
	 * in the TABU list it is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */

	private BayesStaticGraph bestReverse(){
		if(!mynet.isEmpty()){
			BayesStaticGraph intra;
			BayesStaticGraph best = mynet.clone();
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
	
	/**
	 * Makes one Random operation out of the possible 3- add, reverse and remove 
	 * an edge form the graph with random origin and destination node
	 * 
	 * @return	resulting graph after applying the random operation
	 */
	
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
	
	/**
	 * {@inheritDoc}.
	 * The GHC algorithm stars with a graph with no edges. At each iteration the algorithm computes the best scoring 
	 * neighbor (a graph that is created applying one of the 3 operations - add, remove or reverse) of the previous 
	 * best scoring graph. If a better scoring graph is found, it goes in the TABU list and a new iteration starts with 
	 * the new best graph as the one we want to compute the neighbors of. When no neighbor has a better score a new 
	 * random graph is generated with a random number of operations and new iterations are computed until a local maximum
	 * is achieved again. When the total number of restarts is reached the best scoring graph is stored.
	 */
	
	public void greedyHill() {
		// TODO Auto-generated method stub
		BayesStaticGraph aux;
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
			if(scr.getScore(best)>scr.getScore(previous)){
				mynet=best;
				aux=mynet.clone();
				tabu.add(aux);
			}
			else if(scr.getScore(best)==scr.getScore(previous) && restarts<randomrst){
				int ops=(rd.nextInt(nvars*3)+1);
				for(int i=0; i<ops;i++){
					mynet=makeRandomOP();
				}
				restarts++;
			}
			else flag=false;
		}while(flag);
		scr.makeEstimates();
		mynet=best;
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public int[] getPredictions(int var, int[][] testing) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * {@inheritDoc}
	 */

	@Override
	public int[][] getAllPredictions(int[][] testing) {
		// TODO Auto-generated method stub
		return null;
	}
}
