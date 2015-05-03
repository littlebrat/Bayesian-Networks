import java.io.IOException;




public interface Sample {
	
	void add(VetorPharma vet);
	
	int length();
	
	VetorPharma element(int p);
	
	CoupleList indice(int i);
	
	void join(Sample s);
	
	void showSample();
	
	double kIndex();

	void sampleReader (String s) throws IOException ;
}
