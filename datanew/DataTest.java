/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
package data;
import java.io.*;



public class DataTest extends Data{
	protected TimeSample testing = new TimeSample();
	
	public void readData(String url) throws IOException {
		
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow = commaFile.readLine();
		while (dataRow!= null){
			String [] data = dataRow.split (",");//Colunas separadas por virgula
			int [] lista =new int[data.length];
			int i=0;
				while (i<data.length) {
					lista [i]= Integer.parseInt(data[i]);
					i++;
				}
				Event evt=new Event(lista.length);
				evt.add(lista);
				this.testing.add(evt);
				dataRow = commaFile.readLine();//Mudar de linha
		}
		commaFile.close ();
	}

	@Override
	public String toString() {
		return "Data [testing=" + testing + "]";
	}
	
	
	
}
