package data;
import java.io.*;

public class DataTrain {
	protected TimeSample[] training = new TimeSample[2];
	public void readDatas(String url) throws IOException {
		// TODO Auto-generated method stub
		int i=0;
		int j;
		int count=0;
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		String [] valeat = dataRow.split (",");
		while(valeat[i].contains("_0")){
			count=count+1;
			i++;
		}	

		dataRow = commaFile.readLine();
		training[1]=new TimeSample(); //Training t
		//training[2]=new TimeSample(); //Training t+1
		while (dataRow!= null){
			String [] data = dataRow.split (",");//Colunas separadas por virgula
			int [] lista =new int[data.length];
			i=0;
			j=1;
			while(j<(data.length)/count){
				
				while (i<j*count) {
					lista [i]= Integer.parseInt(data[i]);
					i++;
				}
				if(data[i+1]!=null){
				Event evt=new Event(lista.length);
				evt.add(lista);
				this.training[1].add(evt);
				}
				i=j*count;
				j++;
				
			}
			
				dataRow = commaFile.readLine();//Mudar de linha
		}
		
		
		System.out.println(count);
		dataRow = commaFile.readLine();
		
		commaFile.close ();
	}
	@Override
	public String toString() {
		return "DataTrain [training=" + training[1] + "]";
	}


}

	
	


