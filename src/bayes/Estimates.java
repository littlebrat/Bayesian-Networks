package bayes;

public class Estimates extends TensorToArray{
	double[][] paramTable;
	
	protected Estimates(int numcfgs,int numjs,int[] parents,Configurations cfgs){
		// TODO Auto-generated constructor stub
		this.cfgs=cfgs;
		this.myparents=parents;
		paramTable=new double[numcfgs][numjs];
	}
	
	protected void setParam(double p, int j, int k){
		paramTable[k][j]=p;
	}
	
	protected double getParam(int[] fathers,int k){
		if(fathers.length==0) return paramTable[k][0];
		else return paramTable[k][getGlobal(fathers)];
	}
}
