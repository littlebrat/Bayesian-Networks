/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
import java.io.*;

public class ImpSample implements Sample{
	
	int size;//Tamanho do vetor
	private VetorPharma first;//Primeiro vetor VetorPharma 
	
	public void showSample(){//Programa para mostrar a amostra que temos e o seu tamanho
		System.out.println("The size of your sample is:" + size);
		System.out.println("Your sample is:");
		int j=0;
		while(j<this.length()){//A funcao .this invoca a amostra onde nos encontramos
		VetorPharma aux= this.element(j);
		System.out.println("index: "+ aux.index + ";   time: "+ aux.time+";   value: "+aux.value);
		j++;
		}
	}
	
	public void add(VetorPharma vet){//Recebe um vetor "vet" e acrescenta o vetor a amostra, atencao que se quer ordenar por indice
		VetorPharma bin=first;
		if(bin==null || bin.index>vet.index){//Caso em que nao ha vectores definidos ou caso em que o indice do vector inserido "vet" e logo menor ao indice de first (ou "bin")
			vet.next=first;//O proximo do "vet" liga-se ao primeiro. Criacao da ligacao
			first=vet;//O primeiro passa a ser o vet (e o segundo e o bin)
			size++;
			return;	
		}
		while(bin.next!=null && bin.next.index<vet.index)//Enquanto o proximo do "bin" for diferente de zero e o indice do "bin.next" for menor que o do "vet"
			bin=bin.next;//Altera no. Faz correr o ciclo
		vet.next=bin.next;//Cria ligacao
		bin.next=vet;//Cria ligacao
		size++;
		return;//O vetor "vet" fica ordenado e e' adicionado na amostra a seguir ao "bin.next"
	}
	
	
	public int length(){//Retorna o comprimento da amostra
		return this.size;
	}
	
	public VetorPharma element(int p){//Recebe uma posicao "p" e retorna o vetor da amostra
		int i=0;
		VetorPharma aux=first;
		while(aux!=null && i<p){
			aux=aux.next;//Corre todos os vetores ate encontrar o correspondente a posicao p
			i++;
		}
		return aux;//Ocorre um ciclo ate que i chegue a p, e retorna o vetor deste ultimo ciclo p
	}
	
	public CoupleList indice(int i){//Lista dinamica de pares. Retorna uma lista de pares (tempo, valor) para um dado indice
		CoupleList pares = new CoupleList();
		VetorPharma aux = first;
		while(aux!=null){
			if(aux.index==i){
				pares.insert(aux.time,aux.value);
			}//Sempre que encontrar um vector com o indice i, adiciona um par com o tempo e valor associados 'a lista de pares
			aux=aux.next;
		}
		return pares;
	}
	
	public void join(Sample s){//Recebe uma amostra e retorna uma nova amostra com as duas concatenadas
		int len = s.length();
		for(int i=0;i<len;i++){
		VetorPharma aux = new VetorPharma(s.element(i).index,s.element(i).time,s.element(i).value);//Novo vector formado a partir da amostra s
		this.add(aux);//Vai adicionando cada vector da amostra s a amostra onde nos encontramos (recurso ao programa add)
		}
	}
	
	public double kIndex(){//Numero de indices diferentes da amostra
		return this.element(size-1).index+1;//Retorna o indice+1 do penultimo elemento
	}
	
	public void sampleReader (String s) throws IOException {
		
		BufferedReader commaFile = new BufferedReader (new FileReader(s));
		String dataRow = commaFile.readLine();
		dataRow  = commaFile.readLine();
		
		
		while (dataRow!= null){
			String [] data = dataRow.split (";");//Colunas separadas por virgula
			double [] lista =new double[data.length];
			int i=0;
			
				while (i<data.length) {
					lista [i]= Double.parseDouble(data[i]);
					i++;
				}
				VetorPharma vet = new VetorPharma(lista[0],lista[1],lista[2]);
				this.add(vet);
				dataRow = commaFile.readLine();//Mudar de linha
		}
		commaFile.close ();
	}
	
}
