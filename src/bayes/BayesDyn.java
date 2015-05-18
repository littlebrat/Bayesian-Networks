package bayes;

import java.util.Random;

/**
 * Represents a  Dynamic Bayesian Network (implements the BayesianNetwork interface)
 * to be learned using Bayes Transition Graphs, meaning learning dependences between 
 * two time slices (t and t+1) which express two different relations: intra-temporal -
 * - dependences among random variables of time slice t+1 -  and inter-temporal - 
 * - dependence among random variables of time slice t and of time slice t+1  
 * To escape local maximum, besides the random restarts, a TABU list is used to prevent 
 * the revisiting of a recently seen graph. The GHC algorithm uses as stopping criteria
 * reaching a local maximum.
 * 
 * @author Sofia Silva
 * @author Tiago Ricardo
 * @author Nuno Mendes
 *
 */

public class BayesDyn implements BayesianNetwork{
	
	protected BayesTransitionGraph mynet;
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
	
	public BayesDyn(int[][] learning,String s,String[] namevar, int ntabu){

		tabu=new Tabu(ntabu);
		this.learning=learning;
		cfgs = new Configurations(learning);
		nvars=learning[0].length/2;
		mynet = new BayesTransitionGraph(learning[0].length);
		names=namevar;
		if(s.equals("MDL"))	scr = new ScoreMDL(cfgs,learning);
		else if(s.equals("LL")) scr = new ScoreLL(cfgs,learning);
	}
	
	/**
	 * String representation of the BayesDyn in terms of inter-temporal and 
	 * intra-temporal dependences - parents of each node (random variable)
	 */

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
		s+="===Intra-slice connectivity\n";
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
		s+="\n===Scores\n";
		Score scrLL = new ScoreLL(cfgs,learning);
		Score scrMDL = new ScoreMDL(cfgs,learning);
		s+="LL score: "+scrLL.getScore(mynet)+"\n";
		s+="MDL score:  "+scrMDL.getScore(mynet)+"\n";
		return s;
	}
	
	/**
	 * {@inheritDoc}
	 */

	public void setRestarts(int n){
		randomrst=n;
	}
	
	/**
	 * Computes all graphs obtained by adding an edge (representing intra and intra dependences) 
	 * to the previous graph and stores the one with the best score. If the resulting graph is 
	 * in the TABU list it is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */

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
	
	/**
	 * Computes all graphs obtained by removing an edge (representing intra and intra dependences) 
	 * to the previous graph and stores the one with the best score. If the resulting graph is 
	 * in the TABU list it is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */

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
	
	/**
	 * Computes all graphs obtained by reversing an edge (only intra-temporal dependences as 
	 * edges representing inter-temporal dependences can not be reversed) to the previous graph
	 * and stores the one with the best score. If the resulting graph is in the TABU list it 
	 * is not considered as it's score is not computed
	 * 
	 * @return		graph with the best score
	 */

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
	
	/**
	 * Makes one Random operation out of the possible 3- add, reverse and remove 
	 * an edge form the graph with random origin and destination node
	 * 
	 * @return	resulting graph after applying the random operation
	 */

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
	 * {@inheritDoc}.
	 * Computes the value of a certain future random variable (time slice t+1)
	 * given the values for the time slice t.
	 */

	public int[] getPredictions(int var,int[][] testing){
		return scr.getVarFromTests(nvars+var-1, testing);
	}
	
	/**
	 * {@inheritDoc}.
	 * Computes the values for all future random variables (time slice t+1)
	 * given the values for the time slice t.
	 */

	public int[][] getAllPredictions(int[][] testing){
		int[][] pred = new int[testing.length][nvars];
		for(int i=1;i<nvars+1;i++){
			int[] aux=getPredictions(i, testing);
			for(int j=0;j<aux.length;j++){
				pred[j][i-1]=aux[j];
			}
		}
		return pred;
	}

}
