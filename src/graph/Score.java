package graph;


public class Score {

	private double score;
	private static boolean mdl;
	private static boolean ll;
	public static double pseudo=0.5; //verificar se é variável
	protected Graph g;
	
	
	public Score(Graph g) {
		this.g=g;
		// TODO Auto-generated constructor stub
	}
	protected void setScore(String s){
		if(s.equals("MDL")){
			mdl=true;
			ll=false;
		}
		else if(s.equals("LL")){
			mdl=false;
			ll=true;
		}
		else{
			System.out.println("Not a valid scoring criteria!");
			System.exit(1); 
			// Em vez de exit fazer uma exception
		}
	}
	
	public double getScore(){
		if(mdl==true){
			score=scoreMDL();
		}else{
			score=scoreLL();
		}
		return score;
	}
	private int calcB(){
		int b=0;
		for (int i = 0; i <g.size(); i++) {
			b+=((g.get(i).config().length)-1)*g.()
			//ACABAR ISTO E VER CENAS DOS PARENTS!!!
		}
		return b;
		
	}
	private double scoreMDL(){
		double smdl=0;
		double sll=0;
		int b=0;
		int N=this.g.getTimeSample().count();
		sll=scoreLL();
		b=calcB();
		smdl=sll-1/2*Math.log10(N)*b;
		return smdl;
		
	}
	private double scoreLL(){
		double sll=0;
		
		return sll;
	}
}
