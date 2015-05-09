package bayes;

import java.util.ArrayList;
//tem cuidado com os tipos de dados se e int/double
public abstract class Score {
	protected Counts[] varcounts;
	protected static double pseudo = 0.5;
	protected Configurations cfg;
	protected int[][] learning;
	protected int[][] testing;
	
	protected Score(Configurations cfg,int[][] tolearn,int[][] totest){
		learning=tolearn;
		testing=totest;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
	}
	
	protected Score(Configurations cfg,int[][] tolearn,int[][] totest,int pseudo){
		learning=tolearn;
		testing=totest;
		this.cfg=cfg;
		varcounts = new Counts[cfg.size()];
		this.pseudo=pseudo;
	}
	
	protected double getLL(BayesTransitionGraph grp){
		double res = 0;
		int n,nj;
		for (Counts count : varcounts) {
			if(count.myparents.length>0){
				for (int j = 0; j < count.getq(); j++) {
					nj=count.getNij(j);
					for (int k = 0; k < cfg.ri(count.me); k++) {
						n=count.getNijk(j,k);
						res+=n*Math.log(n/nj);
					}
				}
			}
			else{
				nj=count.getNij(0);
				for (int k = 0; k < cfg.ri(count.me); k++) {
					n=count.getNijk(0,k);
					res+=n*Math.log(n/nj);
				}
			}
		}
		return res;
	}
	
	protected void makeCounts(BayesTransitionGraph grp){
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
				count.addcount(count.me,ocorrencia);
			}
		}
	}
	
	public abstract double getScore(BayesTransitionGraph grp);
}
