/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
package data;
import java.io.*;



public class DataTest implements Data{
	protected TimeSample testing = new TimeSample();
	public int num_va=0;
	public int size=0;
	
	public DataTest(String url) throws IOException {
		
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow = commaFile.readLine();
		while (dataRow!= null){
			dataRow=dataRow.replace(" ", "");
			dataRow=dataRow.replace("	", "");
			String [] data = dataRow.split (",");//Colunas separadas por virgula
			num_va=data.length;
			int [] lista =new int[data.length];
			
			int i=0;
				while (i<data.length) {
					lista [i]= Integer.parseInt(data[i]);
					i++;
				}
				Event evt=new Event(lista.length);
				evt.add(lista);
				this.testing.add(evt);
				size=size+1;
				dataRow = commaFile.readLine();//Mudar de linha
		}
		commaFile.close ();
	}

	@Override
	public String toString() {
		return "Data [testing=" + testing + "]";
	}
	
	public int[][] get(){
		int i=0;
		int[][] vect=new int[this.size][this.num_va];
		for (Event evt : this.testing.samples) {
			vect[i]=evt.occurrences;
			i++;
		}
		return vect;
	}
	
	
	public String[] getNames(String url) throws IOException {
		String[] vanames;
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow = commaFile.readLine();
		dataRow=dataRow.replace(" ", "");
		dataRow=dataRow.replace("	", "");
		String [] valeat = dataRow.split (",");
		vanames=new String[valeat.length];
		for(int i=0;i<valeat.length;i++){
			vanames[i]=valeat[i];
		}
		commaFile.close ();
		
		return vanames;
		
	}
	
}
