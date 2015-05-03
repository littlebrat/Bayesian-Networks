
public interface ProbFunctions {
	
	double expectConc(double a1,double a2,double b1,double b2,double t);
	
	double probCond(double sigma,double a1,double a2,double b1,double b2,double[][] tl);
	
	double Xij(Gauss g,MixGauss mix,double[][] tl);
	
	double[][] matXij(Sample s,MixGauss mix);
	
	void showXij(double[][] mat);
	
//	double estimateW(Sample s,Gauss j,MixGauss mix);
	
	double estimateW(double[][] Xij,int j);
	
	double estimateSigma(Sample s,double[][] keep,int j,double a1,double b1,double b2);
	
	double estimateAj(Sample s,double[][] keep,int j,double b1,double b2);
	
	double estimategb1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	double estimatedgb1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	double estimategb2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	double estimatedgb2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	double newtonMethod1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	double newtonMethod2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma);
	
	boolean checkG(double[][] tab);
	
	MixGauss Algorithm(Sample s,MixGauss mix);

}
