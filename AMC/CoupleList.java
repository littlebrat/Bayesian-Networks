
public class CoupleList {
	int size;
	 Par first;
	
	public void insert(double t,double v){
		Par newCouple= new Par(t,v);//Criacao de um par com o tempo e valor recebidos
		Par aux=first;
		if(aux==null || aux.time>t){//Se first for vazio, o par criado passa a equivaler a esse first; se o tempo do first for maior que o tempo do novo par, ocorre o mesmo o par criado passa a primeira posicao e o first fica na segunda
			newCouple.next=first;
			first=newCouple;
			size++;
			return;	
		}
		while(aux.next!=null && aux.next.time<t )
			aux=aux.next;
		newCouple.next=aux.next;
		aux.next=newCouple;//O newCouple fica inserido entre aux e aux.next,  em que a posicao deste vai sendo actualizada com os ciclos até que t seja menor que o tempo de aux, ficando ordenada com o tempo
		size++;
		return;
	}
	
	public int length(){//Comprimento da lista de pares
		return size;
	}
	
	public double[][] getList(){//Transformar a lista de pares numa matriz nx2
		double[][] tl = new double[2][size];
		int i=0;
		Par aux=first;
		
		while(aux!=null){
			tl[0][i]=aux.time; //Primeira linha corresponde aos tempos
			tl[1][i]=aux.value;//Segunda linha corresponde aos valores
			//System.out.println();
			aux=aux.next;
			i++;
		}
		return tl;
	}
	
	public void showCouple(){
		System.out.println("The size of your sample with the chosen index is:" + size);//Tamanho da lista
		System.out.println("Your sample is:");
		Par aux = first;
		while(aux!=null){
		System.out.println("time: "+ aux.time+";   value: "+aux.value);
		aux=aux.next;
		}//Retorna o tempo e valor de cada par ate atingir o ultimo par, ja' que o next do u'ltimo e' null
	}
	
}
