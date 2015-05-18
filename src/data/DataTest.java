/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
package data;
import java.io.*;


/**
 * The class DataTest is an implementation of the interface Data that handles with the testing data file.
 *
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 */

public class DataTest implements Data{
	protected TimeSample testing;
	protected int num_va=0;
	protected int size=0;
	protected String[] names;
	
	/**
	 * The constructor creates an element of the type DataTest that includes the number or random variables, the number of events and an element of type TimeSample. 
	 * 
	 * @throws IOException 	 It throws an IOException when the file does not exist.
	 * @see TimeSample
	 * @see Event
	 * @param url		String with the URL of the file
	 */
	
	public DataTest(String url) throws IOException {
		testing=new TimeSample();
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow = commaFile.readLine();
		while (dataRow!= null){
			dataRow=dataRow.replace(" ", "");
			dataRow=dataRow.replace("	", "");
			String [] data = dataRow.split (",");//Colunas separadas por virgula
			num_va=data.length;
			names=new String[num_va];
			for(int i=0; i<num_va;i++){
				names[i]=data[i];
			}
			int [] lista =new int[num_va];
			
			int i=0;
				while (i<num_va) {
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Data [testing=" + testing + "]";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[][] get(){
		int i=0;
		int[][] vect=new int[this.size][this.num_va];
		for (Event evt : this.testing.samples) {
			vect[i]=evt.occurrences;
			i++;
		}
		return vect;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getNames() {
		return names;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumVA(){
		return num_va;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize(){
		return size;
	}
}
