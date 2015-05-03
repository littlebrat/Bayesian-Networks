
public class VetorPharma {
	double index;
	double time;
	double value;
	
	VetorPharma next;
	
	VetorPharma(double i,double t,double v){
		index=i;
		time=t;
		value=v;
		next=null;
	}
}
//Cada vetor e caracterizado por um indice, tempo e valor (tem tres campos). O next e nulo ao inicio.