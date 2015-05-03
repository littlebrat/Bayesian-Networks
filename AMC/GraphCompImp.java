
public class GraphCompImp implements GraphComp{
		
	Compartment[] grafo;
	int size;
	
	public void grafoo(int n){//Método construtor que recebe um natural n e retorna o grafo com n nos e sem arestas. Cada no representa um compartimento
		if(n>0){
		grafo = new Compartment[n];
		size=n;//O grafo é formado por n nos sem qualquer aresta
		}
	}
	
	public void showLinks(){//Mostrar as ligacoes (arestas) entre nos. E nas arestas que estao as informacoes
		Compartment aux;
		int i=0;
		while(i<size){
			aux=grafo[i];
			System.out.println("Node "+i+" is linked to:");//Devolve o no em que se encontra
			System.out.println();
			while(aux!=null){//Para cada no', retorna o seu nu'mero e os parametros das misturas gaussianas
				System.out.println("Node "+ aux.no + ";   a1: "+ aux.afirst+";   a2: "+aux.asecond+";   b1: "+aux.bfirst+";   b2: "+aux.bsecond+";   sigma: "+aux.sigma+";   w: "+aux.w);
				aux=aux.next;//Devolve o no a que se liga e os parametros do compartimento
			}
			System.out.println();
			i++;
		}
	}

	public void add_edge(int origin,int end){//Recebe dois nos e adiciona ao grafo uma aresta de um no para o outro com as estimativas inicial para uma mistura Gaussiana entre estes compartimentos
		if(origin>-1 && origin<size && end>-1 && end<size && end!=origin){//Esta guarda protege contra nos que possam nao existir para esse grafo
			boolean existe = false;
			Compartment aux = grafo[origin];//aux e definido como o compartimento associado a origem
			while(aux!=null && existe==false){//Percorre os nos do grafo
				if(aux.no==end){
					existe=true;
					return;
				}
				aux=aux.next;//Este while e if permitem determinar se a aresta ja existe, e se esta existir o programa simplesmente nao faz nada
			}
			
			Compartment newEdge = new Compartment(end);//Cria um novo compartimento com o no "end" (se a aresta nao existir)
			newEdge.next=grafo[origin];//Ligacao entre o "end" e o "origin" (criacao da aresta)
			grafo[origin]=newEdge;//O ponto final da aresta que tem origem em "origin" e' o newEdge (que e' o "end")
		}
	}
	
	public void remove_edge(int origin,int end){//Recebe dois nos e retira ao grafo uma aresta de um no para outro
		if(origin>-1 && origin<size && end>-1 && end<size && end!=origin){//Guarda semelhante a guarda do "add_edge"
			Compartment aux = grafo[origin];
			if(aux.no==end){
				grafo[origin]=aux.next;//Nova atribuicao
				return;//Se o no de destino "end" for logo o primeiro, a origem "origin" passa a ser logo o proximo
			}
			Compartment previous = aux;
			aux=aux.next;
			while(aux!=null){
				if(aux.no==end){
					previous.next=aux.next;//Elimina o compartimento, o proximo de previous passa a ser o proximo do compartimento eliminado "aux". Desaparece "aux"
					return;
				}
				previous=previous.next;
				aux=aux.next;//Para o ciclo while continuar
			}
		}
	}
	
	public void up_edge(int origin,int end,double af,double as,double bf,double bs,double si,double wi){//Recebe dois nos modifica a estimativa da mistura de gaussianas entre estes compartimentos
		if(origin>-1 && origin<size && end>-1 && end<size && end!=origin){
			Compartment aux = grafo[origin];
			while(aux!=null){
				if(aux.no==end){
					aux.afirst=af;
					aux.asecond=as;
					aux.bfirst=bf;
					aux.bsecond=bs;
					aux.sigma=si;
					aux.w=wi;
					return;//Ao atingir o no de destino os parametros dos compartimentos vao ser modificados com os valores recebidos previamente
				}
				aux=aux.next;
			}
		}
	}
	
}
