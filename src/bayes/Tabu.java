package bayes;

import java.util.LinkedList;

public class Tabu {
	
	
	private int maxnum;
	private LinkedList<BayesGraph> tabulist = new LinkedList<BayesGraph>();
	
	
	public Tabu(int maxnum) {
		// TODO Auto-generated constructor stub
		this.maxnum=maxnum;
	}
	
	public LinkedList<BayesGraph> getTabuList(){
		return tabulist;
		
	}
	
	public void add(BayesGraph graph){
		if(tabulist.size()==maxnum){
			tabulist.removeFirst();
		}
		tabulist.add(graph);
	}
}
