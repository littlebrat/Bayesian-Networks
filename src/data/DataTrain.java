package data;
import java.io.*;
import java.util.Arrays;

/**
 * The class DataTrain is an implementation of the interface Data that handles with the training data file.
 *
 * @author Nuno Mendes nº73716
 * @author Sofia Silva nº73483
 * @author Tiago Ricardo nº73649
 */
public class DataTrain implements Data{
	protected TimeSample[] training;
	public int num_va=0;
	public int size=0;
	protected String[] names;
	
	/**
	 * The constructor creates an element of the type DataTrain that includes the number or random variables, the number of events and a 2-D array of type TimeSample. 
	 * 
	 * @throws IOException It throws an IOException when the file does not exist.
	 * @see TimeSample
	 * @see Event
	 * @param url		String with the URL of the file
	 */
	public DataTrain(String url) throws IOException {
		// TODO Auto-generated method stub
		training = new TimeSample[2];
		int i=0;
		int j;
		int jnxt;
		int p;
		int count=0;
		int inext=0;
		
		BufferedReader commaFile = new BufferedReader (new FileReader(url));
		String dataRow = commaFile.readLine();
		dataRow=dataRow.replace(" ", "");
		dataRow=dataRow.replace("	", "");
		String [] valeat = dataRow.split (",");
		while(valeat[i].endsWith("_0")){
			count=count+1;
			i++;
		}
		names=new String[count];
		for(i=0; i<count;i++){
			names[i]=valeat[i].replace("_0", "");
			
		}
		num_va=count;

		dataRow = commaFile.readLine();
		training[0]=new TimeSample(); //Training t
		training[1]=new TimeSample(); //Training t+1
		while (dataRow!= null){
			dataRow=dataRow.replace(" ", "");
			dataRow=dataRow.replace("	", "");
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
	
	/**
	 * {@inheritDoc}
	 */
	 @Override
	public String toString() {
		return "DataTrain [training=" + Arrays.toString(training) + ", num_va="
				+ num_va + ", size=" + size + "]";
	}

	 /**
		 * {@inheritDoc}
		 */
	@Override
	public int[][] get(){
		int i=0;
		int j=0;
		int vect[][] =new int[this.size][2*(this.num_va)];
		
		for(int time=0; time<2; time++){
			i=0;
			for(Event evt: this.training[time].samples){
				j=0;
				for(int oc: evt.occurrences){
					vect[i][j+time*this.num_va]=oc;
					j++;
				}
				i++;
			}	
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
	
	
}