import java.io.IOException;
import java.util.ArrayList;


public interface MixGauss {
	
	void mix(int n,ArrayList<double[]> para);
	
	ArrayList<double[]> aa();
	
	Gauss gau(int i);
	
	double prob(double[][] tl);
	
	double[][] theta();
	
	void update(Gauss[] l);
	
	int length();
	
	void mixReader (String s) throws IOException;
	
	//void mixWriter (String s) throws IOException;
}
