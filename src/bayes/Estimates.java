package bayes;

public class Estimates {
	double[][] paramTable;
	
	protected Estimates(int numcfgs,int numjs){
		// TODO Auto-generated constructor stub
		paramTable=new double[numcfgs][numjs];
	}
	
	protected void setParam(double p, int j, int k){
		paramTable[k][j]=p;
	}
}
