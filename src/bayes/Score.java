package bayes;

import java.util.ArrayList;
import java.util.List;

/**
 * Score is an abstract class that provides implementation for calculating scores, estimates and counts.
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo 
 */

public abstract class Score {
	protected Counts[] varcounts;
	protected Estimates[] ests;
	protected static double pseudo = 0.5;
	protected Configurations cfg;
	protected int[][] learning;
	
	/**
	 * Constructor method that builds an object of this class, providing a setup for it.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 */
	protected Score(Configurations cfg,int[][] tolearn){
		learning=tolearn;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
	}
	
	/**
	 * Constructor method that setups the score with a customized pseudo score.
	 * @param cfg Configurations of the all the variables of the network.
	 * @param tolearn Learning data set made with a 2-dimensional array of integers.
	 * @param pseudo parameter customizable to the user that serves as a way of customizing the estimate calculation.
	 */
	@SuppressWarnings("static-access")
	protected Score(Configurations cfg,int[][] tolearn,int pseudo){
		learning=tolearn;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
		this.pseudo=pseudo;
	}
	
	/**
	 * This method calculates the LL score of the graph passed as parameter.
	 * @param grp Network
	 * @return double precision value with the score for the network.
	 */
	protected double getLL(BayesGraph grp){
		double res = 0;
		double n,nj;
		for (Counts count : varcounts) {
			if(count.myparents.length>0){
				for (int j = 0; j < count.getq(); j++) {
					nj=count.getNij(j);
					for (int k = 0; k < cfg.ri(count.me); k++) {
						n=count.getNijk(j,k);
						if(n!=0) 
							res+=n*Math.log(n/nj)/Math.log(2);
					}
				}
			}
			else{
				nj=count.getNij(0);
				for (int k = 0; k < cfg.ri(count.me); k++) {
					n=count.getNijk(0,k);
					if(n!=0)
						res+=n*Math.log(n/nj)/Math.log(2);
				}
			}
		}
		return res;
	}
	
	/**
	 * Method that builds the association between the Score object and the Counts objects.
	 * @param grp Network of the variables.
	 */
	
	protected void makeCounts(BayesGraph grp){
		ArrayList<int[]> fathers = new ArrayList<int[]>();
		// construir a lista das configuracoes dos pais e iniciar os objectos das counts
		for (int i = 0; i < grp.size(); i++) {
			fathers.add(grp.getParents(i));
			varcounts[i]=new Counts(i,fathers.get(i),cfg);
		}
		int[] temp;
		int[] ocorrencia;
		//fazer update das contagens linha a linha
		for (int i = 0; i < learning.length; i++) {
			//para cada variavel atualizar a sua contagem
			for (Counts count : varcounts) {
				//buscar os pais dessa variavel
				temp=fathers.get(count.me);
				ocorrencia= new int[temp.length];
				for (int entrada = 0; entrada < temp.length; entrada++) {
					ocorrencia[entrada]=learning[i][temp[entrada]];
				}
				count.addcount(learning[i][count.me],ocorrencia);
			}
		}
	}
	
	/**
	 * Calculates and returns the score of the network.
	 * @param grp Network of the variables.
	 * @return double precision value with the score of the network.
	 */
	
	public abstract double getScore(BayesGraph grp);
	
	/**
	 * Calculates an estimate (theta) if provided an index for a variable 'i', its parents global configuration 'j' and its value 'k'.
	 * @param i variable index.
	 * @param j global configuration for the parents configuration.
	 * @param k value for the variable 'i'.
	 * @return the theta for this parameters.
	 */
	
	protected double getParameter(int i, int j, int k){
		double res;
		double nk=varcounts[i].getNijk(j, k);
		double nj=varcounts[i].getNij(j);
		res=(nk+pseudo)/(nj+cfg.ri(i)*pseudo);
		return res;
	}
	
	/**
	 * Builds the association between the Score object and the Estimates objects.
	 */
	
	protected void makeEstimates(){
		ests=new Estimates[varcounts.length];
		for (int i = 0; i < varcounts.length; i++) {
			ests[i]=new Estimates(cfg.ri(i),varcounts[i].getLengthJ(),varcounts[i].myparents,varcounts[i].cfgs);
			for (int j = 0; j < varcounts[i].getLengthJ(); j++) {
				for (int k = 0; k < cfg.ri(i); k++) {
					ests[i].setParam(getParameter(i,j,k),j,k);
				}
			}
		}
	}
	
	/**
	 * Gets the estimate specified by the parameters
	 * @param i variable index.
	 * @param occur value combination for parent variables.
	 * @param k value for the variable 'i'.
	 * @return double precision value for its estimate.
	 */
	private double getTheta(int i,int[] occur,int k){
		return ests[i].getParam(occur, k);
	}
	
	/**
	 * Given a restriction array, it produces a table with all the possible combination of free variables values.
	 * @param restric Integer restriction array
	 * @return a table of all possible combination of values with fixed variables.
	 */
	
	private int[][] getAllEvents(int[] restric){
		int lines=1;
		List<Integer> js = new ArrayList<Integer>();
		for (int i = 0; i < restric.length; i++) {
			if(restric[i]==-1){// se for igual a -1 quer dizer que é uma variavel livre
				lines*=cfg.ri(i);
				js.add(cfg.ri(i));
			}
		}
		int[][] res = new int[lines][restric.length];
		for (int i = 0; i < lines; i++) {
			int[] glob=TensorToArray.getLocal(i,js);
			for (int j=0,k=0; j < restric.length; j++) {
				if(restric[j]!=-1){
					res[i][j]=restric[j];
				}
				else{
					res[i][j]=glob[k];
					k++;
				}
			}
		}
		return res;
	}
	
	/**
	 * Give a line of occurrences it calculates the probability of that event to occur.
	 * @param line array of integers with values of variables
	 * @return double precision value with its probability
	 */
	
	private double lineProbability(int[] line){
		double res=1;
		int[] fathers;
		int[] jvalues;
		for (int j = line.length/2; j < line.length; j++) {
			fathers=ests[j].myparents;
			jvalues= new int[fathers.length];
			for (int i = 0; i < fathers.length; i++) {
				jvalues[i]=line[fathers[i]];
			}
			res*=getTheta(j,jvalues,line[j]);
		}
		return res;
	}
	
	/**
	 * It calculates the probability that a variable 'var' has of having a value 'k'.
	 * @param var index of wanted variable.
	 * @param k value of the variable.
	 * @param test array of integers corresponding to occurrences.
	 * @return double precision value with its probability
	 */
	
	private double getVarProb(int var,int k,int[] test){
		double res=0;
		int[] mytest = new int[2*test.length];
		for (int i = 0; i < 2*test.length; i++) {
			if(i<test.length){
				mytest[i]=test[i];
			}
			else if(i==var){
				mytest[i]=k;
			}
			else{
				mytest[i]=-1;
			}
		}
		int[][] table = getAllEvents(mytest);
		for (int i = 0; i < table.length; i++) {
			res+=lineProbability(table[i]);
		}
		return res;
	}
	
	/**
	 * Get the most probable value that the variable 'var' has.
	 * @param var index of wanted variable.
	 * @param test array of integers corresponding to occurrences.
	 * @return integer value of the 'var'.
	 */
	
	private int getVarValue(int var,int[] test){
		int value=0;
		double bestprob=-1;
		double score;
		for (int k = 0; k < cfg.ri(var); k++) {
			score=getVarProb(var,k,test);
			if(score>bestprob){
				value=k;
				bestprob=score;
			}
		}
		return value;
	}
	
	/**
	 * Get all the most probable values of the variable 'var' on the 'testing' data set
	 * @param var index of wanted variable.
	 * @param testing data set.
	 * @return array of integers with the inferred values.
	 */
	
	protected int[] getVarFromTests(int var, int[][] testing){
		int[] res = new int[testing.length];
		for (int i = 0; i < testing.length; i++) {
			res[i]=getVarValue(var,testing[i]);
		}
		return res;
	}
}
