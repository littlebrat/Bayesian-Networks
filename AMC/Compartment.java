
public class Compartment {
	int no;
	double afirst,asecond,bfirst,bsecond,w,sigma;
	Compartment next;
	
	Compartment(int ni){
		no=ni;
		afirst=0;
		asecond=0;
		bfirst=0;
		bsecond=0;
		w=0;
		sigma=0;
		next=null;
	}
}
