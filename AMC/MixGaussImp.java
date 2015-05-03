import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



public class MixGaussImp implements MixGauss{
	
	Gauss[] mixtu;//Vamos dar o nome de mixtu a uma gaussiana
	int length;//Inteiro que corresponde ao numero de misturas 
	ArrayList<double[]> a;//Corresponde ao conjunto de parametros theta. Trata-se de uma ArrayList porque se precisava de uma lista dinamica para poder acrescentar linhas a matriz. Como temos double[] no interior da lista dinamica isto quer dizer que corresponde a um conjunto de parametros de uma gaussiana. E uma lista estatica porque nao se irao acrescentar parametros a esta lista. Temos assim uma matriz com n linhas (numero de gaussianas) e em cada linha o conjunto de parametros de uma so gaussiana.

	public ArrayList<double[]> aa(){
		return a;
	}
	
	public void mix(int n,ArrayList<double[]> para){//mix e um metodo construtor que recebe o numero de misturas (o inteiro "n") e um conjunto de parametros theta que denominamos "para"
		length=n;
		mixtu = new Gauss[n];//Criou-se uma mistura de gaussianas de tamanho n, ou seja, com n gaussianas
		for(int i=0;i<length;i++){//Para cada i (linha) adicionam-se os parametros e vamos aumentando o i assim que se adicionam os parametros a linha. Este i comeca em zero e termina em n.
			Gauss aux= new Gauss(para.get(i)[0],para.get(i)[1],para.get(i)[2],para.get(i)[3],para.get(i)[4],para.get(i)[5]);//Esta gaussiana corresponde a primeira linha da nossa matriz final. Tem de se utilizar o comando para.get(i)[j] porque se trata de um ArrayList. Vai buscar a linha i, o valor que esta na coluna j (ou seja o parametro da gausssiana)
			mixtu[i]=aux;//Definimos uma nova aux e de seguida aumenta-se o i
		}
	}//No final deste programa obtem-se uma matriz com n linhas (numero de gaussianas) e em cada linha os parametros da gaussiana (vai ter 5 colunas que correspondem aos parametros a1,a2,b1,b2,sigma e w)
	
	public Gauss gau(int i){
		return mixtu[i];//Para dar apenas uma gaussiana (a i-esima), retira-se o elemento i da lista mixtu
	}
	
	public double prob(double[][] tl){//Recebe uma lista de pontos ao longo dos tempos (que aqui designamos por "tl", que e uma matriz double)e retorna a probabilidade dessa lista de pontos ser observada pela mistura. Esta matriz "tl" tem apenas duas linhas, a primeira refere-se aos tempos e a segunda aos pontos associados. Ou seja, numa certa coluna j temos na primeira linha o tempo e na segunda o ponto associado.
		double pr =0;//Inicialmente a probabilidade de observar a lista de pontos na mistura e nula
		double wtot=0;//Ainda nao temos qualquer peso das gaussianas. "wtot" designa a soma do peso das gaussianas.
		for(int w=0;w<length;w++){//Para cada w ve-se a matriz gaussiana associada e depois aumenta-se w em uma unidade ate chegarmos a ultima gaussiana
			Gauss aux = mixtu[w];
			wtot+=aux.w;
		}
		for(int i=0;i<length;i++){
			double pi=1;
			Gauss aux = mixtu[i];
			for(int j=0;j<tl[0].length;j++){
				double ul=-Math.pow(tl[1][j]-aux.afirst*Math.exp(-aux.bfirst*tl[0][j])-aux.asecond*Math.exp(-aux.bsecond*tl[0][j]),2);//Formula do calculo da funcao f
				pi*=1/Math.pow(2*Math.PI*aux.sigma,0.5)*Math.exp(ul/(2*aux.sigma));//Formula da probabilidade
			}
			pr+=(aux.w/wtot)*pi;//Multiplica-se a probabilidade o peso normalizado
		}
		//System.out.println(pr);
		return pr;//Retorna a probabilidade da lista de pontos ser observada na mistura
	}
	
	public double[][] theta(){//Retorna a lista de parametros actual
		double[][] lista = new double[length][6];//Matriz de "length" linhas e 6 colunas (que sao os parametros)
		for(int i=0;i<length;i++){
			Gauss aux=mixtu[i];//Define-se que a funcao aux e a mixtu[i], ou seja, a i-esima gaussiana
			lista[i][0]=aux.afirst;
			lista[i][1]=aux.asecond;
			lista[i][2]=aux.bfirst;
			lista[i][3]=aux.bsecond;
			lista[i][4]=aux.sigma;
			lista[i][5]=aux.w;
			System.out.print(aux.afirst);
			System.out.print("     ");
			System.out.print(aux.asecond);
			System.out.print("     ");
			System.out.print(aux.bfirst);
			System.out.print("     ");
			System.out.print(aux.bsecond);
			System.out.print("     ");
			System.out.print(aux.sigma);
			System.out.print("     ");
			System.out.print(aux.w);
			System.out.println();
		}
		return lista;//No final da o conjunto de parametros
	}
	
	public void update(Gauss[] l){//Metodo que recebe uma lista de parametros theta (ou seja uma gaussiana denominada "l")e actualiza a mesma
		mixtu=l;//Definimos uma nova mistura "mixtu" com o conjunto de parametros "l" (fazemos uma atribuicao)
	}
	
	public int length(){//Programa que da o tamanho da mistura das gaussianas (ou seja o numero das gaussianas)
		return length;
	}
	
	
	public void mixReader (String s) throws IOException {
		
		BufferedReader commaFile = new BufferedReader (new FileReader(s));
		String dataRow = commaFile.readLine();//Nao interessam as palavras que vem no inicio do ficheiro
		a = new ArrayList<double[]>();
		double[] thetaj;
		
		while (dataRow!=null){
			String [] data = dataRow.split (",");//Colunas separadas por ","
			int j=0;
			thetaj = new double[6];
				while (j<data.length) {
					if(j==0){
						thetaj[j] = Double.parseDouble(data[j]);
						j++;
					}else{
					thetaj[j+1] = Double.parseDouble(data[j]);
					j++;
					}
				}
				dataRow = commaFile.readLine();//Mudar de linha (passar para a proxima)
				thetaj[1]=-thetaj[0];
				thetaj[4]=1;
				a.add(thetaj);
		}
		commaFile.close();
		for(int x =0;x<a.size();x++){
			double M =a.size();
			a.get(x)[5]=1/M;
		}
		
//		for(int x =0;x<a.size();x++){
//			System.out.println();
//			for(int c=0;c<6;c++){
//				System.out.print(a.get(x)[c]+"     " );
//			}
//		}
	}
	
}
