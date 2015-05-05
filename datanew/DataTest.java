/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
package data;
import java.io.*;



public class DataTest implements Data{
	protected TimeSample testing = new TimeSample();
	protected int num_va=0;
	protected int size=0;
	
	public DataTest(String url) throws IOException {
		
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow = commaFile.readLine();
		while (dataRow!= null){
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
		int[][] vect=new int[this.size][this.num_va];
		for(int i=0;i<this.size;i++){
			for(int j=0;j<this.num_va;j++){
				vect[i][j]=this.testing.samples.get(i).occurrences[j];
			}
		}
		
		return vect;
	}
}
