package data;
import java.io.*;
import java.util.Arrays;

public class DataTrain implements Data{
	protected TimeSample[] training = new TimeSample[2];
	protected int num_va=0;
	protected int size=0;
	
	public DataTrain(String url) throws IOException {
		// TODO Auto-generated method stub
		int i=0;
		int j;
		int jnxt;
		int p;
		int count=0;
		int inext=0;
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		String [] valeat = dataRow.split (",");
		while(valeat[i].endsWith("_0")){
			count=count+1;
			i++;
		}	
		num_va=count;
		dataRow = commaFile.readLine();
		training[0]=new TimeSample(); //Training t
		training[1]=new TimeSample(); //Training t+1
		while (dataRow!= null){
			String [] data = dataRow.split (",");//Colunas separadas por virgula
			i=0;
			j=1;
			while(j<(data.length)/count){
				int [] listafin= new int[count];
				p=0;
				while (i<j*count) {
					listafin[p]=Integer.parseInt(data[i]);
					i++;
					p++;	
				}
				
				if(data[i+1]!=null){
				Event evt=new Event(listafin.length);
				Event evtnxt=new Event(listafin.length);
				p=0;
				int [] listafinnxt= new int[count];
				evt.add(listafin);
				inext=i;
				jnxt=j+1;
				size=size+1;
					while (inext<jnxt*count) {
						listafinnxt[p]=Integer.parseInt(data[inext]);
						inext++;
						p++;
					}
					evtnxt.add(listafinnxt);
				this.training[0].add(evt);	
				this.training[1].add(evtnxt);
				i=j*count;
				j++;
				}
			}	
			dataRow = commaFile.readLine();//Mudar de linha
	}
		commaFile.close ();
	}
	
	 @Override
	public String toString() {
		return "DataTrain [training=" + Arrays.toString(training) + ", num_va="
				+ num_va + ", size=" + size + "]";
	}

	int getcfg(int va){
		int rank = 0;
		int max =0;
		int index =0;
		Event evt=new Event(this.training[0].samples.get(index).size());
		for(index=0;index<this.training[0].samples.size();index++){
			evt=this.training[0].samples.get(index);
			if(evt.occurrences[va]>max){			
			max=evt.occurrences[va];
			}
		}
		rank=max+1;
		return rank;
	}


	@Override
	public int[][] get(){
		int i=0;
		int j=0;
		int vect[][] =new int[this.size][2*(this.num_va)];
		for(i=0;i<this.size;i++){
			for(j=0;j<this.num_va;j++){
				vect[i][j]=this.training[0].samples.get(i).occurrences[j];
			}
			for(j=3;j<2*(this.num_va);j++){
				vect[i][j]=this.training[1].samples.get(i).occurrences[j-3];
			}
		}
		
		return vect;
	}
}