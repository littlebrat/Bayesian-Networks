import java.util.ArrayList;


public class ProbFunctionsImp implements ProbFunctions{
		
	public double expectConc(double a1,double a2,double b1,double b2,double t){
		double r=0;
		r=a1*Math.exp(-b1*t)+a2*Math.exp(-b2*t);
		return r;//Funcao f que queremos estimar
	}
	
	public double probCond(double sigma,double a1,double a2,double b1,double b2,double[][] tl){
		double r=0;//A funcao probCond da-nos o valor da probabilidade condicionada 
		double baixo;
		
		for(int i=0;i<tl[0].length;i++){//Para cada i (coluna) vai efetuar as seguintes accoes
			r += Math.pow((tl[1][i]-expectConc(a1,a2,b1,b2,tl[0][i])),2);//Somatorio de -(y-f)^2 (que sera util para o calculo da probabilidade)	
		}
		
		baixo=Math.pow((2*(Math.PI)*sigma),((-tl[0].length)/2));
		baixo=baixo*Math.exp(((-1)/(2*sigma))*r);
		return baixo;
	}
	
	public double Xij(Gauss g,MixGauss mix,double[][] tl){//Definiçao do Xij
		double r=0;
		double res=0;
			
		for(int u=0;u<mix.length();u++){//g e' uma gaussiana em particular e gau e' uma gaussiana de uma mistura
			r+=Math.exp(500)*mix.gau(u).w*probCond(mix.gau(u).sigma,mix.gau(u).afirst,mix.gau(u).asecond,mix.gau(u).bfirst,mix.gau(u).bsecond,tl);
		}//O programa gau(int i) da a i-esima gaussiana da mistura. Denominador de Xij. Somatorio dos pesos w multiplicados pela probabilidade p
			res=Math.exp(500)*g.w*probCond(g.sigma,g.afirst,g.asecond,g.bfirst,g.bsecond,tl);//Questao de multiplicar e dividir por exp(500) para obter resultados. Numerador de Xij. Os pesos w a multiplicar pelas probabilidades p 
		return res/r;
	}
	
	public double[][] matXij(Sample s,MixGauss mix){//Funcao que da a matriz cujas linhas sao os individuos e as colunas as gaussianas 
		int linhas = (int) s.kIndex();//kIndex e a funcao que devolve o numero de indices diferentes encontrados na amostra (o N da formula). Numero de individuos. O "(int)" e' para nao mudar tudo para int.
		double[][] mat = new double[linhas][mix.length()];
		
		for(int i=0;i<linhas;i++){
			double[][] tl=s.indice(i).getList();
			for(int j=0;j<mix.length();j++){
				mat[i][j]=Xij(mix.gau(j),mix,tl);//i e' os individuos e j as gaussianas 
			}
		}
		return mat;
	}
	
	public void showXij(double[][] mat){//Imprime os valores da matriz dos Xij na consola do utilizador
		for(int i=0;i<mat.length;i++){
			for(int j=0;j<mat[0].length;j++){
				System.out.print(mat[i][j]+"   ");
			}
			System.out.println();
		}
	}
	
	public double estimateW(double[][] Xij,int j){//Da-nos o valor da proxima iterada de w
		double r=0;

		for(int i=0;i<Xij.length;i++){
			r+=Xij[i][j];//Somatorio do Xij (matriz)
		}
		return r/(Xij.length);
	}
	
	public double estimateSigma(Sample s,double[][] keep,int j,double a1,double b1,double b2){//Da-nos o valor da proxima iterada da variancia
		double r=0;
		int K=keep.length;//keep e' o nome que se deu a matriz de Xij
		double x=0;
		
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			x+=keep[i][j]*tl[0].length;//Denominador da formula do sigma
			for(int l=0;l<tl[0].length;l++){
				r+=keep[i][j]*Math.pow((tl[1][l]-expectConc(a1,-a1,b1,b2,tl[0][l])),2);//Numerador da formula do sigma
			}
		}//Deixamos de designar a2 porque e' -a1
		
	
		return r/x;
	}
	
	public double estimateAj(Sample s,double[][] keep,int j,double b1,double b2){//Estimar o valor da proxima iterada de a
		double K=keep.length;
		double ra=0;
		double rb=0;
			
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			for(int l=0;l<tl[0].length;l++){
				rb+=keep[i][j]*tl[1][l]*(Math.exp(-tl[0][l]*b1)-Math.exp(-tl[0][l]*b2));
				ra+=keep[i][j]*Math.pow((Math.exp(-tl[0][l]*b1)-Math.exp(-tl[0][l]*b2)),2);
			}
		}
		return rb/ra;
	}
	
	public double estimategb1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){//Estimador de h(b1)
		double r=0;
		double K=keep.length;
		
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			for(int l=0;l<tl[0].length;l++){
			r+=keep[i][j]*tl[0][l]*Math.exp(-b1*tl[0][l])*(tl[1][l]-a1*(Math.exp(-b1*tl[0][l])-Math.exp(-b2*tl[0][l])));//Parte do somatorio	
			}
		}
		r*=-(a1)/(sigma);//Multiplicar pelo fator a1/variancia
		return r;
	}
	
	public double estimatedgb1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){//Estimador de h'(b1)
		double dr=0;
		double K=keep.length;
		
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			
			for(int l=0;l<tl[0].length;l++){
			dr+=keep[i][j]*Math.pow(tl[0][l],2)*Math.exp(-b1*tl[0][l])*(-tl[1][l]+a1*(2*Math.exp(-b1*tl[0][l])-Math.exp(-b2*tl[0][l])));	
			}
			
		}
		dr*=-((a1)/(sigma));
		return dr;
	}
	
	public double estimategb2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){//Estimador de h(b2)
		double r=0;
		double K=keep.length;
		
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			for(int l=0;l<tl[0].length;l++){
				r+=keep[i][j]*tl[0][l]*Math.exp(-b2*tl[0][l])*(tl[1][l]-a1*(Math.exp(-b1*tl[0][l])-Math.exp(-b2*tl[0][l])));	
			}
		}
		return ((a1)/(sigma))*r;
	}
	
	public double estimatedgb2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){//Estimador de h'(b2)
		double dr=0;
		double K=keep.length;
		
		for(int i=0;i<K;i++){
			double[][] tl=s.indice(i).getList();
			for(int l=0;l<tl[0].length;l++){
				dr+=keep[i][j]*Math.pow(tl[0][l],2)*Math.exp(-b2*tl[0][l])*(-tl[1][l]+a1*(Math.exp(-b1*tl[0][l])-2*Math.exp(-b2*tl[0][l])));	
			}
		}
		dr*=(a1/sigma);
		return dr;
	}
	
	public double newtonMethod1(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){
		int i=0;
		double inic=b1;
		double antes=inic;
		double ram=antes;
		double proximo;
		
		while(i<10000){//Enquanto nao fizer as 10000 iteracoes
		
			if(ram > 0 && ram < b2){
				if(estimategb1(s,keep,j,ram,b2,a1,sigma)==0){
					return ram;
				}else{
				antes=ram;
				ram=ram-(estimategb1(s,keep,j,ram,b2,a1,sigma))/(estimatedgb1(s,keep,j,ram,b2,a1,sigma));
				if(ram > 0 && ram < b2){
					proximo= ram-(estimategb1(s,keep,j,ram,b2,a1,sigma))/(estimatedgb1(s,keep,j,ram,b2,a1,sigma));
						if(Math.abs(proximo-ram)<=Math.abs(ram-antes)*Math.pow(10,-8)){
							return ram;
						}
					}
				}//Iterada do metodo de newton
			}else{//Caso 1, isto e', se a condicao de fronteira for violada, reinicia-se o metodo de Newton
				i=0;
				antes=inic;
				ram=inic;//Reinicio do metodo
				double B=-0.3;//Definicao de Beta
				while(i<10000){
					if(ram > 0 && ram < b2 && estimatedgb1(s,keep,j,ram,b2,a1,sigma)>B){//Caso 2, termina-se o ciclo do metodo de Newton se a derivada de h(b1) for maior que B=-0.3 ou os limites bdj sejam violados
						return ram;
					}else if(ram <= 0|| ram >= b2){//Caso 3, se os limites forem violados decrementa-se B=B-0.2 e voltamos a reiniciar o programa
						B-=0.2;
						i=0;
						antes=inic;
						ram=inic;//Reinicio do metodo
					}else if(ram > 0 && ram < b2 && B<-5){//Caso 4, quando o B e' menor que -5
						return ram;
					}else{
						if(ram > 0 && ram < b2 && estimategb1(s,keep,j,ram,b2,a1,sigma)==0){
							return ram;
						}else{
						antes=ram;
						ram=ram-estimategb1(s,keep,j,ram,b2,a1,sigma)/estimatedgb1(s,keep,j,ram,b2,a1,sigma);
						if(ram > 0 && ram < b2 ){
							proximo= ram-(estimategb1(s,keep,j,ram,b2,a1,sigma))/(estimatedgb1(s,keep,j,ram,b2,a1,sigma));
							if(Math.abs(proximo-ram)<=Math.abs(ram-antes)*Math.pow(10,-8)){
								return ram;
							}
					}
						i++;
						}
					}
				}
			}
			i++;
		}
		return ram;
	}
	
	
	
	public double newtonMethod2(Sample s,double[][] keep,int j,double b1,double b2,double a1,double sigma){//Exactamente o mesmo metodo so que para o b2 em vez de ser para o b1
		int i=0;
		double inic=b2;
		double antes=inic;
		double ram =inic;
		double proximo;
		
		while(i<10000){
			if(ram < 5 && ram>b1){
				if(estimategb2(s,keep,j,b1,ram,a1,sigma)==0){
					return ram;
				}else{
				antes=ram;
				ram=ram-(estimategb2(s,keep,j,b1,ram,a1,sigma))/(estimatedgb2(s,keep,j,b1,ram,a1,sigma));
				if(ram < 5 && ram>b1 ){
					proximo= ram-(estimategb2(s,keep,j,b1,ram,a1,sigma))/(estimatedgb2(s,keep,j,b1,ram,a1,sigma));
					if(Math.abs(proximo-ram)<=Math.abs(ram-antes)*Math.pow(10,-8)){
						return ram;
					}
				}
				}
			}else{
				i=0;
				antes=inic;
				ram=inic;//Reinicio do metodo
				double B=-0.3;
				while(i<10000){
					if(ram < 5 && ram>b1 && estimatedgb2(s,keep,j,b1,ram,a1,sigma)>B){
						return ram;
					}else if(ram >= 5 || ram <= b1){
						B-=0.2;
						i=0;
						antes=inic;
						ram=inic;//Reinicio do metodo
					}else if(ram < 5 && ram>b1 && B<-5){
						return ram;
					}else{
						if(ram < 5 && ram>b1 && estimategb2(s,keep,j,b1,ram,a1,sigma)==0){
							return ram;
						}else{
							antes=ram;
							ram=ram-(estimategb2(s,keep,j,b1,ram,a1,sigma))/(estimatedgb2(s,keep,j,b1,ram,a1,sigma));
							if(ram < 5 && ram>b1){
								proximo= ram-(estimategb2(s,keep,j,b1,ram,a1,sigma))/(estimatedgb2(s,keep,j,b1,ram,a1,sigma));
								if(Math.abs(proximo-ram)<=Math.abs(ram-antes)*Math.pow(10,-8)){
									return ram;
								}
							}
						i++;
						}
					}
				}
			}
			i++;
		}
		return ram;
	}
	
	public boolean checkG(double[][] tab){//Programa para parar o algoritmo quando deixar de verificar a condicao de convergencia (return false)
		
		boolean isIt=true;
		double now =tab[0][0];//Temos uma tabela com duas linhas, na primeira linha temos os Bd(k) ("now") e na segunda linha os Bd(k+1) ("after") para as colunas
		double after =tab[1][0];
		int i=0;
		
		while(i<tab[0].length &&isIt==true){
			now =tab[0][i];//B(k)
			after =tab[1][i];//B(k+1)
			if(Math.pow(after-now,2)>=0.000001){//Quando nao respeitar a condicao de convergencia, retorna false
				return false;
			}
			i++;
			
		}
		return isIt;
	}
	
	public MixGauss Algorithm(Sample s,MixGauss mix){
			int z=0;
			
			MixGauss thetaNow = new MixGaussImp();
			
			thetaNow = mix;
	
			double[][] b1 =new double[2][thetaNow.length()];
			double[][] b2 =new double[2][thetaNow.length()];
			double[] a1 = new double[thetaNow.length()];
			
			double[][] keen =matXij(s,mix);
			
			System.out.println("cheguei aqui!");
			
			for(int i=0;i<thetaNow.length();i++){//Construir as duas matrizes 2xn de parametros bnd
				Gauss aux = thetaNow.gau(i);
				b1[0][i]=aux.bfirst;//Definicao de b1, na primeira linha 
				b1[1][i]=newtonMethod1(s,keen,i,b1[0][i],aux.bsecond,aux.afirst,aux.sigma);//Na segunda linha esta a iterada seguinte do b1, ou entao, b(k+1) 
				a1[i]=estimateAj(s,keen,i,b1[1][i],aux.bsecond);
				b2[0][i]=aux.bsecond;
				b2[1][i]=newtonMethod2(s,keen,i,b1[1][i],b2[0][i],a1[i],aux.sigma);
			}
			
			
			while(checkG(b1)==false || checkG(b2)==false){
				System.out.println(z);
				ArrayList<double[]> newTheta = new ArrayList<double[]>();
				
				for(int j =0;j<thetaNow.length();j++){
					Gauss aux = thetaNow.gau(j);
					double[] newLine = new double[6];//Formacao de novas colunas
					newLine[0]=a1[j];//estimateAj(s,keen,j,aux.bfirst,aux.bsecond);
					System.out.print(newLine[0]+"  ");//Estimar a1
					newLine[1]=-newLine[0];System.out.print(newLine[1]+"  ");//Estimar a2 (que e' o -a1)
					newLine[2]= b1[1][j];System.out.print(newLine[2]+"  ");//Estimar b1
					newLine[3]= b2[1][j];System.out.print(newLine[3]+"  ");//Estimar b2
					newLine[5]=estimateW(keen,j);System.out.print(newLine[5]+"  ");//Estimar peso w
					newLine[4]=estimateSigma(s,keen,j,newLine[0],newLine[2],newLine[3]);System.out.print(newLine[4]+"  ");//Estima variancia
					newTheta.add(newLine);//Adiciono a gaussiana a minha mistura
					System.out.println();//Da-nos as 4 gaussianas
				}
				
				thetaNow.mix(thetaNow.length(),newTheta);//Recebe newTheta e modifica o thetaNow (atualiza parametros)
				
				keen = matXij(s,thetaNow);//Matriz atualizada dos pesos xij
				
				
				for(int i=0;i<thetaNow.length();i++){//Construir as duas matrizes 2xn de parametros bnd
					Gauss aux = thetaNow.gau(i);
					b1[0][i]=aux.bfirst;//Definicao de b1, na primeira linha 
					b1[1][i]=newtonMethod1(s,keen,i,b1[0][i],aux.bsecond,aux.afirst,aux.sigma);//Na segunda linha esta a iterada seguinte do b1, ou entao, b(k+1) 
					a1[i]=estimateAj(s,keen,i,b1[1][i],aux.bsecond);
					b2[0][i]=aux.bsecond;
					b2[1][i]=newtonMethod2(s,keen,i,b1[1][i],b2[0][i],a1[i],aux.sigma);
					
				}
				z++;
			}
			return thetaNow;
		}
	
}
